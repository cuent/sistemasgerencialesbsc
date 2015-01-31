/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablito
 */
@Entity
@Table(name = "responsable_actividad", catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsableActividad.findAll", query = "SELECT r FROM ResponsableActividad r"),
    @NamedQuery(name = "ResponsableActividad.findByIdResponsableActividad", query = "SELECT r FROM ResponsableActividad r WHERE r.responsableActividadPK.idResponsableActividad = :idResponsableActividad"),
    @NamedQuery(name = "ResponsableActividad.findByIdActividad", query = "SELECT r FROM ResponsableActividad r WHERE r.responsableActividadPK.idActividad = :idActividad"),
    @NamedQuery(name = "ResponsableActividad.findByIdUsuario", query = "SELECT r FROM ResponsableActividad r WHERE r.responsableActividadPK.idUsuario = :idUsuario")})
public class ResponsableActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsableActividadPK responsableActividadPK;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDADES", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividades actividades;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public ResponsableActividad() {
    }

    public ResponsableActividad(ResponsableActividadPK responsableActividadPK) {
        this.responsableActividadPK = responsableActividadPK;
    }

    public ResponsableActividad(int idResponsableActividad, int idActividad, int idUsuario) {
        this.responsableActividadPK = new ResponsableActividadPK(idResponsableActividad, idActividad, idUsuario);
    }

    public ResponsableActividadPK getResponsableActividadPK() {
        return responsableActividadPK;
    }

    public void setResponsableActividadPK(ResponsableActividadPK responsableActividadPK) {
        this.responsableActividadPK = responsableActividadPK;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsableActividadPK != null ? responsableActividadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsableActividad)) {
            return false;
        }
        ResponsableActividad other = (ResponsableActividad) object;
        if ((this.responsableActividadPK == null && other.responsableActividadPK != null) || (this.responsableActividadPK != null && !this.responsableActividadPK.equals(other.responsableActividadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.ResponsableActividad[ responsableActividadPK=" + responsableActividadPK + " ]";
    }
    
}
