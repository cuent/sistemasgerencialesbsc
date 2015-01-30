/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ucuenca.edu.sg.sistemabscfinal.CabeceraValor;

/**
 *
 * @author pablito
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
    
}
