package Logic;


import Forms.RFID_Detector;
import java.util.Scanner;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

import gnu.io.*; // for rxtxSerial library
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


 
public class ReadRFIDPort implements SerialPortEventListener {
      public static    String status="",storeID="",mobile="";
         
          public static String ip="";
   static CommPortIdentifier portId;
   static CommPortIdentifier saveportId;
   static Enumeration        portList;
   InputStream           inputStream;
   SerialPort           serialPort;
  public static String user="";
 //public static rfid rr;
  public static ImageIcon ic;

   public static void rfid(){ //Main rf1
     //  JOptionPane.showMessageDialog(null,"Wait For sometime.. ");
     
       
 
      boolean           portFound = false;
      String           rfidPort;
    
      rfidPort="COM10";
      System.out.println("Set default port to "+ rfidPort);
      
		// parse ports and if the default port is found, initialized the reader
      portList = CommPortIdentifier.getPortIdentifiers();
      while (portList.hasMoreElements()) {
         portId = (CommPortIdentifier) portList.nextElement();
         if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            if (portId.getName().equals(rfidPort)) {
               System.out.println("Found port: "+rfidPort);
               portFound = true;
              // rf1.setVisible(false);
             //  new rfid(user).setVisible(true);
            //   JOptionPane.showMessageDialog(null,"Place Your  RFID ID card on The RFID Detecter");
              ReadRFIDPort reader = new ReadRFIDPort();
            
            
            
            
            
            
            
            
            
            
            
            } 
         } 
         
      } 
      if (!portFound) {
         System.out.println("port " + rfidPort + "not found.");
      } 
  // return status;   
   } 
 
   public ReadRFIDPort() {
      try {
         serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
      } catch (PortInUseException e) {}
   
      try {
         inputStream = serialPort.getInputStream();
      } catch (IOException e) {
      
      e.printStackTrace();
      }
   
      try {
         serialPort.addEventListener(this);
      } catch (TooManyListenersException e) {}
      
      // activate the DATA_AVAILABLE notifier
      serialPort.notifyOnDataAvailable(true);
   
      try {
         // set port parameters
         serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, 
                     SerialPort.STOPBITS_1, 
                     SerialPort.PARITY_NONE);
        
      } catch (UnsupportedCommOperationException e) {}
      
 
      
   }
 
    public void serialEvent(SerialPortEvent event) {
        String  result="";
      switch (event.getEventType()) {
      case SerialPortEvent.BI:
      case SerialPortEvent.OE:
      case SerialPortEvent.FE:
      case SerialPortEvent.PE:
      case SerialPortEvent.CD:
      case SerialPortEvent.CTS:
      case SerialPortEvent.DSR:
      case SerialPortEvent.RI:
      case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
         break;
      case SerialPortEvent.DATA_AVAILABLE:
         // we get here if data has been received
          String res="";
              int numBytes=0;
              char ch;
         try {
            // read data
            while (inputStream.available() > 0) {
               // numBytes = inputStream.read(readBuffer);
             numBytes=inputStream.read();
             //   System.out.println("0000>"+numBytes);
              ch=(char)numBytes;
               
               System.out.println("111>"+ch);
            res+=ch;
            
               Thread.sleep(500);
               //Thread.sleep(5000);
            } inputStream.close();
            
          
            // print data
              System.out.println("??????????"+res);

              System.out.println(result + "      ");
              
               res=res.substring(0,8);
               System.out.println("length"+res.length());
            
               System.out.println("length"+res.length());
               
               
               
               String urlString = "";
                         try
			      {
                                  System.out.println("http://localhost:8084/Blind-Shopping/getRfidDetails.jsp?rfid="+res);
			         URL url = new URL("http://localhost:8084/Blind-Shopping/getRfidDetails.jsp?rfid="+res);
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
                               
			      }catch(IOException e)
			      {
			         e.printStackTrace();
			      }
               TextSpeech ts=new TextSpeech();
               ts.speech(urlString);
               
             
             //  ts=null;
//               String ip=InetAddress.getLocalHost().getHostAddress().toString();
//               
//               
//               Socket soc=new Socket("192.168.1.102",5000);//server
//               DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
//               dout.writeUTF(res);
//               dout.writeUTF(storeID);
//               dout.writeUTF(mobile);
//               dout.writeUTF(ip);
//               dout.close();
//               soc.close();
//               
              // Thread.sleep(5000);
              
              
               
                // serialPort.close();
         } catch (Exception ex) {
            Logger.getLogger(ReadRFIDPort.class.getName()).log(Level.SEVERE, null, ex);
        } 
         break;
      }
     
   } 
 
}