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
import javax.servlet.http.HttpSession;
import sample.shopping.Comestic;
import sample.shopping.ShoppingDAO;
import sample.users.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GetListProductController", urlPatterns = {"/GetListProductController"})
public class GetListProductController extends HttpServlet {

    private static final String ERROR = "shopping.jsp";
    private static final String SUCCESS = "listProduct.jsp";
        private static final String ADMIN_PAGE = "AdminPage.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ShoppingDAO shopdao = new ShoppingDAO();
            List<Comestic> ListProduct = shopdao.ListProduct();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO)session.getAttribute("LOGIN_USER");
            session.setAttribute("LIST_PRODUCT", ListProduct);
            url=SUCCESS;
            if(user.getRoleID().equals("AD")){
                url = ADMIN_PAGE;
            }
        }catch(Exception e){
            log("Error at GetListProductController" + e.toString());
        }finally{
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
