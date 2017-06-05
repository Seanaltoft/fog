/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderDAO;
import Domain.CarportOrder;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "PurchaseOrdersServlet", urlPatterns = {"/carport/PurchaseOrdersServlet"})
public class PurchaseOrdersServlet extends BaseServlet {
private final OrderDAO odao;
    
    public PurchaseOrdersServlet()
    {
        this.odao = new OrderDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
       
       HttpSession session = request.getSession();
        UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        int uid = sessionUser.getId();
        List<CarportOrder> orderList = new ArrayList();

        try {
            orderList = odao.getAllUserOrders(uid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    try {
        odao.purchaseOrders(orderList);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(PurchaseOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PurchaseOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
        response.sendRedirect("/");
        
    }
}
