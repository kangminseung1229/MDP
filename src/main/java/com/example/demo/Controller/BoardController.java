package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.demo.DTO.taxnet_logs;
import com.example.demo.Repository.tlRepository;
import com.example.demo.vaildator.taxnet_logsVaildator;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
CRUD temlplate
*/
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private tlRepository tlRepo;

	@Autowired
	private taxnet_logsVaildator tlValidator;

	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model, @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {

		Page<taxnet_logs> list = tlRepo.findByUseridContainingOrIpContainingOrderByIdDesc(searchText, searchText,
				pageable);

		int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
		int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("list", list);

		return "board/list";
	}


	//view
	@GetMapping("/write")
	public String write(Model model, @RequestParam(required = false) Long id) {

		if (id == null) {
			model.addAttribute("taxnet_logs", new taxnet_logs());
		} else {
			taxnet_logs taxnet_logs = tlRepo.findById(id).orElse(null);
			model.addAttribute("taxnet_logs", taxnet_logs);
			model.addAttribute("userid", taxnet_logs.getUserid());
			model.addAttribute("id", taxnet_logs.getId());
		}

		return "board/write";
	}


	//insert / update
	@PostMapping("/write")
	public String writeSubmit(@Valid taxnet_logs taxnet_logs, BindingResult bindingresult, Authentication authentication) {

		tlValidator.validate(taxnet_logs, bindingresult);

		if (bindingresult.hasErrors()) {

			return "board/write";

		}

		// authentication.getName(); 스프링 시큐리티, 인증된 사용자 이름

		tlRepo.save(taxnet_logs);
		return "redirect:/board/list";
		
	}
	
	
	//delete
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam Long id){
		
		tlRepo.deleteById(id);
		
		
		return "redirect:/board/list";
	}
}
