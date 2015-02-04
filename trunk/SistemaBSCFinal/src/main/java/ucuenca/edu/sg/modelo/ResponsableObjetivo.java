/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "responsable_objetivo", catalog = "balanced_scorecard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsableObjetivo.findAll", query = "SELECT r FROM ResponsableObjetivo r"),
    @NamedQuery(name = "ResponsableObjetivo.findByIdResponsable", query = "SELECT r FROM ResponsableObjetivo r WHERE r.responsableObjetivoPK.idResponsable = :idResponsable"),
    @NamedQuery(name = "ResponsableObjetivo.findByIdObjetivoEstrategico", query = "SELECT r.usuario FROM ResponsableObjetivo r WHERE r.responsableObjetivoPK.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "ResponsableObjetivo.findByIdUsuario", query = "SELECT r FROM ResponsableObjetivo r WHERE r.responsableObjetivoPK.idUsuario = :idUsuario"),
@NamedQuery(name = "ResponsableObjetivo.findByIdResponsableidObjetivoEstrategico", query = "SELECT r FROM ResponsableObjetivo r WHERE r.responsableObjetivoPK.idResponsable = :idResponsable AND R.responsableObjetivoPK.idObjetivoEstrategico = :idObjetivoEstrategico")})
public class ResponsableObjetivo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByIdResponsableidObjetivoEstrategico ="ResponsableObjetivo.findByIdResponsableidObjetivoEstrategico";
    public static final String findByIdObjetivoEstrategico ="ResponsableObjetivo.findByIdObjetivoEstrategico";
    @EmbeddedId
    protected ResponsableObjetivoPK responsableObjetivoPK;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico objetivoEstrategico;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public ResponsableObjetivo() {
    }

    public ResponsableObjetivo(ResponsableObjetivoPK responsableObjetivoPK) {
        this.responsableObjetivoPK = responsableObjetivoPK;
    }

    public ResponsableObjetivo(int idResponsable, int idObjetivoEstrategico, int idUsuario) {
        this.responsableObjetivoPK = new ResponsableObjetivoPK(idResponsable, idObjetivoEstrategico, idUsuario);
    }

    public ResponsableObjetivoPK getResponsableObjetivoPK() {
        return responsableObjetivoPK;
    }

    public void setResponsableObjetivoPK(ResponsableObjetivoPK responsableObjetivoPK) {
        this.responsableObjetivoPK = responsableObjetivoPK;
    }

    public ObjetivoEstrategico getObjetivoEstrategico() {
        return objetivoEstrategico;
    }

    public void setObjetivoEstrategico(ObjetivoEstrategico objetivoEstrategico) {
        this.objetivoEstrategico = objetivoEstrategico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsableObjetivoPK != null ? responsableObjetivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsableObjetivo)) {
            return false;
        }
        ResponsableObjetivo other = (ResponsableObjetivo) object;
        if ((this.responsableObjetivoPK == null && other.responsableObjetivoPK != null) || (this.responsableObjetivoPK != null && !this.responsableObjetivoPK.equals(other.responsableObjetivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucuenca.edu.sg.modelo.ResponsableObjetivo[ responsableObjetivoPK=" + responsableObjetivoPK + " ]";
    }
    
}
