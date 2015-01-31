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
    @NamedQuery(name = "Semaforo.findAll", query = "SELECT s FROM Semaforo s"),
    @NamedQuery(name = "Semaforo.findByIdIndicador", query = "SELECT s FROM Semaforo s WHERE s.idIndicador = :idIndicador"),
    @NamedQuery(name = "Semaforo.findByDescripcion", query = "SELECT s FROM Semaforo s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Semaforo.findByValorActual", query = "SELECT s FROM Semaforo s WHERE s.valorActual = :valorActual")})
public class Semaforo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INDICADOR", nullable = false)
    private Integer idIndicador;
    @Size(max = 128)
    @Column(length = 128)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ACTUAL", precision = 22)
    private Double valorActual;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivoEstrategico;

    public Semaforo() {
    }

    public Semaforo(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
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
        hash += (idIndicador != null ? idIndicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semaforo)) {
            return false;
        }
        Semaforo other = (Semaforo) object;
        if ((this.idIndicador == null && other.idIndicador != null) || (this.idIndicador != null && !this.idIndicador.equals(other.idIndicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.sistemabscfinal.Semaforo[ idIndicador=" + idIndicador + " ]";
    }
    
}
