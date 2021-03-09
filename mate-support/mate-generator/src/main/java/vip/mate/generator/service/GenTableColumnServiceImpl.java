package vip.mate.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.mate.generator.entity.GenTableColumn;
import vip.mate.generator.mapper.GenTableColumnMapper;
import vip.mate.generator.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 业务字段 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService 
{
	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	/**
     * 查询业务字段列表
     * 
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
	@Override
	public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId)
	{
	    return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}
	
    /**
     * 新增业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
	@Override
	public int insertGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.insertGenTableColumn(genTableColumn);
	}
	
	/**
     * 修改业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
	@Override
	public int updateGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.updateGenTableColumn(genTableColumn);
	}

	/**
     * 删除业务字段对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGenTableColumnByIds(String ids)
	{
		return genTableColumnMapper.deleteGenTableColumnByIds(toLongArray(",",ids));
	}

	private  Long[] toLongArray(String split, String str)
	{
		if (StringUtils.isEmpty(str))
		{
			return new Long[] {};
		}
		String[] arr = str.split(split);
		final Long[] longs = new Long[arr.length];
		for (int i = 0; i < arr.length; i++)
		{
			final Long v = toLong(arr[i], null);
			longs[i] = v;
		}
		return longs;
	}

	private Long toLong(Object value, Long defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}
		if (value instanceof Long)
		{
			return (Long) value;
		}
		if (value instanceof Number)
		{
			return ((Number) value).longValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtils.isEmpty(valueStr))
		{
			return defaultValue;
		}
		try
		{
			// 支持科学计数法
			return new BigDecimal(valueStr.trim()).longValue();
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

		private String toStr(Object value, String defaultValue)
	{
		if (null == value)
		{
			return defaultValue;
		}
		if (value instanceof String)
		{
			return (String) value;
		}
		return value.toString();
	}
}
