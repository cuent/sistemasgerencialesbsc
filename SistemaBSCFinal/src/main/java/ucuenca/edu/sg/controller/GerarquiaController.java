package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Gerarquia;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("gerarquiaController")
@SessionScoped
public class GerarquiaController extends AbstractController<Gerarquia> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.GerarquiaFacade ejbFacade;

    public GerarquiaController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
