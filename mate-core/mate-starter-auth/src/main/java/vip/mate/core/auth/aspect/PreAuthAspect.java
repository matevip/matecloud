package vip.mate.core.auth.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.common.exception.TokenException;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.system.feign.ISysRolePermissionProvider;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 自定义权限验证
 *
 * @author pangu
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class PreAuthAspect {

	/**
	 * 所有权限标识
	 */
	private static final String ALL_PERMISSION = "*:*:*";

	private final HttpServletRequest request;

	private final ISysRolePermissionProvider sysRolePermissionProvider;

	@Around("@annotation(vip.mate.core.auth.annotation.PreAuth)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		PreAuth preAuth = method.getAnnotation(PreAuth.class);
		if (ObjectUtils.isEmpty(preAuth)) {
			return point.proceed();
		}

		if (!StringUtils.isEmpty(preAuth.hasPerm())) {
			if (hasPerm(preAuth.hasPerm())) {
				return point.proceed();
			}
			throw new TokenException("权限验证不通过");
		}
		return point.proceed();
	}


	/**
	 * 验证用户是否具备某权限
	 *
	 * @param permission 权限字符串
	 * @return 用户是否具备某权限
	 */
	public boolean hasPerm(String permission) {
		LoginUser userInfo = SecurityUtil.getUsername(request);

		if (StringUtils.isEmpty(userInfo)) {
			return false;
		}

		// 如果用户是超级管理员，则直接跳过权限验证
//		if (userInfo.getAccount().equalsIgnoreCase(Oauth2Constant.SUPER_ADMIN)) {
//			return true;
//		}
		List<String> permissionList = sysRolePermissionProvider.getMenuIdByRoleId(String.valueOf(userInfo.getRoleId()));
		return hasPermissions(permissionList, permission);
	}

	/**
	 * 判断是否包含权限
	 *
	 * @param authorities 权限列表
	 * @param permission  权限字符串
	 * @return 用户是否具备某权限
	 */
	private boolean hasPermissions(List<String> authorities, String permission) {
		return authorities.stream().filter(StringUtils::hasText)
				.anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(permission, x));
	}
}
