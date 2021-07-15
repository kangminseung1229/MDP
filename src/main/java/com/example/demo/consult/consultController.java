package com.example.demo.consult;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consult")
public class consultController {


    @Autowired
    public consultRepo csRepo;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size=5) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText){

        // List<consultDTO> list = csRepo.findAll(); 

        Page<Map<String,Object>> list = csRepo.getListByTitle(searchText, searchText, pageable);
		model.addAttribute("list", list);
		
		int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
		int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);


        return "consult/consultList";
    }

	@GetMapping("/write")
	public String write(Model model, @RequestParam(required = true, defaultValue = "") Long id){

		//상담 보기 , 최대 4개를 봐야한다.
		List<consults> view = csRepo.findByParentOrIdOrderByFlagid(id, id);

		model.addAttribute("view",view);

		return "consult/consultWrite";
	}



    
}
