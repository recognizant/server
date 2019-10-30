

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
         <form action="./add_user" method="post"  enctype="multipart/form-data">
      <table>
          
          
          <tr><td>Name</td><td><input type="text" name="name" id="name"/></td></tr>
          <tr>
                
              <td> Upload Employee Photo</td>
        
              <td>
        <input type="file" name="image" >
        </td>
                    
                    
                </tr>
          <tr><td></td><td><input type="submit" name="submit" value="Upload"/></td></tr>
          
          
          
      </table>
      </form>
  
  </div>
</body>
</html>
