package com.dev24.client.pdetail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdetailVO {
	private int pd_num = 0;
	private int pd_price = 0; // å������ ���Ű�(�ܰ��ƴ�)
	private String pd_orderstate = ""; // �ֹ�����(ó���� ������ '��ۿ���')
	private int p_num = 0; // ���Ź�ȣ
	private int c_num = 0; // ȸ����ȣ
	private int b_num = 0; // ������ȣ
	private int pd_qty = 0; // �ش� ������ ���ż���
	
}