<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProyectoFutbol.entidadesdenegocio.BundesLiga" %>
<% BundesLiga bundesLiga = (BundesLiga) request.getAttribute("bundesLiga");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle  BundesLiga</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Detalle BundesLiga</h5>
            <form action="BundesLiga" method="post">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                <input type="hidden" name="id" value="<%=bundesLiga.getId()%>" />
                <div class="row">
                       <div class="input-field col 14 s12">
                        <input type="text" disabled id="textIdLigas" name ="idLigas" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getIdLigas()%>">
                        <label for="textIdLigas">IdLigas</label>
                    </div>
                     <div class="input-field col 14 s12">
                        <input type="number" disabled id="nbPosicion" name ="posicion" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getPosicion()%>">
                        <label for="nbPosicion">Posicion</label>
                    </div>
                        
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtEquipo" name ="equipo" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getEquipo()%>">
                        <label for="txtEquipo">Equipo</label>
                    </div>
                          <div class="input-field col 14 s12">
                        <input type="number" disabled id="nbPuntos" name ="puntos" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getPuntos()%>">
                        <label for="nbPuntos">Puntos</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <a href="BundesLiga?accion=edit&id=%bundesLiga.getId()%>" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">edit</i>Ir a Editar
                        </a>
                       
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