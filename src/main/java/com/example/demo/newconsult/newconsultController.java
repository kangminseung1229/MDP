package com.example.demo.newconsult;

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
@RequestMapping("/newconsult")
public class newconsultController {
    

    @Autowired
    private newconsultRepo ncRepo;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size=2) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText){

        Page<newconsults> list = ncRepo.getAll(searchText, pageable);

		int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
		int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);


        model.addAttribute("list", list);

        return "newconsult/newconsultList";
    }

    @GetMapping("/write")
	public String write(Model model, @RequestParam(name = "id", required = true, defaultValue = "") Long groupId){




		//상담 보기 , 최대 4개를 봐야한다.
        List<Map<String,Object>> view = ncRepo.getView(groupId);

        newconsults consult = ncRepo.findByFlagAndGroupid(0, groupId);
        
        model.addAttribute("consult", consult);
		model.addAttribute("view",view);

		return "newconsult/newconsultWrite";
	}

    
}
