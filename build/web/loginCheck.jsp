



<%@page import="Database.DBQuery"%>
<%
try{
String user=request.getParameter("user");
String pass=request.getParameter("pass");

//String lat=request.getParameter("lat");
//String lon=request.getParameter("lon");

System.out.println(">>>>"+user+"---------------"+pass);

DBQuery db=new DBQuery();
int i=db.loginCheckAD(user, pass);
String status="";
if(i==0)
       {
   
status="notok";
}
else{
     status=db.getStatus(user);
     String path=db.imageLoad(user);
    // db.updateLoction(user, lat, lon);
     if(status.equals(""))
                 {
     status="ok";
     }
     status=status+"-"+path;
//
}
//String utype=db.loginValidate(user, pass);

System.out.println(">>"+status);

out.print(status);
}catch(Exception e){
e.printStackTrace();
}
%>