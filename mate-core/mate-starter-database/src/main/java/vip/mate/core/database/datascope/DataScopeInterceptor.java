package vip.mate.core.database.datascope;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Mybatis PLus拦截器，主要用于数据权限拦截
 *
 * @author pangu
 * @date 2021-3-3
 */
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Component
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {
	private final DataSource dataSource;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (log.isInfoEnabled()) {
			log.debug("进入 PrepareInterceptor 拦截器...");
		}
		StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		this.sqlParser(metaObject);
		// 先判断是不是SELECT操作 不是直接过滤
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
			return invocation.proceed();
		}

		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		// 执行的SQL语句
		String originalSql = boundSql.getSql();
		// SQL语句的参数
		Object parameterObject = boundSql.getParameterObject();

		//查找参数中包含DataScope类型的参数
		DataScope dataScope = findDataScopeObject(parameterObject);
		if (ObjectUtils.isNull(dataScope)) {
			return invocation.proceed();
		}
		String scopeName = dataScope.getScopeName();
		List<Integer> deptIds = dataScope.getDepartIds();
		/**
		 * 待完善
		 */
		return invocation.proceed();
	}

	private DataScope findDataScopeObject(Object parameterObj) {
		if (parameterObj instanceof DataScope) {
			return (DataScope) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof DataScope) {
					return (DataScope) val;
				}
			}
		}
		return null;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}
}
