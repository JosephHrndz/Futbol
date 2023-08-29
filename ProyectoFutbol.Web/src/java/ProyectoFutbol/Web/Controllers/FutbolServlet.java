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
import proyectofutbol.accesoadatos.FutbolDAL;
import proyectofutbol.entidaddenegocio.Futbol;
import ProyectoFutbol.Web.Utils.*;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "FutbolServlet", urlPatterns = {"/Futbol"})
public class FutbolServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private Futbol obtenerFutbol(HttpServletRequest request)
    {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Futbol  futbol = new Futbol ();
        if(accion.equals("create") == false)
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            futbol.setIdFutbol(Integer.parseInt(Utilidad.getParameter(request, "idFutbol",
                    "0")));
        }
        futbol.setNoticias(Utilidad.getParameter(request, "Noticias", ""));
        if(accion.equals("index"))
        {
            futbol.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            futbol.setTop_aux(futbol.getTop_aux() == 0 ? Integer.MAX_VALUE: futbol.getTop_aux());
        }
          futbol.setTitulo(Utilidad.getParameter(request, "Titulo", ""));
        if(accion.equals("index"))
        {
            futbol.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            futbol.setTop_aux(futbol.getTop_aux() == 0 ? Integer.MAX_VALUE: futbol.getTop_aux());
        }
       
        if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            futbol.setIdLigas(Integer.parseInt(Utilidad.getParameter(request, "idLigas",
                    "0")));
        }
         if(accion.equals("index"))
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            futbol.setIdUsuario(Integer.parseInt(Utilidad.getParameter(request, "idUsuario",
                    "0")));
        }
              if(accion.equals("index"))
        {
            futbol.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            futbol.setTop_aux(futbol.getTop_aux() == 0 ? Integer.MAX_VALUE: futbol.getTop_aux());
        }
           
    
        return futbol;
    }
     
     
        protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Futbol futbol = new  Futbol();
            futbol.setTop_aux(10);
            ArrayList< Futbol> futbols =  FutbolDAL.buscar(futbol);
            request.setAttribute("futbols", futbol);
            request.setAttribute("top_aux", futbol.getTop_aux());
            request.getRequestDispatcher("Views/Futbol/index.jsp")
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
            Futbol futbol = obtenerFutbol(request);
            ArrayList< Futbol> futbols  =  FutbolDAL.buscar(futbol);
            request.setAttribute("futbols", futbol);
            request.setAttribute("top_aux",futbol.getTop_aux());
            request.getRequestDispatcher("Views/Futbol/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Futbol/create.jsp")
                .forward(request, response);
    }
    
    
       protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
             Futbol  futbol = obtenerFutbol(request);
            int result =  FutbolDAL.crear(futbol );
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
             Futbol futbol = obtenerFutbol(request);
             Futbol futbol_result = FutbolDAL.obtenerPorId(futbol);
            if(futbol_result.getIdFutbol()> 0)
            {
                request.setAttribute("futbol", futbol_result);
            }
            else
            {
                Utilidad.enviarError("El idFutbol: " + futbol.getIdFutbol() + " no existe en la tabla Futbol", 
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
            request.getRequestDispatcher("Views/Equipos/edit.jsp")
                    .forward(request, response);
    }
      
        protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
              Futbol futbol = obtenerFutbol(request);
            int result = FutbolDAL.modificar(futbol);
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
            request.getRequestDispatcher("Views/Futbol/details.jsp")
                    .forward(request, response);
    }
    
    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/Futbol/delete.jsp")
                    .forward(request, response);
    }
    
    
      protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
             Futbol  futbol = obtenerFutbol(request);
            int result =  FutbolDAL.eliminar(futbol);
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
