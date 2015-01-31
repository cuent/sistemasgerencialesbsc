package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.CabeceraValor;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("cabeceraValorController")
@SessionScoped
public class CabeceraValorController  extends AbstractController<CabeceraValor> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.CabeceraValorFacade ejbFacade;

    public CabeceraValorController() {
    }
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
