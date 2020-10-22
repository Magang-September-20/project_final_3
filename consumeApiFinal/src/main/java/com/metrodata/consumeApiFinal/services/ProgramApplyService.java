/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Program;
import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.User;
import com.metrodata.consumeApiFinal.repositories.ProgramApplyRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pannavr
 */
@Service
public class ProgramApplyService {

    @Autowired
    ProgramApplyRepository programApplyRepository;
    @Autowired
    EmailNotificationProgram enp;
    @Autowired
    UserService userService;
    @Autowired
    ProgramService programService;

    public List<ProgramApply> getApply(int id) {
        return programApplyRepository.getApply(id);
    }

    public void save(ProgramApply programApply) {
        programApplyRepository.save(programApply);
        System.out.println("candidate id = "+programApply.getCandidate().getId());
        System.out.println("hr id = "+programApply.getHr().getId());
        User candidate = userService.getById(programApply.getCandidate().getId());
        User hr = userService.getById(programApply.getHr().getId());
        ProgramApply programApplyGet = getById(programApply.getProgram().getId());
//        System.out.println("candidate full name :) = "+candidate.getFullName());
//        System.out.println("candidate fullname = "+programApply.getCandidate().getFullName());
        try {
            enp.sendEmailCandidate(candidate,hr,programApplyGet);
//            enp.sendEmailPic(candidate,hr,programApplyGet);
        } catch (MessagingException ex) {
             Logger.getLogger(ProgramApplyService.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return newProgramApply;
    }

    public List<ProgramApply> showSchedule() {
        return programApplyRepository.Showschedule();
    }

    public Integer countApply() {
        return programApplyRepository.countApply();
    }
    
    public Integer countInter() {
        return programApplyRepository.applyIntern();
    }
    
    public Integer countJunior() {
        return programApplyRepository.applyJunior();
    }
    
    public Integer countSenior() {
        return programApplyRepository.applySenior();
    }
    public Integer countFront() {
        return programApplyRepository.applyFront();
    }
    public Integer countback() {
        return programApplyRepository.applyBack();
    }
    
    public ProgramApply getById(int id){
        return programApplyRepository.findById(id).get();
    }
}
