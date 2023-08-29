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

import java.util.ArrayList;
import proyectofutbol.accesoadatos.BundesLigaDAL;
import proyectofutbol.entidaddenegocio.BundesLiga;
import ProyectoFutbol.Web.Utils.*;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "BundesLigaServlet", urlPatterns = {"/BundesLiga"})
public class BundesLigaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      private BundesLiga obtenerBundesLiga(HttpServletRequest request)
    {
        String accion = Utilidad.getParameter(request, "accion", "index");
        BundesLiga  bundesLiga = new BundesLiga ();
        if(accion.equals("create") == false)
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            bundesLiga.setId(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        bundesLiga.setEquipo(Utilidad.getParameter(request, "Equipo", ""));
        if(accion.equals("index"))
        {
            bundesLiga.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            bundesLiga.setTop_aux(bundesLiga.getTop_aux() == 0 ? Integer.MAX_VALUE: bundesLiga.getTop_aux());
        }
         if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            bundesLiga.setIdLigas(Integer.parseInt(Utilidad.getParameter(request, "idLigas",
                    "0")));
        }
          if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            bundesLiga.setPosicion(Integer.parseInt(Utilidad.getParameter(request, "posicion",
                    "0")));
        }
          
       if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            bundesLiga.setPuntos(Integer.parseInt(Utilidad.getParameter(request, "puntos",
                    "0")));
        }
       if(accion.equals("index"))
        {
            bundesLiga.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            bundesLiga.setTop_aux(bundesLiga.getTop_aux() == 0 ? Integer.MAX_VALUE: bundesLiga.getTop_aux());
        }
          
    
        return bundesLiga;
    }
     
     
        protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            BundesLiga bundesLiga = new BundesLiga();
            bundesLiga.setTop_aux(10);
            ArrayList<BundesLiga> bundesLigas = BundesLigaDAL.buscar(bundesLiga);
            request.setAttribute("bundesLigas", bundesLiga);
            request.setAttribute("top_aux", bundesLiga.getTop_aux());
            request.getRequestDispatcher("Views/BundesLiga/index.jsp")
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
            BundesLiga bundesLiga = obtenerBundesLiga(request);
            ArrayList<BundesLiga> bundesLigas  = BundesLigaDAL.buscar(bundesLiga);
            request.setAttribute("bundesLiga", bundesLiga);
            request.setAttribute("top_aux",bundesLiga.getTop_aux());
            request.getRequestDispatcher("Views/BundesLiga/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/BundesLiga/create.jsp")
                .forward(request, response);
    }
    
    
       protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            BundesLiga  bundesLiga = obtenerBundesLiga(request);
            int result = BundesLigaDAL.crear(bundesLiga );
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
            BundesLiga bundesLiga = obtenerBundesLiga(request);
            BundesLiga bundesLiga_result = BundesLigaDAL.obtenerPorId(bundesLiga);
            if(bundesLiga_result.getId() > 0)
            {
                request.setAttribute("rol", bundesLiga_result);
            }
            else
            {
                Utilidad.enviarError("El id: " + bundesLiga.getId() + " no existe en la tabla bundesLiga", 
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
            request.getRequestDispatcher("Views/BundesLiga/edit.jsp")
                    .forward(request, response);
    }
      
        protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
             BundesLiga bundesLiga = obtenerBundesLiga(request);
            int result = BundesLigaDAL.modificar(bundesLiga);
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
            request.getRequestDispatcher("Views/BundesLiga/details.jsp")
                    .forward(request, response);
    }
    
    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/BundesLiga/delete.jsp")
                    .forward(request, response);
    }
    
    
      protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            BundesLiga  bundesLiga = obtenerBundesLiga (request);
            int result = BundesLigaDAL.eliminar(bundesLiga);
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
