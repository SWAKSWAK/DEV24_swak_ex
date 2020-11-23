package com.dev24.client.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dev24.client.customer.service.CustomerService;
import com.dev24.client.customer.vo.CustomerVO;
import com.dev24.client.login.service.LoginService;
import com.dev24.client.login.vo.LoginVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/customer/*")
@Log4j
@AllArgsConstructor
public class CustomerController {
	
	private CustomerService customerService;
	
	private LoginService loginService;
	
	
	/**************************************************************
	 * ȸ�� ���� ��
	 **************************************************************/
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String joinForm(Model model) {
		log.info("join get ��Ŀ� ���� �޼��� ȣ�� ����");
		return "customer/join";
	}
	
	/*************************************************
	 * ����� ���̵� �ߺ� üũ �޼���
	 *************************************************/ 
	@ResponseBody
	@RequestMapping(value="/userIdConfirm", method=RequestMethod.POST)
	public String userIdConfirm(@RequestParam("c_id") String c_id){
		int result = customerService.userIdConfirm(c_id);
		return String.valueOf(result);
	}
	
	/*************************************************
	 * ����� ���� �ߺ� üũ �޼���
	 *************************************************/ 
	@ResponseBody
	@RequestMapping(value="/userNickConfirm", method=RequestMethod.POST)
	public String userNickConfirm(@RequestParam("c_nickname") String c_nickname){
		int result = customerService.userNickConfirm(c_nickname);
		return String.valueOf(result);
	}
	
	
	/*************************************************
	 * ����� �̸��� �ߺ� üũ �޼���
	 *************************************************/ 
	@ResponseBody
	@RequestMapping(value="/userEmailConfirm", method=RequestMethod.POST)
	public String userEmailConfirm(@RequestParam("c_email") String c_email){
		int result = customerService.userEmailConfirm(c_email);
		return String.valueOf(result);
	}
	
	
	
	
	
	
	/**************************************************************
	 * ȸ�� ���� ó��(AOP ���� ó�� ��)
	 **************************************************************/
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public ModelAndView customerInsert(@ModelAttribute CustomerVO cvo, @RequestParam String[] cInterest, @RequestParam String[] cNletter) {
		log.info("join post ��Ŀ� ���� �޼��� ȣ�� ����");
		ModelAndView mav = new ModelAndView();
		//log.info(cvo.toString());
			
		int result = 0;
		String c_interest ="";
		String c_nletter  ="";
		
		for (String s : cInterest )
			c_interest += s + " ";
		for (String s : cNletter )
			c_nletter += s + " ";
		
		cvo.setC_interest(c_interest);
		cvo.setC_nletter(c_nletter);
		
		result = customerService.customerInsert(cvo);
		
		
		switch(result) {
			case 1:
				mav.addObject("codeNumber", 1); // userId already exist 
				mav.setViewName("customer/join");
				break;
			case 3:
				mav.addObject("codeNumber", 3);
				mav.setViewName("customer/join_success"); // success to add new member; move to login page
				break;
			default: 
				mav.addObject("codeNumber", 2); // failed to add new member
				mav.setViewName("customer/join");
				break;
		}
		return mav;
	} 
	
	
	/**************************************************************
	 * ȸ�� ���� ��
	 * @SessionAttribute: �޼ҵ忡 @SessionAttribute�� ���� ��� �Ķ���ͷ� ������ �̸����� ��ϵ� ���� ������ �о�ͼ� ������ �Ҵ��Ѵ�.
	 **************************************************************/
	/*@RequestMapping(value="/modify", method = RequestMethod.GET)	
	public ModelAndView customerModify(@SessionAttribute("login") LoginVO login){
		log.info("modify get ��Ŀ� ���� �޼��� ȣ�� ����");
		ModelAndView mav=new ModelAndView();

		if(login==null){
			mav.setViewName("customer/login");	
			return mav;
		}
		
		CustomerVO vo = customerService.customerSelect(login.getC_id());             
		mav.addObject("customer", vo);
		mav.setViewName("/mypage/modify");	
		return mav;
	} */

	/**************************************************************
	 * ȸ�� ���� ó��(AOP ���� ó�� ��)
	 **************************************************************/
	@RequestMapping(value="/modify", method = RequestMethod.POST)	
	public ModelAndView customerModifyProcess(CustomerVO cvo, @SessionAttribute("login") LoginVO login, ModelAndView mav){
		log.info("modify post ��Ŀ� ���� �޼��� ȣ�� ����");

		if(login==null){
			mav.setViewName("customer/login");	
			return mav;
		}
		
		cvo.setC_id(login.getC_id());    
		CustomerVO vo = customerService.customerSelect(cvo.getC_id());           

		if (loginService.loginSelect(cvo.getC_id(), cvo.getOldUserPw()) == null ) {
			mav.addObject("codeNumber", 1);
			mav.addObject("customer",vo);
			mav.setViewName("customer/modify");
			return mav;
		} 

		customerService.customerUpdate(cvo);
		mav.setViewName("redirect:/customer/logout");
		return mav;	
		
	}

	/**************************************************************
	 * ȸ�� Ż�� ó��(AOP ���� ó�� ��)
	 **************************************************************/
	@RequestMapping("/delete")	
	public ModelAndView customerDelete(@SessionAttribute("login") LoginVO login){
		log.info("delete.do get��Ŀ� ���� �޼��� ȣ�� ����");
		
		ModelAndView mav=new ModelAndView();
		
		if(login==null){
			mav.setViewName("customer/login");	
			return mav;
		}
		
		int errCode = customerService.customerDelete(login.getC_id());
		switch(errCode) {
		case 2:
			mav.setViewName("redirect:/customer/logout");
			break;
		case 3:
			mav.addObject("codeNumber", 3);
			mav.setViewName("customer/login");
			break;
		}
	    return mav;	
	}
	
	
	
	
	
}
