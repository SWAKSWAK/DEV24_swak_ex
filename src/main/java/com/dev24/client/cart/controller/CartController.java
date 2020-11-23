package com.dev24.client.cart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dev24.client.cart.service.CartService;
import com.dev24.client.cart.vo.CartVO;
import com.dev24.client.login.vo.LoginVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/cart/*")
@Log4j
@AllArgsConstructor
public class CartController {
	private CartService cartService;

	/************************************************
	 * cart_view�� �̿��� ��ٱ��� ����Ʈ ��� �Ϲݵ���, ebook (��з�) ���� ����ϱ�
	 **************/
	@GetMapping("/cartList")
	public String CartList(@ModelAttribute("data") CartVO cvo, Model model, HttpSession session) {
		log.info("cartList() �޼��� ȣ��");

		LoginVO lvo = (LoginVO) session.getAttribute("login");
		int c_num = lvo.getC_num();
		log.info(lvo);
		log.info("c_num : "+c_num);
		
		cvo.setC_num(c_num);
		
		List<CartVO> list = cartService.cartList(cvo);
		List<CartVO> list1 = new ArrayList<CartVO>();
		List<CartVO> list2 = new ArrayList<CartVO>();

		for (int i = 0; i < list.size(); i++) {
			int cate = list.get(i).getCateone_num();
			if (cate == 1) { // �Ϲݵ����϶�
				CartVO vo1 = list.get(i);
				list1.add(vo1);
			} else if (cate == 2) { // ebook�϶�
				CartVO vo2 = list.get(i);
				list2.add(vo2);
			}
		}

		model.addAttribute("cartList1", list1);
		model.addAttribute("cartList2", list2);

		return "cart/cartList";
	}

	/************************************************
	 * ��ٱ��� ���� ���� REST��Ŀ��� UPDATE�۾��� PUT, PATCH����� �̿��ؼ� ó��.
	 **************/
	@RequestMapping(value = "/{crt_num}", method = { RequestMethod.PUT,
			RequestMethod.PATCH }, consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> cartUpdate(@PathVariable("crt_num") int crt_num, @RequestBody CartVO cvo) {
		log.info("cartUpdate() ȣ�� ����");
		cvo.setCrt_num(crt_num);
		int result = cartService.cartUpdate(cvo);
		return result == 1 ? new ResponseEntity<String>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/************************************************
	 * ��ٱ��� ��ǰ ���� REST��Ŀ��� DELETE�۾��� DELETE����� �̿��ؼ� ó��.
	 **************/
	@DeleteMapping(value = "/{crt_num}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> cartDelete(@PathVariable("crt_num") int crt_num) {
		log.info("cartDelete() ȣ�� ����");
		log.info("crt_num : " + crt_num);
		int result = cartService.cartDelete(crt_num);
		return result == 1 ? new ResponseEntity<String>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/addToCart", produces = "text/plain; charset=utf8")
	@ResponseBody
	public String addToCart(@RequestBody List<Map<String, Object>> cartList, HttpSession session) {

		log.info("addToCart ȣ�� ����");

		String returnStr = "";
		LoginVO lvo = (LoginVO) session.getAttribute("login");
		int c_num = lvo.getC_num();
		log.info(lvo);
		log.info("c_num : "+c_num);
		
		CartVO cvo;
		List<CartVO> cvoList = new ArrayList<CartVO>();

		for (Map<String, Object> map : cartList) {
			cvo = new CartVO();

			int b_num = Integer.parseInt(map.get("b_num") + "");
			int crt_qty = Integer.parseInt(map.get("crt_qty") + "");
			int crt_price = Integer.parseInt(map.get("crt_price") + "");

			cvo.setB_num(b_num);
			cvo.setCrt_qty(crt_qty);
			cvo.setCrt_price(crt_price);
			cvo.setC_num(c_num);

			cvoList.add(cvo);
		}

		int result = cartService.addToCart(cvoList);

		if (result == 1) {
			returnStr = "SUCCESS";
		} else {
			returnStr = "FAIL";
		}
		return returnStr;
	}

	/***
	 * �����׸� ���Ÿ� ���� cart_num_seq.nextval ���� ������� �޼ҵ� 
	 * @param cvo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/buySingleItem", produces = "text/plain; charset=utf8", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> buySingleItem(@RequestBody CartVO cvo, HttpSession session) {

		log.info("buySingleItem ȣ�� ����");

		int crt_num = cartService.getCrtNum();
		log.info("crt_num: " + crt_num);
		int returnVal = -1;
		int result = -1;
		ResponseEntity<String> entity;

		log.info(cvo.toString());

		LoginVO lvo = (LoginVO) session.getAttribute("login");
		int c_num = lvo.getC_num();
		log.info(lvo);
		log.info("c_num : "+c_num);

		cvo.setC_num(c_num);
		cvo.setCrt_num(crt_num);
		log.info("cvo �� c_num, crt_num �߰�");
		log.info("cvoList �� cvo�߰� \n\t" + cvo.toString());
		result = cartService.buySingleItem(cvo);
		log.info("result: " + result);
		
		if (result == 1) {
			log.info("���� �׸� �߰� ����");
			returnVal = crt_num;
			entity = new ResponseEntity<String>(returnVal+"", HttpStatus.OK);
		} else {
			log.info("���� �׸� �߰� ����");
			returnVal = -1;
			entity = new ResponseEntity<String>(returnVal+"", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}

}
