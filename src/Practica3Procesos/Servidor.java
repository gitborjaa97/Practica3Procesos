
package Practica3Procesos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    static boolean guardar = true;
    static int count = 0;
    static ArrayList<Socket> sockets = new ArrayList<>();
    
    public static void main(String[] args) {
        ServerSocket SSocket = getSocket();        
        
        Timer timer = new Timer();
        TimerTask ts = new TimerTask() {
            @Override
            public void run() {
                guardar = false;
                esperarHilos(lanzarHilos(sockets));
                System.out.println("Ya han terminado");
                sockets.clear();
                count = 0;
                guardar = true;
            }
            
            private ArrayList<Thread> lanzarHilos(ArrayList<Socket> sockets){
                ArrayList<Thread> hilos = new ArrayList<>();
                for (Socket socket : sockets) {
                    Thread hilo = new Thread(new HiloServidor(socket));
                    hilo.run();
                    hilos.add(hilo);
                }                
                return hilos;
            }
            
            private void esperarHilos(ArrayList<Thread> hilos){
                for (Thread hilo : hilos) {
                    try {
                        hilo.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        while(true){
            Socket s = accepSocket(SSocket);
            count++;
            if(guardar)
                sockets.add(s);
            if(count == 1)
                timer.schedule(ts, 5000);
        }
    }
    
    private static ServerSocket getSocket(){
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(30000);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  socket;
    }
    
    private static Socket accepSocket(ServerSocket ss){
        Socket socket = null;
        try {
            socket = ss.accept();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socket;
    }
    
    
}
