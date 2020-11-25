package com.dev24.client.ne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev24.client.ne.service.NeService;
import com.dev24.client.ne.vo.NeVO;
import com.dev24.common.vo.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("ne/*")
@Log4j
@AllArgsConstructor
public class NeController {

	@Setter(onMethod_ = @Autowired)
	private NeService neService;
	
	@RequestMapping("/neList")
	public String neList (@ModelAttribute("data") NeVO nevo, Model model) {
		log.info("neList 호출 완료");
		
		List<NeVO> neList = neService.neList(nevo);
		model.addAttribute("neList", neList);
		
		int total = neService.getNeListCnt(nevo);
		model.addAttribute("pageMaker", new PageDTO(nevo, total));
		
		return "ne/neList";
	}
	
	@RequestMapping("/neDetail")
	public String neDetail(int ne_num, Model model) {
		log.info("neDetail 호출 완료");
		
		NeVO nvo = neService.neDetail(ne_num, "client");
		log.info(nvo);
		model.addAttribute("nvo", nvo);
		
		return "ne/neDetail";
	}

}
