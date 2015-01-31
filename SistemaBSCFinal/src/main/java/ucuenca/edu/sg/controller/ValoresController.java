package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Valores;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("valoresController")
@SessionScoped
public class ValoresController extends AbstractController<Valores> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ValoresFacade ejbFacade;

    public ValoresController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
