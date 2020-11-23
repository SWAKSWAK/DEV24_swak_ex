package com.dev24.client.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev24.client.cart.dao.CartDAO;
import com.dev24.client.cart.vo.CartVO;

import lombok.Setter;

@Service
public class CartServiceImpl implements CartService {

	@Setter(onMethod_ = @Autowired)
	private CartDAO cartDAO;
	
	// ��ٱ��Ͽ� ��� ���� ���
	@Override
	public List<CartVO> cartList(CartVO cvo) {
		List<CartVO> list = null;
		list = cartDAO.cartList(cvo);
		return list;
	}

	// ��ٱ��� ���� ����
	@Override
	public int cartUpdate(CartVO cvo) {
		int result = 0;
		result = cartDAO.cartUpdate(cvo);
		return result;
	}

	// ��ٱ��� ��ǰ ���� 
	@Override
	public int cartDelete(int crt_num) {
		int result = 0;
		result = cartDAO.cartDelete(crt_num);
		return result;
	}
	
	//��ٱ��� �߰�
	@Override
	public int addToCart(List<CartVO> cvoList) {
		int result = cartDAO.addToCart(cvoList);
		return result;
	}
	
	@Override
	public int getCrtNum() {
		int result = cartDAO.getCrtNum();
		return result;
	}
	
	@Override
	public int buySingleItem(CartVO cvo) {
		int result = cartDAO.buySingleItem(cvo);
		return result;
	}

}
