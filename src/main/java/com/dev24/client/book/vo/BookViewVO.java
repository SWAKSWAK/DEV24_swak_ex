package com.dev24.client.book.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * equals�� hashCode �޼ҵ� �ڵ� ���� �� �θ� Ŭ������ �ʵ���� �������� �� ������ ���ؼ� ������
 * callSuper = true �θ� Ŭ���� �ʵ� ���鵵 ���� ���� üũ
 * callSuper = false �θ� Ŭ���� �ʵ� ���鵵 ���� ���� üũ����
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class BookViewVO {
	private int b_num				=0;
	private String b_name			="";
	private String b_date			="";
	private String b_list			="";
	private String b_author			="";
	private String b_pub			="";
	private String b_authorinfo		="";
	private String b_info			="";
	private String b_state			="";
	private int b_price				=0;
	private int cateOne_num			=0;
	private int cateTwo_num			=0;
	
	//����
	private double ra_sum			=0.0;
	private double ra_count			=0.0;
	private double ra_avg			= ra_sum * ra_count;
	
	//�̹���
	private String listcover_imgurl 	="";
	private String detailcover_imgurl 	="";
	private String detail_imgurl		="";
}
