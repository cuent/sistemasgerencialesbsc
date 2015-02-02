/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
 * @author mivkys
 */
@Entity
@Table(name = "objetivo_estrategico", catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoEstrategico.findAll", query = "SELECT o FROM ObjetivoEstrategico o"),
    @NamedQuery(name = "ObjetivoEstrategico.findByIdObjetivoEstrategico", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjetivo", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objetivo = :objetivo"),
    @NamedQuery(name = "ObjetivoEstrategico.findByFechaAlcance", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.fechaAlcance = :fechaAlcance"),
    @NamedQuery(name = "ObjetivoEstrategico.findByFechaModificacion", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.fechaModificacion = :fechaModificacion")})
public class ObjetivoEstrategico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    private Integer idObjetivoEstrategico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String objetivo;
    @Column(name = "FECHA_ALCANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlcance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoEstrategico")
    private List<ResponsableObjetivo> responsableObjetivoList;
    @OneToMany(mappedBy = "predecesora")
    private List<Gerarquia> gerarquiaList;
    @OneToMany(mappedBy = "sucesora")
    private List<Gerarquia> gerarquiaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private List<Conceptualizar> conceptualizarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private List<Actividades> actividadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private List<Semaforo> semaforoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private List<Indicador> indicadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private List<Meta> metaList;
    @JoinColumn(name = "ID_PERSPECTIVA", referencedColumnName = "ID_PERSPECTIVA", nullable = false)
    @ManyToOne(optional = false)
    private Perspectiva idPerspectiva;

    public ObjetivoEstrategico() {
    }

    public ObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public ObjetivoEstrategico(Integer idObjetivoEstrategico, String objetivo, Date fechaModificacion) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
        this.objetivo = objetivo;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Date getFechaAlcance() {
        return fechaAlcance;
    }

    public void setFechaAlcance(Date fechaAlcance) {
        this.fechaAlcance = fechaAlcance;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public List<ResponsableObjetivo> getResponsableObjetivoList() {
        return responsableObjetivoList;
    }

    public void setResponsableObjetivoList(List<ResponsableObjetivo> responsableObjetivoList) {
        this.responsableObjetivoList = responsableObjetivoList;
    }

    @XmlTransient
    public List<Gerarquia> getGerarquiaList() {
        return gerarquiaList;
    }

    public void setGerarquiaList(List<Gerarquia> gerarquiaList) {
        this.gerarquiaList = gerarquiaList;
    }

    @XmlTransient
    public List<Gerarquia> getGerarquiaList1() {
        return gerarquiaList1;
    }

    public void setGerarquiaList1(List<Gerarquia> gerarquiaList1) {
        this.gerarquiaList1 = gerarquiaList1;
    }

    @XmlTransient
    public List<Conceptualizar> getConceptualizarList() {
        return conceptualizarList;
    }

    public void setConceptualizarList(List<Conceptualizar> conceptualizarList) {
        this.conceptualizarList = conceptualizarList;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @XmlTransient
    public List<Semaforo> getSemaforoList() {
        return semaforoList;
    }

    public void setSemaforoList(List<Semaforo> semaforoList) {
        this.semaforoList = semaforoList;
    }

    @XmlTransient
    public List<Indicador> getIndicadorList() {
        return indicadorList;
    }

    public void setIndicadorList(List<Indicador> indicadorList) {
        this.indicadorList = indicadorList;
        
    }

    @XmlTransient
    public List<Meta> getMetaList() {
        return metaList;
    }

    public void setMetaList(List<Meta> metaList) {
        this.metaList = metaList;
    }

    public Perspectiva getIdPerspectiva() {
        return idPerspectiva;
    }

    public void setIdPerspectiva(Perspectiva idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivoEstrategico != null ? idObjetivoEstrategico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoEstrategico)) {
            return false;
        }
        ObjetivoEstrategico other = (ObjetivoEstrategico) object;
        if ((this.idObjetivoEstrategico == null && other.idObjetivoEstrategico != null) || (this.idObjetivoEstrategico != null && !this.idObjetivoEstrategico.equals(other.idObjetivoEstrategico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.modelo.ObjetivoEstrategico[ idObjetivoEstrategico=" + idObjetivoEstrategico + " ]";
    }
    
}
