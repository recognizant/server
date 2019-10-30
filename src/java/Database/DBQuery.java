package Database;

import Logic.info;
import Logic.info1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sai
 */
public class DBQuery {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
 public ResultSet getuser() throws ClassNotFoundException, SQLException {

        int i = 0, j = 0;
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q = "select * from user";
        System.out.println("" + q);

        rs = st.executeQuery(q);

        return rs;



    }
    public ResultSet readRegID() throws ClassNotFoundException, SQLException {

        int i = 0, j = 0;
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q = "select * from gcm_key";
        System.out.println("" + q);

        rs = st.executeQuery(q);

        return rs;



    }
 public int getMaxTID() throws ClassNotFoundException, SQLException{
    
     int i=0;
     con=DBConnection.getConnection();
    String q="select count(*) from billing";
     st = con.createStatement();
      
        System.out.println(""+q);
        rs=st.executeQuery(q);
        if(rs.next())
        {
        i=rs.getInt(1);
        }
    return i;
    
    
    
    }
    public int getMaxTID_bill() throws ClassNotFoundException, SQLException{
    
     int i=0;
     con=DBConnection.getConnection();
    String q="select MAX(tid) from billing";
     st = con.createStatement();
      
        System.out.println(""+q);
        rs=st.executeQuery(q);
        if(rs.next())
        {
        i=rs.getInt(1);
        }
    return i;
    
    
    
    }
   
   
   public String get_bill_details() throws ClassNotFoundException, SQLException{
   
   String res="";
   
    con=DBConnection.getConnection();
    
    
    //int tid=getMaxTID_bill();
    String q="select * from current_cart ";//where tid='"+tid+"'
    String ids="",names="",qt="";
   st = con.createStatement();
   double t=0.0;
   rs=st.executeQuery(q);
   
   while(rs.next())
   {
   String items=rs.getString("item_id");
   String name=rs.getString("items_name");
   String qty=rs.getString("qty");
   String total=rs.getString("item_price");
   t+=Double.parseDouble(total)*Integer.parseInt(qty);
  ids+=items+"-";
  names+=name+"-";
  qt+=qty+"-";
   }
    res=ids+"##"+names+"##"+qt+"##"+t;
   
       System.out.println("............"+res);
   return res;
   }
   
     public int addBillData(String tid, String user,String items,String quantity,String amount,String total) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                Date d=new Date();
                String dat=d.toLocaleString();
        String q="insert into billing values ('"+tid+"','"+user+"','"+items+"','"+quantity+"','"+amount+"','"+total+"','"+dat+"')";
        System.out.println(">>"+q);
        st = con.createStatement();
      
        System.out.println(""+q);
        i=st.executeUpdate(q);
        
