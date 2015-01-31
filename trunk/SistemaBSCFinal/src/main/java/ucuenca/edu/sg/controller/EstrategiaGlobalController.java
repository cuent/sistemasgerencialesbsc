package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.EstrategiaGlobal;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("estrategiaGlobalController")
@SessionScoped
public class EstrategiaGlobalController extends AbstractController<EstrategiaGlobal> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.EstrategiaGlobalFacade ejbFacade;

    public EstrategiaGlobalController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    
    public void iniciarNuevo(){
        this.setSelected(new EstrategiaGlobal());
        
    }
    
}
