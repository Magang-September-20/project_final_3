/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.controllers;

import com.metrodata.consumeApiFinal.entities.LoginInput;
import com.metrodata.consumeApiFinal.entities.dao.RegisterInput;
import com.metrodata.consumeApiFinal.entities.rest.LoginOutput;
import com.metrodata.consumeApiFinal.services.LoginService;
import com.metrodata.consumeApiFinal.services.ProgramService;
import com.metrodata.consumeApiFinal.services.UserService;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.tomcat.websocket.AuthenticatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.metrodata.consumeApiFinal.services.RegisterService;
import com.metrodata.consumeApiFinal.services.ScheduleService;
import com.metrodata.consumeApiFinal.services.TestService;
import com.metrodata.consumeApiFinal.services.UserService;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.tomcat.websocket.AuthenticatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    TestService test;

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
            User user = new User(output.getUser().getName(), "", getAuthorities(output.getUser().getRoles()));
            PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/registerverif")
    public String registerVerification(RegisterInput input) throws ParseException {
        System.out.println(input);
        registerService.register(input);
//        LoginOutput output = service.loginNew(input);
//        System.out.println(output);

//        if (output.getStatus().equalsIgnoreCase("success")) {
//            User user = new User(output.getUser().getName(), "", getAuthorities(output.getUser().getRoles()));
//            PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(user, "", user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            return "redirect:/dashboard";
//        } else {
//            return "redirect:/login";
//        }
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            return "dashboard";
        } else {
            return "redirect:/login";
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @GetMapping("employees")
    public String employees(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
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
            return "program";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("schedules", ss.getAll());
            return "schedule";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("showAllSchedule")
    public String showSchedule(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("schedules", ss.getAll());
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
            return "examp";
        } else {
            return "redirect:/login";
        }

    }
}
