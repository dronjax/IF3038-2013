/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.*;
import java.net.*;

/**
 *
import java.util.List *
 * @author User
 */
public class SocketClient {

    Socket myClient = null;
    DataInputStream AccInput = null;
    DataOutputStream DoOutput = null;
    
    private SocketClient(String machinename, int port){
        try{
            myClient = new Socket(machinename, port);
            AccInput = new DataInputStream(myClient.getInputStream());
            DoOutput = new DataOutputStream(myClient.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void closeClient(){
        try{
            myClient.close();
            AccInput.close();
            DoOutput.close();
        }catch(IOException e){
        }
    }
    
    public void doWrite(){
        if(myClient != null &Login(String username, String password){
        List<Byte> msgloginlist = messagecontainer.MessageContainer.construct_message_login(username, password);
        byte[] msglogin = new byte[msgloginlist.size()];
        for(int i = 0; i<msgloginlist.size(); i++){
            msglogin[i] = msgloginlist.get(i);
            
        }
        int usernamelength = username.length();
        int passwordlength = password.length();(){
        if(myClient != null && AccInput != null && DoOutput!= null){
            try{
                DoOutput.writeBytes("HELO\n");
          LO\n");
                DoOutput.writeBytes("MAIL From: k3is@fundy.csd.unbsj.ca\n");
                DoOutput.writeBytes("RCPT To: k3is@fundy.csd.unbsj.ca\n");
                DoOutput.writeBytes("DATA\n");
                DoOutput.writeBytes("From: k3is@fundy.csd.unbsj.ca\n");
                DoOutput.writeBytes("Subject: testing\n");
                DoOutput.writeBytes("Hi there\n"); // message body
                //DoOutput.writeBytes("\n.\n");
                DoOutput.writeBytes("\n.\n");
                             String responseLine;
                while((responseLine = AccInput.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    /*if (responseLine.indexOf("Ok") != -1) {
                     break;
                    }*/
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SocketClient client = new SocketClient("localhost",60000);
        client.doWrite();
        client.closeClient();
    }
}
