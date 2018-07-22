package com.sun.dao;

import java.util.List;
import java.util.Map;

import com.sun.vo.Emp;

public interface IEmpDAO extends IDAO<Integer, Emp> {
	/**
	 * ��½֮��Ҫ��ȡ����ʵ�����Լ��û�����Ƭ
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Emp findLogin(Emp vo) throws Exception;
	/**
	 * ʵ��ģ����ҳ��ѯ
	 * @param paramMap �����˲�ѯ������map����
	 * @return
	 * @throws Exception
	 */
	public List<Emp> findAllAdmin(Map<String,Object> paramMap)throws Exception;
	/**
	 * ͳ�Ʋ�ѯ����������
	 * @param paramMap �����˲�ѯ������map����
	 * @return
	 * @throws Exception
	 */
	public Integer getAllAdminCount(Map<String,Object> paramMap)throws Exception;
	/**
	 * ʵ�ֹ�Ա��ģ����ҳ��ѯ
	 * @param paramMap �����˲�ѯ�����Ĳ���
	 * @return
	 * @throws Exception
	 */
	public List<Emp> findAllEmp()throws Exception;
	/**
	 * ʵ�ֹ�Ա������ͳ��
	 * @param paramMap �����˲�ѯ�����Ĳ���
	 * @return
	 * @throws Exception
	 */
	public Integer getAllEmpCount(Map<String,Object> paramMap)throws Exception;
}
