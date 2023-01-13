package controllers;

import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import services.MemberService;

import java.sql.SQLException;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    //    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @GetMapping("/login")
    public String loginForm() {
        return "/login/login-form";
    }

    //    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping("/login")
    public ModelAndView loginProcess(@ModelAttribute("auth") AuthMemberDTO auth) throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("info", memberService.login(auth));
        mv.setViewName("login/login-result");
        return mv;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "/member/register-form";
    }

    //    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping("/register")
    public ModelAndView registerProcess(@ModelAttribute MemberDTO info) throws SQLException {
        ModelAndView mv = new ModelAndView();
        MemberInfoDTO result = memberService.register(info);
        mv.addObject("info", result);
        mv.setViewName("member/register-result");
        return mv;
    }
}
