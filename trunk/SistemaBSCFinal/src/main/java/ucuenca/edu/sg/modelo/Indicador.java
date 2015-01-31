/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mivkys
 */
@Entity
@Table(catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i"),
    @NamedQuery(name = "Indicador.findByIdIndicador", query = "SELECT i FROM Indicador i WHERE i.idIndicador = :idIndicador"),
    @NamedQuery(name = "Indicador.findByAclaracion", query = "SELECT i FROM Indicador i WHERE i.aclaracion = :aclaracion"),
    @NamedQuery(name = "Indicador.findByConceptualizacion", query = "SELECT i FROM Indicador i WHERE i.conceptualizacion = :conceptualizacion"),
    @NamedQuery(name = "Indicador.findByNombreIndicador", query = "SELECT i FROM Indicador i WHERE i.nombreIndicador = :nombreIndicador"),
    @NamedQuery(name = "Indicador.findByUnidades", query = "SELECT i FROM Indicador i WHERE i.unidades = :unidades"),
    @NamedQuery(name = "Indicador.findByFormula", query = "SELECT i FROM Indicador i WHERE i.formula = :formula"),
    @NamedQuery(name = "Indicador.findByPeriodicidad", query = "SELECT i FROM Indicador i WHERE i.periodicidad = :periodicidad"),
    @NamedQuery(name = "Indicador.findByEstadoActual", query = "SELECT i FROM Indicador i WHERE i.estadoActual = :estadoActual"),
    @NamedQuery(name = "Indicador.findByBajoMinimo", query = "SELECT i FROM Indicador i WHERE i.bajoMinimo = :bajoMinimo"),
    @NamedQuery(name = "Indicador.findByBajoMaximo", query = "SELECT i FROM Indicador i WHERE i.bajoMaximo = :bajoMaximo"),
    @NamedQuery(name = "Indicador.findByMedioMinimo", query = "SELECT i FROM Indicador i WHERE i.medioMinimo = :medioMinimo"),
    @NamedQuery(name = "Indicador.findByMedioMaximo", query = "SELECT i FROM Indicador i WHERE i.medioMaximo = :medioMaximo"),
    @NamedQuery(name = "Indicador.findByAltoMinimo", query = "SELECT i FROM Indicador i WHERE i.altoMinimo = :altoMinimo"),
    @NamedQuery(name = "Indicador.findByAltoMaximo", query = "SELECT i FROM Indicador i WHERE i.altoMaximo = :altoMaximo")})
public class Indicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INDICADOR", nullable = false)
    private Integer idIndicador;
    @Size(max = 128)
    @Column(length = 128)
    private String aclaracion;
    @Size(max = 128)
    @Column(length = 128)
    private String conceptualizacion;
    @Size(max = 128)
    @Column(name = "NOMBRE_INDICADOR", length = 128)
    private String nombreIndicador;
    @Size(max = 64)
    @Column(length = 64)
    private String unidades;
    @Size(max = 256)
    @Column(length = 256)
    private String formula;
    @Size(max = 64)
    @Column(length = 64)
    private String periodicidad;
    @Size(max = 128)
    @Column(name = "ESTADO_ACTUAL", length = 128)
    private String estadoActual;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BAJO_MINIMO", precision = 22)
    private Double bajoMinimo;
    @Column(name = "BAJO_MAXIMO", precision = 22)
    private Double bajoMaximo;
    @Column(name = "MEDIO_MINIMO", precision = 22)
    private Double medioMinimo;
    @Column(name = "MEDIO_MAXIMO", precision = 22)
    private Double medioMaximo;
    @Column(name = "ALTO_MINIMO", precision = 22)
    private Double altoMinimo;
    @Column(name = "ALTO_MAXIMO", precision = 22)
    private Double altoMaximo;
    @OneToMany(mappedBy = "idIndicador")
    private List<CabeceraValor> cabeceraValorList;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivoEstrategico;
    @OneToMany(mappedBy = "idIndicador")
    private List<Valores> valoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndicador")
    private List<ComponenteFormula> componenteFormulaList;

    public Indicador() {
    }

    public Indicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public String getConceptualizacion() {
        return conceptualizacion;
    }

    public void setConceptualizacion(String conceptualizacion) {
        this.conceptualizacion = conceptualizacion;
    }

    public String getNombreIndicador() {
        return nombreIndicador;
    }

    public void setNombreIndicador(String nombreIndicador) {
        this.nombreIndicador = nombreIndicador;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Double getBajoMinimo() {
        return bajoMinimo;
    }

    public void setBajoMinimo(Double bajoMinimo) {
        this.bajoMinimo = bajoMinimo;
    }

    public Double getBajoMaximo() {
        return bajoMaximo;
    }

    public void setBajoMaximo(Double bajoMaximo) {
        this.bajoMaximo = bajoMaximo;
    }

    public Double getMedioMinimo() {
        return medioMinimo;
    }

    public void setMedioMinimo(Double medioMinimo) {
        this.medioMinimo = medioMinimo;
    }

    public Double getMedioMaximo() {
        return medioMaximo;
    }

    public void setMedioMaximo(Double medioMaximo) {
        this.medioMaximo = medioMaximo;
    }

    public Double getAltoMinimo() {
        return altoMinimo;
    }

    public void setAltoMinimo(Double altoMinimo) {
        this.altoMinimo = altoMinimo;
    }

    public Double getAltoMaximo() {
        return altoMaximo;
    }

    public void setAltoMaximo(Double altoMaximo) {
        this.altoMaximo = altoMaximo;
    }

    @XmlTransient
    public List<CabeceraValor> getCabeceraValorList() {
        return cabeceraValorList;
    }

    public void setCabeceraValorList(List<CabeceraValor> cabeceraValorList) {
        this.cabeceraValorList = cabeceraValorList;
    }

    public ObjetivoEstrategico getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(ObjetivoEstrategico idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    @XmlTransient
    public List<Valores> getValoresList() {
        return valoresList;
    }

    public void setValoresList(List<Valores> valoresList) {
        this.valoresList = valoresList;
    }

    @XmlTransient
    public List<ComponenteFormula> getComponenteFormulaList() {
        return componenteFormulaList;
    }

    public void setComponenteFormulaList(List<ComponenteFormula> componenteFormulaList) {
        this.componenteFormulaList = componenteFormulaList;
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
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.idIndicador == null && other.idIndicador != null) || (this.idIndicador != null && !this.idIndicador.equals(other.idIndicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.modelo.Indicador[ idIndicador=" + idIndicador + " ]";
    }
    
}
