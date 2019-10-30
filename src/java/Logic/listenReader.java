/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Database.DBQuery;
import Forms.Read_Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumit
 */
public class listenReader extends Thread{
    
    
    public listenReader(){
    start();
    }
    
    public void run(){
    
    while(true)
    {
    
        String tag=Read_Data.tfTag.getText();
        //System.out.println("tag>"+tag);
    if(!tag.equals(""))
    {
        String urlString = "";
        System.out.println("******************************"+info.flag);
        System.out.println("******************************"+info.dblock);
        System.out.println("******************************"+info.drfid);
        System.out.println("******************************"+info.dshelf);
         String det="";
        DBQuery db=new DBQuery();
            try {
                 det=db.get_Current_status();
                 System.out.println("det="+det);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(listenReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(listenReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        String d[]=det.split("-");
        
     if(d[0].equals("1"))
     {
               try
			      {
                               //   System.out.println("http://localhost:8084/Blind-OSN/getRfidDetails.jsp?rfid="+res);
                                  System.out.println("http://localhost:8084/Blind-Shopping/get_derection?rfid="+tag+"&drfid="+d[3]+"&dblock="+d[1]+"&dshelf="+d[2]);
			         URL url = new URL("http://localhost:8084/Blind-Shopping/get_derection?rfid="+tag+"&drfid="+d[3]+"&dblock="+d[1]+"&dshelf="+d[2]);
			         URLConnection urlConnection = url.openConnection();
			         HttpURLConnection connection = null;
			         if(urlConnection instanceof HttpURLConnection)
			         {
			            connection = (HttpURLConnection) urlConnection;
			         }
			         else
			         {
			            System.out.println("Please enter an HTTP URL.");
			            return;
			         }
			         BufferedReader in = new BufferedReader(
			         new InputStreamReader(connection.getInputStream()));
			         
			         String current;
			         while((current = in.readLine()) != null)
			         {
			            urlString += current;
			         }
			         System.out.println(urlString);
//                                   TextSpeech ts=new TextSpeech();
//               ts.speech(urlString);
//               
                                 
                                 
                                 String u[]=urlString.split("\n");
                                 tts t=new tts();
                                 for(int i=0;i<u.length;i++)
                                 {
                                 
                                 
                                  
                                 t.convert(u[i]);
                                      try {
                                          Thread.sleep(3000);
                                      } catch (InterruptedException ex) {
                                          Logger.getLogger(listenReader.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                 
                                 }
                                 
                                 
                                 
                                
			      }catch(IOException e)
			      {
			         e.printStackTrace();
			      }
                
     }else{
    
       try
			      {
                                  System.out.println("http://localhost:8084/Blind-OSN/getRfidDetails.jsp?rfid="+tag);
			         URL url = new URL("http://localhost:8084/Blind-Shopping/getRfidDetails.jsp?rfid="+tag);
			         URLConnection urlConnection = url.openConnection();
			         HttpURLConnection connection = null;
			         if(urlConnection instanceof HttpURLConnection)
			         {
			            connection = (HttpURLConnection) urlConnection;
			         }
			         else
			         {
			            System.out.println("Please enter an HTTP URL.");
			            return;
			         }
			         BufferedReader in = new BufferedReader(
			         new InputStreamReader(connection.getInputStream()));
			         
			         String current;
			         while((current = in.readLine()) != null)
			         {
			            urlString += current;
			         }
			         System.out.println(urlString);
//                                   TextSpeech ts=new TextSpeech();
//               ts.speech(urlString);
//               
                                 
                                 
                                 String u[]=urlString.split("\n");
                                 tts t=new tts();
                                 for(int i=0;i<u.length;i++)
                                 {
                                 
                                 
                                  
                                 t.convert(u[i]);
                                      try {
                                          Thread.sleep(3000);
                                      } catch (InterruptedException ex) {
                                          Logger.getLogger(listenReader.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                 
                                 }
                                 
                                 
                                 
                                
			      }catch(IOException e)
			      {
			         e.printStackTrace();
			      }
     
     }
               
    Read_Data.tfTag.setText("");
    }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(listenReader.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
    
}
