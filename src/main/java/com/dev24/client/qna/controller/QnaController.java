package com.dev24.client.qna.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev24.client.login.vo.LoginVO;
import com.dev24.client.qna.service.QnaService;
import com.dev24.client.qna.vo.QnaVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/qna/*")
@AllArgsConstructor

public class QnaController { //������ �������� ���� �� �ִ� �ʵ带 ����

	private QnaService qnaService; //�ܺο��� �ν��Ͻ� ���� ����
	
	//QNA �۸�� �����ϱ� (����¡ ó�� ��� ��ȸ)
	@RequestMapping(value="/qnaList", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("data") QnaVO qvo, Model model) {
		//@ModelAttribute("data") QnaVO qvo, Model model �� ���� ������� �־�� ȣ���� �����ϴ�.
		log.info("qnaList ȣ�� ����");
		//��ü ���ڵ� ��ȸ
		List<QnaVO> qnaList = qnaService.qnaList(qvo);
		model.addAttribute("qnaList",qnaList);
		
		return "qna/qnaList";
	}
	
	
	
	/*****************************************************
	 * Q&A �۾��� �� ����ϱ� �� ��� �����ϱ� (����¡ ó�� ��� ��ȸ)
	 ******************************************************/
	@RequestMapping(value="/qwriteForm")
	public String writeForm(@ModelAttribute("data") QnaVO qvo) {
		log.info("qwriteForm ȣ�� ����");
		
		return "qna/qwriteForm";
	}
	
	/*****************************************************
	 * Q&A �۾��� �����ϱ�
	 ******************************************************/
	@RequestMapping(value="/qnaInsert", method=RequestMethod.POST)
	//@PostMapping("/qnaInsert")
	public String qnaInsert(QnaVO qvo,Model model, @SessionAttribute("login")LoginVO lvo) {
		log.info("qnaInsert ȣ�� ����");
		
		int result = 0; //ȣ�⿩�� Ȯ�ο뵵
		String url = "";
//		log.info(qvo.getC_num());
		log.info(lvo.getC_id());
		qvo.setC_num(lvo.getC_num());
		result = qnaService.qnaInsert(qvo); //���񽺿��� ���޹��� ��� ���� ����
		if(result==1) {
			url = "/qna/qnaList";
		} else {
			url = "/qna/qwriteForm";
		}
		
		return "redirect:"+url;
	}
	
	
	
	/*****************************************************
	 * Q&A �� �����ϱ�
	 ******************************************************/
	@RequestMapping(value="/qnaDelete")
	//@PostMapping("/qnaInsert")
	public String qnaDelete(QnaVO qvo, RedirectAttributes ras) {
		log.info("qnaDelete ȣ�� ����");
		
		int result = 0; //ȣ�⿩�� Ȯ�ο뵵
		String url = "";
		
		result = qnaService.qnaDelete(qvo.getQ_num()); 
		ras.addFlashAttribute("qnaVO",qvo); //���������� ��ȣ�ۿ� ������ ���� ����
		
		if(result==1) {
			url = "/qna/qnaList";
		} else {
			url = "/qna/qnaDetail";
		}
		
		return "redirect:"+url;
	}
	
	
	
	
	/*****************************************************
	 * �� �� ������ �����ϱ�
	 ******************************************************/
	@RequestMapping(value="/qnaDetail", method=RequestMethod.GET)
	//@PostMapping("/qnaDetail")
	public String qnaDetail(@ModelAttribute("data") QnaVO qvo, Model model) {
		log.info("qnaDetail ȣ�� ����");
		
		qnaService.qnaCount(qvo.getQ_num());
		QnaVO detail = qnaService.qnaDetail(qvo);
		model.addAttribute("detail", detail);
		
		return "qna/qnaDetail";
	}
	
	
	
	/**********************************************************************************
	 * �� ���� �� ����ϱ�
	 * @param q_num
	 * @param QnaVO
	 *********************************************************************************/
	@RequestMapping(value="/qupdateForm")
	public String qupdateForm(@ModelAttribute("data") QnaVO qvo, Model model) {
		log.info("qupdateForm ȣ�� ����");
		log.info("q_num =" + qvo.getQ_num());
		
		QnaVO updateData = qnaService.qupdateForm(qvo);
		
		model.addAttribute("updateData",updateData);
		return "qna/qupdateForm";
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
	public String qnaUpdate(@ModelAttribute QnaVO qvo, RedirectAttributes ras) {
		log.info("qnaUpdate ȣ�� ����");
		
		int result = 0;
		String url ="";
		
		result = qnaService.qnaUpdate(qvo);
		ras.addFlashAttribute("data",qvo);
		
		if(result==1) {
			//�Ʒ� url�� ���� �� ���������� �̵�
			//url = "/board/boardDeatil?b_num="+bvo.getB_num(); 
			url = "/qna/qnaDetail";
		}else {
			//url = "/board/updateForm?b_num="+bvo.getB_num(); 
			url="/qna/qupdateForm";
		}
		
		return "redirect:"+url;
		
	}
	

	/**********************************************************************************
	 * �亯 �� �� ����ϱ�
	 * @param q_num
	 * @param QnaVO
	 *********************************************************************************/
	@RequestMapping(value="/qreplyForm", method=RequestMethod.POST)
	public String qreplyForm(@ModelAttribute("data") QnaVO qvo, Model model) {
		log.info("qreplyForm ȣ�� ����");
		QnaVO qreplyData = qnaService.qnaDetail(qvo);
		model.addAttribute("qreplyData",qreplyData);
		return "qna/qreplyForm";
	}
	
	/**********************************************************************************
	 * �亯 �� �ۼ��ϱ�
	 * @param q_num
	 * @param QnaVO
	 *********************************************************************************/
	@RequestMapping(value="/qinsertReply",method=RequestMethod.POST)
	public String qinsertReply(@ModelAttribute("data") QnaVO qvo, Model model) {
		log.info("qinsertReply ȣ�� ����");
		
		log.info("q_reproot:" + qvo.getQ_repRoot());
		log.info("q_repIndent:" + qvo.getQ_repIndent());
		log.info("q_repStep:" + qvo.getQ_repStep());
		
		
		int result = 0; //ȣ�⿩�� Ȯ�ο뵵
		String url = "";
		
		result = qnaService.replyInsert(qvo); //���񽺿��� ���޹��� ��� ���� ����
		if(result==1) {
			url = "/qna/qnaList";
		} else {
			url = "/qna/qreplyForm";
		}
		
		return "redirect:"+url;
	}
	
}
