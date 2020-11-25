package com.dev24.client.ne.vo;

import org.springframework.web.multipart.MultipartFile;

import com.dev24.common.vo.CommonVO;

import lombok.Data;

@Data
public class NeVO extends CommonVO{
	private int ne_num;
	private int ne_readcnt;
	private String ne_title;
	private String ne_imgurl;
	private String ne_date;
	private String ne_content;
	private String ne_cate;
	private int ne_rcnt;
	
	private String ne_searchSelect;
	private String ne_searchKeyword;
	
	//이미지 업로드 관련
	MultipartFile imgFile;
}
