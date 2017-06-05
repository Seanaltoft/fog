<%-- 
    Document   : index
    Created on : Apr 6, 2017, 11:38:10 AM
    Author     : petermihok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Home Page</title>
        <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
        <%@include file="/views/partial/header.jsp" %>
    </head>

    <body>

        <%@include file="/views/partial/nav.jsp" %>

        <div class="cover">
            <h1>Search your carport</h1>
            <form>
                <input placeholder="Search..">
            </form>
        </div>

        <div class="welcome">
            <h1>Welcome to Fog Assignment Page</h1>
            <h2>We hope our teamwork, will help you to choose your desired carport!</h2>
        </div>

        <div class="content">
            <div class="fleft">
                <p class="fp">
                    Where can I get some?
                    There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. 
                    If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. 
                    All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. 
                    It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. 
                    The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.             
                </p>
                <a href="#" class="read-more">Read more...</a>
            </div>
            <div class="fright">
                <img src="img/flatroof.jpg" class="fi">
            </div>
            <div class="fright">
                <p class="fp">
                    Where can I get some?
                    There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. 
                    If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. 
                    All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. 
                    It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. 
                    The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.
                </p>
                <a href="#" class="read-more">Read more...</a>
            </div>
            <div class="fleft">
                <img src="img/triangleroof.jpg" class="fi">       
            </div>
        </div>

        <div class="pre-footer">
            <h2>Newsletter</h2>
            <p>Get the lates news and special offers</p>
            <ul class="line-up">
                <li>
                    <button class="newsletter">Get it!</button>
                </li>
                <li>
                    <input placeholder="Your email">
                </li>
            </ul>   
        </div>

        <div class="footer">
            <h2>Check us also here!</h2>
            <ul class="footer-ul">
                <li><a class="icons"><i class="fa fa-instagram fa-lg"></i>INSTAGRAM</a></li>
                <li><a class="icons"><i class="fa fa-linkedin-square fa-lg"></i>LINKEDIN</a></li>
                <li><a class="icons"><i class="fa fa-facebook fa-lg"></i>FACEBOOK</a></li>
            </ul>
            <p>Johannes Fog A / S - Firskovvej 20-2800 Lyngby - Reg. 16314439</p>
        </div>

    </body>
</html>

