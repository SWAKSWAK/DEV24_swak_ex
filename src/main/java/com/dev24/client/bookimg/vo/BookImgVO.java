package com.dev24.client.bookimg.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BookImgVO {
	
	//BOOKIMG ���̺� �� �������ʵ�
	private int b_num = 0;
	private String listcover_imgurl 	= "";
	private String detailcover_imgurl 	= "";
	private String detail_imgurl 		= "";
	
	//������ �����͸� �����ϱ� ���� �ʵ�
	private MultipartFile listcoverFile;
	private MultipartFile detailcoverFile;
	private MultipartFile detailFile;
}
