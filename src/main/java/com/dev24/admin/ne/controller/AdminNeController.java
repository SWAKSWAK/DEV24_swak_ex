package com.dev24.admin.ne.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev24.client.ne.service.NeService;
import com.dev24.client.ne.vo.NeVO;
import com.dev24.common.vo.PageDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/*")
@Log4j
@AllArgsConstructor
public class AdminNeController {
		
	private NeService neService;

	@RequestMapping(value="/neList", method=RequestMethod.GET)
	public String adminNeList(@ModelAttribute("data") NeVO nevo, Model model){
		log.info("adminNeList È£Ãâ ¼º°ø");
		
		List<NeVO> neList = neService.neList(nevo);
		model.addAttribute("neList", neList);
		
		int total = neService.getNeListCnt(nevo);
		model.addAttribute("pageMaker", new PageDTO(nevo, total));
		
		return "admin/adminNeList";
	}
	
	@RequestMapping(value="/neDetail")
	public String neDetail (@RequestParam("ne_num") Integer ne_num, Model model) {
		log.info("neDetail È£ï¿½ï¿½ ï¿½Ï·ï¿½");
		
		NeVO nvo = neService.neDetail(ne_num, "admin");
		
		model.addAttribute("nvo", nvo);
		
		return "admin/adminNeDetail";
	}

	@RequestMapping(value="/neDelete", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> neDelete(
			@RequestParam int ne_num, 
			@RequestParam int replyCnt, 
			Model model
	) throws Exception 
	{
		
		log.info("neDelete È£ï¿½ï¿½ ï¿½Ï·ï¿½");
		
		ResponseEntity<String> entity;
		
		int result = neService.neDelete(ne_num, replyCnt);
		
		if(result > 0) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	@RequestMapping("/neInsertForm")
	public String neInsertForm() {
		log.info("neInsertForm È£ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½");
		return "admin/adminNeInsertForm";
	}
	
	@RequestMapping("/neInsert")
	public String neInsert(@ModelAttribute("data") NeVO nevo, Model model) throws Exception{
		log.info("neInsert È£ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½");
		
		int result = neService.neInsert(nevo);
		log.info("result: " + result);
		
		return "redirect:/admin/neList";
	}
	
}
