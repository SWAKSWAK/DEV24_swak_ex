package com.dev24.client.book.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**************************************************************
 * equals�� hashCode �޼ҵ� �ڵ� ���� �� �θ� Ŭ������ �ʵ���� �������� �� ������ ���ؼ� ������
 * callSuper = true �θ� Ŭ���� �ʵ� ���鵵 ���� ���� üũ
 * callSuper = false �θ� Ŭ���� �ʵ� ���鵵 ���� ���� üũ����
 **************************************************************/

@Data
@EqualsAndHashCode(callSuper = false)
public class BookVO {
	private int b_num				=0;
	private String b_name			="";
	private String b_date			="";
	private String b_list			="";
	private String b_author			="";
	private String b_pub			="";
	private String b_authorinfo		="";
	private String b_info			="";
	private String b_state			=""; //�������� : null(���), unreg, oop(out of print)
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
	
	//�������� �̹����� �����ϱ� ���� ������� �̹��� ��������
	//listcover/detailcover/detail �� key���� ���� �� �ִ�.
	private MultipartFile listcoverFile;
	private MultipartFile detailcoverFile;
	private MultipartFile detailFile;
	
	//json�� ����� url������ ������
	private String url;
	
	// ���Ŀ� ���� �����ϱ� ���� ���� (�⺻�� = "best")
	// dev24 / best / new / old / lowPrice / highPrice
	private String b_sort = "best";
	
	//b_salesRate : b_num ���� ������ sum(pd_qty)
	//�Ǹŷ��� ��ȸ�� �� �ִ�.
	private int salescnt;
	
	/*************************************************************
	 * ���/�̵��/���� ���θ� �Ǵ��ϱ� ���� �ʵ�
	 * null �Ǵ� "" �Ǵ� "all"	:��� ��ȸ
	 * "null"				:��ϻ�ǰ�� ��ȸ
	 * "unreg"				:�̵�� ��ǰ�� ��ȸ
	 * "outOfPrint"				:���� ��ǰ�� ��ȸ
	 * "reg or oop"			:��� �� ���� ��� ���
	 *************************************************************/
	private String b_stateKeyword = "all";
	
	//���������������� ���� üũ �� �ϰ� ó���� ����ϴ� �׸�s
	private List<Integer> bNumList;
	
	/*************************************************************
	 * �˻�����
	 * 	- searchSelect : select�ڽ��� ���� � �׸��� �˻����� ����
	 * 					all, b_name, b_author, b_pub, b_info
	 *  - searchKeyword : �Է��� �˻���
	 ************************************************************/
	private String b_searchSelect = "";
	private String b_searchKeyword = "";
}
