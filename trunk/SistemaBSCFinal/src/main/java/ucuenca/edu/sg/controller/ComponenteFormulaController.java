package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ComponenteFormula;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("componenteFormulaController")
@SessionScoped
public class ComponenteFormulaController extends AbstractController<ComponenteFormula> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ComponenteFormulaFacade ejbFacade;

    public ComponenteFormulaController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
