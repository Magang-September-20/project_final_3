/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pannavr
 */
@Entity
@Table(name = "tb_tr_schedule_test")
@NamedQueries({
    @NamedQuery(name = "ScheduleTest.findAll", query = "SELECT s FROM ScheduleTest s")})
public class ScheduleTest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @JoinColumn(name = "apply", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProgramApply apply;
    @JoinColumn(name = "pic", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User pic;
    @JoinColumn(name = "test", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Test test;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "scheduleTest", fetch = FetchType.LAZY)
    private Result result;

    public ScheduleTest() {
    }

    public ScheduleTest(Integer id) {
        this.id = id;
    }

    public ScheduleTest(Integer id, Date date, Date startTime, Date endTime, String location) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ProgramApply getApply() {
        return apply;
    }

    public void setApply(ProgramApply apply) {
        this.apply = apply;
    }

    public User getPic() {
        return pic;
    }

    public void setPic(User pic) {
        this.pic = pic;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
        if (!(object instanceof ScheduleTest)) {
            return false;
        }
        ScheduleTest other = (ScheduleTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodata.consumeApiFinal.entities.ScheduleTest[ id=" + id + " ]";
    }
    
}
