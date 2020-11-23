package com.dev24.admin.purchase.vo;

import com.dev24.common.vo.CommonVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AdminPurchaseViewVO extends CommonVO {
	private int p_num = 0;
	private int c_num = 0;
	private String c_id = "";
	private String p_pmethod = ""; // �������
	private int sales_price = 0; // ����ݾ�(���űݾ�-ȯ�ұݾ�)
	private String p_buydate = ""; // ������
	private String isRefund = ""; // ȯ�ҿ��� Y/N

}
