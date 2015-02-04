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
import ucuenca.edu.sg.modelo.ComponenteFormula;

/**
 *
 * @author mivkys
 */
@Stateless
public class ComponenteFormulaFacade extends AbstractFacade<ComponenteFormula> {

    @PersistenceContext(unitName = "com.mycompany_SistemaBSCFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComponenteFormulaFacade() {
        super(ComponenteFormula.class);
    }

    public List<ComponenteFormula> getitemsComponenteFormula(Integer idIndicador) {
        Query query = this.em.createNamedQuery(ComponenteFormula.findByIndicador);
        query.setParameter("idIndicador", idIndicador);
        return query.getResultList();
    }
}
