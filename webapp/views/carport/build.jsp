<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<html>
<head>
  <title>Build Carport</title>
  
  <%@include file="/views/partial/header.jsp" %>

  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>Build Carport</h1>
          
            <form action="/carport/build" method="post">
                <div class="field-wrap">
                    <label>        
                    Select Roof Type
                    </label>
                      
                <select name="rtype">
                      <optgroup label="Choose Type">
                  <option selected value="flat">Flat</option>
                  <option option disabled value="triangle">Triangle (out of stock)</option>
                      </optgroup>
                </select>
                    </div>
               <div class="field-wrap">
            <label>
              Length(cm)
            </label>
            <input type="number" name="length" min = "240" max="1200" required autocomplete="off"/>
          </div>
            <div class="field-wrap">
            <label>
              Width(cm)
            </label>
            <input type="number" name="width" min = "240" max="1200" required autocomplete="off"/>
             
          </div>
            <div class="field-wrap">
            <label>
              Height(cm)
            </label>
            <input type="number" name="height" min = "240" max="1200" required autocomplete="off"/>
          </div>
           <button type="submit" class="button button-block">Generate</button>
            </form>
        </div>
    </div>
  </div>
      
  </div>

  
      
  <script src='/js/build.js'></script>
</body>
</html>