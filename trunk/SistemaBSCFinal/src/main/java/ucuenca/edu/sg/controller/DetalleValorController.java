package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.DetalleValor;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import ucuenca.edu.sg.facade.DetalleValorFacade;
import ucuenca.edu.sg.modelo.CabeceraValor;
import ucuenca.edu.sg.modelo.ComponenteFormula;

@Named("detalleValorController")
@SessionScoped
public class DetalleValorController extends AbstractController<DetalleValor> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.DetalleValorFacade ejbFacade;

    public DetalleValorController() {
    }

    public DetalleValorFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DetalleValorFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    public void iniciar() {
        this.setSelected(new DetalleValor());
    }

    @Override
    public void create() {

        super.create(); //To change body of generated methods, choose Tools | Templates.
    }

    public void inicializar(CabeceraValor cabeceraValor, ComponenteFormula componenteFormula, Double valorCopmponente) {

        this.setSelected(new DetalleValor());
        this.getSelected().setIdCabeceraValor(cabeceraValor);
        this.getSelected().setIdComponenteFormula(componenteFormula);
        this.getSelected().setValorComponente(valorCopmponente);
    }
}
