/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import Logic.ImageResizer;
import Logic.Main;
import Logic.path_info;
import com.oreilly.servlet.MultipartRequest;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author sumit
 */
public class add_user extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
                String path=path_info.path+"sg_photo_temp/"; 
             RequestDispatcher rd=null;
             String npath=path_info.path+"temp/";//              "E://robor_photo/"; 
             String paramname="",name="";
          MultipartRequest mr=new MultipartRequest (request,path);
          Enumeration params = mr.getParameterNames();
			while (params.hasMoreElements()) 
			{
				paramname = (String) params.nextElement();
                               
                                if(paramname.equalsIgnoreCase("name"))
				{
					name=mr.getParameter(paramname);
				}
                               
                        }
                        System.out.println(name);
          
                 DBQuery db=new DBQuery();
            try {
                db.add_user(name);
                

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(add_user.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(add_user.class.getName()).log(Level.SEVERE, null, ex);
            }



			Enumeration files = mr.getFileNames();	
                        while (files.hasMoreElements()) 
                        {
                            paramname = (String) files.nextElement();
                            
		
                            if(paramname != null && paramname.equals("image"))
                            {
                               
                                String abPath = mr.getFilesystemName(paramname);
                                System.out.println("image>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+abPath);
                                String fPath = path+abPath;
                                System.out.println("<><><><><><><><><><><><>"+fPath);
                                 if(!fPath.contains("null"))  
                                 {
                                File file1 = new File(fPath);
                                FileInputStream fsA = new FileInputStream(file1);
                                byte b[]=new byte[fsA.available()];
                                
                                String newPath=npath+name+"_1.png";
                                     System.out.println("newPath="+newPath);
                                FileOutputStream foutPdf=new FileOutputStream(newPath);
                                int j=0;
                                while((j=fsA.read())!=-1)
                                {
                                
                                foutPdf.write((byte)j);
                                
                                }
                                fsA.close();
                                foutPdf.close();
                                
                                  ImageResizer ir=new ImageResizer();
                        Image img = null;
                        img = ImageIO.read(new File(newPath));
                        BufferedImage tempPNG = null;
                        tempPNG = ir.resizeImage(img, 600, 500);
                        File newFilePNG = null;
                        
                        newFilePNG = new File(newPath);
                        ImageIO.write(tempPNG, "png", newFilePNG);
                        
                        
                        
                                     try {
            Main mn = new Main();
           
             path =newPath;// "C:/Users/sumit/Desktop/ft.jpg";
              System.out.println("path=="+path);
            BufferedImage image = ImageIO.read(new File(path));
          
            byte[] b1 = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            mat.put(0,0, b1);
            //   new showResult(mat, "Original Image");
            
            mn.initfd(mat,path_info.pathwhr+"/haarcascades/haarcascade_frontalface_alt.xml",path,name+"_1"+".jpg",name);
            
            
         
                
                
                
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
                                
                                
                                
                            }
                        
		          }
                        }


                    rd=request.getRequestDispatcher("add_image.jsp");
                    rd.forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
