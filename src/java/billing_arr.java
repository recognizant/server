/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author sumit
 */
public class billing_arr extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
       String itemname = request.getParameter("itemname");
       String amount = request.getParameter("amount");
       String quantity = request.getParameter("quantity");
       String tot = request.getParameter("tot");
       String tid = request.getParameter("tid");
       String uname = request.getParameter("uname");
       
       itemname = itemname.substring(0,itemname.lastIndexOf("-"));
       amount = amount.substring(0,amount.lastIndexOf("-"));
       quantity = quantity.substring(0,quantity.lastIndexOf("-"));
       tot = tot.substring(0,tot.lastIndexOf("-"));
       tid = tid.substring(0,tid.lastIndexOf("-"));
       uname = uname.substring(0,uname.lastIndexOf("-"));
       
        System.out.println(">>>"+itemname.toString());
        System.out.println(">>>"+amount.toString());
        System.out.println(">>>"+quantity.toString());
        System.out.println(">>>"+tot.toString());
        System.out.println(">>>"+tid.toString());
        System.out.println(">>>"+uname.toString());
        
        String titA[]=tid.split("-");
        String unameA[]=uname.split("-");
        String totA[]=tot.split("-");
        double tatalAmount=0.0;
        for(int i=0;i<totA.length;i++)
        {
        
        double d=Double.parseDouble(totA[i]);
            System.out.println("................."+d);
        tatalAmount+=d;
            System.out.println(".........."+tatalAmount);
        }
        
        
        
        HttpSession session=request.getSession();
        RequestDispatcher rd=null;
        
        
        DBQuery db=new DBQuery();
           int i=db.addBillData(titA[0],unameA[0],itemname,quantity,amount,tatalAmount+"");
           System.out.println("i= "+i);
           if(i==1)
           {
               System.out.println("???????????????????????????????????????");
           
             response.getWriter().write("Bill_updated");
             
           }
           else{
           
          }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(billing_arr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(billing_arr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(billing_arr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(billing_arr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
