/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ucuenca.edu.sg.modelo.Indicador;

/**
 *
 * @author mivkys
 */
@Stateless
public class IndicadorFacade extends AbstractFacade<Indicador> {

    @PersistenceContext(unitName = "com.mycompany_SistemaBSCFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicadorFacade() {
        super(Indicador.class);
    }

    public List<Indicador> getitemsIndicador(Integer idObjetivoEstrategico) {
        Query query = this.em.createNamedQuery(Indicador.findByidObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico", idObjetivoEstrategico);
        return query.getResultList();
    }
    
}
