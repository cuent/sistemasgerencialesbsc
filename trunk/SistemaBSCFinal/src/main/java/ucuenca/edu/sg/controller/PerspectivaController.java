package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Perspectiva;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("perspectivaController")
@SessionScoped
public class PerspectivaController extends AbstractController<Perspectiva> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.PerspectivaFacade ejbFacade;

    public PerspectivaController() {
    }

    @PostConstruct
    public void init() {        
        super.setFacade(ejbFacade);
        this.setSelected(new Perspectiva());
    }
    
    public void iniciarNuevo(){
        this.setSelected(new Perspectiva());
        
    }
      @Override
    public List<Perspectiva> getItems() {
        return super.getItems(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
