package com.dev24.data.book.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev24.client.book.dao.BookDAO;
import com.dev24.data.book.crawler.JSONtoDB;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class InsertBookDataTest{
	
	@Setter(onMethod_ = @Autowired)
	private BookDAO bookDAO;
	
	/**********************************************
	 * ���� ������ insert ���� �޼���
	 * Run As -> JUnit Test �� ����
	 **********************************************/

	@Test
	public void testInsertBookData(){
		log.info("testInsertBookData ȣ�� �Ϸ�");
		
		JSONtoDB jsonToDB = new JSONtoDB();
		
		//�׽�Ʈ �ϱ� �� InserBookDataDAO Ŭ�������� oracle id, password Ȯ�� (�ּ��� �ִ� Ŭ������ ctrl������ Ŀ�� �ø��� ���� ����)
        jsonToDB.jsonToDB("src\\test\\resources\\json\\Books.json", bookDAO);//�̹� ����� Json�� DB�� ����

		log.info("testInsertBookData DB���� �Ϸ�");
	}
}