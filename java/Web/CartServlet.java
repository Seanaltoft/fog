/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderDAO;
import Domain.Carport;
import Domain.CarportOrder;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
@WebServlet(name = "Cart", urlPatterns = {"/carport/cart"})
public class CartServlet extends BaseServlet {
    OrderDAO odao;
    
    public CartServlet()
    {
        this.odao = new OrderDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
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
        
        int totalprice = 0;
        for (CarportOrder co: orderList)
        {
            totalprice += co.getPrice();
        }
        
        request.setAttribute("totalprice", totalprice);
        request.setAttribute("orderList", orderList);

        //RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/carport/cart.jsp");
        RequestDispatcher dispatcher = 
        request.getRequestDispatcher("/views/carport/cart.jsp");
        dispatcher.forward(request, response);
      
    }

}
