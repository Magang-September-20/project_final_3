/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.repositories.ProgramApplyRepository;
import java.util.List;
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

    public List<ProgramApply> getApply(int id) {
        return programApplyRepository.getApply(id);
    }

    public ProgramApply save(ProgramApply programApply) {
        return programApplyRepository.save(programApply);
    }
}
