/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mivkys
 */
@Entity
@Table(catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gerarquia.findAll", query = "SELECT g FROM Gerarquia g"),
    @NamedQuery(name = "Gerarquia.findByIdGerarquia", query = "SELECT g FROM Gerarquia g WHERE g.idGerarquia = :idGerarquia")})
public class Gerarquia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GERARQUIA", nullable = false)
    private Integer idGerarquia;
    @JoinColumn(name = "PREDECESORA", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO")
    @ManyToOne
    private ObjetivoEstrategico predecesora;
    @JoinColumn(name = "SUCESORA", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO")
    @ManyToOne
    private ObjetivoEstrategico sucesora;

    public Gerarquia() {
    }

    public Gerarquia(Integer idGerarquia) {
        this.idGerarquia = idGerarquia;
    }

    public Integer getIdGerarquia() {
        return idGerarquia;
    }

    public void setIdGerarquia(Integer idGerarquia) {
        this.idGerarquia = idGerarquia;
    }

    public ObjetivoEstrategico getPredecesora() {
        return predecesora;
    }

    public void setPredecesora(ObjetivoEstrategico predecesora) {
        this.predecesora = predecesora;
    }

    public ObjetivoEstrategico getSucesora() {
        return sucesora;
    }

    public void setSucesora(ObjetivoEstrategico sucesora) {
        this.sucesora = sucesora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGerarquia != null ? idGerarquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gerarquia)) {
            return false;
        }
        Gerarquia other = (Gerarquia) object;
        if ((this.idGerarquia == null && other.idGerarquia != null) || (this.idGerarquia != null && !this.idGerarquia.equals(other.idGerarquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.modelo.Gerarquia[ idGerarquia=" + idGerarquia + " ]";
    }
    
}
