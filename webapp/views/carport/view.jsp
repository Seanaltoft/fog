<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<html>
<head>
  <title>View Carport</title>
  
  <%@include file="/views/partial/header.jsp" %>

  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>View Carport</h1>
          
            
        </div>
          <font color="white">
          UUID: ${CusCP.UUID}</br>
          Length: ${CusCP.length}cm </br>
          Width: ${CusCP.width}cm </br>
          Height: ${CusCP.height}cm </br>
          RoofType: ${CusCP.roofType}cm </br>
          CarportType: ${CusCP.carportType} </br>
          
          </font>
           <form action = "order" method = "POST">
             <button name="cpid" type="number" value="${CusCP.carportID}" onclick="return confirm('Are you sure?')" class="button button-block" >Add to cart</button>
           </form>
    </div>
  </div>
      
  </div>

  
      
  <script src='/js/build.js'></script>
</body>
</html>