package com.dev24.client.book.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev24.client.book.vo.BookVO;
import com.dev24.common.pagination.Pagination;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private BookDAO bookDAO;
	
	@Test
	public void testBookList() {
		BookVO bvo;
//		ArrayList<BookViewVO> bookList = bookDAO.bookViewList();
		log.info("bvo ��������");
		
		//��� ������
//		for (int i = 0 ; i < bookList.size() ; i++) {
//			bvo = bookList.get(i);
//			log.info(bvo.toString());
//					
//		}
		
		//index 0, ������ �����͸�
//		String first = bookList.get(0).toString();
//		String last = bookList.get(bookList.size() - 1).toString();
//		
//		log.info(first);
//		log.info(last);
		
		//search Test
		bvo = new BookVO();
		bvo.setB_searchKeyword("����");
		bvo.setB_searchSelect("all");
		bvo.setB_stateKeyword("regOrOopOrSoldOut");
		bvo.setB_sort("new");
		bvo.setCateOne_num(0);
		bvo.setCateTwo_num(0);
	
		int total = bookDAO.getBookListCnt(bvo);
		
		//pagination ������(bookLength, startPage, page, cateOne_num, cateTwo_num, listRange, b_sort, b_stateKeyword)
		Pagination pagination = new Pagination(total, 1, 1, 0, 0, 20, "new", "regOrOopOrSoldOut");
		pagination.setB_searchKeyword("����");
		pagination.setB_searchSelect("all");
		List<BookVO> searchList = bookDAO.bookList(pagination);
		
//		log.info(searchList.toString());
		
		
	}
	
}
