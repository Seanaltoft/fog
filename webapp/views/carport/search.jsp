<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<html>
<head>
  <title>Search Carport</title>
  
  <%@include file="/views/partial/header.jsp" %>

  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>Search</h1>
          
            <form action="/carport/search" method="post">
                <div class="field-wrap">
                    </div>
               <div class="field-wrap">
            <label>
                Input Carport UUID:</b>
            </label>
            <input type="text" name="uuid" required autocomplete="off"/>
          </div>
           <button type="submit" class="button button-block">Search</button>
            </form>
        </div>
    </div>
  </div>
      
  </div>
</body>
</html>