        return i;
    }
    public int updateRegId(String user, String regid) throws ClassNotFoundException, SQLException {
        int i = 0;
        String q1 = "select * from gcm_key where user='" + user + "'";
        System.out.println("" + q1);
        String q = "update gcm_key set reg_id='" + regid + "' where user='" + user + "'";
        System.out.println("" + q);
        con = DBConnection.getConnection();
        st = con.createStatement();
        rs = st.executeQuery(q1);
        if (rs.next()) {
            i = 1;
        }
        System.out.println(">>>" + i);
        if (i == 0) {
            String q2 = "insert into gcm_key values('" + user + "','" + regid + "')";
            System.out.println("" + q2);
            i = st.executeUpdate(q2);


        } else {
            i = st.executeUpdate(q);

        }
        return i;
    }
     public int add_user(String user) throws ClassNotFoundException, SQLException {
        int i = 0;
       
       
            String q2 = "insert into user values('" + user + "')";
            System.out.println("" + q2);
              con = DBConnection.getConnection();
        st = con.createStatement();
            i = st.executeUpdate(q2);


       
        return i;
    }
    
     public int update_cart(String id, String price,String qt) throws ClassNotFoundException, SQLException {
        int i = 0;
       int qn=Integer.parseInt(qt);
       double d=Double.parseDouble(price)/qn;
        String q = "update current_cart set cost='" + d + "' where item_id='" + id + "'";
        System.out.println("" + q);
        con = DBConnection.getConnection();
        st = con.createStatement();
       
            i = st.executeUpdate(q);

        
        return i;
    }
  public String get_item_details_map(String item) throws ClassNotFoundException, SQLException{
       con=DBConnection.getConnection();
         st = con.createStatement();
       String i_id="",b_id="",s_id="",rfid="",catg="";
       
       int row=0,col=0,block=0,shelf=0;
       
       String q="select * from store_item where item_name like '%"+item+"%'";
         System.out.println(""+q);
      
        rs=st.executeQuery(q);
    if(rs.next())
    {
    
    i_id=rs.getString("item_id");
    }
    
    String q1="select * from store_shelf where sf_item_id='"+i_id+"'";
         System.out.println(""+q1);
    st = con.createStatement();
        rs=st.executeQuery(q1);
    if(rs.next())
    {
    
    b_id=rs.getString("block_id");
    s_id=rs.getString("sf_num");
    rfid=rs.getString("sf_rfid_tag");
    }
    
    
           System.out.println("rfid="+rfid);
    
    String qq="select * from store_map where rfid='"+rfid+"'";
    
    
    rs=st.executeQuery(qq);
    
    while(rs.next())
    {
    row=rs.getInt("row");
    col=rs.getInt("column");
    block=rs.getInt("block");
    shelf=rs.getInt("shelf");
    catg=rs.getString("catg");
    
    }
    info.dblock=block+"";
    info.dshelf=shelf+"";
    info.drfid=rfid;
    info1.src_block=block;
    
    
           System.out.println("...................."+row+""+col+block+shelf+catg);
      add_current_status(1, block, shelf, rfid);
    
    
    String res=rfid+"-"+block+"-"+shelf;
    return res;
    }
  
  public String get_dir(String tag,String dest,String src) throws ClassNotFoundException, SQLException{
    String description="";
    
    con=DBConnection.getConnection();
      st = con.createStatement();
      String d="b"+dest;
       String qq="select * from map_corners where rfid='"+tag+"' and dest_block='"+d+"' and src_block='"+src+"'";
    
         System.out.println(""+qq);
    rs=st.executeQuery(qq);
    
    while(rs.next())
    {
  
    description=rs.getString("description");
   
    
    }
        return description;
    }
     
  
  public String get_Current_status() throws ClassNotFoundException, SQLException{
  String det="";
  con=DBConnection.getConnection();
         st = con.createStatement();
  
  String q="select * from current_status";
  rs=st.executeQuery(q);
  
  if(rs.next())
  {
  int status=rs.getInt("status");
  int dblock=rs.getInt("dblock");
  int dshelf=rs.getInt("dshelf");
  String drfid=rs.getString("drfid");
  
  det=status+"-"+dblock+"-"+dshelf+"-"+drfid;
  }
  return det;
  }
    public String get_block(String tag) throws ClassNotFoundException, SQLException{
    int block=0,shelf=0;
    
    con=DBConnection.getConnection();
      st = con.createStatement();
       String qq="select * from store_map where rfid='"+tag+"'";
    
        System.out.println(""+qq);
    rs=st.executeQuery(qq);
    
    while(rs.next())
    {
  
    block=rs.getInt("block");
    shelf=rs.getInt("shelf");
   
    
    }
    return block+"-"+shelf;
    }
     public String get_catg(String tag) throws ClassNotFoundException, SQLException{
     String catg="";
    
    con=DBConnection.getConnection();
      st = con.createStatement();
       String qq="select * from store_map where rfid='"+tag+"'";
    
    
    rs=st.executeQuery(qq);
    
    while(rs.next())
    {
  
    catg=rs.getString("catg");
   
    
    }
        return catg;
    }
     public String get_dir(String tag,String dest) throws ClassNotFoundException, SQLException{
    String description="";
    
    con=DBConnection.getConnection();
      st = con.createStatement();
      String d="B"+dest;
       String qq="select * from map_corners where rfid='"+tag+"' and dest_block='"+d+"'";
         System.out.println(""+qq);
    
    rs=st.executeQuery(qq);
    
    while(rs.next())
    {
  
    description=rs.getString("description");
   
    
    }
        return description;
    }
         public int add_current_status(int status, int dblock,int shelf,String rfid) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                Date d=new Date();
                String dat=d.toLocaleString();
                
                String qq="truncate current_status";
