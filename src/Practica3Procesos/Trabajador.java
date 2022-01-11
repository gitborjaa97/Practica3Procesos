
package Practica3Procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trabajador {
    
    public static void main(String[] args) {
        int tiempoDisponible = 40;
        Socket s =getSocket();
        ObjectInputStream recibir = getInput(s);
        Tarea task;
        boolean salir = false;
        boolean tareaValida;
        while (!salir) {
            task = recibirTarea(recibir);
            if(tiempoDisponible - task.getDuracion() >= 0){
                tiempoDisponible -= task.getDuracion();
                tareaValida = true;
            }else
                tareaValida = false;
        }
        
    }
    
    private static Socket getSocket(){
        Socket s = null;
        try {
            s = new Socket("127.0.0.1" , 30000);
        } catch (IOException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    private static ObjectInputStream getInput(Socket s){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ois;
    }
    
    private static Tarea recibirTarea(ObjectInputStream recibir){
        Tarea task = null;
        try {
            task =(Tarea) recibir.readObject();
            System.out.println(task);
        } catch (IOException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }
}
