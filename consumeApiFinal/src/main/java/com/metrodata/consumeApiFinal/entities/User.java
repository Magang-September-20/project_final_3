/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sweje
 */
@Entity
@Table(name = "tb_m_user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<ProgramApply> programApplyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hr", fetch = FetchType.LAZY)
    private List<ProgramApply> programApplyList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pic", fetch = FetchType.LAZY)
    private List<ScheduleTest> scheduleTestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hr", fetch = FetchType.LAZY)
    private List<Program> programList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "full_name")
    private String fullName;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String fullName, String email, String gender, Date birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  id + "";
    }

    public List<ProgramApply> getProgramApplyList() {
        return programApplyList;
    }

    public void setProgramApplyList(List<ProgramApply> programApplyList) {
        this.programApplyList = programApplyList;
    }

    public List<ProgramApply> getProgramApplyList1() {
        return programApplyList1;
    }

    public void setProgramApplyList1(List<ProgramApply> programApplyList1) {
        this.programApplyList1 = programApplyList1;
    }

    public List<ScheduleTest> getScheduleTestList() {
        return scheduleTestList;
    }

    public void setScheduleTestList(List<ScheduleTest> scheduleTestList) {
        this.scheduleTestList = scheduleTestList;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }
    
}
