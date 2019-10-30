/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Database.DBConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class listenRFIDDetector extends Thread{
    public listenRFIDDetector(){
    start();
    }
    public void run(){
        try {
            ServerSocket ss=new ServerSocket(5000);
            while(true)
            {
            Socket soc=ss.accept();
            DataInputStream din=new DataInputStream(soc.getInputStream());
            String user=din.readUTF();
            String storeID=din.readUTF();
            String mobile=din.readUTF();
            String ip=din.readUTF();
             Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();
           
             String am="";
            String q4="select amnt from regis_table where rfid='"+user+"'";
                System.out.println(">>>"+q4);
            ResultSet rs2=st.executeQuery(q4);
            if(rs2.next())
            {
            am=rs2.getString(1);
            
            }
            
            if(Double.parseDouble(am)>50.0)
            {
            int amt=0;
            String q="insert into temp values('"+user+"','0','"+amt+"')";
            
            String q1="select * from temp where rfid='"+user+"'";
            ResultSet rs=st.executeQuery(q1);
            int i=0;
            if(rs.next())
            {
            i=1;
            }
                if(i==1)
                {
                String q2="delete from temp where rfid='"+user+"'";
                int i1=st.executeUpdate(q2);
                
                }
            
            
            
            
            int i1=st.executeUpdate(q);
            
            Socket soc1=new Socket(ip,3000);//server
               DataOutputStream dout=new DataOutputStream(soc1.getOutputStream());
               dout.writeUTF("ok");
               
               dout.close();
               soc1.close();
            }
            else{
            
               Socket soc1=new Socket(ip,3000);//server
               DataOutputStream dout=new DataOutputStream(soc1.getOutputStream());
               dout.writeUTF("notok");
               
               dout.close();
               soc1.close();
            }
            din.close();
            soc.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listenRFIDDetector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(listenRFIDDetector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(listenRFIDDetector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
