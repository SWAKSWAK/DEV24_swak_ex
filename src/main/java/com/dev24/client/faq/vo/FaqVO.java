package com.dev24.client.faq.vo;

import com.dev24.common.vo.CommonVO;

import lombok.Data;

@Data
public class FaqVO extends CommonVO {
	
	private	int 	faq_num       =0;		//FAQ �۹�ȣ
	private String  faq_content     ="";	//FAQ �۳���
	private String  faq_title       ="";	//FAQ ������
	private String  faq_writedate   =""; 	//FAQ ���ۼ���
	private String  faq_category    ="";  //FAQ ī�װ�                         
	private int     faq_readcnt     =0;	//�� ��ȸ��
	
	
}
