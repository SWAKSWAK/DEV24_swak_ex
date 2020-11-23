package com.dev24.data.book.crawler;

import java.io.File;
import java.util.List;

import com.dev24.client.book.dao.BookDAO;
import com.dev24.client.book.vo.BookVO;
import com.dev24.data.book.dao.InserBookDataDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONtoDB {
	
//	@Setter(onMethod_ = @Autowired)
//	private BookDAO bookDAO;

	/********************************************************************
	 * JSON�� ��Ƴ��� �����͸� DB�� �ű�� �޼ҵ�. DAO�� ȣ�����ִ� �۾��� �Ѵ�.
	 * 
	 * @param filePath: ����Ǿ��ִ� ��� + ���ϸ�
	 ********************************************************************/
	public void jsonToDB(String filePath, BookDAO bookDAO) {
		ObjectMapper mapper = new ObjectMapper();
		InserBookDataDAO inserBookDataDAO = new InserBookDataDAO();
		
		
		try {
			File file = new File(filePath);
			
			//���� ��� üũ��
//			System.out.println(file.getAbsolutePath());
			
			// JSON to VO
			List<BookVO> voList = mapper.readValue(file, new TypeReference<List<BookVO>>() {
			});

			// InserBookDataDAO.bookInsert()ȣ��
			inserBookDataDAO.booksInsert(voList);
//			bookDAO.mergeBookData(voList);
//			bookDAO.mergeBookImgData(voList);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
