<%-- 
    Document   : StoreItemReg
    Created on : Jan 11, 2016, 4:37:16 PM
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
           
               <tr>
                   <td><label>Item Id</label></td>
                   <td><label>Item Name</label></td>
                   <td><label>Quantity</label></td>
                   <td><label>Total</label></td>
               </tr>
       <%
       DBQuery db=new DBQuery();
       double tot=0;
       ResultSet rs=db.get_cart();
       while(rs.next())
                     {
       
       String i=rs.getString("item_id");
       String n=rs.getString("items_name");
       String q=rs.getString("qty");
       String c=rs.getString("item_price");
       System.out.println(""+i+"="+n+"=="+q);
       tot+=(Double.parseDouble(c)*Integer.parseInt(q));
       
       %>
     
       <form action="./update_cart" method="post">
               <tr>
               <input type="hidden" name="id" value="<%=i%>"/>
               <input type="hidden" name="q" value="<%=q%>"/>
                   <td><%=i%></td>
                   <td><%=n%> </td>
                   <td><%=q%> </td>
                   <%
                   
                   if(n.equals("Apple") || n.equals("Carrot"))
                                             {
                   %>
                   <td><input type="text"  name="price" value="<%=(Double.parseDouble(c)*Integer.parseInt(q))%>"/> </td>
                    <td><input type="submit" name="submit" value="Update"/></td>
                   
                   <%
                                     }
             else{
                
                           %>
                              <td><%=(Double.parseDouble(c)*Integer.parseInt(q))%></td>
                  
                              <%
                                                           }
                              %>
                              
               </tr>
       </form>  
             
          <%}%>
           <tr>
               <td></td>
                   <td> </td>
                   <td> </td>
                   <td><%=tot%> </td>
                  
           </tr>
       </table>   
  
  </div>
</body>
</html>
