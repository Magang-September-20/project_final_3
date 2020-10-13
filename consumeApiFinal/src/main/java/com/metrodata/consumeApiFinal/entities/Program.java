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
@Table(name = "tb_m_program")
@NamedQueries({
    @NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p")})
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program", fetch = FetchType.LAZY)
    private List<ProgramApply> programApplyList;
    @JoinColumn(name = "hr", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User hr;

    public Program() {
    }

    public Program(Integer id) {
        this.id = id;
    }

    public Program(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public List<ProgramApply> getProgramApplyList() {
        return programApplyList;
    }

    public void setProgramApplyList(List<ProgramApply> programApplyList) {
        this.programApplyList = programApplyList;
    }

    public User getHr() {
        return hr;
    }

    public void setHr(User hr) {
        this.hr = hr;
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
        if (!(object instanceof Program)) {
            return false;
        }
        Program other = (Program) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodata.consumeApiFinal.entities.Program[ id=" + id + " ]";
    }
    
}
