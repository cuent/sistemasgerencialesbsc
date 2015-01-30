/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.sistemabscfinal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablito
 */
@Entity
@Table(catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptualizar.findAll", query = "SELECT c FROM Conceptualizar c"),
    @NamedQuery(name = "Conceptualizar.findByIdConceptualizar", query = "SELECT c FROM Conceptualizar c WHERE c.idConceptualizar = :idConceptualizar"),
    @NamedQuery(name = "Conceptualizar.findByDefinir", query = "SELECT c FROM Conceptualizar c WHERE c.definir = :definir"),
    @NamedQuery(name = "Conceptualizar.findByAclarar", query = "SELECT c FROM Conceptualizar c WHERE c.aclarar = :aclarar"),
    @NamedQuery(name = "Conceptualizar.findByConceptualizar", query = "SELECT c FROM Conceptualizar c WHERE c.conceptualizar = :conceptualizar"),
    @NamedQuery(name = "Conceptualizar.findByFechaModificacion", query = "SELECT c FROM Conceptualizar c WHERE c.fechaModificacion = :fechaModificacion")})
public class Conceptualizar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONCEPTUALIZAR", nullable = false)
    private Integer idConceptualizar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String definir;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String aclarar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String conceptualizar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivoEstrategico;

    public Conceptualizar() {
    }

    public Conceptualizar(Integer idConceptualizar) {
        this.idConceptualizar = idConceptualizar;
    }

    public Conceptualizar(Integer idConceptualizar, String definir, String aclarar, String conceptualizar, Date fechaModificacion) {
        this.idConceptualizar = idConceptualizar;
        this.definir = definir;
        this.aclarar = aclarar;
        this.conceptualizar = conceptualizar;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdConceptualizar() {
        return idConceptualizar;
    }

    public void setIdConceptualizar(Integer idConceptualizar) {
        this.idConceptualizar = idConceptualizar;
    }

    public String getDefinir() {
        return definir;
    }

    public void setDefinir(String definir) {
        this.definir = definir;
    }

    public String getAclarar() {
        return aclarar;
    }

    public void setAclarar(String aclarar) {
        this.aclarar = aclarar;
    }

    public String getConceptualizar() {
        return conceptualizar;
    }

    public void setConceptualizar(String conceptualizar) {
        this.conceptualizar = conceptualizar;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public ObjetivoEstrategico getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(ObjetivoEstrategico idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConceptualizar != null ? idConceptualizar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conceptualizar)) {
            return false;
        }
        Conceptualizar other = (Conceptualizar) object;
        if ((this.idConceptualizar == null && other.idConceptualizar != null) || (this.idConceptualizar != null && !this.idConceptualizar.equals(other.idConceptualizar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.Conceptualizar[ idConceptualizar=" + idConceptualizar + " ]";
    }
    
}
