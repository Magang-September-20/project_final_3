/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.entities.ScheduleTest;
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

    public List<ProgramApply> getApply(int id) {
        return programApplyRepository.getApply(id);
    }

    public ProgramApply save(ProgramApply programApply) {
        ProgramApply newProgramApply = programApplyRepository.save(programApply);
        try {
            enp.sendEmailP(programApply);
        } catch (MessagingException ex) {
             Logger.getLogger(ProgramApplyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newProgramApply;
    }

    public List<ProgramApply> showSchedule() {
        return programApplyRepository.Showschedule();
    }

    public Integer countApply() {
        return programApplyRepository.countApply();
    }
    
    public ProgramApply getById(int id){
        return programApplyRepository.findById(id).get();
    }
}
