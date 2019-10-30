
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath(); 


String msg="";

if(session.getAttribute("msg")!=null)
       {

msg=session.getAttribute("msg").toString();

}
String userid="";

if(session.getAttribute("userid")!=null)
       {

userid=session.getAttribute("userid").toString();

}
DBQuery db=new DBQuery();
ResultSet rs=db.getuser();

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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
        <li class="active"><a href="index.jsp">Home</a></li>
       
       
       
        <li><a href="StoreItemReg.jsp">Store Item Registration</a></li>
    
         <li><a href="current_cart.jsp">Current Cart</a></li>
         <li><a href="add_image.jsp">Add Image</a></li>
         <li><a href="view_user.jsp">View User</a></li>
         <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">
<%=msg%>
<%
    while(rs.next())
    {
    
    String name=rs.getString("name");
            
                 %>                            
		  <form action="upload_photo.jsp" method="post">
                <table>
               <tr>
                   <td>User ID</td>
                   <td><input type="text" name="uname" id="uname" value="<%=name%>" readonly="true"/></td>
               <input type="hidden" name="name" value="<%=name%>"/>
               <td><input type="submit" name="submit" value="Proceed"/></td>
               </tr>
        
       </table>   
                  </form>
                   <%
                       }
                       %>
    
  
  </div>
</body>
</html>
