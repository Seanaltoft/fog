<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Domain.Product"%>

<html>
<head>
  <title>ProductionList</title>
  
<%@include file="/views/partial/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
       
          <div id="build-carport">   
            <h1>Production List</h1>
             <b>List:</b>
       <c:if test="${not empty productList}">
      <table class="sortable">
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
        <c:forEach var="product" items="${productList}">
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
                                 <form action = "ProductionView" method = "POST">
                                <td> <button name="oid" type="number" value="${product.orderid}">View</button></td>
                                   </form>
                        
                                </form></td>
                        </tr>
                </c:forEach></table>
        </c:if>
        </div>
    </div>
  </div>
      
  </div>

  
      <script src="/js/sorttable.js"></script>
</body>
</html>
 
  