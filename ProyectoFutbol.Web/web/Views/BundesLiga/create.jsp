
<%-- 
    Document   : create.jsp
    Created on : 30 ago 2023, 8:36:52
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear BundesLiga</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Crear BundesLiga</h5>
            <form action="BundesLiga" method="post">
                <input type="text" name="accion" 
                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                <div class="row">
                       <div  class="input-field col 14 s12">
                        <jsp:include page="/Views/Ligas/select.jsp">
                            <jsp:param name="id" value="0"/>
                        </jsp:include>
                </div>
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtEquipo" name ="equipo" required 
                               class="validate" maxlength="30">
                        <label for="txtEquipo">Equipo</label>
                    </div>
                   
                  <div class="input-field col 14 s12">
                        <input type="number" id="nbPosicion" name ="posicion" required 
                               class="validate" maxlength="30">
                        <label for="nbPosicion">Posicion</label>
                    </div>
                    <div class="input-field col 14 s12">
                        <input type="number" id="nbPuntos " name ="puntos" required 
                               class="validate" maxlength="30">
                        <label for="nbPuntos">Puntos</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">save</i>Guardar
                        </button>
                        <a href="BundesLiga" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

