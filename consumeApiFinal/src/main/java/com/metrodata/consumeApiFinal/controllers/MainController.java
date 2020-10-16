/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.controllers;

import com.metrodata.consumeApiFinal.entities.LoginInput;
import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.dao.RegisterInput;
import com.metrodata.consumeApiFinal.entities.dao.ScheduleTestInput;
import com.metrodata.consumeApiFinal.entities.rest.LoginOutput;
import com.metrodata.consumeApiFinal.services.EducationService;
import com.metrodata.consumeApiFinal.services.LoginService;
import com.metrodata.consumeApiFinal.services.ProgramApplyService;
import com.metrodata.consumeApiFinal.services.ProgramService;
import com.metrodata.consumeApiFinal.services.RegisterService;
import com.metrodata.consumeApiFinal.services.ScheduleService;
import com.metrodata.consumeApiFinal.services.ScheduleTestService;
import com.metrodata.consumeApiFinal.services.TestService;
import com.metrodata.consumeApiFinal.services.UserService;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class MainController {

    @Autowired
    LoginService service;
    @Autowired
    RegisterService registerService;
    @Autowired
    UserService userService;
    @Autowired
    ProgramService pr;
    @Autowired
    ScheduleService ss;
    @Autowired
    EducationService es;

    @Autowired
    TestService test;

    @Autowired
    ScheduleTestService scheduleTestService;

    @Autowired
    ProgramApplyService programApplyService;

    @GetMapping(value = {"", "/index"})
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            return "redirect:/dashboard";
        } else {
            return "index";
        }
    }

    @GetMapping("/403")
    public String eror() {
        return "403";
    }

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/404.html";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
//        model.addAttribute("user", new LoginInput());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {

            return "redirect:/dashboard";
        } else {
            return "login";
        }
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "user";
        } else {
            return "login";
        }
    }

    @GetMapping("/admin")
    public String admin(Model model) {
//        model.addAttribute("user", new LoginInput());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "admin";
        } else {
            return "login";
        }
//         return "admin";
    }

    @PostMapping("/verification")
    public String verification(LoginInput input) {
        System.out.println(input);
        LoginOutput output = service.loginNew(input);
        System.out.println(output);

        if (output.getStatus().equalsIgnoreCase("success")) {
            User user = new User(output.getUser().getEmail(), "", getAuthorities(output.getUser().getRoles()));
            PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth.getName());
            System.out.println(auth.getAuthorities());

            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:/admin";
            } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_HR"))) {
                return "redirect:/hr";
            } else {
                System.out.println(auth.getAuthorities());
                return "redirect:/user";
            }

//            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/registerverif")
    public String registerVerification(RegisterInput input) throws ParseException {
        System.out.println(input);
        registerService.register(input);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("employees")
    public String employees(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            model.addAttribute("employees", userService.getEmployee());
            return "employees";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("program")
    public String progam(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("programs", pr.getAll());
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "program";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
//        ScheduleTest scheduleTest = new ScheduleTest();
//        scheduleTest = ss.getByEmail(auth.getName());

        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
//            model.addAttribute("schedules", ss.getByEmail(auth.getName()));
            model.addAttribute("schedules", ss.getEmail(auth.getName()));

            return "schedule";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/profile")
    public String profil(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile1", es.getEducation(auth.getName()));
            model.addAttribute("profile", userService.getProfil(auth.getName()));

            return "profile";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("showAllSchedule")
    public String showSchedule(Model model, @Validated ScheduleTest scheduleTest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("schedules", ss.getAll());
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            model.addAttribute("hr", userService.getHr());
            model.addAttribute("user", userService.getUser());
            model.addAttribute("test", test.getAll());
//            model.addAttribute("scheduleTest", new ScheduleTest());
//            model.addAttribute("pics", userService.getEmployee());
//            model.addAttribute("employees", userService.getEmployee());
            return "showAllSchedule";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("applicant")
    public String applican(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("applicants", userService.getUser());
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "applicant";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("examp")
    public String examp(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("examp", test.getAll());
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "examp";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("hr")
    public String hr(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
//            model.addAttribute("examp", test.getAll());
            return "hr";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("scheduleHr")
    public String ScheduleHr(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("scheduleTestHr", ss.getSchedule(auth.getName()));
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "scheduleHr";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("inputExam")
    public String InputExam(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            model.addAttribute("inputExam", ss.getTest(auth.getName()));
            return "inputExam";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("profileHr")
    public String ProfileHr(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            model.addAttribute("profile", userService.getProfil(auth.getName()));
            return "profileHr";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("programApply")
    public String ProgramApply(Model model, @Validated ProgramApply programApply) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("apply", programApplyService.getApply(auth.getName()));
            model.addAttribute("iduser", programApplyService.getId(auth.getName()));
            model.addAttribute("applys", new ProgramApply());
            model.addAttribute("program", pr.getAll());

            System.out.println(programApplyService.getId(auth.getName()));
            return "programApply";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/saveApply")
    public String saveApply(Model model, @Validated ProgramApply programApply) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
//            int as = ((Integer)programApply.getProgram().;
            System.out.println("PUkii");
//            int ass = (Integer.parseInt(programApply.getProgram().toString()));
//            programApply.getCandidate().setId(9);
            System.out.println(programApply.getProgram());
            System.out.println(programApply.getHr());
            System.out.println(programApply.getNote());
            System.out.println(programApply.getCandidate());

//            System.out.println(ass);
//            pr.getHR(1);
            
            
//            model.addAttribute("apply", programApplyService.getApply(auth.getName()));
            programApplyService.save(programApply);
            return "redirect:/programApply";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/saveSchedule")
    public String save(ScheduleTestInput input) throws ParseException {
        System.out.println(input.getDate());
        System.out.println(input.getStartTime());
        System.out.println(input.getEndTime());
        System.out.println(input.getLocation());
        System.out.println(input.getTest());
        System.out.println(input.getApply());
        System.out.println(input.getPic());
        scheduleTestService.save(input);
//    public String save(ScheduleTest scheduleTest) throws ParseException {
//
////        model.addAttribute("scheduleTest", new ScheduleTest());
////        scheduleTestService.save(scheduleTest);
////        System.out.println(scheduleTest.getDate());
////        System.out.println(scheduleTest.getStartTime());
////        System.out.println(scheduleTest.getPic());
////        System.out.println(scheduleTest.getEndTime());
////        System.out.println(scheduleTest.getApply());
////        System.out.println(scheduleTest);
//        System.out.println(scheduleTest.getDate());
//        System.out.println(scheduleTest.getStartTime());
//        System.out.println(scheduleTest.getPic());
//        System.out.println(scheduleTest.getApply());
//        scheduleTestService.save(scheduleTest);
        return "redirect:/showAllSchedule";
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
