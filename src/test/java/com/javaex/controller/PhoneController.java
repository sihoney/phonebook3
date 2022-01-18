package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
public class PhoneController {
	
	///////////////////////
	// field
	///////////////////////
	
	
	///////////////////////
	// constructor
	///////////////////////
	
	
	///////////////////////
	// method
	/////////////////////// 
	@RequestMapping(value="writeForm", method=RequestMethod.GET)
	public String writeForm() {
		System.out.println("/phone/writeForm");
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write(@ModelAttribute PersonVo pvo) {
		// @ModelAttribute: param과 일치하는 setter를 찾아 값을 넣어준다. (default 생성자를 꼭 정의!)
		System.out.println("/phhone/write");	
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.insertPerson(pvo);
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model  model) {
		System.out.println("/phone/list");
		
		// 다오에서 리스트를 가져온다.
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.selectPerson();
		
		// 컨트롤러 --> DS 데이터를 보낸다. (model)
		model.addAttribute("personList", personList);
		
		// jsp정보를 리턴한다 (view)
		return "/WEB-INF/views/list.jsp";
	}
	
	@RequestMapping(value="delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam int personId) {
		System.out.println("/phone/delete");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.deletePerson(personId);
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping("updateForm")
	public String updateForm(Model model, @RequestParam int personId) {
		System.out.println("/phone/updateForm");
		
		PhoneDao phoneDao = new PhoneDao();
		PersonVo pvo = phoneDao.getPerson(personId);
		
		model.addAttribute("pvo", pvo);
		
		return "/WEB-INF/views/updateForm.jsp";
	}
	
	@RequestMapping("update")
	public String udpate(@ModelAttribute PersonVo pvo) {
		System.out.println("/phone/update");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.updatePerson(pvo);

		return "redirect:/phone/list";
	}
}
