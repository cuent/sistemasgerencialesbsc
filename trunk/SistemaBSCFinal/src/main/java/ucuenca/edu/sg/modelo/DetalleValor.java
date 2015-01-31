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
 * @author pablito
 */
@Entity
@Table(name = "detalle_valor", catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleValor.findAll", query = "SELECT d FROM DetalleValor d"),
    @NamedQuery(name = "DetalleValor.findByIdDetalleValor", query = "SELECT d FROM DetalleValor d WHERE d.idDetalleValor = :idDetalleValor"),
    @NamedQuery(name = "DetalleValor.findByValorComponente", query = "SELECT d FROM DetalleValor d WHERE d.valorComponente = :valorComponente")})
public class DetalleValor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_VALOR", nullable = false)
    private Integer idDetalleValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_COMPONENTE", precision = 22)
    private Double valorComponente;
    @JoinColumn(name = "ID_CABECERA_VALOR", referencedColumnName = "ID_CABECERA_VALOR")
    @ManyToOne
    private CabeceraValor idCabeceraValor;
    @JoinColumn(name = "ID_COMPONENTE_FORMULA", referencedColumnName = "ID_COMPONENTE_FORMULA", nullable = false)
    @ManyToOne(optional = false)
    private ComponenteFormula idComponenteFormula;

    public DetalleValor() {
    }

    public DetalleValor(Integer idDetalleValor) {
        this.idDetalleValor = idDetalleValor;
    }

    public Integer getIdDetalleValor() {
        return idDetalleValor;
    }

    public void setIdDetalleValor(Integer idDetalleValor) {
        this.idDetalleValor = idDetalleValor;
    }

    public Double getValorComponente() {
        return valorComponente;
    }

    public void setValorComponente(Double valorComponente) {
        this.valorComponente = valorComponente;
    }

    public CabeceraValor getIdCabeceraValor() {
        return idCabeceraValor;
    }

    public void setIdCabeceraValor(CabeceraValor idCabeceraValor) {
        this.idCabeceraValor = idCabeceraValor;
    }

    public ComponenteFormula getIdComponenteFormula() {
        return idComponenteFormula;
    }

    public void setIdComponenteFormula(ComponenteFormula idComponenteFormula) {
        this.idComponenteFormula = idComponenteFormula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleValor != null ? idDetalleValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleValor)) {
            return false;
        }
        DetalleValor other = (DetalleValor) object;
        if ((this.idDetalleValor == null && other.idDetalleValor != null) || (this.idDetalleValor != null && !this.idDetalleValor.equals(other.idDetalleValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.DetalleValor[ idDetalleValor=" + idDetalleValor + " ]";
    }
    
}
