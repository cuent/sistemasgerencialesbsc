/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jq733100.utilidades.CodigoBarra;
import jq733100.utilidades.Documento;
import jq733100.utilidades.Encabezado;
import jq733100.utilidades.EspacioBlanco;
import jq733100.utilidades.Imagen;
import jq733100.utilidades.LineaCentrada;
import jq733100.utilidades.LineaNormal;
import jq733100.utilidades.TablaHorizontal;
import jq733100.utilidades.TablaVertical;
import jq733100.utilidades.Titulo1;
import jq733100.utilidades.Titulo2;
import jq733100.utilidades.Titulo3;
import jq733100.utilidades.Titulo4;
import org.eclipse.persistence.internal.core.helper.CoreClassConstants;
import ucuenca.edu.sg.modelo.Indicador;
import ucuenca.edu.sg.modelo.ResponsableActividad;
import ucuenca.edu.sg.modelo.ResponsableObjetivo;

/**
 *
 * @author pablito
 */
public class ReporteMatrizBSC {

    private Encabezado encabezado;
    private CodigoBarra codigoBarra;

    private LineaNormal lineaNormal;
    private LineaCentrada lineaCentrada;
    private TablaHorizontal tablaHorizontal;
    private TablaVertical tablaVertical;
    private Imagen imagen;
    private Titulo1 titulo1;
    private Titulo2 titulo2;
    private Titulo3 titulo3;
    private Titulo4 titulo4;
    private EspacioBlanco espacioBlanco;
    private Documento documento;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    public ReporteMatrizBSC() {
        encabezado = new Encabezado();
        codigoBarra = new CodigoBarra();
        espacioBlanco = new EspacioBlanco();
        lineaNormal = new LineaNormal();
        lineaCentrada = new LineaCentrada();
        tablaHorizontal = new TablaHorizontal();
        tablaVertical = new TablaVertical();
        imagen = new Imagen();
        titulo1 = new Titulo1();
        titulo2 = new Titulo2();
        titulo3 = new Titulo3();
        titulo4 = new Titulo4();
    }

    public void generardocumento(List<Indicador> indicadores) throws DocumentException, BadElementException, IOException {
        String ruta = getEc().getRealPath("resources");
        System.out.println(ruta);
        documento = new Documento(ruta + "/rep2.pdf");
        documento.setMargins(60, 30, 30, 40);
        piePaguina();
        documento.open();
        cabezera();
        cuerpo(indicadores);
        
        documento.close();
    }

    private void cabezera() throws DocumentException, BadElementException, IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        titulo1.setTexto("REPORTE DE LA MATRIZ DEL CUADRO INTEGRAL");
        titulo1.getElementoRojo();
        espacioBlanco.getElemento();
        documento.add(titulo1.getElemento());
        documento.add(espacioBlanco.getElemento());
        documento.add(lineaNormal.getLinea());
        documento.add(espacioBlanco.getElemento());
        documento.add(espacioBlanco.getElemento());

        Image foto = Image.getInstance(getEc().getRealPath("resources") + "/img/golden.jpg");
        foto.scaleToFit(130, 130);
        foto.setAlignment(Chunk.ALIGN_MIDDLE);
        documento.add(foto);

        tablaVertical.limpiar();
        tablaVertical.setTitulos("Nombre Empresa:", "Ubicacion:", "Fecha:");

        tablaVertical.setContenidos(new Object[]{"Gonlden Bringe Corporation S.A ", "Florencia Astudillo 1-28 y Av. 12 de Abril", fecha()});

        tablaVertical.setAlineamientos(new int[]{0, 0});
        tablaVertical.setAnchoTabla(50);
        tablaVertical.setPosicion(0);
        tablaVertical.setTamanos(new int[]{30, 70});
        tablaVertical.llenarTabla(false);
        documento.add(tablaVertical.getTabla());

        documento.add(espacioBlanco.getElemento());
        documento.add(espacioBlanco.getElemento());
    }

    public String fecha() {
        //Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        return dia + "/" + (mes + 1) + "/" + año;
    }

    private void cuerpo(List<Indicador> indicadores) throws DocumentException {

        titulo3.setTexto("Matriz CMI:");
        documento.add(titulo3.getElemento());
        documento.add(espacioBlanco.getElemento());
        tablaHorizontal.setColumnas(7);
        tablaHorizontal.setTitulos("Perspectiva", "Objetivo", "kpi", "Unidades", "Responsable", "Est. Actual", "Periodicidad");
        Vector vector = new Vector();
        for (Indicador indicador : indicadores) {
            // indicador.getUnidades()
            ArrayList<String> dato = new ArrayList<String>();
            dato.add(String.valueOf(indicador.getIdObjetivoEstrategico().getIdPerspectiva().getPerspectiva()));
            dato.add(String.valueOf(indicador.getIdObjetivoEstrategico().getObjetivo()));
            dato.add("");
            dato.add(indicador.getUnidades());
            dato.add(cargarResponsables(indicador.getIdObjetivoEstrategico().getResponsableObjetivoList()));
            dato.add(indicador.getEstadoActual());
            dato.add(indicador.getPeriodicidad());
            // dato.add()
            vector.add(dato);
        }
        tablaHorizontal.setContenidos(vector.toArray());
        tablaHorizontal.setAlineamientos(new int[]{1, 0, 1, 1, 1, 1, 1});
        tablaHorizontal.setTamanos(new int[]{15, 35, 10, 10, 20, 10, 20});
        tablaHorizontal.setAnchoTabla(100);
        tablaHorizontal.llenarTabla();
        documento.add(tablaHorizontal.getTabla());
        documento.add(espacioBlanco.getElemento());
        documento.add(espacioBlanco.getElemento());
    }

    public String cargarResponsables(List<ResponsableObjetivo> lista) {
        String respuesta = "";
        for (ResponsableObjetivo res : lista) {
            respuesta += res.getUsuario().getNombres() + "\n";
        }
        return respuesta;
    }

    private void piePaguina() {
        java.util.Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        encabezado.setEncabezado("Creado el: " + formateador.format(ahora));
        documento.getWriter().setPageEvent(encabezado);
    }

    public ExternalContext getEc() {
        return ec;
    }

    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }

}
