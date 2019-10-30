/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Database.DBQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import la.matrix.Matrix;
import la.matrix.SparseMatrix;
import ml.clustering.KMeans;
import ml.options.KMeansOptions;

/**
 *
 * @author Anspro
 */
public class K_Means {
    public static  ArrayList<String> items = new ArrayList<String>();
     public static ArrayList<String> cost = new ArrayList<String>();
    private ArrayList<double[]> data1 = new ArrayList<double[]>();
    public static String[] vals = {"Good Day","Dairy Milk","Colgate","Dove","Carrot","Apple","Milky Mist","Butter","Axe"};
    public K_Means() throws SQLException {
       Query();
        Dataset();
//        Cluster();
    }
    
    public void Dataset() throws SQLException
    {
        for(int i=0;i<items.size();i++)
        {
            for(int j=0;j<vals.length;j++)
            {
                if(items.get(i).equalsIgnoreCase(vals[j]))
                {
                    System.out.println(">>>>"+cost.get(i));
                    data1.add(new double[]{j,cost(Integer.parseInt(cost.get(i)))});
                }
            }
        }
        
        for(int i=0;i<data1.size();i++)
        {
            double[] d = data1.get(i);
            for(int j=0;j<d.length;j++)
            {
                 System.out.print(data1.get(i)[j]+"\t");
            }
            System.out.println();
            System.out.println();
        }
       
    }
    
    
    public double cost(int cost)
    {
        double val = 0;
        if(cost<=400)
        {
           val = 0.0;
        }
        else if(cost>400 && cost<=800)
        {
           val = 0.1;
        }
        else if(cost>800 && cost<=1200)
        {
            val = 0.2;
        }
        else if(cost>1200 && cost<=1600)
        {
            val = 0.3;
        }
        else if(cost>1600 && cost<=2000)
        {
            val = 0.4;
        }
        return val;
    }
    public void Query() throws SQLException
    {
        Connection con = getConnection();
        Statement st = con.createStatement();
        
        String q = "select * from billing";
        ResultSet rs = st.executeQuery(q);
        while(rs.next())
        {
            String it=rs.getString("items");
            String itm[]=it.split("-");
            for(int i=0;i<itm.length;i++)
            {
         items.add(itm[i]);   
            }
            String amt=rs.getString("amount");
            String am[]=amt.split("-");
            for(int i=0;i<am.length;i++)
            {
         cost.add(am[i]);   
            }
          
        }
    }
    
      public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blind_shopping", "root","root");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
      
      public ArrayList<double[]> Cluster()
      {
          double[][] data =new double[data1.size()][2];
//          for(int i=0;i<data.length;i++)
//          {
//              for(int j=0;j<data.length;j++)
//              {
////                  data[i][j] = 
//              }
//          }
          
          for(int i=0;i<data1.size();i++)
          {
              double[] d = data1.get(i);
//              System.out.println(">>>"+d.length);
              for(int j=0;j<d.length;j++)
              {
                  data[i][j] = d[j];
              }
          }

KMeansOptions options = new KMeansOptions();
options.nClus = 2;
options.verbose = true;
options.maxIter = 100;

KMeans KMeans= new KMeans(options);

KMeans.feedData(data);
// KMeans.initialize(null);
Matrix initializer = null;
initializer = new SparseMatrix(3, 2);
initializer.setEntry(0, 0, 1);
initializer.setEntry(1, 1, 1);
initializer.setEntry(2, 0, 1);
KMeans.clustering(null); // Use null for random initialization

System.out.println("Indicator Matrix:");
          System.out.println(KMeans.getIndicatorMatrix().toString());
          
          String det=KMeans.getIndicatorMatrix().toString();
          return data1;
      }
      
