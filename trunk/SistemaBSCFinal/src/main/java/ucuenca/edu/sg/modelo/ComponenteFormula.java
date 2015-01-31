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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pablito
 */
@Entity
@Table(name = "componente_formula", catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteFormula.findAll", query = "SELECT c FROM ComponenteFormula c"),
    @NamedQuery(name = "ComponenteFormula.findByIdComponenteFormula", query = "SELECT c FROM ComponenteFormula c WHERE c.idComponenteFormula = :idComponenteFormula"),
    @NamedQuery(name = "ComponenteFormula.findByFormula", query = "SELECT c FROM ComponenteFormula c WHERE c.formula = :formula"),
    @NamedQuery(name = "ComponenteFormula.findByUnidad", query = "SELECT c FROM ComponenteFormula c WHERE c.unidad = :unidad")})
public class ComponenteFormula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMPONENTE_FORMULA", nullable = false)
    private Integer idComponenteFormula;
    @Size(max = 128)
    @Column(length = 128)
    private String formula;
    @Size(max = 64)
    @Column(length = 64)
    private String unidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComponenteFormula")
    private Set<DetalleValor> detalleValorSet;
    @JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR", nullable = false)
    @ManyToOne(optional = false)
    private Indicador idIndicador;

    public ComponenteFormula() {
    }

    public ComponenteFormula(Integer idComponenteFormula) {
        this.idComponenteFormula = idComponenteFormula;
    }

    public Integer getIdComponenteFormula() {
        return idComponenteFormula;
    }

    public void setIdComponenteFormula(Integer idComponenteFormula) {
        this.idComponenteFormula = idComponenteFormula;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @XmlTransient
    public Set<DetalleValor> getDetalleValorSet() {
        return detalleValorSet;
    }

    public void setDetalleValorSet(Set<DetalleValor> detalleValorSet) {
        this.detalleValorSet = detalleValorSet;
    }

    public Indicador getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Indicador idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComponenteFormula != null ? idComponenteFormula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponenteFormula)) {
            return false;
        }
        ComponenteFormula other = (ComponenteFormula) object;
        if ((this.idComponenteFormula == null && other.idComponenteFormula != null) || (this.idComponenteFormula != null && !this.idComponenteFormula.equals(other.idComponenteFormula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.ComponenteFormula[ idComponenteFormula=" + idComponenteFormula + " ]";
    }
    
}
