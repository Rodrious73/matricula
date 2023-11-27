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
@Table(name = "aula")
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a"),
    @NamedQuery(name = "Aula.findByCodiAula", query = "SELECT a FROM Aula a WHERE a.codiAula = :codiAula"),
    @NamedQuery(name = "Aula.findByNombAula", query = "SELECT a FROM Aula a WHERE a.nombAula = :nombAula")})
public class Aula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiAula")
    private Integer codiAula;
    @Basic(optional = false)
    @Column(name = "nombAula")
    private String nombAula;

    public Aula() {
    }

    public Aula(Integer codiAula) {
        this.codiAula = codiAula;
    }

    public Aula(Integer codiAula, String nombAula) {
        this.codiAula = codiAula;
        this.nombAula = nombAula;
    }

    public Integer getCodiAula() {
        return codiAula;
    }

    public void setCodiAula(Integer codiAula) {
        this.codiAula = codiAula;
    }

    public String getNombAula() {
        return nombAula;
    }

    public void setNombAula(String nombAula) {
        this.nombAula = nombAula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiAula != null ? codiAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.codiAula == null && other.codiAula != null) || (this.codiAula != null && !this.codiAula.equals(other.codiAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Aula[ codiAula=" + codiAula + " ]";
    }
    
}
