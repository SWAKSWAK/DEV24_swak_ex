package com.dev24.client.mypage.orderhistory.dao;

import java.util.List;

import com.dev24.client.mypage.orderhistory.vo.OrderhistoryVO;

public interface OrderhistoryDAO {

	// �ֹ����� ��ȸ
	public List<OrderhistoryVO> orderhistoryList(OrderhistoryVO ohvo);
	public int orderstateUpdate(OrderhistoryVO ohvo);
}
