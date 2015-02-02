/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import com.itextpdf.text.DocumentException;
import java.util.ArrayList;
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
import ucuenca.edu.sg.modelo.Indicador;

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

    public void generardocumento(List<Indicador> indicadores) throws DocumentException {
        String ruta = getEc().getRealPath("resources");
        System.out.println(ruta);
        documento = new Documento(ruta + "/rep2.pdf");
        documento.setMargins(60, 30, 30, 40);
        documento.open();
        cabezera();
        cuerpo(indicadores);
        piePaguina();
        documento.close();
    }

    private void cabezera() {
        titulo1.setTexto("INFORME DE LA MATRIZ DEL CUADRO INTEGRAL");
        titulo1.getElementoRojo();
        espacioBlanco.getElemento();
    }

    private void cuerpo(List<Indicador> indicadores) throws DocumentException {

        titulo3.setTexto("Matriz:");
        documento.add(titulo3.getElemento());
        documento.add(espacioBlanco.getElemento());
        tablaHorizontal.setColumnas(6);
        tablaHorizontal.setTitulos("Perspectiva", "Objetivo", "kpi", "Unidades", "DURACION", "RESPONSABLE");
        Vector vector = new Vector();
        for (Indicador indicador : indicadores) {
           // indicador.getUnidades()
            ArrayList<String> dato = new ArrayList<String>();
            dato.add(String.valueOf(indicador.getIdObjetivoEstrategico().getIdPerspectiva().getPerspectiva()));
            dato.add(String.valueOf(indicador.getIdObjetivoEstrategico().getObjetivo()));
            dato.add("");
            dato.add(indicador.getUnidades());
           // dato.add()
           vector.add(dato);
        }
         tablaHorizontal.setContenidos(vector.toArray());
            tablaHorizontal.setAlineamientos(new int[]{1, 0, 1, 1, 1, 0});
            tablaHorizontal.setTamanos(new int[]{35, 20, 15, 15, 10, 20});
            tablaHorizontal.setAnchoTabla(100);
            tablaHorizontal.llenarTabla();
            documento.add(tablaHorizontal.getTabla());
            documento.add(espacioBlanco.getElemento());
            documento.add(espacioBlanco.getElemento());
    }

    private void piePaguina() {
    }

    public ExternalContext getEc() {
        return ec;
    }

    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }

}
