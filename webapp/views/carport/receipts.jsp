<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Domain.CarportOrder"%>

<html>
    <head>
        <title>Receipts</title>
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
                        <h1>Receipt List</h1>
                        <c:choose>
                            <c:when test="${not empty purchaseList}">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>

                                            <th>Title</th>
                                            <th>Date</th>
                                            <th>Price</th>
                                            <th>Height</th>
                                            <th>Width</th>
                                            <th>Length</th>
                                            <th>Type</th>
                                            <th>Roof</th>
                                        </tr>
                                    </thead>
                                    <c:forEach var="purchase" items="${purchaseList}">
                                        <tr>                               
                                            <td>${purchase.title}</td>
                                            <td>${purchase.date}</td>
                                            <td>${purchase.price}kr</td>
                                            <td>${purchase.height}</td>
                                            <td>${purchase.width}</td>
                                            <td>${purchase.length}</td>
                                            <td>${purchase.cptype}</td>
                                            <td>${purchase.rooftype}</td>
                                        <form action = "ViewReceipt" method = "POST">
                                            <td> <button class="btnv" name="cpid" type="number" value="${purchase.carportID}">View</button></td>
                                        </form>
                                        </form></td>
                                        </tr>
                                    </c:forEach></table>
                                </c:when> 
                                <c:otherwise> 
                                <td>You have made no purchases!</td>
                            </c:otherwise> 
                        </c:choose>
                    </div>
                </div>
            </div>

        </div>



        <script src='/js/build.js'></script>
    </body>
</html>