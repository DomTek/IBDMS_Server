/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ibdms_server;


import java.net.*;
import java.io.*;

/**
 *
 * @author H529780
 */
public class IBDMS_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
                //Creates a server socket 
            int serverPort=8888; 
            ServerSocket listenSocket=new ServerSocket(serverPort); 
            
		//while loop that listents for any new connection, when a client attempts a connection and is sucessfull, the conection class will create a new thread
            while(true) {
                Socket clientSocket=listenSocket.accept();
                Connection c = new Connection(clientSocket);
                
            }
            // An exception to catch a failed try statement
          } catch(IOException e) { 
              System.out.println("Listen :"+e.getMessage());}           
          }
    
}

// connection class
class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try { // an echo server
            String data = in.readUTF();
            out.writeUTF(data);
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/*close failed*/
            }
        }
    }
}
