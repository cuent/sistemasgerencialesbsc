/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pablito
 */
@Entity
@Table(catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a"),
    @NamedQuery(name = "Actividades.findByIdActividades", query = "SELECT a FROM Actividades a WHERE a.idActividades = :idActividades"),
    @NamedQuery(name = "Actividades.findByActividad", query = "SELECT a FROM Actividades a WHERE a.actividad = :actividad"),
    @NamedQuery(name = "Actividades.findByDuracion", query = "SELECT a FROM Actividades a WHERE a.duracion = :duracion"),
    @NamedQuery(name = "Actividades.findByControl", query = "SELECT a FROM Actividades a WHERE a.control = :control"),
    @NamedQuery(name = "Actividades.findByFechaInicio", query = "SELECT a FROM Actividades a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Actividades.findByFechaFin", query = "SELECT a FROM Actividades a WHERE a.fechaFin = :fechaFin")})
public class Actividades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDADES", nullable = false)
    private Integer idActividades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String actividad;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int duracion;
    @Size(max = 128)
    @Column(length = 128)
    private String control;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivoEstrategico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividades")
    private Set<ResponsableActividad> responsableActividadSet;

    public Actividades() {
    }

    public Actividades(Integer idActividades) {
        this.idActividades = idActividades;
    }

    public Actividades(Integer idActividades, String actividad, int duracion, Date fechaInicio, Date fechaFin) {
        this.idActividades = idActividades;
        this.actividad = actividad;
        this.duracion = duracion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdActividades() {
        return idActividades;
    }

    public void setIdActividades(Integer idActividades) {
        this.idActividades = idActividades;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ObjetivoEstrategico getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(ObjetivoEstrategico idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    @XmlTransient
    public Set<ResponsableActividad> getResponsableActividadSet() {
        return responsableActividadSet;
    }

    public void setResponsableActividadSet(Set<ResponsableActividad> responsableActividadSet) {
        this.responsableActividadSet = responsableActividadSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividades != null ? idActividades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.idActividades == null && other.idActividades != null) || (this.idActividades != null && !this.idActividades.equals(other.idActividades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.Actividades[ idActividades=" + idActividades + " ]";
    }
    
}
