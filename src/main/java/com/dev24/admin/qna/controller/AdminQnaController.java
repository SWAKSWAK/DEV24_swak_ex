package com.dev24.admin.qna.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev24.admin.qna.service.AdminQnaService;
import com.dev24.client.qna.vo.QnaVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/*")
@AllArgsConstructor

public class AdminQnaController {

	private AdminQnaService adminQnaService;
	
	//QNA �۸�� �����ϱ� (����¡ ó�� ��� ��ȸ)
		@RequestMapping(value="/qnaList", method=RequestMethod.GET)
		public String adminQnaList(@ModelAttribute("data") QnaVO qvo, Model model) {
			//@ModelAttribute("data") QnaVO qvo, Model model �� ���� ������� �־�� ȣ���� �����ϴ�.
			//��ü ���ڵ� ��ȸ
			List<QnaVO> qnaList = adminQnaService.qnaList(qvo);
			model.addAttribute("qnaList",qnaList);
			
			return "admin/qnaList";
		}
		
		
		
		/*****************************************************
		 * Q&A �۾��� �� ����ϱ� �� ��� �����ϱ� (����¡ ó�� ��� ��ȸ)
		 ******************************************************/
		@RequestMapping(value="/qwriteForm")
		public String adminWriteForm(@ModelAttribute("data") QnaVO qvo) {
			
			return "admin/qwriteForm";
		}
		
		/*****************************************************
		 * Q&A �۾��� �����ϱ�
		 ******************************************************/
		@RequestMapping(value="/qnaInsert", method=RequestMethod.POST)
		//@PostMapping("/qnaInsert")
		public String adminQnaInsert(QnaVO qvo,Model model) {
			
			int result = 0; //ȣ�⿩�� Ȯ�ο뵵
			String url = "";
			
			result = adminQnaService.qnaInsert(qvo); //���񽺿��� ���޹��� ��� ���� ����
			if(result==1) {
				url = "/admin/qnaList";
			} else {
				url = "/admin/qwriteForm";
			}
			
			return "redirect:"+url;
		}
		
		
		
		/*****************************************************
		 * Q&A �� �����ϱ�
		 ******************************************************/
		@RequestMapping(value="/qnaDelete")
		//@PostMapping("/qnaInsert")
		public String adminQnaDelete(QnaVO qvo, RedirectAttributes ras) {
			
			int result = 0; //ȣ�⿩�� Ȯ�ο뵵
			String url = "";
			
			result = adminQnaService.qnaDelete(qvo.getQ_num()); 
			ras.addFlashAttribute("qnaVO",qvo); //���������� ��ȣ�ۿ� ������ ���� ����
			
			if(result==1) {
				url = "/admin/qnaList";
			} else {
				url = "/admin/qnaDetail";
			}
			
			return "redirect:"+url;
		}
		
		
		
		
		/*****************************************************
		 * �� �� ������ �����ϱ�
		 ******************************************************/
		@RequestMapping(value="/qnaDetail", method=RequestMethod.GET)
		//@PostMapping("/qnaDetail")
		public String adminQnaDetail(@ModelAttribute("data") QnaVO qvo, Model model) {
			
			adminQnaService.qnaCount(qvo.getQ_num());
			QnaVO detail = adminQnaService.qnaDetail(qvo);
			model.addAttribute("detail", detail);
			
			return "admin/qnaDetail";
		}
		
		
		
		/**********************************************************************************
		 * �� ���� �� ����ϱ�
		 * @param q_num
		 * @param QnaVO
		 *********************************************************************************/
		@RequestMapping(value="/qupdateForm")
		public String adminQupdateForm(@ModelAttribute("data") QnaVO qvo, Model model) {
			log.info("q_num =" + qvo.getQ_num());
			
			QnaVO updateData = adminQnaService.qupdateForm(qvo);
			
			model.addAttribute("updateData",updateData);
			return "admin/qupdateForm";
		}
		
		/**********************************************************************************
		 * �� ���� �����ϱ�
		 * @param QnaVO
		 * ����: RedirectAttributes ��ü�� �����̷�Ʈ ����(return "redirect:/���")��
		 * �ѹ��� ���Ǵ� �����͸� ������ �� �ִ� addFlashAttribute()��� ����� �����Ѵ�.
		 * addFlashAttribute() �޼���� ���������� ���۵Ǳ�� ������,
		 * URI�󿡴� ������ �ʴ� ������ �������� ���·� ���޵ȴ�.
		 *********************************************************************************/
		@RequestMapping(value="/qnaUpdate", method=RequestMethod.POST)
		public String adminQnaUpdate(@ModelAttribute QnaVO qvo, RedirectAttributes ras) {
			
			int result = 0;
			String url ="";
			
			result = adminQnaService.qnaUpdate(qvo);
			ras.addFlashAttribute("data",qvo);
			
			if(result==1) {
				//�Ʒ� url�� ���� �� ���������� �̵�
				//url = "/board/boardDeatil?b_num="+bvo.getB_num(); 
				url = "/admin/qnaDetail";
			}else {
				//url = "/board/updateForm?b_num="+bvo.getB_num(); 
				url="/admin/qupdateForm";
			}
			
			return "redirect:"+url;
			
		}
		

		/**********************************************************************************
		 * �亯 �� �� ����ϱ�
		 * @param q_num
		 * @param QnaVO
		 *********************************************************************************/
		@RequestMapping(value="/qreplyForm", method=RequestMethod.POST)
		public String adminQreplyForm(@ModelAttribute("data") QnaVO qvo, Model model) {
			QnaVO qreplyData = adminQnaService.qnaDetail(qvo);
			model.addAttribute("qreplyData",qreplyData);
			return "admin/qreplyForm";
		}
		
		/**********************************************************************************
		 * �亯 �� �ۼ��ϱ�
		 * @param q_num
		 * @param QnaVO
		 *********************************************************************************/
		@RequestMapping(value="/qinsertReply",method=RequestMethod.POST)
		public String adminQinsertReply(@ModelAttribute("data") QnaVO qvo, Model model) {
			
			
			int result = 0; //ȣ�⿩�� Ȯ�ο뵵
			String url = "";
			
			result = adminQnaService.replyInsert(qvo); //���񽺿��� ���޹��� ��� ���� ����
			if(result==1) {
				url = "/admin/qnaList";
			} else {
				url = "/admin/qreplyForm";
			}
			
			return "redirect:"+url;
		}
	
	
	
}
