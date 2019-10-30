/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Forms.RFID_Detector;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Karthik
 */
public class listenServer extends Thread{
    public listenServer(){
    
    start();
    
    }
      public void run(){
        try {
            ServerSocket ss=new ServerSocket(3000);
            while(true)
            {
            Socket soc=ss.accept();
            DataInputStream din=new DataInputStream(soc.getInputStream());
            String res=din.readUTF();
            din.close();
            soc.close();
            if(res.equals("ok"))
            {
            RFID_Detector.flag=true;
            }
            else{
            RFID_Detector.flag=false;
            }
           // String destStation=din.readUTF();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
      }
    
}
