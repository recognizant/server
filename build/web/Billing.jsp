<%-- 
    Document   : Billing
    Created on : Jan 11, 2016, 4:58:53 PM
    Author     : win 7
--%>

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
    <script>
        function validate() {
         var numbers = /^[0-9]+$/;  
      if(tid.value.match(numbers))  
      {  
      alert('Your Registration number has accepted....');  
      document.form1.text1.focus();  
      return true;  
      }  
      else  
      {  
      alert('Please input numeric characters only');  
      document.form1.text1.focus();  
      return false;  
      }
      var letters = /^[A-Za-z]+$/;  
      if(user.value.match(letters))  
      {  
      alert('Your name have accepted : you can try another');  
      return true;  
      }  
      else  
      {  
      alert('Please input alphabet characters only');  
      return false;  
      } 
      
      var numbers = /^[0-9]+$/;  
      if(amount.value.match(numbers))  
      {  
      alert('Your Registration number has accepted....');  
      document.form1.text1.focus();  
      return true;  
      }  
      else  
      {  
      alert('Please input numeric characters only');  
      document.form1.text1.focus();  
      return false;  
      }
            
        }
    
 
    </script>
<div class="wrapper col1">
  <div id="header">
    
        <h1>Blind Shopping OCR</a></h1>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
      <li><a href="StoreItemReg.jsp">Store Item Registration</a></li>
       
         <li><a href="current_cart.jsp">Current Cart</a></li>
         <li><a href="Billing.jsp">Item Bill</a></li>
         <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>
<% 

int count=0;

DBQuery dbq=new DBQuery();
count=dbq.getMaxTID();
count++;



ResultSet rs=dbq.getItems();


%>
</div>
 <script>
        var itemname = null;
        var amount = null;
        var quant = null;
        var tot = null;
        var count = null;
        var uname = null;
        function add(p)
        {
            console.log(p);
            $('#amount').val(p);
        }
        
        function details()
        {
            var tid = $('#tid').val();
            var name = $('#user').val();
            var itemName = $('#itemid').val();
            var amt = $('#amount').val();
            var quantity = $('#quantity').val();
            var total = amt*quantity;
           console.log(itemName+"---"+amt+"---"+quantity); 
           
           $('#details').append("<tr><td id='itemname'>"+itemName+"</td><td id='amount'>"+amt+"</td><td id='quant'>"+quantity+"</td><td id='tot'>"+total+"</td></tr>");
           if(itemname == null)
               {
           itemname = itemName+"-";
           amount = amt+"-";
           quant = quantity+"-";
           tot = total+"-";
           count = tid+"-";
           uname = name+"-";
           }
           else
               {
                   itemname += itemName+"-";
           amount+= amt+"-";
           quant += quantity+"-";
           tot += total+"-";
           count+=tid+"-";
           uname+=name+"-";
               }
        }
        
        function send()
        {
            
            console.log(itemname);
            console.log(amount);
            console.log(quant);
            console.log(tot);
            var data = "itemname="+itemname+"&amount="+amount+"&quantity="+quant+"&tot="+tot+"&tid="+count+"&uname="+uname;
            $.ajax({
                url:'billing_arr',
                type:'POST',
                data:data,
                success:function(data)
                {
                    alert("Bill Information Updated");
                    if(data === "Bill_updated")
                    {
                       window.location = "index.jsp"; 
                    }
                }
            })
            
            
        }
    </script>
   <div style="margin-top: 85px; margin-left: 83px;">
<%=msg%>
       <table>
<!--           <form action="./billing" method="post" onsubmit="return validate();" >-->
               <tr>
                   <td><label>TId</label></td>
                   <td><input type="text" name="tid" id="tid" value="<%=count%>"/></td>
               </tr>
               <tr>
                   <td><label>User</label></td>
                   <td><input type="text" name="user" id="user"/></td>
               </tr>
               <tr>
                   <td><label>Items</label></td>
                   
                   <td><select name="itemid" id="itemid">
                           <option value="select">Select Item</option>
                           <%
                           while(rs.next())
                                                             {
                               String itemID=rs.getString("item_id");
                               String name=rs.getString("item_name");
                             String price=rs.getString("item_price");
                               
                           %>
                           
                           <option value="<%=name%>" onclick="add(<%=price%>);"><%=itemID%>   <%=name%></option>
                           <%}%>
                       </select>
                      </td>
                  
               </tr>
               <tr>
                   <td><label>Amount</label></td>
                   <td><input type="text" name="amt" id="amount"/></td>
               </tr>
               <tr>
                   <td><label>Quantity</label></td>
                   <td><input type="text" name="quantity" id="quantity"/></td>
               </tr>
               <tr>
                   <td></td> <td><input type="button" name="add" id="add" value="Add" onclick="details();"</td>
               </tr>
          
           
       </table>   
                     
                       <table id="details">
                           
                           <tr><td>Item Name</td><td>Amount</td><td>Quantity</td><td>Total</td></tr>
                       </table>
                       <input type="button" value="Update Bill" onclick="send()"/>
                       <form action="./viewBill" method="post">
                           <input type="hidden" name="tid" value="<%=count%>"/>
                           <input type="submit" name="submit" value="View Bill"/>
                           
                           
                       </form>       
  </div>
</body>
</html>