st.executeUpdate(qq);
                
                
        String q="insert into current_status values ('"+status+"','"+dblock+"','"+shelf+"','"+rfid+"')";
        System.out.println(">>"+q);
        st = con.createStatement();
      
        System.out.println(""+q);
        i=st.executeUpdate(q);
        
        return i;
    }
     public ResultSet readBillDetails(String tid) throws ClassNotFoundException, SQLException{
     
 int i=0,j=0;
 con=DBConnection.getConnection();
 st=con.createStatement();
 String q="select * from billing where tid='"+tid+"'";
 System.out.println(""+q);
 
 rs=st.executeQuery(q);
 
 return rs; 
     
     
     
     }

//      public int addBillData(String tid, String user,String items,String quantity,String amount,String total) throws ClassNotFoundException, SQLException
//    {
//          int i= 0;
//                con=DBConnection.getConnection();
//                Date d=new Date();
//                String dat=d.toLocaleString();
//        String q="insert into billing values ('"+tid+"','"+user+"','"+items+"','"+quantity+"','"+amount+"','"+total+"','"+dat+"')";
//        System.out.println(">>"+q);
//        st = con.createStatement();
//      
//        System.out.println(""+q);
//        i=st.executeUpdate(q);
//        
//        return i;
//    }
    public int insertDetails(String email, String mobile, String name, String pass) throws ClassNotFoundException, SQLException {
        int i = 0;
        try {
            con = DBConnection.getConnection();
            String q = "insert into user_details values ('" + email + "','" + mobile + "','" + name + "','" + pass + "')";
            System.out.println(">>" + q);
            st = con.createStatement();

            System.out.println("" + q);
            i = st.executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
            i = 2;
        }
        return i;
    }

    public int loginCheckAD(String userid, String password) throws ClassNotFoundException, SQLException {
        int i = 0;

        String q = "select * from login where user_id='" + userid + "' and password='" + password + "'";
        System.out.println("" + q);
        con = DBConnection.getConnection();
        st = con.createStatement();
        rs = st.executeQuery(q);
        while (rs.next()) {

            i = 1;

        }
        return i;
    }

    public String updatePass(String user, String op, String np) throws ClassNotFoundException, SQLException {

        int i = 0;
        String dbpass = "", status = "";
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q = "select * from login where user_id='" + user + "'";

        rs = st.executeQuery(q);
        if (rs.next()) {
            dbpass = rs.getString("password");

        }
        if (dbpass.equals(op)) {
            String qq = "update login set password='" + np + "' where user_id='" + user + "'";
            String qq1 = "update user_reg set password='" + np + "' where user_id='" + user + "'";
            st.executeUpdate(qq);
            st.executeUpdate(qq1);
            status = "ok";
        } else {
            status = "notok";

        }


        return status;
    }

    public int get_store_item_count(String sid) throws ClassNotFoundException, SQLException {

        int id = 0;
        con = DBConnection.getConnection();
        st = con.createStatement();

        String q = "select count(*) from store_item where store_id='" + sid + "'";
        rs = st.executeQuery(q);
        if (rs.next()) {

            id = rs.getInt(1);
        }
        return id;
    }

    public int updateLoction(String userid, String lat, String lon) throws ClassNotFoundException, SQLException {
        int i = 0;
        String q1 = "select * from userLocation where userid='" + userid + "'";
        String q = "update userLocation set lat='" + lat + "' where lon='" + lon + "'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        rs = st.executeQuery(q1);
        if (rs.next()) {
            i = 1;
        }
        System.out.println(">>>" + i);
        if (i == 0) {
            String q2 = "insert into userLocation values('" + userid + "','" + lat + "','" + lon + "')";
            i = st.executeUpdate(q2);


        } else {
            i = st.executeUpdate(q);

        }
        return i;
    }

    public String loginCheck(String name, String pass) throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        st = con.createStatement();
        String utype = "";
        String query = "select * from login where user_id='" + name + "' and password='" + pass + "'";
        System.out.println(query);
        rs = st.executeQuery(query);

        if (rs.next()) {
            utype = rs.getString("utype");
        }
        return utype;
    }

    public int addStoreData(String store_id, String store_name, String store_address,  String pass) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into store_registration values ('" + store_id + "','" + store_name + "','" + store_address + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);


        String q1 = "insert into login values ('" + store_id + "','" + pass + "','store')";
        System.out.println(">>" + q1);
        st = con.createStatement();

        System.out.println("" + q1);
        i = st.executeUpdate(q1);
        return i;
    }

    public int addEmpData(String storeid, String emp_id, String store_id, String emp_name, String emp_address, String emp_mobile, String emp_pass) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into store_emp_reg values ('" + storeid + "','" + emp_id + "','" + store_id + "','" + emp_name + "','" + emp_address + "','" + emp_mobile + "','" + emp_pass + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public int addStoreItem(String storeid, String item_id, String item_name, String item_desc, String item_manu, String item_price, String item_type) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into store_item values ('" + storeid + "','" + item_id + "','" + item_name + "','" + item_desc + "','" + item_manu + "','" + item_price + "','" + item_type + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public ResultSet getItems() throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        String q = "select * from store_item";
        st = con.createStatement();
        rs = st.executeQuery(q);



        return rs;
    }

    public ResultSet getRFIDs_item() throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        String q = "select * from rfid_details where rfid_cat='item'";
        st = con.createStatement();
        rs = st.executeQuery(q);



        return rs;
    }

    public ResultSet getRFIDs_direction() throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        String q = "select * from rfid_details where rfid_cat='direction'";
        st = con.createStatement();
        rs = st.executeQuery(q);



        return rs;
    }

    public int addStoreShelfData(String storeid, String block_id, String sf_num, String sf_item_id, String sf_rfid_tag) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into store_shelf values ('" + storeid + "','" + block_id + "','" + sf_num + "','" + sf_item_id + "','" + sf_rfid_tag + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public int addItemOfferData(String storeid, String item_id, String item_offer) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into item_offer values ('" + storeid + "','" + item_id + "','" + item_offer + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public int addrfidRegisterData(String storeid, String rfid_tag, String rfid_cat) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into rfid_details values ('" + storeid + "','" + rfid_tag + "','" + rfid_cat + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public int addStorePlanData(String storeid, String rfid_tag, String desc1) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into store_plan values ('" + storeid + "','" + rfid_tag + "','" + desc1 + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public String getRFIDDescription(String rfid) throws ClassNotFoundException, SQLException {
        String desc = "";
        String item = "";
        con = DBConnection.getConnection();
        String q = "select * from rfid_details where rfid_tag='" + rfid + "'";
        System.out.println("" + q);
        st = con.createStatement();
        rs = st.executeQuery(q);

        if (rs.next()) {
            desc = rs.getString("rfid_cat");
        }
        System.out.println(".........." + desc);
        if (desc.equals("direction")) {

            String q2 = "select * from store_plan where rfid_tag='" + rfid + "' ";

            st = con.createStatement();
            rs = st.executeQuery(q2);
            if (rs.next()) {
                desc = rs.getString("desc1");
            }

        } else if (desc.equals("item")) {

            String q3 = "select * from store_shelf where sf_rfid_tag='" + rfid + "'";
            System.out.println("....." + q3);
            st = con.createStatement();
            rs = st.executeQuery(q3);
            if (rs.next()) {
                item = rs.getString("sf_item_id");
            }

            String q4 = "select * from store_item where item_id='" + item + "'";
            rs = st.executeQuery(q4);
            if (rs.next()) {
                String item_name = rs.getString("item_name");
                String item_desc = rs.getString("item_desc");
                String manufaturer = rs.getString("item_manu");
                String price = rs.getString("item_price");
                String categ = rs.getString("item_type");
                desc = item+"=Item is, " + item_name + ". Item type is, " + categ + ". Manufacturer is, " + manufaturer + ". Item description is, " + item_desc + ". Price is rupees, " + price;
            }

        }


        return desc;
    }
public void place_order(String itm,String qty) throws ClassNotFoundException, SQLException{
   con = DBConnection.getConnection();
   st = con.createStatement();
   String name="";
   double item_price=0;
   String q="select * from store_item where item_id='"+itm+"'";
    System.out.println(""+q);
   rs=st.executeQuery(q);
   if(rs.next())
   {
   name=rs.getString("item_name");
   item_price=rs.getDouble("item_price");
   }
   //String qq="truncate current_cart";
  // st.executeUpdate(qq);
   String q1="insert into current_cart values('"+itm+"','"+name+"','"+qty+"','"+item_price+"','pending')";
    System.out.println(q1);
   st.executeUpdate(q1);


}
public String check_bal(String ac)
{
    String bal="";
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            String q1="select * from account where ac='"+ac+"'";
            rs=st.executeQuery(q1);
            if(rs.next())
            {
            bal=rs.getString("amount");
            
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
return bal;
}
public String pay(String ac,String total)
{
    String bal=check_bal(ac);
    String res="";
    if(Double.parseDouble(total)<=Double.parseDouble(bal))
    {
      try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            String q1="update current_cart set pay_status='paid'";
            st.executeUpdate(q1);
            double newbal=Double.parseDouble(bal)-Double.parseDouble(total);
             String q2="update account set amount='"+newbal+"'";
             st.executeUpdate(q2);
            res=newbal+"";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    else{
    
    res=bal;
    }
      
return res;
}
public ResultSet get_cart() throws ClassNotFoundException, SQLException{
 con = DBConnection.getConnection();
   st = con.createStatement();
String q="select * from current_cart";
    System.out.println(""+q);
rs=st.executeQuery(q);




return rs;
}
    public int addBillData(String tid, String user, String items, String amount) throws ClassNotFoundException, SQLException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "insert into billing values ('" + tid + "','" + user + "','" + items + "','" + amount + "')";
        System.out.println(">>" + q);
        st = con.createStatement();

        System.out.println("  valueof q" + q);
        i = st.executeUpdate(q);

        return i;
    }

    public int getStoreCount() throws SQLException, ClassNotFoundException {
        int i = 0;
        con = DBConnection.getConnection();
        String q = "select count(*) from store_registration";
        st = con.createStatement();

        System.out.println("" + q);
        rs = st.executeQuery(q);
        if (rs.next()) {
            i = rs.getInt(1);
        }
        return i;
    }

    public String get_item_details(String item) throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        String i_id = "", b_id = "", s_id = "";
        String q = "select * from store_item where item_name like '%" + item + "%'";
        System.out.println("" + q);
        st = con.createStatement();
        rs = st.executeQuery(q);
        if (rs.next()) {

            i_id = rs.getString("item_id");
        }
String desc="";
       String q4 = "select * from store_item where item_id='" + i_id + "'";
            rs = st.executeQuery(q4);
            if (rs.next()) {
                String item_name = rs.getString("item_name");
                String item_desc = rs.getString("item_desc");
                String manufaturer = rs.getString("item_manu");
                String price = rs.getString("item_price");
                String categ = rs.getString("item_type");
               // desc = i_id+"="+item+"=Item is, " + item_name + ". Item type is, " + categ + ". Manufacturer is, " + manufaturer + ". Item description is, " + item_desc + ". Price is rupees, " + price;
                desc = i_id+"="+ item_name + "=" + categ + "=" + manufaturer + "=" + item_desc + "=" + price;
                System.out.println(">>>"+desc);
            }
        return desc;
    }
}
