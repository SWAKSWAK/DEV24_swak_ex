package com.dev24.client.customer.dao;

import com.dev24.client.customer.vo.CustomerVO;

public interface CustomerDAO {
	// ���� ȭ�鿡�� �ֹ��� ���� ���
	public CustomerVO getSenderInfo(int c_num);
	
	
	public CustomerVO customerSelect(String c_id);
	public CustomerVO customerNickSelect(String c_nickname);
	public CustomerVO customerEmailSelect(String c_email);
	public int customerInsert(CustomerVO cvo);
	public int customerUpdate(CustomerVO cvo);
	public int customerDelete(String c_id);
}
