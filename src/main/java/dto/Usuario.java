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

@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodiUsua", query = "SELECT u FROM Usuario u WHERE u.codiUsua = :codiUsua"),
    @NamedQuery(name = "Usuario.findByLogiUsua", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua"),
    @NamedQuery(name = "Usuario.validar", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua AND u.passUsua = :passUsua"),
    @NamedQuery(name = "Usuario.findByPassUsua", query = "SELECT u FROM Usuario u WHERE u.passUsua = :passUsua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiUsua")
    private Integer codiUsua;
    @Basic(optional = false)
    @Column(name = "logiUsua")
    private String logiUsua;
    @Basic(optional = false)
    @Column(name = "passUsua")
    private String passUsua;

    public Usuario() {
    }

    public Usuario(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Usuario(Integer codiUsua, String logiUsua, String passUsua) {
        this.codiUsua = codiUsua;
        this.logiUsua = logiUsua;
        this.passUsua = passUsua;
    }

    public Integer getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getLogiUsua() {
        return logiUsua;
    }

    public void setLogiUsua(String logiUsua) {
        this.logiUsua = logiUsua;
    }

    public String getPassUsua() {
        return passUsua;
    }

    public void setPassUsua(String passUsua) {
        this.passUsua = passUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ codiUsua=" + codiUsua + " ]";
    }
    
}
