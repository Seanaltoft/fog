<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Domain.CarportOrder"%>

<html>
<head>
  <title>Build Carport</title>
  <link rel="stylesheet" href="../../css/style.css">
  <%@include file="/views/partial/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>Cart</h1>
            <c:choose>
                                 <c:when test="${not empty orderList}">
         
             <table class="table table-bordered">
          <thead>
          <h1>
            <tr>
              <th>Title</th>
              <th>Width</th>
              <th>Length</th>
              <th>Height</th>
              <th>CarportType</th>
              <th>RoofType</th>
              <th>Price</th>
            </tr>
          
          </thead>
        <c:forEach var="product" items="${orderList}">
                        <tr>                             
                                <td>${product.title}</td>
                               <td>${product.width}</td>
                                <td>${product.length}</td>
                                <td>${product.height}</td>
                                <td>${product.carportType}</td>
                                <td>${product.roofType}</td>
                                <td>${product.price}</td>
                              <form action = "RemoveOrderServlet" method = "POST">
                                  <td> <button  class="btnx" name="oid" type="number" value="${product.orderid}" onclick="return confirm('Remove Order?')">X</button></td>
                                   </form>
                                     
                              
                        </tr>
                </c:forEach></table>
                                     Total Price: ${totalprice}kr
                                      <form action = "PurchaseOrdersServlet" method = "POST">
                                <td> <button class="btnp" name="orders" type="number" value="${orderList}" onclick="return confirm('Purchase Cart?')">Purchase</button></td>
                                   </form>
                                 </c:when> 
                                 <c:otherwise> 
                                <td>Cart is Empty!</td>
                                 </c:otherwise> 
            </c:choose>
        </div>
    </div>
  </div>
      
  </div>

  
      
  <script src='/js/build.js'></script>
</body>
</html>