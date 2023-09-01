<%-- 
    Document   : Index
    Created on : 24 ago 2023, 10:16:46
    Author     : JOSEPH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ligue1</title>
    </head>
       <tbody>
@foreach (var item in Model) {
        <tr>
           
               
            <td>
                @Html.DisplayFor(modelItem => item.Posicion)
            </td>
            <td>
                    @{
                        var nombreimg = "";
                        nombreimg = item.Posicion + ".png";
                    }
                    <img src="~/laliga/@nombreimg" width="50" height="50" />
                @Html.DisplayFor(modelItem => item.Equipo)
            </td>
            <td>
                @Html.DisplayFor(modelItem => item.Puntos)
            </td>
           
           
        </tr>
        
    </body>
</html>
