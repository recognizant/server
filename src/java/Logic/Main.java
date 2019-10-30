package Logic;





import java.awt.FileDialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;



import java.awt.image.DataBufferByte;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafaces.FaceRecognition;




public class Main{
    String name="",user="",msg="";
     String muser="";
	private CascadeClassifier faceCascade;
	private int absoluteFaceSize;
	static
	{
	//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	//	System.loadLibrary("C:/opencv/opencv/build/java/x64/opencv_java310.dll");
	System.load("C:/opencv/opencv/build/java/x64/opencv_java310.dll");
	}
	
	
	public void init(Mat frame,String path,String fpath,String image_name)
	{
            this.name=image_name;
           
		this.faceCascade = new CascadeClassifier();
		this.absoluteFaceSize = 0;
		this.faceCascade.load(path);
		detectAndDisplay(frame,fpath);
	//	new showResult(frame, "Detected");
	}
           public void initfd(Mat frame,String path,String fpath,String image_name,String owner)
	{
            this.name=image_name;
           
		this.faceCascade = new CascadeClassifier();
		this.absoluteFaceSize = 0;
		this.faceCascade.load(path);
		detectAndDisplay(frame,fpath);
		
	}
	public String init1(Mat frame,String path,String fpath,String image_name,String user)
	{
            this.name=image_name;
           this.user=user;
		this.faceCascade = new CascadeClassifier();
		this.absoluteFaceSize = 0;
		this.faceCascade.load(path);
		String s=detectAndDisplay1(frame,fpath,user);
		//new showResult(frame, "Detected");
                return s;
	}
	private void detectAndDisplay(Mat frame,String fpath)
	{
		MatOfRect faces = new MatOfRect();
		Mat grayFrame = new Mat();
		
		// convert the frame in gray scale
		Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(grayFrame, grayFrame);
		
		// compute minimum face size (20% of the frame height, in our case)
		if (this.absoluteFaceSize == 0)
		{
			int height = grayFrame.rows();
			if (Math.round(height * 0.2f) > 0)
			{
				this.absoluteFaceSize = Math.round(height * 0.2f);
			}
		}
		System.out.println(">>>>"+this.absoluteFaceSize);
		// detect faces
//		this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
//				new Size(this.absoluteFaceSize, this.absoluteFaceSize), new Size());
		this.faceCascade.detectMultiScale(grayFrame, faces);
				
		// each rectangle in faces is a face: draw them!
		Rect[] facesArray = faces.toArray();
		System.out.println("number of heads= "+facesArray.length);
//		for (int i = 0; i < facesArray.length; i++)
//			Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);
		ImageCropper ic=new ImageCropper();
                ArrayList alemails=new ArrayList();
		int i=0;
		for(Rect R:facesArray){
		
                   
                        Imgproc.rectangle(frame, new Point(R.x,R.y),new Point(R.x+R.width,R.y+R.height), new Scalar(0, 255, 0));
                        System.out.println("******"+i);
                        System.out.println("R.x.."+R.x);
                        System.out.println("R.y.."+R.y);
                        System.out.println("R.x+R.width="+R.width);
                        System.out.println("R.y+R.height="+R.height);
                        ic.crop(fpath, path_info.path_temp+name, R.x, R.y, R.width, R.height);
                        i++;
                      
                   
                          
		}
                
                
                
                
                int j=0;
                for(Rect R:facesArray){
                    try {
                        ImageResizer ir=new ImageResizer();
                        Image img = null;
                        img = ImageIO.read(new File(path_info.path_temp+name));
                        BufferedImage tempPNG = null;
                        tempPNG = ir.resizeImage(img, 70, 70);
                        File newFilePNG = null;
                        newFilePNG = new File(path_info.path_probes+name);
                        ImageIO.write(tempPNG, "png", newFilePNG);
                        
                        
                       
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
     
                
}
	public String detectAndDisplay1(Mat frame,String fpath,String user)
	{
            try{
		MatOfRect faces = new MatOfRect();
		Mat grayFrame = new Mat();
		
		// convert the frame in gray scale
		Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(grayFrame, grayFrame);
		
		// compute minimum face size (20% of the frame height, in our case)
		if (this.absoluteFaceSize == 0)
		{
			int height = grayFrame.rows();
			if (Math.round(height * 0.2f) > 0)
			{
				this.absoluteFaceSize = Math.round(height * 0.2f);
			}
		}
		System.out.println(">>>>"+this.absoluteFaceSize);
		// detect faces
//		this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
//				new Size(this.absoluteFaceSize, this.absoluteFaceSize), new Size());
		this.faceCascade.detectMultiScale(grayFrame, faces);
				
		// each rectangle in faces is a face: draw them!
		Rect[] facesArray = faces.toArray();
		System.out.println("number of heads= "+facesArray.length);
                if(facesArray.length>0)
                {
//		for (int i = 0; i < facesArray.length; i++)
//			Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);
		ImageCropper ic=new ImageCropper();
                ArrayList alemails=new ArrayList();
		int i=0;
		for(Rect R:facesArray){
		
                   
                        Imgproc.rectangle(frame, new Point(R.x,R.y),new Point(R.x+R.width,R.y+R.height), new Scalar(0, 255, 0));
                        System.out.println("******"+i);
                        System.out.println("R.x.."+R.x);
                        System.out.println("R.y.."+R.y);
                        System.out.println("R.x+R.width="+R.width);
                        System.out.println("R.y+R.height="+R.height);
                        System.out.println("src--------------"+fpath);
                        System.out.println("dest-----------"+path_info.path_temp+name);
                        ic.crop(fpath, path_info.path_temp+name, R.x, R.y, R.width, R.height);
                        i++;
                      
                   
                          
		}
//                int j=0;
//                for(Rect R:facesArray){
//                    try {
//                        ImageResizer ir=new ImageResizer();
//                        Image img = null;
//                        img = ImageIO.read(new File(path_info.path+"probes/"+j+name));
//                        BufferedImage tempPNG = null;
//                        tempPNG = ir.resizeImage(img, 70, 70);
//                        File newFilePNG = null;
//                        newFilePNG = new File(path_info.path+"probes/"+j+name);
//                        ImageIO.write(tempPNG, "png", newFilePNG);
//                        
//                        
//                       
//                    } catch (IOException ex) {
//                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
              Thread.sleep(4000);
           
 ImageResizer ir=new ImageResizer();
                        Image img = null;
                        img = ImageIO.read(new File(path_info.path_temp+name));
                        BufferedImage tempPNG = null;
                        tempPNG = ir.resizeImage(img, 70, 70);
                        File newFilePNG = null;
                        
                        newFilePNG = new File(path_info.path_temp1+"test.jpg");
                        ImageIO.write(tempPNG, "jpg", newFilePNG);


              
      String a[]=FaceRecognition.test("test.jpg");//path_info.path+"gallery/"+
                 System.out.println("**matching user**"+a[0]);
                  System.out.println("**dis**"+a[1]);
                 
                  boolean flag=false;
                  if(a[0].contains(user) && Double.parseDouble(a[1])<.3)
                  {
                      flag=true;
                      
                      muser=a[0].substring(0, (a[0].indexOf("_")-1));
                      System.out.println("properly matched");
                  }
                 
                  if(flag==true)//Double.parseDouble(a[1])<.4)
                  {
                      
                     System.out.println("properly matched");
                  }
                  else{
                      muser="Unknown";
                      System.out.println("Not Matched");
         
                  }
           /*       else{
                   // if unknown
                                    
                           try{  
 ImageResizer ir=new ImageResizer();
                        Image img = null;
                        img = ImageIO.read(new File(path_info.path+"gallery/"+name));
                        BufferedImage tempPNG = null;
                        tempPNG = ir.resizeImage(img, 70, 70);
                        File newFilePNG = null;
                        
                        newFilePNG = new File(path_info.pathw+"img.jpg");
                        ImageIO.write(tempPNG, "png", newFilePNG);
                        newFilePNG = new File(path_info.pathw1+"img.jpg");
                        ImageIO.write(tempPNG, "png", newFilePNG);

              }catch(Exception e)
              {
              e.printStackTrace();
              }
                      System.out.println("adding "+user);
                   try {
                File f=new File(path_info.path+"face.txt");
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(user.getBytes());
                fout.close();
                
                
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                
                 
                 
                  }*/
                  
                }     
        }catch(Exception e) 
        {
        e.printStackTrace();
        }
        
      return muser;            
}
	private String  browse()
    {
        JFrame frame = new JFrame("Select Image");
        frame.setBounds(0,0,500,500);
        FileDialog fd = new FileDialog(frame,"Select Image",FileDialog.LOAD);
        fd.show();
        String path = fd.getDirectory()+fd.getFile();
        
        return path;
    }
}