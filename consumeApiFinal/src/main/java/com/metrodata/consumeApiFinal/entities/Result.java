/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author pannavr
 */
@Entity
@Table(name = "tb_tr_result")
@NamedQueries({
    @NamedQuery(name = "Result.findAll", query = "SELECT r FROM Result r")})
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "note")
    private String note;
    @Column(name = "is_passed")
    private Boolean isPassed;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private ScheduleTest scheduleTest;

    public Result() {
    }

    public Result(Integer id) {
        this.id = id;
    }

    public Result(Integer id, Integer grade, String note, Boolean isPassed) {
        this.id = id;
        this.grade = grade;
        this.note = note;
        this.isPassed = isPassed;
    }

    public Result(ScheduleTest scheduleTest) {
        this.scheduleTest = scheduleTest;
    }

    public Result(Integer id, Integer grade, String note, Boolean isPassed, ScheduleTest scheduleTest) {
        this.id = id;
        this.grade = grade;
        this.note = note;
        this.isPassed = isPassed;
        this.scheduleTest = scheduleTest;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Boolean isPassed) {
        this.isPassed = isPassed;
    }

    public ScheduleTest getScheduleTest() {
        return scheduleTest;
    }

    public void setScheduleTest(ScheduleTest scheduleTest) {
        this.scheduleTest = scheduleTest;
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
        if (!(object instanceof Result)) {
            return false;
        }
        Result other = (Result) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " ]";
    }
    
}
