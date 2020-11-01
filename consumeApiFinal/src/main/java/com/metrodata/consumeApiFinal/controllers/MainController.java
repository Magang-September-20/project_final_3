/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.controllers;

import com.metrodata.consumeApiFinal.entities.File;
import com.metrodata.consumeApiFinal.entities.LoginInput;
import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.entities.Result;
import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.dao.EducationInput;
import com.metrodata.consumeApiFinal.entities.dao.RegisterInput;
import com.metrodata.consumeApiFinal.entities.dao.ScheduleTestInput;
import com.metrodata.consumeApiFinal.entities.rest.LoginOutput;
import com.metrodata.consumeApiFinal.services.EducationService;
import com.metrodata.consumeApiFinal.services.EmailNotificationProgram;
import com.metrodata.consumeApiFinal.services.EmailNotificationService;
import com.metrodata.consumeApiFinal.services.FileService;
import com.metrodata.consumeApiFinal.services.LoginService;
import com.metrodata.consumeApiFinal.services.MajorService;
import com.metrodata.consumeApiFinal.services.ProgramApplyService;
import com.metrodata.consumeApiFinal.services.ProgramService;
import com.metrodata.consumeApiFinal.services.RegisterService;
import com.metrodata.consumeApiFinal.services.ResultService;
import com.metrodata.consumeApiFinal.services.ScheduleService;
import com.metrodata.consumeApiFinal.services.ScheduleTestService;
import com.metrodata.consumeApiFinal.services.TestService;
import com.metrodata.consumeApiFinal.services.UniversityService;
import com.metrodata.consumeApiFinal.services.UserService;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    MajorService ms;
    @Autowired
    UniversityService us;
    @Autowired
    TestService test;
    @Autowired
    ResultService resultService;
    @Autowired
    FileService fileService;
    @Autowired
    EmailNotificationProgram emailNotificationProgram;
    @Autowired
    EmailNotificationService emailNotificationService;
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("major", ms.getAllMajor());
            model.addAttribute("univ", us.getAll());
            model.addAttribute("file", test.getAll());
            model.addAttribute("EducationInput", new EducationInput());
            return "user";
        } else {
            return "login";
        }
    }

    @PostMapping("/educationForm")
    public String educationForm(@Validated EducationInput input) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int idTemp = Integer.parseInt(auth.getName());
        System.out.println(input.getMajor());
        System.out.println(input.getIpk());
        System.out.println(input.getStatus());
        es.save(input, idTemp);
        return "redirect:/profile";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
