package com.dev24.client.ne.service;

import java.util.List;

import com.dev24.client.ne.vo.NeVO;

public interface NeService {
	public int getNeListCnt(NeVO nevo);
	public List<NeVO> neList(NeVO nevo);
	public NeVO neDetail(int ne_num, String from);
	public int neDelete(int ne_numm, int replyCnt) throws Exception;
	public int neInsert (NeVO nevo) throws Exception;
}
