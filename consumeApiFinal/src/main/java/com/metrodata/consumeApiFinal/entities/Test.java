/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities;

import java.io.Serializable;
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

/**
 *
 * @author pannavr
 */
@Entity
@Table(name = "tb_m_test")
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "passing_grade")
    private int passingGrade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", fetch = FetchType.LAZY)
    private List<ScheduleTest> scheduleTestList;

    public Test() {
    }

    public Test(Integer id) {
        this.id = id;
    }

    public Test(Integer id, String name, int passingGrade) {
        this.id = id;
        this.name = name;
        this.passingGrade = passingGrade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
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
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + "";
    }
    
}
