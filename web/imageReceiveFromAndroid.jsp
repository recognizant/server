
<%@page import="org.opencv.core.CvType"%>
<%@page import="org.opencv.core.Mat"%>
<%@page import="java.awt.image.DataBufferByte"%>
<%@page import="Logic.Main"%>
<%@page import="Logic.path_info"%>
<%@page import="Logic.ImageResizer"%>

<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>

<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Image"%>



<%@page import="java.util.Random"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%
FileInputStream instream = null;
	FileOutputStream outstream = null;
String res="";
//ServletContext context = getServletContext();
//String dirName = System.getenv("OPENSHIFT_DATA_DIR");//context.getRealPath("\\");
String dirName =path_info.path;//context.getRealPath("\\");
System.out.println("????????????????????????????????"+dirName);
File file2 = null;
String paramname=null,fname="",file="",filePath="",user="";
try {
			 
			MultipartRequest multi = new MultipartRequest(request, dirName,	10 * 1024 * 1024); // 10MB
                       
			Enumeration params = multi.getParameterNames();
			while (params.hasMoreElements()) 
			{
				paramname = (String) params.nextElement();
				if(paramname.equalsIgnoreCase("usn"))
				{
					user=multi.getParameter(paramname);
                                        System.out.println("user:::"+user);
				}
				
                                if(paramname.equalsIgnoreCase("uploaded_file"))
				{
					file=multi.getParameter(paramname);
				}
                        
                         }
        Enumeration files = multi.getFileNames();	
	while (files.hasMoreElements()) 
	{
		paramname = (String) files.nextElement();
	/*	if(paramname.equals("d1"))
		{
			paramname = null;
		}*/
		String fPath="";
		 if(paramname != null && paramname.equals("uploaded_file"))
		{
			
			filePath = multi.getFilesystemName(paramname);
			fPath = dirName+filePath;
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>1>>"+filePath);

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>2>>"+fPath);

		
                        file2 = new File(fPath);
                        try{
                   
                    String s[]=filePath.split("_");
                    System.out.println("s[1]>>>"+s[1]);
                    String user1=s[1];
                    int index=user1.indexOf(".");
                    System.out.println("................"+index);
                    user1=user1.replace(".", "_");
                    String ss[]=user1.split("_");
                    System.out.println("ss[0]....>>"+ss[0]);
                    
                       instream = new FileInputStream(fPath);
    	  //  outstream = new FileOutputStream( System.getenv("OPENSHIFT_DATA_DIR")+"/test.png");
    	    outstream = new FileOutputStream(dirName+"/test.png");
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    /*copying the contents from input stream to
    	     * output stream using read and write methods
    	     */
    	    while ((length = instream.read(buffer)) > 0){
    	    	outstream.write(buffer, 0, length);
    	    }

    	    //Closing the input/output file streams
    	    instream.close();
    	    outstream.close();
                    
                    
                    
                    //    file2.renameTo(new File("G:/ANTITHEFT WITH PIC SMS FORMAT/WEB/antitheft/web/test.png"  ));//file2.getName()
                   
                    
                    
                   //  String fromPath="C:/SavedImages";
                   //     String toPath="C://tempFace1/"+ss[0]+".png";
                    Thread.sleep(5000);
               //   SendAttachmentInEmail sendAttachmentInEmail=new SendAttachmentInEmail();
               //   sendAttachmentInEmail.sendMail("C://tempFace/"+ss[0]+".png");
//                      Mail1 m=new Mail1();
//                      m.mail("G://ANTITHEFT WITH PIC SMS FORMAT/WEB/antitheft/web/test.png");
                      ImageResizer ir=new ImageResizer();
                        Image img = null;
                      //  img = ImageIO.read(new File(System.getenv("OPENSHIFT_DATA_DIR")+"/test.png"));
                        img = ImageIO.read(new File(dirName+"/test.png"));
                        BufferedImage tempPNG = null;
                        tempPNG = ir.resizeImage(img, 600, 500);
                        File newFilePNG = null;
                      //  newFilePNG = new File(System.getenv("OPENSHIFT_DATA_DIR")+"/test.png");
                        newFilePNG = new File(dirName+"/test.png");
                        ImageIO.write(tempPNG, "png", newFilePNG);
                  
                 //     Thread.sleep(5000);
                     //   db.add_iamge("", System.getenv("OPENSHIFT_DATA_DIR")+"/test.png");
                   
                     
                     
                     
//                     
//                     ImageResizer ir=new ImageResizer();
//                        Image img = null;
//                        img = ImageIO.read(new File(dirName+""+"/test.png"));
//                        BufferedImage tempPNG = null;
//                        tempPNG = ir.resizeImage(img, 600, 500);
//                        File newFilePNG = null;
//                        
//                        newFilePNG = new File(dirName+""+"/test.png");
//                        ImageIO.write(tempPNG, "png", newFilePNG);
                        
                       
            Main mn = new Main();
           
            String   path =dirName+""+"test.png";// "C:/Users/sumit/Desktop/ft.jpg";
              System.out.println("path=="+path);
            BufferedImage image = ImageIO.read(new File(path));
          
            byte[] b = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            mat.put(0,0, b);
            //   new showResult(mat, "Original Image");
            
           String muser= mn.init1(mat,"D:/2019_Projects/Blind_Shopping_OCR/src/java/resources/haarcascades/haarcascade_frontalface_alt.xml",path,"test.png","test");
            
           File f=new File("D:/faceresult.txt");
           FileOutputStream fout= new FileOutputStream(f);
           
           fout.write(muser.getBytes());
           fout.close();
           if(muser.equals("") || muser==null)
           {
           muser="Could not recognize";
           }
            
                   System.out.println("###############"+muser);
                   out.print(muser);
                     
                     
                     
                     
                     }catch(Exception e){e.printStackTrace();}
		}
          }
          }catch(Exception e)
                                   {
          e.printStackTrace();
          }



%>