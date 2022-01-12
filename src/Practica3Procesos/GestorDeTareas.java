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
        if (result) {
            //Añade la tarea realizada al la lista de tareas y vuelve a entrar 
            //a enviarTarea para pedir una nueva tarea
        } else {
            //Comprueba si la tarea es longitud 1 si es asi recibe una tarea de duracion 0
            //y se muere. Si no es asi espera a que todos los hilos terminen o que uno de ellos
            //reciba una tarea valida
        }
    }

    private synchronized void generarTarea() {
        tarea = new Tarea();
        notify();
    }

}
