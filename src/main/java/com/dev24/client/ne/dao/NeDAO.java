package com.dev24.client.ne.dao;

import java.util.List;

import com.dev24.client.ne.vo.NeVO;

public interface NeDAO {
	
	//select
	public int getNeListCnt(NeVO nevo);
	public List<NeVO> neList(NeVO nevo);
	public NeVO neDetail(int ne_num);
	
	//delete
	public int neDelete (int ne_num);
	
	//insert
	public int neInsert (NeVO nevo);
	
	//update
	public int neReadCnt(int ne_num);
}