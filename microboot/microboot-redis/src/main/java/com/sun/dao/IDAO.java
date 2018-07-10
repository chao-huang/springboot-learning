package com.sun.dao;
import java.util.List;
import java.util.Set;
public interface IDAO<K,V> {
	/**
	 * 插入数据
	 * 
	 * @param vo包含要修改的数据的vo对象
	 * @return 返回受到影响的行数
	 * @throws Exception
	 */
	public int doCreate(V vo) throws Exception;

	/**
	 * 实现修改数据
	 * 
	 * @param vo 包含要修改的数据的vo对象
	 * @return 返回受影响的行数
	 * @throws Exception
	 */
	public int doUpdate(V vo) throws Exception;
	/**
	 * 根据编号删除数据
	 * @param vo 包含了删除数据的编号的vo对象
	 * @return
	 * @throws Exception
	 */
	public int doRemove(K id) throws Exception;
	/**
	 * 实现批量删除数据
	 * 
	 * @param ids 保存要删除的数据的编号
	 * @return 删除成功返回true，删除失败返回false
	 * @throws Exception
	 */
	public int doRemoveBatch(List<K> ids) throws Exception;
	/**
	 * 根据编号进行查找
	 * 
	 * @param empno
	 *            要查找的编号
	 * @return 如果有数据返回Emp对象，否则返回null
	 * @throws Exception
	 */
	public V findById(K id) throws Exception;
	/**
	 * 查询所用的数据
	 * @return
	 * @throws Exception
	 */
	public List<V> findAll()throws Exception;
	/**
	 * 实现模糊分页查询
	 * @param column 模糊查询的字段
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页显示的数据量
	 * @return
	 * @throws Exception
	 */
	public List<V> findAllSplit(Integer start,Integer ls) throws Exception;

	/**
	 * 统计数量
	 * @param column 模糊查询的关键字段
	 * @param keyword 模糊查询的关键字
	 * @return
	 * @throws Exception
	 */
	public int findAllCount(String column,String keyword) throws Exception;
	
}
