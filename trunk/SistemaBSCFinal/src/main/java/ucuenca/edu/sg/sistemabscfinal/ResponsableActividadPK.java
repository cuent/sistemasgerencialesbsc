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
public class ResponsableActividadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_RESPONSABLE_ACTIVIDAD", nullable = false)
    private int idResponsableActividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTIVIDAD", nullable = false)
    private int idActividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;

    public ResponsableActividadPK() {
    }

    public ResponsableActividadPK(int idResponsableActividad, int idActividad, int idUsuario) {
        this.idResponsableActividad = idResponsableActividad;
        this.idActividad = idActividad;
        this.idUsuario = idUsuario;
    }

    public int getIdResponsableActividad() {
        return idResponsableActividad;
    }

    public void setIdResponsableActividad(int idResponsableActividad) {
        this.idResponsableActividad = idResponsableActividad;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
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
        hash += (int) idResponsableActividad;
        hash += (int) idActividad;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsableActividadPK)) {
            return false;
        }
        ResponsableActividadPK other = (ResponsableActividadPK) object;
        if (this.idResponsableActividad != other.idResponsableActividad) {
            return false;
        }
        if (this.idActividad != other.idActividad) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK[ idResponsableActividad=" + idResponsableActividad + ", idActividad=" + idActividad + ", idUsuario=" + idUsuario + " ]";
    }
    
}
