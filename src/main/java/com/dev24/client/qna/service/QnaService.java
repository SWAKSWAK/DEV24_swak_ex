package com.dev24.client.qna.service;

import java.util.List;

import com.dev24.client.qna.vo.QnaVO;

public interface QnaService {
	public List<QnaVO> qnaList(QnaVO qvo); //���� �������� �ʰ� ���� �Ǿ��ִ� ����
	public int qnaInsert(QnaVO qvo);
	public QnaVO qnaDetail(QnaVO qvo);
	public QnaVO qupdateForm(QnaVO qvo);
	public int replyInsert(QnaVO qvo);
	
	public int qnaCount(int q_num);
	public int qnaDelete(int q_num);
	public int qnaUpdate(QnaVO qvo);

	public List<QnaVO> myQnaList(QnaVO qvo); // mypage
}
