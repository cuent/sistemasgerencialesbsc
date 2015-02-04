package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.controller.util.MatrizBSC;
import ucuenca.edu.sg.modelo.Actividades;
import ucuenca.edu.sg.modelo.Conceptualizar;
import ucuenca.edu.sg.modelo.Indicador;
import ucuenca.edu.sg.modelo.Meta;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ucuenca.edu.sg.modelo.CabeceraValor;

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
    @EJB
    private ucuenca.edu.sg.facade.CabeceraValorFacade ejbCabezeraValorFacade;

    private List<Conceptualizar> itemsConceptualizar;
    private List<Meta> itemsMeta;
    private List<Indicador> itemsIndicador;
    private List<Actividades> itemsActividades;
    private Double valorActual;
    private Double desempeno;

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

    public String cargarValor(ObjetivoEstrategico obje) {        
        Indicador identi = getUitimoIndicador(obje.getIndicadorList());
        //System.out.println(identi.getNombreIndicador());
        if (identi != null) {
         
            CabeceraValor cabezeraValor = ejbCabezeraValorFacade.getValores(identi.getIdIndicador());
            if (cabezeraValor != null) {
                return String.valueOf(cabezeraValor.getValorTotal());
            } else {
                return "No hay Valor";
            }
        } else {
            return "No hay valor";
        }          

    }
    
    public String desempenoValor(ObjetivoEstrategico obje) {        
        Indicador identi = getUitimoIndicador(obje.getIndicadorList());
       // System.out.println(identi.getNombreIndicador());
        if (identi != null) {
         
            CabeceraValor cabezeraValor = ejbCabezeraValorFacade.getValores(identi.getIdIndicador());
            if (cabezeraValor != null) {
                double base = obje.getIndicadorList().get(0).getMedioMinimo();
                double meta = obje.getIndicadorList().get(0).getAltoMinimo();
                double desem = (cabezeraValor.getValorTotal()-base)/(meta-base);
                return String.valueOf(desem);
            } else {
                
                return "No hay Valor";
            }
        } else {
            return "No hay valor";
        }          

    }

    public Indicador getUitimoIndicador(List<Indicador> identificadores) {
        Collections.sort(identificadores, new Comparator<Indicador>() {

            @Override
            public int compare(Indicador o1, Indicador o2) {
                return new Integer(o2.getIdIndicador()).compareTo(new Integer(o1.getIdIndicador()));
            }
        });

        if (identificadores != null && identificadores.size() > 0) {
            return identificadores.get(0);
        }
        return null;
    }

    public String estadoActual(Object li, Object ls, Object act) {
        if (li != null && ls != null && act != null) {
            int inferior = Integer.parseInt(li.toString());
            int superior = Integer.parseInt(ls.toString());
            int actual = Integer.parseInt(act.toString());
            System.out.println("sipER" + inferior + " " + superior + " " + actual);
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

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    public Double getDesempeno() {
        desempeno = ((getValorActual()) / (getValorActual()));
        return desempeno;
    }

    public void setDesempeno(Double desempeno) {
        this.desempeno = desempeno;
    }

}
