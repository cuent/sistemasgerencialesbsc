/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.formula;

import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author ESTUDIANTE 3-05
 */
public class Formula {

    private ScriptEngine engine;
    private final String formula;
    private Object Resultado = null;

    public Formula(List<Variable> listaVariables, String formula) {
        ScriptEngineManager manager;
        manager = new ScriptEngineManager();
        engine = manager.getEngineByExtension("js");
        this.formula = formula;
        for (Variable variable : listaVariables) {
            engine.put(variable.getComponenteFormula().getFormula(), variable.getValor());
        }

    }

    public boolean validar() {
        try {
            Resultado = engine.eval(formula);
            return true;
        } catch (ScriptException ex) {
            return false;
        }
    }

    public String resultado() {
        return Resultado + "";
    }

}
