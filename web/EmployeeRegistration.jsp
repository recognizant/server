<%-- 
    Document   : EmployeeRegistration
    Created on : Jan 11, 2016, 4:30:34 PM
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
    
        <h1>RFID Store</a></h1>
    
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="Login.jsp">Login</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">

       <table>
           <form action="./empRegister" method="post">
               <tr>
                   <td><label>Employee Id</label></td>
                   <td><input type="text" name="eid" id="eid"/></td>
               </tr>
               <tr>
                   <td><label>Store Id</label></td>
                   <td><input type="text" name="sid" id="sid"/></td>
               </tr>
               <tr>
                   <td><label>Name</label></td>
                   <td><input type="text" name="ename" id="ename"/></td>
               </tr>
               <tr>
                   <td><label>Address</label></td>
                   <td><input type="text" name="eadd" id="eadd"/></td>
               </tr>
               <tr>
                   <td><label>Mobile No</label></td>
                   <td><input type="text" name="mobno" id="mobno"/></td>
               </tr>
               <tr>
                   <td><label>Password</label></td>
                   <td><input type="password" name="pass" id="pass"/></td>
               </tr>
               
               <tr>
                   <td><input type="submit" name="submit" value="Register"</td>
               </tr>
           </form>
           
       </table>   
  
  </div>
</body>
</html>
