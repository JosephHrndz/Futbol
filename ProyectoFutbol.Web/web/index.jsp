<%-- 
    Document   : index
    Created on : 27 ago 2023, 14:53:08
    Author     : JOSEPH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProyectoFutbol.Web.Utils.*"%>
<% //if (SessionUser.isAuth(request) == false) {
        // response.sendRedirect("Usuario?accion=login");
    //}
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Home</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container"> 
            <div class="row">
                <div class="col l12 s12">
                    <center>
                    <h4>"¡Bienvenido al apasionante mundo del fútbol, donde cada partido es una historia por escribir y cada jugador, un héroe en potencia!" </h4> 
                    </center>
                    <div class="card small">
    <div class="col s12 m7">
    <h5 class="header">BUNDESLIGA</h5>
    <div class="card horizontal">
      <div class="card-image">
        <img src="wwwroot/images/Xabi.jpg">
      </div>
      <div class="card-stacked">
        <div class="card-content">
          <p>El Bayer Leverkusen es un rodillo en este inicio de Bundesliga. El equipo de Xabi Alonso goleó (5-1) al Darmstadt para seguir en la cima de la Liga alemana.</p>
          <br><!-- comment -->
         
        </div>
        <div class="card-action">
         <a href="#">Mas Info...</a>
        </div>
      </div>
    </div>
    
  </div>
      </div>    
                     <div class="card small">
    <div class="col s12 m7">
    <h5 class="header">PREMIER LIGUE</h5>
    <div class="card horizontal">
      <div class="card-image">
        <img src="wwwroot/images/Haland.jpg">
      </div>
      <div class="card-stacked">
        <div class="card-content">
            <p> Haaland hunde al Fulham con un hat-trick en la fiesta del City</p>
        </div>
        <div class="card-action">
         <a href="#">Mas Info...</a>
        </div>
      </div>
    </div>
  </div> 
                </div>
                    <div class="card small">
    <div class="col s12 m7">
    <h5 class="header">BUNDESLIGA</h5>
    <div class="card horizontal">
      <div class="card-image">
        <img src="wwwroot/images/Tel.jpg">
      </div>
      <div class="card-stacked">
        <div class="card-content">
            <p>Tel culmina la remontada del Bayern y los mantiene invictos
Gran partido de Sané, autor de uno de los tantos. Kimmich, asistente en las dos jugadas de gol.</p>
            <br><!-- comment -->
            <br><!-- comment -->
        </div>
        <div class="card-action">
          
            </br>
         <a href="#">Mas Info...</a>
        </div>
      </div>
    </div>
  </div> 
   </div>
                                       <div class="card small">
    <div class="col s12 m7">
    <h5 class="header">PREMIER LIGUE</h5>
    <div class="card horizontal">
      <div class="card-image">
        <img src="wwwroot/images/Liverpoli.jpg">
      </div>
      <div class="card-stacked">
        <div class="card-content">
            <p>El Liverpool no da opción al Aston Villa de Emery
Los de Klopp tuvieron un plácido encuentro ante los 'villanos'</p>
            <br><!-- comment -->

        </div>
        <div class="card-action">
          
            </br>
         <a href="#">Mas Info...</a>
        </div>
      </div>
    </div>
  </div> 
                </div>
            </div>            
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>

