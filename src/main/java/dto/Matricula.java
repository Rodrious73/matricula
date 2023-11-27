/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "matricula")
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByCodiMatr", query = "SELECT m FROM Matricula m WHERE m.codiMatr = :codiMatr"),
    @NamedQuery(name = "Matricula.findByCodiAlum", query = "SELECT m FROM Matricula m WHERE m.codiAlum = :codiAlum"),
    @NamedQuery(name = "Matricula.findByCodiAula", query = "SELECT m FROM Matricula m WHERE m.codiAula = :codiAula"),
    @NamedQuery(name = "Matricula.findByActvMatr", query = "SELECT m FROM Matricula m WHERE m.actvMatr = :actvMatr")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiMatr")
    private Integer codiMatr;
    @Basic(optional = false)
    @Column(name = "codiAlum")
    private String codiAlum;
    @Basic(optional = false)
    @Column(name = "codiAula")
    private int codiAula;
    @Basic(optional = false)
    @Column(name = "actvMatr")
    private boolean actvMatr;

    public Matricula() {
    }

    public Matricula(Integer codiMatr) {
        this.codiMatr = codiMatr;
    }

    public Matricula(Integer codiMatr, String codiAlum, int codiAula, boolean actvMatr) {
        this.codiMatr = codiMatr;
        this.codiAlum = codiAlum;
        this.codiAula = codiAula;
        this.actvMatr = actvMatr;
    }

    public Integer getCodiMatr() {
        return codiMatr;
    }

    public void setCodiMatr(Integer codiMatr) {
        this.codiMatr = codiMatr;
    }

    public String getCodiAlum() {
        return codiAlum;
    }

    public void setCodiAlum(String codiAlum) {
        this.codiAlum = codiAlum;
    }

    public int getCodiAula() {
        return codiAula;
    }

    public void setCodiAula(int codiAula) {
        this.codiAula = codiAula;
    }

    public boolean getActvMatr() {
        return actvMatr;
    }

    public void setActvMatr(boolean actvMatr) {
        this.actvMatr = actvMatr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMatr != null ? codiMatr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.codiMatr == null && other.codiMatr != null) || (this.codiMatr != null && !this.codiMatr.equals(other.codiMatr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Matricula[ codiMatr=" + codiMatr + " ]";
    }
    
}
