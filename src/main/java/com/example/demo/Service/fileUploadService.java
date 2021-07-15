package com.example.demo.Service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileUploadService {
    
    @Autowired
    private ResourceLoader resourceLoader;

    

    //외부 리소스 파일에 파일이 존재하는 지 확인
    public String getfileName(String filePath){
        Resource resource = resourceLoader.getResource("classpath:files/" + filePath);
        return resource.getFilename();
    }

    //파일업로드
    public boolean upload(MultipartFile filetag) throws Exception {

        boolean result = true;

        //test window용
        String filePath = "E:/newtaxnet/external/static/files/" + filetag.getOriginalFilename();

        try {
            File dest = new File(filePath);
            filetag.transferTo(dest);
        } catch (Exception e) {
            //TODO: handle exception
            result=false;
            e.printStackTrace();
        }

        return result;

    }

}
