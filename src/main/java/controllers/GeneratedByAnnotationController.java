package controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GeneratedByAnnotationController {
    @RequestMapping("/annotation")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("annotation");
        return mv;
    }

    @RequestMapping("/a")
    public String a(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "a";
    }

    @RequestMapping("/b")
    public void b(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setStatus(200);
    }
}

