/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import com.itextpdf.text.DocumentException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucuenca.edu.sg.modelo.Actividades;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;

/**
 *
 * @author pablito
 */
public class GeneracionReportes {
  
    

    public void generarActividades(List<Actividades> listaGlobales, List<ObjetivoEstrategico> listObjetivo) {
        try {
            GenerarActividades generarActividades = new GenerarActividades();
            generarActividades.generardocumento(listaGlobales,listObjetivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GeneracionReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
