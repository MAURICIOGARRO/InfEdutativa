/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gramatica;

/**
 *
 * @author User
 */
@WebServlet(name = "EliminarVariablesInalcanzables", urlPatterns = {"/EliminarVariablesInalcanzables.do"})
public class EliminarVariablesInalcanzables extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        System.out.println("EliminarVariablesInalcanzables.do");
        
        Normalizacion normalizacion = (Normalizacion) request.getSession().getAttribute("normalizacion");
        Gramatica gramatica = (Gramatica) request.getSession().getAttribute("gramatica");
        
        int cont = (int) (request.getSession().getAttribute("contador")!=null? request.getSession().getAttribute("contador") : 0);
        
        
        if(normalizacion!=null && gramatica!=null){
            while(normalizacion.existenInalacanzables(gramatica, gramatica.getInicial())){
                normalizacion.eliminarInalacanzables(gramatica, gramatica.getInicial());
            }
            cont++;
        }
        
        request.getSession().setAttribute("gramatica", gramatica);
        request.getSession().setAttribute("normalizacion", normalizacion);
        request.getSession().setAttribute("contador", cont);
        
        request.getRequestDispatcher("./index.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
