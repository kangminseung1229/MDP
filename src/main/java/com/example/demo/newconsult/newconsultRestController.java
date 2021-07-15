package com.example.demo.newconsult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * newconsultRestController
 */

@RestController
@RequestMapping("/newconsultRest")
public class newconsultRestController {


    @Autowired
    private newconsultRepo ncRepo;


    // 상담 데이터 개별 수정
    @PostMapping("/update")
    public int updateNewconsults(@RequestParam Long id, @RequestParam String title, @RequestParam String contents){

        int result = 1;
        newconsults returnconsult = new newconsults();


        //부분 jpa save는 없으므로, 업데이트 시 바뀐 컬럼만 하려면 native query 를 이용하는 것이 더 빠르다.
        // 빈 객체 생성 후 id 기준으로 객체를 채운 다음, 변경된 부분만 바꾸기에는 모든 컬럼을 수정해야 하는 속도 문제가 있다.
        try {
            result = ncRepo.setConsult(title, contents, id);
        } catch (Exception e) {
            //TODO: handle exception
            result=0;
            e.printStackTrace();
        }


        return result;
    }
    
}