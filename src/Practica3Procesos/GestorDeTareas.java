package Practica3Procesos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorDeTareas {

    Tarea tarea;
    int nTrabajadores;
    boolean tareaValida;
    int contador = 0;

    public GestorDeTareas(int nTrabajadores) {
        this.nTrabajadores = nTrabajadores;
        tareaValida = true;
    }

    public synchronized Tarea enviarTarea() {
        contador++;
        if (contador != nTrabajadores) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            generarTarea();
            contador = 0;
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return tarea;
    }

    public synchronized void resultadoDeTarea(boolean result) {
        tareaValida = result;
        if (result) {

        } else {

        }
    }

    private synchronized void generarTarea() {
        tarea = new Tarea();
        notify();
    }

}
