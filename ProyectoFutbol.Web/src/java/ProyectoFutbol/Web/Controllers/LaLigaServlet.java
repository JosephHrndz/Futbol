/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ProyectoFutbol.Web.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MINEDUCYT
 */

import java.util.ArrayList;
import proyectofutbol.accesoadatos.LaLigaDAL;
import proyectofutbol.entidaddenegocio.LaLiga;
import ProyectoFutbol.Web.Utils.*;

@WebServlet(name = "LaLigaServlet", urlPatterns = {"/LaLiga"})
public class LaLigaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      private LaLiga obtenerLaLiga(HttpServletRequest request)
    {
        String accion = Utilidad.getParameter(request, "accion", "index");
        LaLiga  laLiga = new LaLiga ();
        if(accion.equals("create") == false)
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            laLiga.setId(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        laLiga.setEquipo(Utilidad.getParameter(request, "Equipo", ""));
        if(accion.equals("index"))
        {
            laLiga.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            laLiga.setTop_aux(laLiga.getTop_aux() == 0 ? Integer.MAX_VALUE: laLiga.getTop_aux());
        }
         if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            laLiga.setIdLigas(Integer.parseInt(Utilidad.getParameter(request, "idLigas",
                    "0")));
        }
          if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            laLiga.setPosicion(Integer.parseInt(Utilidad.getParameter(request, "posicion",
                    "0")));
        }
          
       if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            laLiga.setPuntos(Integer.parseInt(Utilidad.getParameter(request, "puntos",
                    "0")));
        }
       if(accion.equals("index"))
        {
            laLiga.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            laLiga.setTop_aux(laLiga.getTop_aux() == 0 ? Integer.MAX_VALUE: laLiga.getTop_aux());
        }
          
    
        return laLiga;
    }
     
     
        protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LaLiga laLiga = new LaLiga();
            laLiga.setTop_aux(10);
            ArrayList<LaLiga> laLigas = LaLigaDAL.buscar(laLiga);
            request.setAttribute("laLiga", laLiga);
            request.setAttribute("top_aux", laLiga.getTop_aux());
            request.getRequestDispatcher("Views/LaLiga/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
        
            protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LaLiga laLiga = obtenerLaLiga(request);
            ArrayList<LaLiga> laLigas  = LaLigaDAL.buscar(laLiga);
            request.setAttribute("laLiga", laLiga);
            request.setAttribute("top_aux",laLiga.getTop_aux());
            request.getRequestDispatcher("Views/LaLiga/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/LaLiga/create.jsp")
                .forward(request, response);
    }
    
    
       protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LaLiga  laLiga = obtenerLaLiga(request);
            int result = LaLigaDAL.crear(laLiga );
            if(result != 0)
            {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            }
            else
            {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

       
       
    protected void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LaLiga laLiga = obtenerLaLiga(request);
            LaLiga laLiga_result = LaLigaDAL.obtenerPorId(laLiga);
            if(laLiga_result.getId() > 0)
            {
                request.setAttribute("laLiga", laLiga_result);
            }
            else
            {
                Utilidad.enviarError("El id: " + laLiga.getId() + " no existe en la tabla laLiga", 
                        request, response);
            }
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
      protected void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/LaLiga/edit.jsp")
                    .forward(request, response);
    }
      
        protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
             LaLiga laLiga = obtenerLaLiga(request);
            int result = LaLigaDAL.modificar(laLiga);
            if(result != 0)
            {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            }
            else
            {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
        
        
           protected void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/LaLiga/details.jsp")
                    .forward(request, response);
    }
    
    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/LaLiga/delete.jsp")
                    .forward(request, response);
    }
    
    
      protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LaLiga  laLiga = obtenerLaLiga (request);
            int result = LaLigaDAL.eliminar(laLiga);
            if(result != 0)
            {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            }
            else
            {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, 
                    "accion", "index");
            switch(accion)
            {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
            }
        });
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, 
                    "accion", "index");
            switch(accion)
            {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
            }
        //});
 }
}
