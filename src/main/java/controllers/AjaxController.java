package controllers;

import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import services.MemberService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/login")
    public String loginAjax() {
        return "ajax/login-form";
    }

    @ResponseBody
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String loginProcess(String id, String pw) {
        String result;
        if (id.equals("ajax") && pw.equals("ajax")) result = "success";
        else result = "fail";
        return "{\"process\" : \"" + result + "\" }";
    }
    @ResponseBody
    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MemberInfoDTO loginInform(String id, String pw) throws SQLException {
        MemberInfoDTO dto = memberService.getMemberInfo(new AuthMemberDTO(id, pw));
        return dto;
    }

    @ResponseBody
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MemberInfoDTO> getAllMember(){
        return memberService.getAllMember();
    }

    @ResponseBody
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String upload(MultipartFile uploadFile) throws IOException {
        String filePath = "c:/Users/user/uploads/";
        String filename = uploadFile.getOriginalFilename();
        File file = new File(filePath + genFilename(filename));
        uploadFile.transferTo(file);
        return "ㅎㅎ";
    }
    private String genFilename(String filename) {
        if (filename.contains(".")) {
            int splitter = filename.lastIndexOf('.');
            String filenameWithoutExtension = filename.substring(0, splitter);
            String extension = splitter == -1 ? "" : filename.substring(splitter);
            return filenameWithoutExtension + "(" + UUID.randomUUID() + ")" + extension;
        }
        return filename + "(" + UUID.randomUUID() + ")";
    }

}
