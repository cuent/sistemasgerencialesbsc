package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.CabeceraValor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;
import ucuenca.edu.sg.formula.Formula;
import ucuenca.edu.sg.formula.Variable;
import ucuenca.edu.sg.modelo.ComponenteFormula;

@Named("cabeceraValorController")
@SessionScoped
public class CabeceraValorController extends AbstractController<CabeceraValor> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.CabeceraValorFacade ejbFacade;
    @EJB
    private ucuenca.edu.sg.facade.DetalleValorFacade ejbFacade1;
    private String formula;
    //private List<ComponenteFormula> lComponentes;
    private List<Variable> variables;
    private List<CabeceraValor> filtroCabeceras;

    public CabeceraValorController() {
    }
    private CartesianChartModel viviendas;

    public CartesianChartModel getViviendas() {
        return viviendas;
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        variables = new ArrayList<>();
        createLineModels();

        viviendas = new CartesianChartModel();

        final ChartSeries venta = new ChartSeries("Venta");
        venta.set("2008", 800);
        venta.set("2009", 1300);
        venta.set("2010", 700);
        venta.set("2011", 500);

        final ChartSeries alquiler = new ChartSeries("Alquiler");
        alquiler.set("2008", 1200);
        alquiler.set("2009", 1100);
        alquiler.set("2010", 1700);
        alquiler.set("2011", 1900);

        viviendas.addSeries(venta);
        viviendas.addSeries(alquiler);
    }

    public List<CabeceraValor> getFiltroCabeceras() {
        return filtroCabeceras;
    }

    public void setFiltroCabeceras(List<CabeceraValor> filtroCabeceras) {
        this.filtroCabeceras = filtroCabeceras;
    }

    public void iniciar() {
        this.setSelected(new CabeceraValor());
        this.getSelected().setValorTotal(0.0);
    }

    @Override
    public void create() {
        try {
            Date d = new Date();
            this.getSelected().setFecha(d);
            System.out.println(d);
            Date dGMT = cvtToGmt(d);

            System.out.println(d);
            System.out.println(dGMT);
            //super.create(); //To change body of generated methods, choose Tools | Templates.
            for (Variable variable : variables) {

                DetalleValorController dvc = new DetalleValorController();
                dvc.inicializar(this.getSelected(), variable.getComponenteFormula(), variable.getValor());
                dvc.setFacade(ejbFacade1);
                dvc.create();
            }
            anadirMensaje(1);
            createLineModels();

        } catch (ELException | NullPointerException e) {
            anadirMensaje(2);
        }

    }

    private Date cvtToGmt(Date date) {
        TimeZone tz = TimeZone.getDefault();
        Date ret = new Date(date.getTime() - tz.getRawOffset());

        if (tz.inDaylightTime(ret)) {
            Date dstDate = new Date(ret.getTime() - tz.getDSTSavings());

            if (tz.inDaylightTime(dstDate)) {
                ret = dstDate;
            }
        }
        return ret;
    }

    public void mostrarformula() {
        setFormula(formula);
        formula = this.getSelected().getIdIndicador().getFormula();
        List<ComponenteFormula> listaComponentes = this.getSelected().getIdIndicador().getComponenteFormulaList();
        variables.clear();
        for (ComponenteFormula Componente : listaComponentes) {
            Variable v = new Variable(Componente, 0.0);
            variables.add(v);
        }
        setVariables(variables);
        //setlComponentes(listaComponentes);
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public String getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

    public double getResultado() {
        Formula f = new Formula(variables, formula);
        Double resultado = 0.0;
        if (f.validar()) {
            resultado = Double.parseDouble(f.resultado());
            this.getSelected().setValorTotal(resultado);
        } else {
            resultado = 0.0;
        }
        return resultado;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valor Cambiado", "a: " + newValue + ", Resultado: " + getResultado());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void anadirMensaje(int tipo) {
        FacesMessage msg = null;
        switch (tipo) {
            case 1:
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Almacenado", "Se ha guardado con exito");
                break;
            case 2:
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Actualice la pagina e ingrese daos coherentes");
                break;
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private LineChartModel lineModel2;

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    private void createLineModels() {
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Grafica Valor Actual por Día");
        lineModel2.setLegendPosition("e");
        lineModel2.setZoom(true);
        lineModel2.setShowPointLabels(true);
        //lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Dia"));
        lineModel2.getAxis(AxisType.Y).setLabel("Valores Actuales");
        //Eje X
        DateAxis axis = new DateAxis("Días");
        axis.setTickAngle(-50);
        //axis.setMax("2015-08-01");
        axis.setTickFormat("%#d %b, %y");
        lineModel2.getAxes().put(AxisType.X, axis);

        //Eje Y
//        Axis yAxis = lineModel2.getAxis(AxisType.Y);
//        yAxis.setLabel("Valor Total");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
    }

    private LineChartModel initCategoryModel() {
        List<CabeceraValor> l = super.getItemsAvailableSelectMany();

        HashMap<String, String> hm = new HashMap();
        for (CabeceraValor l1 : l) {
            hm.put(l1.getIdIndicador().getNombreIndicador(), l1.getIdIndicador().getNombreIndicador());
        }

        LineChartModel model = new LineChartModel();
        Iterator it = hm.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println(e.getKey() + " " + e.getValue());
            LineChartSeries cs = new LineChartSeries();
            cs.setLabel(e.getKey() + "");
            for (CabeceraValor l1 : l) {
                if (e.getKey().equals(l1.getIdIndicador().getNombreIndicador())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(l1.getFecha());
                    //SimpleDateFormat format1 = new SimpleDateFormat("EEE, dd MMM YYYY");
                    SimpleDateFormat format1 = new SimpleDateFormat("YYYY-MM-dd");
                    String conFormato = format1.format(calendar.getTime());
                    String mes_ = (calendar.get(Calendar.MONTH) + 1) + "";
                    String min = (calendar.get(Calendar.DAY_OF_MONTH) + 1) + "";
                    cs.set(conFormato, l1.getValorTotal());
                }
            }
            model.addSeries(cs);
        }
        return model;
    }

    private CartesianChartModel initLinearModel() {
        List<CabeceraValor> l = super.getItems();
        CartesianChartModel model = new CartesianChartModel();

        HashMap<String, String> hm = new HashMap();
        for (CabeceraValor l1 : l) {
            hm.put(l1.getIdIndicador().getNombreIndicador(), l1.getIdIndicador().getNombreIndicador());
        }

        Iterator it = hm.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println(e.getKey() + " " + e.getValue());
            LineChartSeries cs = new LineChartSeries();
            cs.setLabel(e.getKey() + "");
            for (CabeceraValor l1 : l) {
                if (e.getKey().equals(l1.getIdIndicador().getNombreIndicador())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(l1.getFecha());
                    String mes_ = (calendar.get(Calendar.MONTH) + 1) + "";
                    String min = (calendar.get(Calendar.DAY_OF_MONTH) + 1) + "";
                    cs.set(min, l1.getValorTotal());
                }
            }
            model.addSeries(cs);
        }

        return model;
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
}
