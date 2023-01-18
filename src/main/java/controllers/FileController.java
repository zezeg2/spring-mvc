package controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import uploads.UploadDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

@Controller
public class FileController {

    private String filePath = "c:/Users/user/uploads/";

    @GetMapping("/file-upload")
    public String upload() {
        return "uploads/form";
    }

    @PostMapping("/file-upload")
    public String uploadProcess(@ModelAttribute("dto") UploadDTO uploadDTO) throws IOException {

        MultipartFile mf1 = uploadDTO.getFile1();
        MultipartFile mf2 = uploadDTO.getFile2();

        String filename1 = mf1.getOriginalFilename();
        String filename2 = mf2.getOriginalFilename();


        File file1 = new File(filePath + genFilename(filename1));
        File file2 = new File(filePath + genFilename(filename2));

        mf1.transferTo(file1);
        mf2.transferTo(file2);
        return "uploads/form-result";
    }

    @GetMapping("/file-list")
    public ModelAndView fileList() {
        ModelAndView mv = new ModelAndView();
        File f = new File(filePath);
        String[] list = f.list();
        System.out.println(Arrays.toString(list));

        mv.setViewName("uploads/file-list");
        mv.addObject("list", list);
        return mv;
    }

    @GetMapping("file-download")
    public void fileDownload(HttpServletResponse resp, String file) throws IOException {
        File f = new File(filePath + file);

        resp.setContentType("application/download;charset=UTF-8");
        resp.setContentLength((int) f.length());
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + file + "\"");

        try (FileInputStream in = new FileInputStream(f);
             OutputStream out = resp.getOutputStream();) {
            FileCopyUtils.copy(in, out);
        }
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
