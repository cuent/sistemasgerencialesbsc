package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Actividades;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import ucuenca.edu.sg.controller.util.GeneracionReportes;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico_;

@Named("actividadesController")
@SessionScoped
public class ActividadesController extends AbstractController<Actividades> implements Serializable {
    
    @EJB
    private ucuenca.edu.sg.facade.ActividadesFacade ejbFacade;
    @EJB
    private ucuenca.edu.sg.facade.ResponsableActividadFacade ejbResponsableActividadFacade;
     @EJB
    private ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade ejbObjetivoEstrategicoFacade;
    private List<ObjetivoEstrategico> listObjetivo;

    public ActividadesController() {
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListObjetivo(ejbObjetivoEstrategicoFacade.findAll());
        
    }
    
    public void generarReporte() {
        List<Actividades> listaGlobales = getItems();
        GeneracionReportes generarReporte = new GeneracionReportes();
        generarReporte.generarActividades(listaGlobales,getListObjetivo());
    }

    /**
     * @return the listObjetivo
     */
    public List<ObjetivoEstrategico> getListObjetivo() {
        return listObjetivo;
    }

    /**
     * @param listObjetivo the listObjetivo to set
     */
    public void setListObjetivo(List<ObjetivoEstrategico> listObjetivo) {
        this.listObjetivo = listObjetivo;
    }
    
     public void iniciarNuevo(){
        this.setSelected(new Actividades());
        
    }
     
    public void guadarActividad(){
        this.create();
       
    
    }
}
