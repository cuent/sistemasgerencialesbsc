package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.DetalleValor;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("detalleValorController")
@SessionScoped
public class DetalleValorController extends AbstractController<DetalleValor> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.DetalleValorFacade ejbFacade;

    public DetalleValorController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
