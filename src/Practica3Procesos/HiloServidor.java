
package Practica3Procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloServidor implements Runnable{

    private Socket socket;
    private GestorDeTareas gestor;

    public HiloServidor(Socket socket, GestorDeTareas gestor) {
        this.socket = socket;
        this.gestor = gestor;
    }
    
    @Override
    public void run() {
        ObjectOutputStream enviar = getWriter(socket);
        
        while (true) {            
            
        }
        
    }
    
    private static BufferedReader getReader(Socket s) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            System.out.println(ex);}
        return br;
    }

    private static ObjectOutputStream getWriter(Socket s) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return oos;
    }
    
    private static void enviarTarea(ObjectOutputStream enviar){
        try {
            enviar.writeObject(new Tarea());
            enviar.flush();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
