package com.example.demo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.Service.fileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class testController {

    @Autowired
    private fileUploadService fs;

    @Autowired
    private ResourceLoader resourceLoader;

    // @GetMapping("/principal")
    // public String getUserLoginPage(Model model) {

    // // 권한 확인 페이지
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();

    // model.addAttribute("principal", authentication.getPrincipal());
    // model.addAttribute("auth", hasAdminRole());

    // return "test/principal";
    // }

    // public static boolean hasAdminRole() {
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // Collection<? extends GrantedAuthority> authorities =
    // authentication.getAuthorities();
    // return authorities.stream().filter(o ->
    // o.getAuthority().equals("ROLE_USER")).findAny().isPresent();
    // }

    @GetMapping("/file")
    public String filetest(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Resource resource = resourceLoader.getResource("classpath:files/file_pdf.png");

        
        session.setAttribute("filename1", resource.getFilename());

        return "test/filetest";
    }

    @PostMapping("/file")
    public String fileupload(HttpServletRequest request, MultipartFile filetag) throws Exception {

        String filePath = "E:/newtaxnet/external/static/files/" + filetag.getOriginalFilename();

        File dest = new File(filePath);

        filetag.transferTo(dest);

        return "redirect:file";
    }

    @GetMapping("/fileDownload")
    public String fileDownload(Model model) {

        return "test/filedownload";

    }

    // 파일 다운로드
    @GetMapping("/fileDownload/{filename}")
    public ResponseEntity<Resource> resouceFileDownload(@PathVariable String filename) {
        try {
            Resource resource = resourceLoader.getResource("classpath:static/files/" + filename);
            File file = resource.getFile(); // 파일이 없는 경우 fileNotFoundException error가 난다.

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, file.getName()) // 다운 받아지는 파일 명 설정
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length())) // 파일 사이즈 설정
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString()) // 바이너리 데이터로 받아오기
                                                                                                     // 설정
                    .body(resource); // 파일 넘기기
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 경로 파일다운로드
    @ResponseBody
    @GetMapping("/fileDownload2")
    public void doDownloadFile(HttpServletResponse response, @RequestParam String fileName) throws IOException {

        response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream("/files/" + fileName);

            int count = 0;
            byte[] bytes = new byte[512];

            while ((count = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, count);
            }
            
            fis.close();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        }
    }



}
