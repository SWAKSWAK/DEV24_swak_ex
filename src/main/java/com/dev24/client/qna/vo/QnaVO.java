package com.dev24.client.qna.vo;

import com.dev24.common.vo.CommonVO;

import lombok.Data;

@Data
public class QnaVO extends CommonVO{
	
	private	int 	q_num       =0;		//Q&A �۹�ȣ
	private int 	c_num       =0;	//ȸ����ȣ
	private String  c_nickname    ="";	//ȸ������
	private String  q_content     ="";	//Q&A �۳���
	private String  q_title       ="";	//Q&A ������
	private String  q_writedate   =""; //Q&A ���ۼ���
	private String  q_category    ="";  //Q&A ī�װ�                         
	private String  q_imgurl      ="";		//Q&A �̹��� ÷������ ���
	private int     q_readcnt     =0;	//�� ��ȸ��
	private int     q_repIndent   =0;	//�亯�� �ۼ� �� ���(�亯���� ���� ����)
	private int     q_repRoot     =0;	//�亯�� �ۼ� �� ���(�������� ��ȣ����)
	private int     q_repStep     =0;	//�亯�� �ۼ� �� ���(�亯���� �鿩���� ����)
		   
}
