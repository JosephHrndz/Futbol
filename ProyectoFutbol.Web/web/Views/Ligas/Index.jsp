<%-- 
    Document   : Index
    Created on : 31 ago 2023, 8:14:51
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ligas</title>
    </head>
    <body>
        <h1>¡Explora nuestras ligas y descubre todo lo que el mundo del fútbol tiene para ofrecer!</h1>
        
          <div class="galeria">
            <div class="imagenes">
                <a href="/Ligue1/Index">
                    <img src="wwwroot/images/LIGUE1.png">
                </a>
            </div>
                <div class="pie">
   
                    <p>LIGUE1</p>

                </div>

            </div>

              
              <div class="galeria">
            <div class="imagenes">
                <a href="/SerireA/Index">
                    <img src="wwwroot/images/SERIEA.png">
                </a>
            </div>
                <div class="pie">
   
                    <p>SERIE A</p>

                </div>

            </div>



               <div class="galeria">
            <div class="imagenes">
                <a href="/BundesLiga/Index">
                    <img src="wwwroot/images/bundes.png">
                </a>
            </div>
                <div class="pie">
   
                    <p>BUNDESLIGA</p>

                </div>

            </div>

        
              <div class="galeria">
            <div class="imagenes">
                <a href="/LaLiga/Index">
                    <img src="wwwroot/images/laliga.png">
                </a>
            </div>
                <div class="pie">
   
                    <p>LA LIGA</p>

                </div>

            </div>

      <div class="galeria">
            <div class="imagenes">
                <a href="/PremierLigue/Index">
                    <img src="wwwroot/images/PREMIER.png">
                </a>
            </div>
                <div class="pie">
   
                    <p>PREMIER LIGUE</p>

                </div>

            </div>

            <style>
                h1 {
                    color: blue;
                    text-shadow: 1px 2px 2px black, 0 0 25px blue;
                    text-align: center;
                }



                div.galeria {
                    margin: 10px 20px;
                    box-shadow: 0 4px 8px 0 lightskyblue, 0 6px 20px 0 rgba(0,0,0,0.19);
                    float: left;
                    width: 175px;
                }


                div.galeria img {
                    width: 175px;
                    height: auto;
                }

                div.galeria:hover {
                    border: 1px solid lightcoral;
                    transform: rotate(-3deg);
                }



                div.pie {
                    text-align: center;
                    text-shadow: 2px 2px 5px purple;
                    padding: 10px;
                }

            

            </style>

    
    </body>
</html>
