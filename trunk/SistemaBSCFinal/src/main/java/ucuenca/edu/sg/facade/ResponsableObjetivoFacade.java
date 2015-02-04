/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ucuenca.edu.sg.modelo.ResponsableObjetivo;
import ucuenca.edu.sg.modelo.Usuario;

/**
 *
 * @author mivkys
 */
@Stateless
public class ResponsableObjetivoFacade extends AbstractFacade<ResponsableObjetivo> {
    @PersistenceContext(unitName = "com.mycompany_SistemaBSCFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResponsableObjetivoFacade() {
        super(ResponsableObjetivo.class);
    }
    public ResponsableObjetivo getObjetivoEstrategicoResponsable(Integer idResponsable, Integer idObjetivoEstrategico) {
        Query query = this.em.createNamedQuery(ResponsableObjetivo.findByIdResponsableidObjetivoEstrategico);
        query.setParameter("idResponsable", idResponsable);
        query.setParameter("idObjetivoEstrategico", idObjetivoEstrategico);
        try{
            return (ResponsableObjetivo)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    public List<Usuario> getitemsObjetivoResponsable(Integer idObjetivoEstrategico) {
        Query query = this.em.createNamedQuery(ResponsableObjetivo.findByIdObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico", idObjetivoEstrategico);
            return query.getResultList();
    }
}
