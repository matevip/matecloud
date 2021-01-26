package vip.mate.core.web.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import vip.mate.core.common.util.DateUtil;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 基础控制类
 *
 * @author aristotle
 * @since 2020-06-01
 */
public class BaseController {

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtil.parseLocalDateTime(text, DateUtil.DATETIME_FORMATTER));
			}
		});
	}
}
