package com.dev24.client.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dev24.client.customer.service.CustomerService;
import com.dev24.client.customer.vo.CustomerVO;
import com.dev24.client.login.vo.LoginVO;
import com.dev24.client.mypage.orderhistory.service.OrderhistoryService;
import com.dev24.client.mypage.orderhistory.vo.OrderhistoryVO;
import com.dev24.client.mypage.refundhistory.service.RefundhistoryService;
import com.dev24.client.mypage.refundhistory.vo.RefundhistoryVO;
import com.dev24.client.qna.service.QnaService;
import com.dev24.client.qna.vo.QnaVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mypage/*")
@Log4j
@AllArgsConstructor
public class MypageController {
	
	private OrderhistoryService orderhistoryService;
	private RefundhistoryService refundhistoryService;
	private CustomerService customerService;
	private QnaService qnaService;
	
	/************************************************
	 *  mypage main print
	 *  ****************/
	@RequestMapping(value="/mypage", method= {RequestMethod.GET})
	public String mypage(OrderhistoryVO ohvo, RefundhistoryVO rfhvo, QnaVO qvo, Model model, @SessionAttribute("login") LoginVO lvo) {
		log.info("mypage ȣ�� ����");
			
		if(lvo == null){
			return "redirect:/customer/login";
		}
		int c_num = lvo.getC_num();
		log.info(lvo);
		log.info("c_num : "+c_num);
		
		ohvo.setC_num(c_num);
		rfhvo.setC_num(c_num);
		qvo.setC_num(c_num);
		
		// �ֹ����� ��ȸ
		List<OrderhistoryVO> ohlist = orderhistoryService.orderhistoryList(ohvo);
		model.addAttribute("ohvo", ohlist);
		
		// ȯ�ҳ��� ��ȸ
		List<RefundhistoryVO> rfhlist = refundhistoryService.refundhistoryList(rfhvo);
		model.addAttribute("rfhvo", rfhlist);
		
		// ���� ���� ��ȸ
		List<QnaVO> qnalist = qnaService.myQnaList(qvo);
		model.addAttribute("qvo", qnalist);
		
		return "mypage/mypage";
	}

	
	/**************************************************************
	 * ȸ�� ���� ��
	 * @SessionAttribute: �޼ҵ忡 @SessionAttribute�� ���� ��� �Ķ���ͷ� ������ �̸����� ��ϵ� ���� ������ �о�ͼ� ������ �Ҵ��Ѵ�.
	 **************************************************************/
	@RequestMapping(value="/modify", method = RequestMethod.GET)	
	public ModelAndView customerModify(@SessionAttribute("login") LoginVO login){
		log.info("modify get ��Ŀ� ���� �޼��� ȣ�� ����");
		ModelAndView mav=new ModelAndView();

		if(login==null){
			mav.setViewName("customer/login");	
			return mav;
		}
		
		CustomerVO vo = customerService.customerSelect(login.getC_id());             
		mav.addObject("customer", vo);
		mav.setViewName("mypage/modify");	
		return mav;
	} 
	
	
	/****************************************************
	 * my qna history page print
	 * *******************/
	@RequestMapping(value="/qnaHistory", method= {RequestMethod.GET})
	public String qnaHistory(@ModelAttribute("data") QnaVO qvo, Model model, HttpSession session) {
		log.info("qnaHistory ȣ�� ����");
		
		LoginVO lvo = (LoginVO) session.getAttribute("login");
		int c_num = lvo.getC_num();
		log.info(lvo);
		log.info("c_num : "+c_num);
		
		qvo.setC_num(c_num);
		
		List<QnaVO> qnalist = qnaService.myQnaList(qvo);
		model.addAttribute("qvo", qnalist);
		
		return "mypage/qnaHistory";
	}

}
