/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import Logic.info1;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumit
 */
public class get_derection extends HttpServlet {

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
 public static    int src_block=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException
            , IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       String tag=request.getParameter("rfid");
       String drfid=request.getParameter("drfid");
       String dblock=request.getParameter("dblock");
       String dshelf=request.getParameter("dshelf");
       String cshelf=request.getParameter("cshelf");
        System.out.println("get_derection>>>rfid="+tag+"-drfid="+drfid+"-dblock="+dblock+"-dshelf="+dshelf+"-cshelf="+cshelf);
        if(cshelf==null || cshelf.equals(""))
        {
        cshelf="0";
        }
       int csh=Integer.parseInt(cshelf);
       int shelf=0;
       DBQuery db=new DBQuery();
        System.out.println("??"+info1.flag);
       if(info1.flag==false)
       {
           String s=db.get_block(tag);
           String a[]=s.split("-");
        src_block=Integer.parseInt(a[0]);
        shelf=Integer.parseInt(a[1]);
        info1.src_block=src_block;
        info1.flag=true;
       }
       
       
       
       
        String s=db.get_block(tag);
           String a[]=s.split("-");
          int block=Integer.parseInt(a[0]);
          shelf=Integer.parseInt(a[1]);
      System.out.println("current shelf="+shelf);
        System.out.println("src_block="+src_block);
        System.out.println("current tag block="+block);
       if(dblock.equals(block+"") && tag.equals(drfid) )
       {
       info1.flag=false;
       src_block=0;
       out.print("Here is your item!!");
       
       }
       else if(dblock.equals(block+"")  && csh<shelf)
       {
       
       out.print(shelf+"##Go Straight");
       
       }
        else if(dblock.equals(block+"")  && csh>shelf)
       {
       
       out.print(shelf+"##Move Back");
       
       }
       else{
           System.out.println("different block........................");
       String catg=db.get_catg(tag);
       if(catg.equals("n"))
       {
           
           if(csh<shelf)
           {
            out.print(shelf+"##Go Straight");
           }
           else{
            out.print(shelf+"##Turn Back");
           }
           
      
       }
       else{
       System.out.println("corner..............");
       String desc=db.get_dir(tag, dblock,"b"+src_block);
       out.print(shelf+"##"+desc);
       }
       
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
            Logger.getLogger(get_derection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(get_derection.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(get_derection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(get_derection.class.getName()).log(Level.SEVERE, null, ex);
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
