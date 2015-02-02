
package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.controller.util.MatrizBSC;
import ucuenca.edu.sg.modelo.Actividades;
import ucuenca.edu.sg.modelo.Conceptualizar;
import ucuenca.edu.sg.modelo.Indicador;
import ucuenca.edu.sg.modelo.Meta;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named("matrizBSCController")
@SessionScoped
public class MatrizBSCController extends AbstractController<ObjetivoEstrategico> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade ejbFacade;
    @EJB
    private ucuenca.edu.sg.facade.PerspectivaFacade ejbPerspectivaFacade;
    @EJB
    private ucuenca.edu.sg.facade.ActividadesFacade ejbActividadesFacade;
    @EJB
    private ucuenca.edu.sg.facade.ConceptualizarFacade ejbConceptualizarFacade;
    @EJB
    private ucuenca.edu.sg.facade.EstrategiaGlobalFacade ejbEstrategiaGlobalFacade;
    @EJB
    private ucuenca.edu.sg.facade.IndicadorFacade ejbIndicadorFacade;
    @EJB
    private ucuenca.edu.sg.facade.MetaFacade ejbMetaFacade;
    @EJB
    private ucuenca.edu.sg.facade.ResponsableObjetivoFacade ejbResponsableFacade;

    private List<Conceptualizar> itemsConceptualizar;
    private List<Meta> itemsMeta;
    private List<Indicador> itemsIndicador;
    private List<Actividades> itemsActividades;

    public MatrizBSCController() {
        super(ObjetivoEstrategico.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        itemsConceptualizar = new ArrayList<>();
        itemsMeta = new ArrayList<>();
        itemsIndicador = new ArrayList<>();
        itemsActividades = new ArrayList<>();
    }


    public String estadoActual(Object li, Object ls, Object act) {
        if (li != null && ls != null && act != null) {
            int inferior = Integer.parseInt(li.toString());
            int superior = Integer.parseInt(ls.toString());
            int actual = Integer.parseInt(act.toString());
            System.out.println("sipER"+ inferior + " "+superior+" "+ actual);
            if (actual < inferior) {
                return "../resources/image/rojo.gif";
            } else {
                if (actual > superior) {
                    return "../resources/image/verde.gif";
                } else {
                    return "../resources/image/amarillo.gif";
                }
            }
        }
        return "../resources/image/rojo.gif";
    }

     
    public List<Conceptualizar> getItemsConceptualizar() {
        return itemsConceptualizar;
    }

    public void setItemsConceptualizar(List<Conceptualizar> itemsConceptualizar) {
        this.itemsConceptualizar = itemsConceptualizar;
    }
 
    public List<Meta> getItemsMeta() {
        return itemsMeta;
    }

    public void setItemsMeta(List<Meta> itemsMeta) {
        this.itemsMeta = itemsMeta;
    }

    public List<Indicador> getItemsIndicador() {
        return itemsIndicador;
    }

    public void setItemsIndicador(List<Indicador> itemsIndicador) {
        this.itemsIndicador = itemsIndicador;
    }

    public List<Actividades> getItemsActividades() {
        return itemsActividades;
    }

    public void setItemsActividades(List<Actividades> itemsActividades) {
        this.itemsActividades = itemsActividades;
    }

}
