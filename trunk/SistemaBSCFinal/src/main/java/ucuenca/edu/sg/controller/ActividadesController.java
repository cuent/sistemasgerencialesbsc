package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Actividades;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("actividadesController")
@SessionScoped
public class ActividadesController extends AbstractController<Actividades> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ActividadesFacade ejbFacade;
    @EJB
    private ucuenca.edu.sg.facade.ResponsableActividadFacade ejbResponsableActividadFacade;

    public ActividadesController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    
     public void iniciarNuevo(){
        this.setSelected(new Actividades());
        
    }
     
    public void guadarActividad(){
        this.create();
       
    
    }
}
