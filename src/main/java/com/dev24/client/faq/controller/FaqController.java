package com.dev24.client.faq.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev24.client.faq.service.FaqService;
import com.dev24.client.faq.vo.FaqVO;
import com.dev24.client.qna.service.QnaService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/faq/*")
@AllArgsConstructor
public class FaqController {

	private FaqService faqService;
	
		@RequestMapping(value="/faqMain", method=RequestMethod.GET)
		public String faqMain(@ModelAttribute("data") FaqVO fvo, Model model) {
		log.info("faqMain ȣ�� ����");
		//��ü ���ڵ� ��ȸ
		List<FaqVO> faqList = faqService.faqList(fvo);
		model.addAttribute("faqList",faqList);
		
		return "faq/faqMain";
		}	
	
	
		
		
		/*****************************************************
		 * �� �� ������ �����ϱ�
		 ******************************************************/
		@RequestMapping(value="/faqMainDetail", method=RequestMethod.GET)
		//@PostMapping("/faqDetail")
		public String faqDetail(@ModelAttribute("data") FaqVO fvo, Model model) {
			log.info("faqDetail ȣ�� ����");
			
			faqService.faqCount(fvo.getFaq_num());
			FaqVO detail = faqService.faqDetail(fvo);
			model.addAttribute("detail", detail);
			
			return "faq/faqMainDetail";
		}
		
		
		

	
	
}
