/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.sistemabscfinal;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pablito
 */
@Embeddable
public class ResponsableObjetivoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_RESPONSABLE", nullable = false)
    private int idResponsable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    private int idObjetivoEstrategico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;

    public ResponsableObjetivoPK() {
    }

    public ResponsableObjetivoPK(int idResponsable, int idObjetivoEstrategico, int idUsuario) {
        this.idResponsable = idResponsable;
        this.idObjetivoEstrategico = idObjetivoEstrategico;
        this.idUsuario = idUsuario;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public int getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(int idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idResponsable;
        hash += (int) idObjetivoEstrategico;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsableObjetivoPK)) {
            return false;
        }
        ResponsableObjetivoPK other = (ResponsableObjetivoPK) object;
        if (this.idResponsable != other.idResponsable) {
            return false;
        }
        if (this.idObjetivoEstrategico != other.idObjetivoEstrategico) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.ResponsableObjetivoPK[ idResponsable=" + idResponsable + ", idObjetivoEstrategico=" + idObjetivoEstrategico + ", idUsuario=" + idUsuario + " ]";
    }
    
}