//        model.addAttribute("user", new LoginInput());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            System.out.println(userService.countUser() + "jumlah user");
            model.addAttribute("countUser", userService.countUser());
            model.addAttribute("countApply", programApplyService.countApply());
            model.addAttribute("countIntern", programApplyService.countInter());
            model.addAttribute("countJunior", programApplyService.countJunior());
            model.addAttribute("countSenior", programApplyService.countSenior());
            model.addAttribute("countFront", programApplyService.countFront());
            model.addAttribute("countBack", programApplyService.countback());
            model.addAttribute("examDone", resultService.examDone());
            model.addAttribute("countCV", fileService.countCV());
            model.addAttribute("countPassed", resultService.passedUser());
            model.addAttribute("countFailed", resultService.failedUser());
            model.addAttribute("countPsikotes", resultService.passedPsikotes());
            model.addAttribute("countTechnical", resultService.passedTechnical());

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
            User user = new User(output.getUser().getId() + "", "", getAuthorities(output.getUser().getRoles()));
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
//            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            return "program";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
//            model.addAttribute("schedules", ss.getByEmail(auth.getName()));
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("schedules", ss.getEmail(Integer.parseInt(auth.getName())));

            return "schedule";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/profile")
    public String profil(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("profile1", es.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("file", fileService.getById(Integer.parseInt(auth.getName())));
            return "profile";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("createSchedule")
    public String CreateSchedule(Model model, @Validated ScheduleTest scheduleTest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
//            model.addAttribute("schedules", programApplyService.showSchedule());
            model.addAttribute("scheduleInput", new ScheduleTest());
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("schedules", programApplyService.showSchedule());

            model.addAttribute("test", test.getAll());
            return "createSchedule";
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
//            model.addAttribute("schedules", programApplyService.showSchedule());
            model.addAttribute("scheduleInput", new ScheduleTestInput());
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("schedules", scheduleTestService.getAll());

            model.addAttribute("test", test.getAll());
            return "showAllSchedule";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/saveSchedule")
    public String save(@Validated ScheduleTestInput input) throws ParseException {
        System.out.println(input.getLocation());
//        input.setTest(1);
        System.out.println(input.getApply());
        System.out.println(input.getTest());
        System.out.println(input.getPic());
        scheduleTestService.save(input);
        return "redirect:/showAllSchedule";
    }

    @GetMapping("showPsikotes")
    public String showPsikotes(Model model, @Validated ScheduleTest scheduleTest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("psikotes", scheduleTestService.ShowPsikotes());
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));

            model.addAttribute("schedules", scheduleTestService.getAll());
            model.addAttribute("scheduleInput", new ScheduleTestInput());
            model.addAttribute("test", test.getAll());
            return "showPsikotes";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("showTechnical")
    public String showTechnical(Model model, @Validated ScheduleTest scheduleTest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("technical", scheduleTestService.ShowTechnical());
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("scheduleInput", new ScheduleTestInput());
            model.addAttribute("test", test.getAll());
            return "showTechnical";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("showInterview")
    public String showInterview(Model model, @Validated ScheduleTest scheduleTest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("interview", scheduleTestService.ShowInterview());
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("scheduleInput", new ScheduleTestInput());
            model.addAttribute("test", test.getAll());
            return "showInterview";
        } else {
            return "redirect:/login";
        }
    }

    @ResponseBody
    @GetMapping("/passInterview/{candidate}/{program}")
    public void passInterview(@PathVariable("candidate") int candidate, @PathVariable("program") int program) throws MessagingException {
        System.out.println(candidate + " " + program);
        com.metrodata.consumeApiFinal.entities.User user = userService.getById(candidate);
        ProgramApply programApply = programApplyService.getById(program);
        emailNotificationProgram.sendEmailCongrat(user, programApply);
    }

    @GetMapping("applicant")
    public String applican(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("applicants", userService.getUser());
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
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
            model.addAttribute("scheduleTestHr", ss.getSchedule(Integer.parseInt(auth.getName())));
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("inputExam", ss.getTest(Integer.parseInt(auth.getName())));
            model.addAttribute("resultGrade", new Result());

            return "inputExam";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/insertResult")
    public String insertResult(@Validated Result result) throws MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            ScheduleTest schedule = scheduleTestService.getById(result.getId());

            result.setScheduleTest(schedule);
            int passingGrade = schedule.getTest().getPassingGrade();
            if (result.getGrade() >= passingGrade) {
                result.setIsPassed(Boolean.TRUE);
            } else {
                emailNotificationService.sendResultPass(result.getId(), schedule);
                result.setIsPassed(Boolean.FALSE); 
            }
            System.out.println(result.getId());
            resultService.saveResult(result);
            scheduleTestService.saveHastest(result.getId());
            
            return "redirect:/inputExam";
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
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));
            return "profileHr";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("programApply")
    public String ProgramApply(Model model, @Validated ProgramApply programApply) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
//        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            model.addAttribute("apply", programApplyService.getApply(Integer.parseInt(auth.getName())));
            model.addAttribute("iduser", Integer.parseInt(auth.getName()));
            model.addAttribute("profile", userService.getById(Integer.parseInt(auth.getName())));

            model.addAttribute("applys", new ProgramApply());
            model.addAttribute("program", pr.getAll());

//            System.out.println(programApplyService.getId(auth.getName()));
            return "programApply";
        } else {
            return "redirect:/login";
        }
    }

    @ResponseBody
    @GetMapping("isScheduled/{id}")
    public boolean getDetailProgress(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(id);
        if (programApplyService.getDetailProgress(Integer.parseInt(auth.getName()), 1, id) != null) {
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @GetMapping("isPassedTest/{id}")
    public boolean isPassedTest(@PathVariable("id") int programId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(programId);
        if (programApplyService.isPassedTest(Integer.parseInt(auth.getName()), 1, programId) != null) {
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @GetMapping("isPassedTeknikal/{id}")
    public boolean isPassedTeknikal(@PathVariable("id") int programId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(programId);
        if (programApplyService.isPassedTest(Integer.parseInt(auth.getName()), 2, programId) != null) {
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @GetMapping("isPassedInterview/{id}")
    public boolean isPassedInterview(@PathVariable("id") int programId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(programId);
        if (programApplyService.isPassedTest(Integer.parseInt(auth.getName()), 3, programId) != null) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/saveApply")
    public String saveApply(Model model, @Validated ProgramApply programApply) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {

            int compare = programApplyService.compare(Integer.parseInt(auth.getName()), programApply.getProgram().getId());
//            int as = ((Integer)programApply.getProgram().;
//            com.metrodata.consumeApiFinal.entities.User hr = userService.getById(pr.getHR(programApply.getProgram().getId()));
//            com.metrodata.consumeApiFinal.entities.User candidate = userService.getById(programApply.getCandidate().getId());
            if (compare == 0) {
                try {
                    programApply.setHr(new com.metrodata.consumeApiFinal.entities.User(pr.getHR(programApply.getProgram().getId())));
                    programApply.setCandidate(new com.metrodata.consumeApiFinal.entities.User(Integer.parseInt(auth.getName())));
                    programApplyService.save(programApply);
                } catch (Exception e) {
                    return "redirect:/programApply";
                }
            }
            return "redirect:/programApply";
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

    @PostMapping("/saveFile")
    public String handleFormSubmit(@RequestParam("cv") MultipartFile cv,
            @RequestParam("photo") MultipartFile photo) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        File file = fileService.getById(Integer.parseInt(auth.getName()));
        try {
            String CV = fileService.UploadFileName(cv, file, "cv");
            String PHOTO = fileService.UploadFileName(photo, file, "photo");
            file.setCv(CV);
            file.setPhoto(PHOTO);
            fileService.save(file);
        } catch (Exception e) {
        }

        return "redirect:/user";
    }
}
