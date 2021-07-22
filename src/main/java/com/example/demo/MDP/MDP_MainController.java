package com.example.demo.MDP;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jdk.nashorn.api.tree.TryTree;

@Controller
@RequestMapping("MDP")
public class MDP_MainController {

    @Autowired
    private mdpRepository mdpRepo;

    @GetMapping("/main")
    public String main() {
        return "MDP/main";
    }

    @GetMapping("/login")
    public String login() {
        return "MDP/login";
    }

    @GetMapping("/join")
    public String join() {
        return "MDP/join";
    }

    @GetMapping("/process")
    public String process() {
        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter() {
        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin() {
        return "MDP/fin";
    }

    //(Model model,@RequestParam int count)
    // @RequestParam(required = false, defaultValue = "") String searchText
    // 검색할 text가 꼭 있을 필요가 없다

    @GetMapping("/manage")
    public String manage(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 15) Pageable pageable){


        // mdpPurchaseCode mp = new mdpPurchaseCode();
        // TestTable tTable = new TestTable();

        // int randomCHECK = 0;

        /*** 구매코드 생성 ***/ 
        /*
        Random random = new Random();
        ArrayList<Long> id = new ArrayList<Long>();
        ArrayList<String> code = new ArrayList<String>();


        for(int i=0; i<count; i++){
            tTable.setId(Long.valueOf(i+1l)); //id 설정
            tTable.setCode("THINKUS_"+String.format("%05d",random.nextInt(100000))); 
            
            if(mdpRepo.countByCode(tTable.getCode())>0){
                i--;
                continue;
            }
            
            mdpRepo.save(tTable);
            mdpRepo.flush();

            id.add(tTable.getId());
            code.add(tTable.getCode());
        }
        
        model.addAttribute("id", id);
        model.addAttribute("code", code);
         */

        // 페이징 작업
        // Page<TestTable> page = mdpRepo.findByUser(searchText, pageable);
        
        Page<TestTable> page = mdpRepo.findAll(pageable);
        int startPage = Math.max(1, page.getPageable().getPageNumber() - 9);
		int endPage = Math.min(page.getTotalPages(), page.getPageable().getPageNumber() + 9);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
        model.addAttribute("list", page);
        
        return "MDP/manage";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 15) Pageable pageable){
        Page<TestTable> page = mdpRepo.findByUser(searchText,pageable);
        int startPage = Math.max(1, page.getPageable().getPageNumber() - 9);
		int endPage = Math.min(page.getTotalPages(), page.getPageable().getPageNumber() + 9);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
        model.addAttribute("list", page);
        
        return "MDP/manage";
    }



    @GetMapping("/add")
    public String add(Model model, @RequestParam int count){
        TestTable mp = new TestTable();

        Random random = new Random();

        

       long last=mdpRepo.last_column();

        for(int i=0; i<count; i++){

            mp.setId(Long.valueOf(i+last+1l)); //id 설정
            mp.setCode("THINKUS_"+String.format("%05d",random.nextInt(30))); 
            
            //중복 제거
            if(mdpRepo.countByCode(mp.getCode())>0){
                i--;
                continue;
            }
            
            mdpRepo.save(mp);
            mdpRepo.flush();
        }

        return "redirect:manage";
        
    }


    //board 만들기
    // @GetMapping("/boardList")
    // public String  boardList(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 3) Pageable pageable) {

    //     // Page<TestTable> list = mdpRepo.findByUserContainingOrCodeContaining(searchText, searchText, pageable);
        
    //     int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
	// 	int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);

	// 	model.addAttribute("startPage", startPage);
	// 	model.addAttribute("endPage", endPage);
    //     model.addAttribute("list", list);
    //     return "MDP/boardList";
    // }
   

}
