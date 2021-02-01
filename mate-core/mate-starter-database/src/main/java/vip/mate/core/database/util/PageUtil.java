package vip.mate.core.database.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.mate.core.database.entity.Search;
import vip.mate.core.database.enums.PageTypeEnum;

/**
 * 分页工具类
 *
 * @author pangu
 */
public class PageUtil {

	public static <T> IPage<T> getPage(Search search) {
		// 从前端获取分页数据
		if (ObjectUtils.isNotEmpty(search.getCurrent())) {
			return new Page<T>(search.getCurrent(), search.getSize());
		// 如果为空则默认设置
		} else {
			return new Page<T>(PageTypeEnum.CURRENT.getNumber(), PageTypeEnum.SIZE.getNumber());
		}
	}
}
