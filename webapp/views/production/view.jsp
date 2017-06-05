<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Domain.Product"%>

<html>
<head>
  <title>View</title>
  
<%@include file="/views/partial/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>View Production</h1>
            <b>Current Order Status:</b>
  <c:forEach var="product" items="${productviewList}">                      
    <c:choose>
    <c:when test="${product.status=='Pending'}">
        This order is currently pending(Pending) 
        <br />
        <br />
       <form action = "CancelProduct" method = "POST">
           <button name="oid" type="number" value="${product.orderid}" onclick="return confirm('Are you sure you want to cancel this order?')" >Cancel Order</button>
       </form> 
        <form action = "AssignProduct" method = "POST">
            <button name="oid" type="number" value="${product.orderid}" onclick="return confirm('Are you sure you want to be assigned this order?')" >Process Order</button>
        </form>
    </c:when>  
         <c:when test="${product.status=='Processing'}">
        This order is currently being processed(Processing) by employee( ${product.empno} )
        <br />
        <br />
        <form action = "CompleteProduct" method = "POST">
            <button name="oid" type="number" value="${product.orderid}" onclick="return confirm('Are you sure you have completed this order?')" >Complete Order</button>
        </form>
        <form action = "CancelProduct" method = "POST">
            <button name="oid" type="number" value="${product.orderid}" onclick="return confirm('Are you sure you want to cancel this order?')" >Cancel Order</button>
        </form> 
    </c:when> 
         <c:when test="${product.status=='Cancelled'}">
        This order has been cancelled(Cancelled). 
        <br />
    </c:when>  
    <c:otherwise>      
        This order has been completed(Confirmed).        
        <br /> 
    </c:otherwise>
</c:choose>
        
        
        
                                       </c:forEach>
        
    
      <b>List:</b>
       <c:if test="${not empty productviewList}">
      <table class="table table-bordered">
          <thead>
            <tr>
              <th>Started</th>
              <th>OrderID</th>
              <th>Name</th>
              <th>address</th>
              <th>zipcode</th>
              <th>city</th>
               <th>EMPNO</th>
              <th>Status</th>
            </tr>
          </thead>
        <c:forEach var="product" items="${productviewList}">
                        <tr>                               
                                <td>${product.started}</td>
                                <td>${product.orderid}</td>
                                <td>${product.name}</td>
                                <td>${product.address}</td>
                                <td>${product.zipcode}</td>
                                <td>${product.city}</td>
                                <c:choose>
                                 <c:when test="${product.empno=='0'}">
                                     <td>Unassigned</td>
                                 </c:when> 
                                 <c:otherwise> 
                                <td>${product.empno}</td>
                                 </c:otherwise> 
                                </c:choose>
                                <td>${product.status}</td>                  
                                </form></td>
                        </tr>
                </c:forEach></table>
        </c:if>
        </div>
    </div>
  </div>
      
  </div>

  
      
  <script src='/js/build.js'></script>
</body>
</html>
 
  