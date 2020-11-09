package vip.mate.core.mongodb.annotation;

import org.springframework.data.mongodb.core.query.Criteria;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 查询媒介
 * 1. equals：相等
 * 2. like:mongodb的like查询
 * 3. in:用于列表的in类型查询
 *
 * @author LaoWang
 * @date 2020-10-20
 */
public enum QueryType {
	/**
	 * 相等
	 */
	EQUALS {
		@Override
		public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
			if (check(queryFieldAnnotation, field, value)) {
				String queryField = getQueryFieldName(queryFieldAnnotation, field);
				return Criteria.where(queryField).is(value.toString());
			}
			return new Criteria();
		}
	},
	/**
	 * mongodb的like查询
	 */
	LIKE {
		@Override
		public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
			if (check(queryFieldAnnotation, field, value)) {
				String queryField = getQueryFieldName(queryFieldAnnotation, field);
				return Criteria.where(queryField).regex(value.toString());
			}
			return new Criteria();
		}
	},
	/**
	 * 用于列表的in类型查询
	 */
	IN {
		@Override
		public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
			if (check(queryFieldAnnotation, field, value)) {
				if (value instanceof List) {
					String queryField = getQueryFieldName(queryFieldAnnotation, field);
					// 此处必须转型为List，否则会在in外面多一层[]
					return Criteria.where(queryField).in((List<?>) value);
				}
			}
			return new Criteria();
		}
	};

	private static boolean check(QueryField queryField, Field field, Object value) {
		return !(queryField == null || field == null || value == null);
	}

	public abstract Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value);


	/**
	 * 如果实体bean的字段上QueryField注解没有设置attribute属性时，默认为该字段的名称
	 *
	 * @param field
	 * @return
	 */
	private static String getQueryFieldName(QueryField queryField, Field field) {
		String queryFieldValue = queryField.attribute();
		if (!StringUtils.hasText(queryFieldValue)) {
			queryFieldValue = field.getName();
		}
		return queryFieldValue;
	}
}
