package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Indicador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("indicadorController")
@SessionScoped
public class IndicadorController extends AbstractController<Indicador> implements Serializable{

    @EJB
    private ucuenca.edu.sg.facade.IndicadorFacade ejbFacade;
    
    private List<String> estructuraFormula; 
    public IndicadorController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        estructuraFormula =new ArrayList<>();
    }

    /**
     * @return the estructuraFormula
     */
    public List<String> getEstructuraFormula() {
        return estructuraFormula;
    }

    /**
     * @param estructuraFormula the estructuraFormula to set
     */
    public void setEstructuraFormula(List<String> estructuraFormula) {
        this.estructuraFormula = estructuraFormula;
    }
    
}
