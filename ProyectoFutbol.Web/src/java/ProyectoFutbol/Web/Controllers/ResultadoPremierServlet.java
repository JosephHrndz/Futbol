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
import proyectofutbol.accesoadatos.ResultdoPremierDAL;
import proyectofutbol.entidaddenegocio.ResultadosPremier;
import ProyectoFutbol.Web.utils.*;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "ResultadoPremierServlet", urlPatterns = {"/ResultadosPremier"})
public class ResultadoPremierServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private ResultadosPremier obtenerResultadosPremier(HttpServletRequest request)
    {
        String accion = Utilidad.getParameter(request, "accion", "index");
        ResultadosPremier resultadospremier = new ResultadosPremier();
        if(accion.equals("create") == false)
        {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            resultadospremier.setId(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        resultadospremier.setEquipo1(Utilidad.getParameter(request, "Equipo1", ""));
        if(accion.equals("index"))
        {
            resultadospremier.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            resultadospremier.setTop_aux(resultadospremier.getTop_aux() == 0 ? Integer.MAX_VALUE: resultadospremier.getTop_aux());
        }
          resultadospremier.setResultadoDeportes(Utilidad.getParameter(request, "ResultadoDeportes", ""));
        if(accion.equals("index"))
        {
            resultadospremier.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            resultadospremier.setTop_aux(resultadospremier.getTop_aux() == 0 ? Integer.MAX_VALUE: resultadospremier.getTop_aux());
        }
                  resultadospremier.setEquipo2(Utilidad.getParameter(request, "Equipo2", ""));
        if(accion.equals("index"))
        {
            resultadospremier.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            resultadospremier.setTop_aux(resultadospremier.getTop_aux() == 0 ? Integer.MAX_VALUE: resultadospremier.getTop_aux());
        }
        return resultadospremier;
    }
     
     
        protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            ResultadosPremier resultadospremier = new ResultadosPremier();
            resultadospremier.setTop_aux(10);
            ArrayList<ResultadosPremier> resultadopremier = ResultdoPremierDAL.buscar(resultadospremier);
            request.setAttribute("resultadospremier", resultadospremier);
            request.setAttribute("top_aux", resultadopremier.getTop_aux());
            request.getRequestDispatcher("Views/ResultadoPremier/index.jsp")
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
            ResultadosPremier resultadospremier = obtenerResultadosPremier(request);
            ArrayList<ResultadosPremier> resultadopremier = ResultdoPremierDAL.buscar(resultadospremier);
            request.setAttribute("resultadospremier", resultadospremier);
            request.setAttribute("top_aux", resultadopremier.getTop_aux());
            request.getRequestDispatcher("Views/ResultadoPremier/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/ResultadoPremier/create.jsp")
                .forward(request, response);
    }
    
    
       protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            ResultadosPremier resultadospremier = obtenerResultadosPremier(request);
            int result = ResultdoPremierDAL.crear(resultadospremier );
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
            ResultadosPremier resultadospremier = obtenerResultadosPremier(request);
            ResultadosPremier resultadopremier_result = ResultdoPremierDAL.obtenerPorId(resultadospremier);
            if(resultadopremier_result.getId() > 0)
            {
                request.setAttribute("rol", resultadopremier_result);
            }
            else
            {
                Utilidad.enviarError("El id: " + resultadospremier.getId() + " no existe en la tabla resultadospremier", 
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
            request.getRequestDispatcher("Views/ResultadosPremier/edit.jsp")
                    .forward(request, response);
    }
      
        protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            ResultadosPremier resultadospremier = obtenerResultadosPremier(request);
            int result = ResultdoPremierDAL.modificar(resultadospremier);
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
            request.getRequestDispatcher("Views/ResultadoPremier/details.jsp")
                    .forward(request, response);
    }
    
    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/ResultadoPremier/delete.jsp")
                    .forward(request, response);
    }
    
    
      protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            ResultadosPremier resultadospremier= obtenerResultadosPremier(request);
            int result = ResultdoPremierDAL.eliminar(resultadospremier);
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
