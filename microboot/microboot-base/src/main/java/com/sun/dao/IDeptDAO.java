package com.sun.dao;

import java.util.List;

import com.sun.vo.Dept;

public interface IDeptDAO extends IDAO<Integer, Dept> {
	/**
	 * ���ݲ��ŵ������־���в�ѯ
	 * @param sflag �����ǣ���������Ϊ0����ʾ��ͨ�Ĳ���
	 * @return
	 * @throws Exception
	 */
public List<Dept> findAllBySflag(Integer sflag)throws Exception;
}
