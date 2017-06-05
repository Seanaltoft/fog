/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.GenerateCarportDAO;
import DAL.Repositories.OrderDAO;
import DAL.Repositories.UserRepository;
import Domain.Carport;
import Services.UserValidator;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
@WebServlet(name = "Order", urlPatterns = {"/carport/order"})
public class OrderServlet extends BaseServlet {
    private GenerateCarportDAO gcdao;
    private final OrderDAO odao;
    
    public OrderServlet()
    {
        try {
            this.gcdao = new GenerateCarportDAO();
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.odao = new OrderDAO();
    }
   
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            HttpSession session = request.getSession();
            UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
            Carport cp = null;
            int uid = sessionUser.getId();
            int cpid = Integer.parseInt(request.getParameter("cpid"));
            cp = gcdao.CarportFromid(cpid);
            odao.insertOrder(uid, cp);
            // Redirect to front page
           
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         response.sendRedirect("/");
    }
    
}
