package vip.mate.core.mongodb.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import vip.mate.core.mongodb.vo.Page;

import java.util.List;

/**
 * MongoDB通用Dao
 *
 * @param <T>
 * @author LaoWang
 * @date 2020-10-20
 */
public interface BaseMongoDAO<T> {

	/**
	 * 保存一个对象到mongodb
	 *
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	/**
	 * 根据id删除对象
	 *
	 * @param t
	 */
	public void deleteById(T t);

	/**
	 * 根据对象的属性删除
	 *
	 * @param t
	 */
	public void deleteByCondition(T t);


	/**
	 * 根据id进行更新
	 *
	 * @param id
	 * @param t
	 */
	public void updateById(String id, T t);


	/**
	 * 根据对象的属性查询
	 *
	 * @param t
	 * @return
	 */
	public List<T> findByCondition(T t);


	/**
	 * 通过条件查询实体(集合)
	 *
	 * @param query
	 */
	public List<T> find(Query query);

	/**
	 * 通过一定的条件查询一个实体
	 *
	 * @param query
	 * @return
	 */
	public T findOne(Query query);

	/**
	 * 通过条件查询更新数据
	 *
	 * @param query
	 * @param update
	 * @return
	 */
	public void update(Query query, Update update);

	/**
	 * 通过ID获取记录
	 *
	 * @param id
	 * @return
	 */
	public T findById(String id);

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 *
	 * @param id
	 * @param collectionName 集合名
	 * @return
	 */
	public T findById(String id, String collectionName);

	/**
	 * 通过条件查询,查询分页结果
	 *
	 * @param page
	 * @param query
	 * @return
	 */
	public Page<T> findPage(Page<T> page, Query query);

	/**
	 * 求数据总和
	 *
	 * @param query
	 * @return
	 */
	public long count(Query query);


	/**
	 * 获取MongoDB模板操作
	 *
	 * @return
	 */
	public MongoTemplate getMongoTemplate();
}
