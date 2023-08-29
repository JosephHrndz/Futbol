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
import proyectofutbol.accesoadatos.ResultadoBudesDAL;
import proyectofutbol.entidaddenegocio.ResultadosBundes;
import ProyectoFutbol.Web.Utils.*;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "ResultadoBundesServlet", urlPatterns = {"/ResultadosBundes"})
public class ResultadoBundesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ResultadosBundes obtenerResultadosBundes(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        ResultadosBundes resultadosbundes = new ResultadosBundes();
        if (accion.equals("create") == false) {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            resultadosbundes.setId(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        resultadosbundes.setEquipo1(Utilidad.getParameter(request, "Equipo1", ""));
        if (accion.equals("index")) {
            resultadosbundes.setTop_aux(Integer.parseInt(Utilidad.getParameter(request,
                    "top_aux", "10")));
            resultadosbundes.setTop_aux(resultadosbundes.getTop_aux() == 0 ? Integer.MAX_VALUE : resultadosbundes.getTop_aux());
        }
        resultadosbundes.setResultadoDeportes(Utilidad.getParameter(request, "ResultadoDeportes", ""));
        if (accion.equals("index")) {
            resultadosbundes.setTop_aux(Integer.parseInt(Utilidad.getParameter(request,
                    "top_aux", "10")));
            resultadosbundes.setTop_aux(resultadosbundes.getTop_aux() == 0 ? Integer.MAX_VALUE : resultadosbundes.getTop_aux());
        }
        resultadosbundes.setEquipo2(Utilidad.getParameter(request, "Equipo2", ""));
        if (accion.equals("index")) {
            resultadosbundes.setTop_aux(Integer.parseInt(Utilidad.getParameter(request,
                    "top_aux", "10")));
            resultadosbundes.setTop_aux(resultadosbundes.getTop_aux() == 0 ? Integer.MAX_VALUE : resultadosbundes.getTop_aux());
        }
        return resultadosbundes;
    }

    protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResultadosBundes resultadosbundes = new ResultadosBundes();
            resultadosbundes.setTop_aux(10);
            ArrayList<ResultadosBundes> resultadobundes = ResultadoBudesDAL.buscar(resultadosbundes);
            request.setAttribute("resultadoslaliga", resultadosbundes);
            request.setAttribute("top_aux", resultadosbundes.getTop_aux());
            request.getRequestDispatcher("Views/ResultadosBundes/index.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResultadosBundes resultadosbundes = obtenerResultadosBundes(request);
            ArrayList<ResultadosBundes> resultadobundes = ResultadoBudesDAL.buscar(resultadosbundes);
            request.setAttribute("resultadosbundes", resultadosbundes);
            request.setAttribute("top_aux", resultadosbundes.getTop_aux());
            request.getRequestDispatcher("Views/ResultadosBundes/index.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/ResultadosBundes/create.jsp")
                .forward(request, response);
    }

    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResultadosBundes resultadosbundes = obtenerResultadosBundes(request);
            int result = ResultadoBudesDAL.crear(resultadosbundes);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResultadosBundes resultadosbundes = obtenerResultadosBundes(request);
            ResultadosBundes resultadosbundes_result = ResultadoBudesDAL.obtenerPorId(resultadosbundes);
            if (resultadosbundes_result.getId() > 0) {
                request.setAttribute("resultadosbundes", resultadosbundes_result);
            } else {
                Utilidad.enviarError("El id: " + resultadosbundes.getId() + " no existe en la tabla resultadosbundes",
                        request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/ResultadosBundes/edit.jsp")
                .forward(request, response);
    }

    protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResultadosBundes resultadosbundes = obtenerResultadosBundes(request);
            int result = ResultadoBudesDAL.modificar(resultadosbundes);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/ResultadosBundes/details.jsp")
                .forward(request, response);
    }

    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/ResultadosBundes/delete.jsp")
                .forward(request, response);
    }

    protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResultadosBundes resultadosbundes = obtenerResultadosBundes(request);
            int result = ResultadoBudesDAL.eliminar(resultadosbundes);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
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
            switch (accion) {
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
        switch (accion) {
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
