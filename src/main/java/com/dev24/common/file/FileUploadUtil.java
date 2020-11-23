package com.dev24.common.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.dev24.client.book.vo.BookVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class FileUploadUtil {

	// ���� ���ε��� ���� ���� �ڹٿ����� �ڵ����� ������ ���� �����ϴ�. ���ǹ�: ���� fileDirectory(���� ���) �� �����Ѵٸ�,
	// �޼ҵ� ���� (return;)
	/* ���� ���õ� �޼ҵ�� �������� ���Ǳ⶧���� �������� �������ش� */
	public static void makeDir(String docRoot) {
		log.info("docRoot: " + docRoot);
		File fileDir = new File(docRoot);
		if (fileDir.exists()) {
			log.info("[" + fileDir + "].exists() : " + fileDir.exists());
			return;
		}
		fileDir.mkdirs();
	}

	public static String bookImgUpload(MultipartFile file, BookVO bvo, String imgUsage)
			throws IllegalStateException, IOException {
		log.info("bookImgUpload ȣ�� ����");

		String docRoot = "";
		String fullPath = "";
		int b_num = bvo.getB_num();
		int cateOne_num = bvo.getCateOne_num();
		int cateTwo_num = bvo.getCateTwo_num();

		String real_name = null;
		// MultipleFile Ŭ������ getFile()�޼���� Ŭ���̾�Ʈ�� ���ε��� ����
		String org_name = file.getOriginalFilename();
		log.info("org_name : " + org_name);

		// ���ϸ� ����
		if (org_name != null && (!org_name.equals(""))) {

			if (imgUsage.equals("listcover")) {
				real_name = b_num + "-listcover.jpg";
			} else if (imgUsage.equals("detailcover")) {
				real_name = b_num + "-detailcover.jpg";
			} else if (imgUsage.equals("detail")) {
				real_name = b_num + "-detail.jpg";
			} else {
				log.info("imgUsage�� listcover/detailcover/detail �� ����Ҽ� �ֽ��ϴ�.");
				return null;
			}
			
			docRoot = "C:\\uploadStorage\\bookimg\\" + cateOne_num + "\\" + cateTwo_num + "\\";
			makeDir(docRoot);
			
			fullPath =  docRoot + real_name;
			File fileAdd = new File(fullPath); // ���� ���� ��
			log.info("���ε��� ����(fileAdd) : " + fileAdd);

			file.transferTo(fileAdd);
		}
		return fullPath.replace("C:", "");
	}

	
	/**
	 * ���� ���ε� �޼��� ���ϸ� �ߺ��� �ذ� ��� System.currentTimeMillis() �� ����ϰų� UUID�� 128��Ʈ�� ���̴�.
	 * ǥ�� ���Ŀ��� UUID�� 32���� 16������ ǥ���Ǹ� 36�� ����(32�� ���ڿ� 4���� ������)�� �� 8-4-4-4-12��� 5���� �׷���
	 * ���������� �����Ѵ�. �̸��׸� ������ ����. �̶� UUID.randomUUID().toString()�� �̿��ؼ� ��´�.
	 * 50e8400-e29b-41d4-a716-d446655440000 fileName<��> : board_0000000_a.gif
	 * 
	 * @param file
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String fileUpload(MultipartFile file, String fileName) throws IOException {
		log.info("fileUploadȣ�� ����");

		String real_name = null;
		// MultipleFile Ŭ������ getFile()�޼���� Ŭ���̾�Ʈ�� ���ε��� ����
		String org_name = file.getOriginalFilename();
		log.info("org_name : " + org_name);

		// ���ϸ� ����
		if (org_name != null && (!org_name.equals(""))) {
			real_name = fileName + "_" + System.currentTimeMillis() + "_" + org_name;// ������ ���� �̸�

			String docRoot = "C:\\uploadStorage\\" + fileName;
			makeDir(docRoot);

			File fileAdd = new File(docRoot + "/" + real_name); // ���� ���� ��
			log.info("���ε��� ����(fileAdd) : " + fileAdd);

			file.transferTo(fileAdd);
		}
		return real_name;
	}

	/**
	 * ���� ���� ���ε� �޼ҵ�
	 * 
	 * @param file
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<String> MultipleFileUpload(List<MultipartFile> file, String fileName) throws IOException {

		log.info("fileUpload ȣ�� ����");

		List<String> real_name = new ArrayList<String>();
		String name = "";
		// ���ϸ� ����
		if (!file.isEmpty()) {

			String docRoot = "C:\\uploadStorage\\" + fileName;
			makeDir(docRoot);
			File fileAdd = null;
			for (MultipartFile multiFile : file) {
				name = fileName + "_" + UUID.randomUUID().toString().replaceAll("-", "") + "_"
						+ multiFile.getOriginalFilename();// ������ ���� �̸�

				fileAdd = new File(docRoot + "/" + name);
				log.info("���ε��� ����(fileAdd) : " + fileAdd);

				multiFile.transferTo(fileAdd);// ���� ����
				real_name.add(name);
			}
		}
		return real_name;
	}

	public static void fileDelete(String fileName) throws IOException {
		log.info("fileDelete ȣ�� ����");
		boolean result = false;
		String startDirName = "", docRoot = "";
		String dirName = fileName.substring(0, fileName.indexOf("_"));

		if (dirName.equals("thumnail")) {
			startDirName = fileName.substring(dirName.length() + 1, fileName.indexOf("_", dirName.length() + 1));
			docRoot = "C:\\uploadStorage\\" + startDirName + "\\" + dirName;
		} else {
			docRoot = "C:\\uploadStorage\\" + dirName;
		}

		File fileDelete = new File(docRoot + "/" + fileName); // ���� ���� ��
		log.info("������ ����(fileDelete) : " + fileDelete);
		if (fileDelete.exists() && fileDelete.isFile()) {
			result = fileDelete.delete();
		}
		log.info("���� ���� ����(true/false) : " + result);

	}
}