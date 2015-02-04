/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;

/**
 *
 * @author mivkys
 */
@Stateless
public class ObjetivoEstrategicoFacade extends AbstractFacade<ObjetivoEstrategico> {
    @PersistenceContext(unitName = "com.mycompany_SistemaBSCFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoEstrategicoFacade() {
        super(ObjetivoEstrategico.class);
    }
     public ObjetivoEstrategico getObjetivoEstrategico(Integer idObjetivoEstrategico) {
        Query query = this.em.createNamedQuery(ObjetivoEstrategico.findByIdObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico", idObjetivoEstrategico);
        try{
            return (ObjetivoEstrategico)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
     public ObjetivoEstrategico getObjetivoEstrategico(String objetivo) {
        Query query = this.em.createNamedQuery(ObjetivoEstrategico.findByObjetivo);
        query.setParameter("objetivo", objetivo);
        try{
            return (ObjetivoEstrategico)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

}
