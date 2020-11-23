package com.dev24.client.login.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dev24.client.book.service.BookService;
import com.dev24.client.book.vo.BookVO;
import com.dev24.client.login.service.LoginService;
import com.dev24.client.login.vo.LoginVO;
import com.dev24.common.pagination.Pagination;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
/* @SessionAttributes �Ķ���ͷ� ������ �̸��� ���� �̸��� @ModelAttribute�� �����Ǿ� ���� ��� �޼ҵ尡 ��ȯ�Ǵ� ���� ���ǿ� ����ȴ�. */
@SessionAttributes("login")
@RequestMapping("/customer/*")
@Log4j
@AllArgsConstructor
public class LoginController {

	private LoginService loginService;
	private BookService bookService;
	/* @SessionAttributes�� �Ķ���Ϳ� ���� �̸��� @ModelAttribute�� ���� ��� ���ǿ� �ִ� ��ü�� ������ ��, Ŭ���̾�Ʈ�� ���۹��� ���� �����Ѵ�. */
	@ModelAttribute("login")
	public LoginVO login() {
		return new LoginVO();
	}
	
	/**************************************************************
	 * �α��� �� ó��
	 **************************************************************/
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginForm() {
		log.info("login.do get ȣ�� ����");
		return "customer/login";
	}
	
	/**************************************************************
	 * �α��� ó��
	 * ���� : �α��� ���н� Ƚ�� ������ �������� ���� ó��
	 **************************************************************/
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginInsert(@ModelAttribute LoginVO lvo, ModelAndView mav) {
		log.info("login.do post ȣ�� ����");

		String c_id = lvo.getC_id();
		String c_passwd = lvo.getC_passwd();
		LoginVO loginCheckResult = loginService.loginSelect(c_id, c_passwd);
		
		Pagination pagination = new Pagination(0, 0, 1, 1, 1, 18, "best", "reg");
		List<BookVO> bvoList = bookService.bookList(pagination);

		// �Է¹��� ���̵�� ��й�ȣ�� DB Ȯ�� �� ��ġ �����Ͱ� �������� ������
		if(loginCheckResult == null){
			mav.addObject("codeNumber", 1);	
			mav.setViewName("customer/login");			
			return mav; 
		}else { // ��ġ�ϸ�
			mav.addObject("login", loginCheckResult);
			mav.addObject("bvoList", bvoList);
			mav.setViewName("/index");
			return mav;
		}  
	} 
	
	
	/**************************************************************
	 * �α׾ƿ� ó�� �޼���
	 **************************************************************/
	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus){
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
	
	
	
}
