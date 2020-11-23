package com.dev24.admin.faq.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev24.admin.faq.service.AdminFaqSerivce;
import com.dev24.client.faq.vo.FaqVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/*")
@AllArgsConstructor
public class AdminFaqController {
	
	private AdminFaqSerivce adminFaqService;
	
	
	
	//faq �۸�� �����ϱ� (����¡ ó�� ��� ��ȸ)
			@RequestMapping(value="/faqList", method=RequestMethod.GET)
			public String faqList(@ModelAttribute("data") FaqVO fvo, Model model) {
				log.info("faqList ȣ�� ����");
				//��ü ���ڵ� ��ȸ
				List<FaqVO> faqList = adminFaqService.faqList(fvo);
				model.addAttribute("faqList",faqList);
				
				return "admin/faqList";
			}
			
			
			
			/*****************************************************
			 * Q&A �۾��� �� ����ϱ� �� ��� �����ϱ� (����¡ ó�� ��� ��ȸ)
			 ******************************************************/
			@RequestMapping(value="/faqInsertForm")
			public String faqInsertForm(@ModelAttribute("data") FaqVO fvo) {
				log.info("faqInsertForm ȣ�� ����");
				
				return "admin/faqInsertForm";
			}
			
			/*****************************************************
			 * Q&A �۾��� �����ϱ�
			 ******************************************************/
			@RequestMapping(value="/faqInsert", method=RequestMethod.POST)
			//@PostMapping("/faqInsert")
			public String faqInsert(FaqVO fvo,Model model) {
				log.info("faqInsert ȣ�� ����");
				
				int result = 0; //ȣ�⿩�� Ȯ�ο뵵
				String url = "";
				
				result = adminFaqService.faqInsert(fvo); //���񽺿��� ���޹��� ��� ���� ����
				if(result==1) {
					url = "/admin/faqList";
				} else {
					url = "/admin/faqInsertForm";
				}
				
				return "redirect:"+url;
			}
			
			
			
			/*****************************************************
			 * Q&A �� �����ϱ�
			 ******************************************************/
			@RequestMapping(value="/faqDelete")
			//@PostMapping("/faqInsert")
			public String faqDelete(FaqVO fvo, RedirectAttributes ras) {
				log.info("faqDelete ȣ�� ����");
				
				int result = 0; //ȣ�⿩�� Ȯ�ο뵵
				String url = "";
				
				result = adminFaqService.faqDelete(fvo.getFaq_num()); 
				ras.addFlashAttribute("faqVO",fvo); //���������� ��ȣ�ۿ� ������ ���� ����
				
				if(result==1) {
					url = "/admin/faqList";
				} else {
					url = "/admin/faqDetail";
				}
				
				return "redirect:"+url;
			}
			
			
			
			
			/*****************************************************
			 * �� �� ������ �����ϱ�
			 ******************************************************/
			@RequestMapping(value="/faqDetail", method=RequestMethod.GET)
			//@PostMapping("/faqDetail")
			public String faqDetail(@ModelAttribute("data") FaqVO fvo, Model model) {
				log.info("faqDetail ȣ�� ����");
				
				adminFaqService.faqCount(fvo.getFaq_num());
				FaqVO detail = adminFaqService.faqDetail(fvo);
				model.addAttribute("detail", detail);
				
				return "admin/faqDetail";
			}
			
			
			
			/**********************************************************************************
			 * �� ���� �� ����ϱ�
			 * @param q_num
			 * @param faqVO
			 *********************************************************************************/
			@RequestMapping(value="/faqUpdateForm")
			public String faqUpdateForm(@ModelAttribute("data") FaqVO fvo, Model model) {
				log.info("faqUpdateForm ȣ�� ����");
				
				FaqVO updateData = adminFaqService.faqupdateForm(fvo);
				
				model.addAttribute("updateData",updateData);
				return "admin/faqUpdateForm";
			}
			
			/**********************************************************************************
			 * �� ���� �����ϱ�
			 * @param faqVO
			 * ����: RedirectAttributes ��ü�� �����̷�Ʈ ����(return "redirect:/���")��
			 * �ѹ��� ���Ǵ� �����͸� ������ �� �ִ� addFlashAttribute()��� ����� �����Ѵ�.
			 * addFlashAttribute() �޼���� ���������� ���۵Ǳ�� ������,
			 * URI�󿡴� ������ �ʴ� ������ �������� ���·� ���޵ȴ�.
			 *********************************************************************************/
			@RequestMapping(value="/faqUpdate", method=RequestMethod.POST)
			public String faqUpdate(@ModelAttribute FaqVO fvo, RedirectAttributes ras) {
				log.info("faqUpdate ȣ�� ����");
				
				int result = 0;
				String url ="";
				
				result = adminFaqService.faqUpdate(fvo);
				ras.addFlashAttribute("data",fvo);
				
				if(result==1) {
					//�Ʒ� url�� ���� �� ���������� �̵�
					//url = "/board/boardDeatil?b_num="+bvo.getB_num(); 
					url = "/admin/faqDetail";
				}else {
					//url = "/board/updateForm?b_num="+bvo.getB_num(); 
					url="/admin/faqUpdateForm";
				}
				
				return "redirect:"+url;
				
			}
	
	

	
}
