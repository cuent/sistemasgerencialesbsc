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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mivkys
 */
@Entity
@Table(name = "cabecera_valor", catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CabeceraValor.findAll", query = "SELECT c FROM CabeceraValor c"),
    @NamedQuery(name = "CabeceraValor.findByIdCabeceraValor", query = "SELECT c FROM CabeceraValor c WHERE c.idCabeceraValor = :idCabeceraValor"),
    @NamedQuery(name = "CabeceraValor.findByFecha", query = "SELECT c FROM CabeceraValor c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CabeceraValor.findByValorTotal", query = "SELECT c FROM CabeceraValor c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "CabeceraValor.findByUltimoValor", query = "SELECT c FROM CabeceraValor c WHERE c.idIndicador.idIndicador = :idIndicador ORDER BY c.fecha DESC")})
public class CabeceraValor implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUltimoValor = "CabeceraValor.findByUltimoValor";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CABECERA_VALOR", nullable = false)
    private Integer idCabeceraValor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_TOTAL", precision = 22)
    private Double valorTotal;
    @OneToMany(mappedBy = "idCabeceraValor")
    private List<DetalleValor> detalleValorList;
    @JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
    @ManyToOne
    private Indicador idIndicador;

    public CabeceraValor() {
    }

    public CabeceraValor(Integer idCabeceraValor) {
        this.idCabeceraValor = idCabeceraValor;
    }

    public Integer getIdCabeceraValor() {
        return idCabeceraValor;
    }

    public void setIdCabeceraValor(Integer idCabeceraValor) {
        this.idCabeceraValor = idCabeceraValor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @XmlTransient
    public List<DetalleValor> getDetalleValorList() {
        return detalleValorList;
    }

    public void setDetalleValorList(List<DetalleValor> detalleValorList) {
        this.detalleValorList = detalleValorList;
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
        hash += (idCabeceraValor != null ? idCabeceraValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabeceraValor)) {
            return false;
        }
        CabeceraValor other = (CabeceraValor) object;
        if ((this.idCabeceraValor == null && other.idCabeceraValor != null) || (this.idCabeceraValor != null && !this.idCabeceraValor.equals(other.idCabeceraValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.modelo.CabeceraValor[ idCabeceraValor=" + idCabeceraValor + " ]";
    }
    
}
