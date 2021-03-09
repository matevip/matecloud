package vip.mate.core.database.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.mate.core.database.entity.Search;

/**
 * 分页工具类
 *
 * @author pangu
 */
public class PageUtil {

	public static <T> IPage<T> getPage(Search search) {
		return new Page<T>(search.getCurrent(), search.getSize());
	}
}
