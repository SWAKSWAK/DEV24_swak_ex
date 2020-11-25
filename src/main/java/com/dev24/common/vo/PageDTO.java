package com.dev24.common.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total; 
	private CommonVO cvo;
	
	public PageDTO(CommonVO cvo, int total) {
		this.cvo=cvo;
		this.total=total;
		
		/*�럹�씠吏뺤쓽 �걹踰덊샇(endPage) 援ы븯湲�
		 * this.endPAge = (int) (Math.ceil(�럹�씠吏�踰덊샇/10.0)) * 10;
		*/
		this.endPage= (int)(Math.ceil(cvo.getPageNum()/10.0))*10;
		
		// �럹�씠吏뺤쓽 �떆�옉踰덊샇(startPage) 援ы븯湲� 
		this.startPage = this.endPage-9;
		
		//�걹�럹�씠吏� 援ы븯湲�
		int realEnd = (int)(Math.ceil((total*1.0)/cvo.getAmount()));
		
		if(realEnd <= this.endPage) {
			this.endPage = realEnd;
		}
		
		// �씠�쟾(prev) 援ы븯湲� 
		this.prev = this.startPage > 1;
		
		// �떎�쓬 (next) 援ы븯湲�
		this.next = this.endPage < realEnd;
	}
	
	
}
