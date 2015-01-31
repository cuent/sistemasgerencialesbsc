/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author miguesaca
 */
public class Mensaje {

    // Tipos de Severidad del Mensaje
    private static final int INFORMACION = 0;
    private static final int SATISFACTORIO = 0;
    private static final int ADVERTENCIA = 1;
    private static final int ERROR = 2;
    private static final int FATALERROR = 3;

    // error en capa de negocio
    public static void addErrorCapaNegocio() {
        String msg = "Error en Facade";
        String titulo = "ERROR";
        message(titulo, msg, ERROR);
    }

    // error en capa de negocio
    public static void addErrorCapaControl() {
        String msg = "Error en Controller";
        String titulo = "ERROR";
        message(titulo, msg, ERROR);
    }

    /**
     * Muestra el mensaje de error al usuario con el nombre del Facade en donde
     * se proporciono el error
     *
     * @param nombreFacade
     */
    public static void addErrorCapaControlFacace(String nombreFacade) {
        String msg = "BeanFacade " + nombreFacade;
        String titulo = "ERROR";
        message(titulo, msg, ERROR);
    }
    //MENSAJES DE PROCESOS ALMACENADOS

    public static void addErrorLlamaSP(String msg) {
        String titulo = "ERROR";
        message(titulo, msg, ERROR);
    }

    public static void addFatalErrorLlamaSP(Exception ext) {
        String msg = ext.toString();
        String titulo = "ERROR FATAL";
        message(titulo, msg, FATALERROR);
    }

    //MENSAJES DE PERSISTENCIA
    public static void addErrorPersistencia() {
        String msg = "Error en Controller";
        String titulo = "ERROR";
        message(titulo, msg, ERROR);
    }

    public static void addErrorPersistencia(String msg) {
        String titulo = "ERROR PERSISTENCIA";
        message(titulo, msg, ERROR);
    }

    public static void addSatisfactorioPersistencia(int tipo) {
        if (tipo == 1) {
            String msg = "Registro Grabado";
            String titulo = "Informacion";
            message(titulo, msg, INFORMACION);
        } else {
            String msg = "Registro Eliminado";
            String titulo = "Informacion";
            message(titulo, msg, INFORMACION);
        }
    }

    // MENSAJES GENERALES
    public static void addInformacion(String msg) {
        String titulo = "Informacion";
        message(titulo, msg, INFORMACION);
    }

    public static void addSatisfactorio(String msg) {
        String titulo = "Informacion";
        message(titulo, msg, SATISFACTORIO);
    }

    public static void addAdvertencia(String msg) {
        String titulo = "Advertiencia";
        message(titulo, msg, ADVERTENCIA);
    }

    public static void addError(String msg) {
        String titulo = "Error";
        message(titulo, msg, ERROR);
    }

    public static void addFatalError(String msg) {
        String titulo = "FatalError";
        message(titulo, msg, FATALERROR);
    }

    // Mensaje
    private static void message(String informacion, String mensaje, int severidadMsg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage.Severity severidad = null;
        if (severidadMsg == 0) {
            severidad = FacesMessage.SEVERITY_INFO;
        } else if (severidadMsg == 1) {
            severidad = FacesMessage.SEVERITY_WARN;
        } else if (severidadMsg == 2) {
            severidad = FacesMessage.SEVERITY_ERROR;
        } else if (severidadMsg == 3) {
            severidad = FacesMessage.SEVERITY_FATAL;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, informacion, mensaje));
    }

    /**
     * Devuelve el objeto Face de Un error
     *
     * @param msg
     * @return
     */
    public static FacesMessage getFaceError(String msg) {
        String titulo = "Error";
        FacesMessage.Severity severidad = FacesMessage.SEVERITY_ERROR;
        FacesMessage faceMsg
                = new FacesMessage(titulo, msg);
        faceMsg.setSeverity(severidad);

        return faceMsg;
    }

}
