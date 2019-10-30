<%-- 
    Document   : StoreItemReg
    Created on : Jan 11, 2016, 4:37:16 PM
    Author     : win 7
--%>

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
    
        <h1>Blind Shopping OCR</a></h1>
    
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
          <li><a href="StoreItemReg.jsp">Store Item Registration</a></li>
       
         <li><a href="current_cart.jsp">Current Cart</a></li>
<!--         <li><a href="Billing.jsp">Item Bill</a></li>-->
         <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">

       <table>
           <form action="./storeItemRegister" method="post">
               <tr>
                   <td><label>Item Id</label></td>
                   <td><input type="text" name="itemid" id="itemid" value="<%=count%>"/></td>
               </tr>
               
               <tr>
                   <td><label>Item Name</label></td>
                   <td><input type="text" name="itname" id="itname"/></td>
               </tr>
               <tr>
                   <td><label>Item Description</label></td>
                   <td><input type="text" name="itdesc" id="itdesc"/></td>
               </tr>
               <tr>
                   <td><label>Item Manufacture</label></td>
                   <td><input type="text" name="itmanu" id="itmanu"/></td>
               </tr>
               <tr>
                   <td><label>Item Unit Price</label></td>
                   <td><input type="text" name="itprice" id="itprice"/></td>
               </tr>
               <tr>
                   <td><label>Item Type</label></td>
                   <td><input type="text" name="ittype" id="ittype"/></td>
               </tr>
               <tr>
                   <td></td>  <td><input type="submit" name="submit" value="Add" onclick="StoreItemReg.jsp"</td>
               </tr>
           </form>
           
       </table>   
  
  </div>
</body>
</html>
