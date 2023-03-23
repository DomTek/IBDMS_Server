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
            int serverPort=7896; 
            ServerSocket listenSocket=new ServerSocket(serverPort); 
            
		//while loop that listents for any new connections	
            while(true) {
                Socket clientSocket=listenSocket.accept();
                Connection c = new Connection(clientSocket);
                System.out.printf("\nServer waiting on: %d for client from %d ",
                listenSocket.getLocalPort(),clientSocket.getPort() );
            }
            // if the try statement fails 
          } catch(IOException e) { 
              System.out.println("Listen :"+e.getMessage());}           
          }
    
}
