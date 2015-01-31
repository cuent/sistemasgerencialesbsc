/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Perspectiva.findAll", query = "SELECT p FROM Perspectiva p"),
    @NamedQuery(name = "Perspectiva.findByIdPerspectiva", query = "SELECT p FROM Perspectiva p WHERE p.idPerspectiva = :idPerspectiva"),
    @NamedQuery(name = "Perspectiva.findByPerspectiva", query = "SELECT p FROM Perspectiva p WHERE p.perspectiva = :perspectiva")})
public class Perspectiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERSPECTIVA", nullable = false)
    private Integer idPerspectiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String perspectiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerspectiva")
    private Set<ObjetivoEstrategico> objetivoEstrategicoSet;

    public Perspectiva() {
    }

    public Perspectiva(Integer idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    public Perspectiva(Integer idPerspectiva, String perspectiva) {
        this.idPerspectiva = idPerspectiva;
        this.perspectiva = perspectiva;
    }

    public Integer getIdPerspectiva() {
        return idPerspectiva;
    }

    public void setIdPerspectiva(Integer idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    public String getPerspectiva() {
        return perspectiva;
    }

    public void setPerspectiva(String perspectiva) {
        this.perspectiva = perspectiva;
    }

    @XmlTransient
    public Set<ObjetivoEstrategico> getObjetivoEstrategicoSet() {
        return objetivoEstrategicoSet;
    }

    public void setObjetivoEstrategicoSet(Set<ObjetivoEstrategico> objetivoEstrategicoSet) {
        this.objetivoEstrategicoSet = objetivoEstrategicoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerspectiva != null ? idPerspectiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perspectiva)) {
            return false;
        }
        Perspectiva other = (Perspectiva) object;
        if ((this.idPerspectiva == null && other.idPerspectiva != null) || (this.idPerspectiva != null && !this.idPerspectiva.equals(other.idPerspectiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.Perspectiva[ idPerspectiva=" + idPerspectiva + " ]";
    }
    
}
