
package Practica3Procesos;

import java.io.Serializable;
import java.util.Random;

public class Tarea implements Serializable{
    
    private int id;
    private static int contador = 0;
    private int duracion;

    public Tarea() {
        contador++;
        id = contador;
        duracion = generarTarea();
    }
    
    private int generarTarea(){
        Random r = new Random();
        return r.nextInt(3 - 1) + 1; 
    }

    @Override
    public String toString() {
        return "Tarea{" + "id=" + id + ", duracion=" + duracion + '}';
    }

    public int getDuracion() {
        return duracion;
    }
     
    
}
