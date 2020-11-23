package com.dev24.client.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev24.client.qna.dao.QnaDAO;
import com.dev24.client.qna.vo.QnaVO;

import lombok.Setter;

//@Log4j
@Service
public class QnaServiceImpl implements QnaService {
	
	@Setter(onMethod_=@Autowired) //Setter Injection; 
	private QnaDAO qnaDAO;
	
	
	//QNA 
	@Override
	public List<QnaVO> qnaList(QnaVO qvo) {
		
		List<QnaVO> list = null;
		list = qnaDAO.qnaList(qvo);
		
		return list;
	}

	//QNA �۾���
	@Override
	public int qnaInsert(QnaVO qvo) {
		int result = 0;
		//qvo.setC_num(10); //�ܷ�Ű�� ���� �ִ� c_num�� ���Ƿ� �� �� �۾��� �׽�Ʈ
		result = qnaDAO.qnaInsert(qvo);
		return result;
	}
	
	//QNA �󼼱� Ȯ���ϱ�
	@Override
	public QnaVO qnaDetail(QnaVO qvo) {
		
		 QnaVO detail = null;
		 detail = qnaDAO.qnaDetail(qvo);
		 
		 if(detail!=null) {
				detail.setQ_content(detail.getQ_content().toString().replaceAll("\n", "<br>"));
				//�� ������ ó������ ���� ���, �Խ��� ������ ���ٷθ� ��µ�
		 }
		return detail;
	}
	
	//QNA �Խñ� ��ȸ�� ����
	@Override
	public int qnaCount(int q_num) {
		
		int result = 0;
		result = qnaDAO.qnaCount(q_num);
		
		return result;
	}
	
	//QNA �� �����ϱ�
	@Override
	public int qnaDelete(int q_num) {
		int result = 0;
		result = qnaDAO.qnaDelete(q_num);
		
		return result;
	}

	@Override//�����ϰ��� �ϴ� ���ڵ带 �����´�.
	public QnaVO qupdateForm(QnaVO qvo) {
		QnaVO detail = null;
		detail = qnaDAO.qnaDetail(qvo);
		return detail;
	}
	
	
	/* �� ���� �����ϱ� */
	@Override
	public int qnaUpdate(QnaVO qvo) {
		int result = 0;
		result = qnaDAO.qnaUpdate(qvo);
		return result;
	}
	
	
	@Override
	public int replyInsert(QnaVO qvo) {
		int result =0;
		qnaDAO.makeReply(qvo);
		qvo.setC_num(7);
		result = qnaDAO.replyInsert(qvo);
		
		return result;
	}

	/* mypage */
	@Override
	public List<QnaVO> myQnaList(QnaVO qvo) {
		List<QnaVO> list = null;
		list = qnaDAO.myQnaList(qvo);
		
		return list;
	}

	
	
	
}
