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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablito
 */
@Entity
@Table(catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valores.findAll", query = "SELECT v FROM Valores v"),
    @NamedQuery(name = "Valores.findByIdValores", query = "SELECT v FROM Valores v WHERE v.idValores = :idValores"),
    @NamedQuery(name = "Valores.findByFecha", query = "SELECT v FROM Valores v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Valores.findByValorTotal", query = "SELECT v FROM Valores v WHERE v.valorTotal = :valorTotal")})
public class Valores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VALORES", nullable = false)
    private Integer idValores;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_TOTAL", precision = 22)
    private Double valorTotal;
    @JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
    @ManyToOne
    private Indicador idIndicador;

    public Valores() {
    }

    public Valores(Integer idValores) {
        this.idValores = idValores;
    }

    public Integer getIdValores() {
        return idValores;
    }

    public void setIdValores(Integer idValores) {
        this.idValores = idValores;
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

    public Indicador getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Indicador idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValores != null ? idValores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valores)) {
            return false;
        }
        Valores other = (Valores) object;
        if ((this.idValores == null && other.idValores != null) || (this.idValores != null && !this.idValores.equals(other.idValores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.Valores[ idValores=" + idValores + " ]";
    }
    
}
