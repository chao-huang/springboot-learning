package com.sun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.vo.Action;

public interface IActionDAO extends IDAO<Integer, Action> {
	/**
	 * ����Ȩ�����Ų�ѯȨ����Ϣ
	 * 
	 * @param gid
	 *            Ȩ������
	 * @return
	 * @throws Exception
	 */
	public List<Action> findAllByGroups(Integer gid) throws Exception;

	/**
	 * ���ݲ�����Ȩ�޲�ѯָ����Ȩ������
	 * @param did
	 * @param actid
	 * @return
	 * @throws Exception
	 */
	public Action findByIdAndDept(@Param("did")Integer did, @Param("actid")Integer actid) throws Exception;
}
