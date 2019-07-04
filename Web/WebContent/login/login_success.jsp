<%@ page language= "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8" %>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >   
<html>   
  <head>   
   <title> My JSP 'login_failure.jsp' starting page </title>   
   <meta http-equiv= "content-type" content = "text/html; charset=UTF-8">   
   <meta http-equiv= "pragma" content = "no-cache">   
   <meta http-equiv= "cache-control" content = "no-cache">   
   <meta http-equiv= "expires" content = "0">   
   <meta http-equiv= "keywords" content = "keyword1,keyword2,keyword3">   
   <meta http-equiv= "description" content = "This is my page">   
  </head>   
  <body>   
   <%   
    String userName = ( String ) session.getAttribute ( "userName" ) ;
    String userNames = ( String ) session.getAttribute("userName");
   %>
   <input type="hidden" value="userNames">
   <div align = center>   
    <%=userName%>
   		登录成功！
    <%  
        //转向语句  
        response.setHeader("Refresh", "1;URL=/Web/frame/index");
    %>
  </div>   
  </body>   
</html>  