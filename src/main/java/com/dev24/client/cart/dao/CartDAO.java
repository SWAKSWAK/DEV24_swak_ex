package com.dev24.client.cart.dao;

import java.util.List;

import com.dev24.client.cart.vo.CartVO;

public interface CartDAO {
	public List<CartVO> cartList(CartVO cvo);
	public int cartUpdate(CartVO cvo);
	public int cartDelete(int crt_num);
	
	//select
	public int getCrtNum();
	public int buySingleItem(CartVO cvo);
	
	//insert
	public int addToCart(List<CartVO> cvoList);
	
	// ���� �Ϸ��� ��ǰ ����
	public int purchasedItemDelete(List<CartVO> cvoList);
	
}
