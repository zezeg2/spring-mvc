package controllers;

import daos.MemberDAO;
import daos.MybatisMemberDAOImpl;
import daos.SpringMybatisMemberDAOImpl;
import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import domains.member.dtos.UpdateMemberDTO;
import exceptions.IncorrectPasswordException;
import exceptions.MemberNotFoundException;
import exceptions.SessionExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.MemberService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/login")
    public String loginForm(HttpSession session) throws SQLException {
        if (session.getAttribute("auth") != null) {
            return memberInfo(session);
        }
        return "/login/login-form";
    }

    @PostMapping("/login")
    public ModelAndView loginProcess(@ModelAttribute("auth") AuthMemberDTO auth, HttpServletRequest req) throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("info", memberService.login(auth));
        mv.setViewName("member/member-info");
        req.getSession().setAttribute("auth", auth);
        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("auth");
        return home();
    }

    @GetMapping("/register")
    public String registerForm() {
        return "/member/register-form";
    }

    //    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping("/register")
    public String registerProcess(@ModelAttribute MemberDTO dto, HttpSession session) throws SQLException {
        memberService.register(dto);
        session.setAttribute("auth", new AuthMemberDTO(dto.id(), dto.pw()));
        return memberInfo(session);
    }

    @GetMapping("/member-info")
    public String memberInfo(HttpSession session) throws SQLException {
        if (session.getAttribute("auth") == null) throw new SessionExpiredException();
        session.setAttribute("info", memberService.getMemberInfo((AuthMemberDTO) session.getAttribute("auth")));
        return "member/member-info";
    }

    @GetMapping("/member-list")
    public ModelAndView memberList(@RequestParam("page") int page) throws SQLException {
        ModelAndView mv = new ModelAndView();
        List<MemberInfoDTO> list = memberService.getMemberListByPage(page);
        int totalPage = memberService.getTotalPage();
        mv.addObject("memberList", list);
        mv.addObject("totalPage", totalPage);
        mv.setViewName("member/member-list");
        return mv;
    }

    @GetMapping("/member-update")
    public ModelAndView updateForm(HttpSession session) throws SQLException {
        AuthMemberDTO auth = (AuthMemberDTO) session.getAttribute("auth");
        if (auth == null) throw new SessionExpiredException();
        MemberDTO prevInfo = memberService.getMember(auth);
        ModelAndView mv = new ModelAndView();
        mv.addObject("prevInfo",prevInfo);
        mv.setViewName("member/member-update-form");
        return mv;
    }
    @PatchMapping("/member-update")
    public String updateProcess(@ModelAttribute UpdateMemberDTO dto, HttpSession session) throws SQLException {
        memberService.updateMember(dto);
        return memberInfo(session);
    }

    @GetMapping("/member-delete")
    public String getDeleteRequest(HttpSession session) throws SQLException {
        AuthMemberDTO auth = (AuthMemberDTO)session.getAttribute("auth");
        if (auth == null) throw new SessionExpiredException();
        return "member/member-delete-form";
    }
    @PostMapping("/member-delete")
    public String deleteProcess(@ModelAttribute AuthMemberDTO inputAuth, HttpSession session) throws SQLException {
        AuthMemberDTO auth = (AuthMemberDTO)session.getAttribute("auth");
        if (!auth.pw().equals(inputAuth.pw())) throw new IncorrectPasswordException();
        memberService.deleteMember(auth);
        session.removeAttribute("auth");
        return home();
    }

    @ExceptionHandler({RuntimeException.class, SQLException.class})
    public ModelAndView commonExceptionHandler(Exception exception){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", exception.getMessage());
        mv.setViewName("error-page");
        return mv;
    }
}
