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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */
@WebServlet(name = "Search", urlPatterns = {"/carport/search"})
public class SearchServlet extends BaseServlet {
GenCarport gen;
GenerateCarportDAO gcdao;

    public SearchServlet()
    {
        try {
            this.gcdao = new GenerateCarportDAO();
        } catch (Exception ex) {
            Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        super.forward("/views/carport/search.jsp", request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
            gen = new GenCarport();
        String uuid = request.getParameter("uuid");
            Carport CusCP = null;
        try {
            CusCP = gcdao.CarportFromUUID(uuid);
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
