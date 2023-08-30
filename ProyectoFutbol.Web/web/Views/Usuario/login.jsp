<%-- 
    Document   : login
    Created on : 30 ago 2023, 12:36:58
    Author     : JOSEPH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Login</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main class="container">
            <h5>Login</h5>
            <form action="Usuario?accion=login" method="post">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>">
                <div class="row">
                    <div class="input-field col 15 s12">
                        <i class="material-icons prefix">account_circle</i>
                        <input type="text" id="txtLogin" name="login"
                               required class="validate" maxlength="25">
                        <label for="txtLogin">Login</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 15 s12">
                        <i class="material-icons prefix">enhanced_encryption</i>
                        <input type="password" id="txtPassword" name="password"
                               required class="validate" minlength="5">
                        <label for="txtPassword">Password</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">send</i>Login
                        </button>
                    </div>
                </div>
                <%
                  if(request.getAttribute("error") != null)
                    {
                %>
                <div class="row">
                    <div class="col 112 s12">
                        <span style="color:red;font-weight:bold">
                            <%= request.getAttribute("error") %>
                        </span>
                    </div>
                </div>
                <%}%>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

