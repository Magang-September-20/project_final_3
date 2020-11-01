/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pannavr
 */
@Entity
@Table(name = "tb_tr_program_apply")
@NamedQueries({
    @NamedQuery(name = "ProgramApply.findAll", query = "SELECT p FROM ProgramApply p")})
public class ProgramApply implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User candidate;
    @JoinColumn(name = "hr", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User hr;
    @JoinColumn(name = "program", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Program program;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apply", fetch = FetchType.LAZY)
    private List<ScheduleTest> scheduleTestList;

    public ProgramApply() {
    }

    public ProgramApply(Integer id) {
        this.id = id;
    }

    public ProgramApply(Integer id, String note) {
        this.id = id;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public User getHr() {
        return hr;
    }

    public void setHr(User hr) {
        this.hr = hr;
    }
//    public void setHr(int hr) {
//        this.hr = hr;
//    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<ScheduleTest> getScheduleTestList() {
        return scheduleTestList;
    }

    public void setScheduleTestList(List<ScheduleTest> scheduleTestList) {
        this.scheduleTestList = scheduleTestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramApply)) {
            return false;
        }
        ProgramApply other = (ProgramApply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProgramApply{" + "id=" + id + ", note=" + note + ", candidate=" + candidate + ", hr=" + hr + ", program=" + program + ", scheduleTestList=" + scheduleTestList + '}';
    }

    
    
}
