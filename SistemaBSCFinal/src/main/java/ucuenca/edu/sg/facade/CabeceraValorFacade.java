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
import ucuenca.edu.sg.modelo.CabeceraValor;
import ucuenca.edu.sg.modelo.Valores;

/**
 *
 * @author mivkys
 */
@Stateless
public class CabeceraValorFacade extends AbstractFacade<CabeceraValor> {

    @PersistenceContext(unitName = "com.mycompany_SistemaBSCFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CabeceraValorFacade() {
        super(CabeceraValor.class);
    }

    public CabeceraValor getValores(Integer idIndicandor) {
        System.out.println(idIndicandor);
        Query query = this.em.createNamedQuery(CabeceraValor.findByUltimoValor);
        query.setParameter("idIndicador", idIndicandor);
        try {
            query.setMaxResults(1);
            return (CabeceraValor) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Error");
            return null;
        }

    }
}
