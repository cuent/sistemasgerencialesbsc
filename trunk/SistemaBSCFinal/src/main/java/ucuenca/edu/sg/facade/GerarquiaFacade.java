/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ucuenca.edu.sg.modelo.Gerarquia;

/**
 *
 * @author mivkys
 */
@Stateless
public class GerarquiaFacade extends AbstractFacade<Gerarquia> {
    @PersistenceContext(unitName = "com.mycompany_SistemaBSCFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GerarquiaFacade() {
        super(Gerarquia.class);
    }
    
}
