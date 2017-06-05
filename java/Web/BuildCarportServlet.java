/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.GenerateCarportDAO;
import Domain.Carport;
import Services.GenCarport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author azurwular
 */
@WebServlet(name = "carportBuild", urlPatterns = {"/carport/build"})
public class BuildCarportServlet extends BaseServlet {
GenCarport gen;
GenerateCarportDAO gcdao;

    public BuildCarportServlet()
    {
    try {
        this.gcdao = new GenerateCarportDAO();
    } catch (Exception ex) {
        Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
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

            super.forward("/views/carport/build.jsp", request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
            gen = new GenCarport();
        int curLength = Integer.parseInt(request.getParameter("length"));
        int curWidth = Integer.parseInt(request.getParameter("width"));
        int curHeight = Integer.parseInt(request.getParameter("height"));
        String rooftype  = request.getParameter("rtype");
        
        //Generate the UUID KEY
        String uuid = gen.generateUUID();
        Carport CusCP = null;
        Carport cdp = null;
//        //Generate Inventory & Push to DB
         try {
              cdp = new Carport("Custom", uuid, curHeight, curWidth, curLength, "Standard", rooftype);
              gen.generateCP(cdp);
              
          } catch (Exception ex) {
              Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
        try {
            CusCP = gcdao.CarportFromUUID(cdp.getUUID());
        } catch (SQLException ex) {
            Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("CusCP", CusCP); 
       
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/carport/view.jsp");
        dispatcher.forward(request, response);
       
    }
}
