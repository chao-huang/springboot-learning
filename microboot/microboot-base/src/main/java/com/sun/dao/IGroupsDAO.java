package com.sun.dao;
import java.util.List;
import com.sun.vo.Groups;
public interface IGroupsDAO extends IDAO<Integer, Groups>{
	/**
	 * ���ݲ��ŵı�Ų�ѯȨ������Ϣ
	 * @param did ���ű��
	 * @return
	 * @throws Exception
	 */
public List<Groups> findAllByDept(Integer did)throws Exception;
}
