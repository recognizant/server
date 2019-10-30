<%-- 
    Document   : StoreShelf
    Created on : Jan 11, 2016, 4:45:58 PM
    Author     : win 7
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath();  

String userid="";

if(session.getAttribute("userid")!=null)
       {

userid=session.getAttribute("userid").toString();

}

int count=0;
DBQuery dbq=new DBQuery();
count =dbq.get_store_item_count(userid);
count++;


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="<%= projPath%>/styles/layout.css" type="text/css">

</head>
<body id="top">
<div class="wrapper col1">
  <div id="header">
    
        <h1>RFID Store</a></h1>
    
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
        <li><a href="StoreItemReg.jsp">Store Item Registration</a></li>
        <li><a href="StoreShelf.jsp">Shelf Details</a></li>
    
         <li><a href="RfidReg.jsp">RFID Registration</a></li>
         <li><a href="StorePlan.jsp">Store Plan Registration</a></li>
         <li><a href="current_cart.jsp">Current Cart</a></li>
         <li><a href="Billing.jsp">Item Bill</a></li>
         <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">
<%


ResultSet rs=dbq.getItems();
ResultSet rs1=dbq.getRFIDs_item();

%>
       <table>
           <form action="./storeShelfRegister" method="post">
               <tr>
                   <td><label>Block Id</label></td>
                   <td><input type="text" name="bid" id="bid"/></td>
               </tr>
               
               <tr>
                   <td><label>Shelf Number</label></td>
                   <td><input type="text" name="sfnum" id="sfnum"/></td>
               </tr>
               <tr>
                   <td><label>Shelf Item Id</label></td>
                   <td><select name="sfitid" >
                           <option value="select">Select Item</option>
                           <%
                           while(rs.next())
                                                             {
                               String itemID=rs.getString("item_id");
                               String name=rs.getString("item_name");
                           
                               
                           %>
                           
                           <option value="<%=itemID%>"><%=itemID%>   <%=name%></option>
                           <%}%>
                       </select>
                       
                       
                     </td>
               </tr>
               <tr>
                   <td><label>Shelf RFID Tag</label></td>
                   <td><select name="sftag" >
                            <option value="select">Select RFID</option>
                           <%
                           while(rs1.next())
                                                             {
                               String rfid=rs1.getString("rfid_tag");
                             
                           
                               
                           %>
                           
                           <option value="<%=rfid%>"><%=rfid%></option>
                           <%}%>
                       </select></td>
               </tr>
              
               
               <tr>
                   <td><input type="submit" name="submit" value="Register"</td>
               </tr>
           </form>
           
       </table>   
  
  </div>
</body>
</html>
