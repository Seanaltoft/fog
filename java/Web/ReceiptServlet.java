/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderDAO;
import Domain.Purchase;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "Receipt", urlPatterns = {"/carport/receipts"})
public class ReceiptServlet extends BaseServlet {
    private final OrderDAO odao;
    
    public ReceiptServlet()
    {
        this.odao = new OrderDAO();
    }
    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException
//    {
//        super.forward("/views/carport/receipts.jsp", request, response);
//    }

     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        int uid = sessionUser.getId();
        
        List<Purchase> purchaseList = odao.createPurchaseList(uid);
       
       
       // Forward to /WEB-INF/views/homeView.jsp
       // (Users can not access directly into JSP pages placed in WEB-INF)
       request.setAttribute("purchaseList", purchaseList); 
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/carport/receipts.jsp");
       dispatcher.forward(request, response);
    }   
    
     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        int uid = sessionUser.getId();
        
        List<Purchase> purchaseList = odao.createPurchaseList(uid);
       
       
       // Forward to /WEB-INF/views/homeView.jsp
       // (Users can not access directly into JSP pages placed in WEB-INF)
       request.setAttribute("purchaseList", purchaseList); 
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/carport/receipts.jsp");
       dispatcher.forward(request, response);
    }   
}