      public static void main(String[] args) throws SQLException {
        K_Means km=new K_Means();
        
//        km.Query();
//        String det=km.Dataset();
//        
        
        ArrayList<double[]> arr=km.Cluster();
       
        
      
        Random r=new Random();
        
        int n1=r.nextInt(arr.size());
        int n2=r.nextInt(arr.size());
        
        while(n2==n1)
        {
        n2=r.nextInt(n2);
        }
        int n3=r.nextInt(arr.size());
        while(n3==n2 || n3==n2)
        {
        
        n3=r.nextInt(n3);
        }
        int ii1=0,ii2=0,ii3=0;
        
        
        ArrayList al=new ArrayList();
       
        String items[]=new String[3];
        String costs[]=new String[3];
        int k=0,kk=0;
          System.out.println("n1="+n1);
          System.out.println("n2="+n2);
          System.out.println("n3="+n3);
          System.out.println("arr.size()="+arr.size());
            double[] d1 = arr.get(n1);
            
            items[0]=d1[0]+"";
            costs[0]=d1[1]+"";
            
            System.out.println("");
            double[] d2 = arr.get(n2);
            items[1]=d2[0]+"";
            costs[1]=d2[1]+"";
            
            double[] d3 = arr.get(n3);
            items[2]=d3[0]+"";
            costs[2]=d3[1]+"";
            for(int i=0;i<3;i++)
            {
                System.out.println(""+items[i]);
                System.out.println(""+costs[i]);
            
            }
            
            double nn1=Double.parseDouble(items[0]);
            double nn2=Double.parseDouble(items[1]);
            double nn3=Double.parseDouble(items[2]);
            
            
            int i1=(int)nn1;
            int i2=(int)nn2;
            int i3=(int)nn3;
            
            System.out.println(i1+"-------------"+i2+"---------"+i3);
            String cui1=vals[i2];
            String cui2=vals[i1];
            String cui3=vals[i3];
               System.out.println(cui1+"--"+cui2+"--------------"+cui3);  
                double nnn1=Double.parseDouble(costs[0]);
            double nnn2=Double.parseDouble(costs[1]);
            double nnn3=Double.parseDouble(costs[2]);
            
            
            int in1=(int)nnn1;
            int in2=(int)nnn2;
            int in3=(int)nnn3;
            
            System.out.println(in1+"-------------"+in2+"---------"+in3);
            String ic1=vals[in1];
            String ic2=vals[in2];
            String ic3=vals[in3];
               System.out.println(ic1+"--"+ic2+"--------------"+ic3);     
               

        
        }
      
      
      public String get_popular_items() throws SQLException{
      String p_items="";
      
        K_Means km=new K_Means();
        
//        km.Query();
//        String det=km.Dataset();
//        
        
        ArrayList<double[]> arr=km.Cluster();
       
        
      
        Random r=new Random();
        
        int n1=r.nextInt(arr.size());
        int n2=r.nextInt(arr.size());
        
        while(n2==n1)
        {
        n2=r.nextInt(n2);
        }
        int n3=r.nextInt(arr.size());
        while(n3==n2 || n3==n2)
        {
        
        n3=r.nextInt(n3);
        }
        int ii1=0,ii2=0,ii3=0;
        
        
        ArrayList al=new ArrayList();
       
        String items[]=new String[3];
        String costs[]=new String[3];
        int k=0,kk=0;
          System.out.println("n1="+n1);
          System.out.println("n2="+n2);
          System.out.println("n3="+n3);
          System.out.println("arr.size()="+arr.size());
            double[] d1 = arr.get(n1);
            
            items[0]=d1[0]+"";
            costs[0]=d1[1]+"";
            
            System.out.println("");
            double[] d2 = arr.get(n2);
            items[1]=d2[0]+"";
            costs[1]=d2[1]+"";
            
            double[] d3 = arr.get(n3);
            items[2]=d3[0]+"";
            costs[2]=d3[1]+"";
            for(int i=0;i<3;i++)
            {
                System.out.println(""+items[i]);
                System.out.println(""+costs[i]);
            
            }
            
            double nn1=Double.parseDouble(items[0]);
            double nn2=Double.parseDouble(items[1]);
            double nn3=Double.parseDouble(items[2]);
            
            
            int i1=(int)nn1;
            int i2=(int)nn2;
            int i3=(int)nn3;
            
            System.out.println(i1+"-------------"+i2+"---------"+i3);
            String cui1=vals[i2];
            String cui2=vals[i1];
            String cui3=vals[i3];
               System.out.println(cui1+"--"+cui2+"--------------"+cui3);  
                double nnn1=Double.parseDouble(costs[0]);
            double nnn2=Double.parseDouble(costs[1]);
            double nnn3=Double.parseDouble(costs[2]);
            
            
            int in1=(int)nnn1;
            int in2=(int)nnn2;
            int in3=(int)nnn3;
            
            System.out.println(in1+"-------------"+in2+"---------"+in3);
            String ic1=vals[in1];
            String ic2=vals[in2];
            String ic3=vals[in3];
               System.out.println(ic1+"--"+ic2+"--------------"+ic3);     
               p_items=ic1+"="+ic2+"="+ic3;

        return p_items; 
      
      }
      
      
        }

