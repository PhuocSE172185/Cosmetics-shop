/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.controllers;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.shopping.Comestic;
import sample.shopping.ShoppingDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    String ERROR = "SearchProductManage";
    String SUCCESS = "SearchProductManage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ShoppingDAO shopdao = new ShoppingDAO();
            String productID = request.getParameter("productID");
            if (shopdao.CheckProductID(productID)) {
                String productName = request.getParameter("productName");
                double productPrice = Double.parseDouble(request.getParameter("productPrice"));
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                String productImg = request.getParameter("productImg");
                boolean Status = true;
                Comestic comestic = new Comestic(productID, productName, productPrice, productQuantity, productImg,Status);
                boolean check = shopdao.insertProduct(comestic);
                if (check) {
                    url = SUCCESS;
                }
            }else{
                request.setAttribute("ERROR_ADD", "Repeat ProductID");
            }

        } catch (Exception e) {
            log("Error at AddProductController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
