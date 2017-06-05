/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderDAO;
import Domain.Inventory;
import Domain.Purchase;
import Services.CustomExceptions.MissingInventoryException;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ViewReceipt", urlPatterns = {"/carport/ViewReceipt"})
public class ViewReceiptServlet extends BaseServlet {
    private final OrderDAO odao;
    
    public ViewReceiptServlet()
    {
        this.odao = new OrderDAO();
    }
      
  @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        int uid = sessionUser.getId();
        
        int cpid = Integer.parseInt(request.getParameter("cpid"));
        List<Inventory> inventoryList = null;
        try{
            inventoryList = odao.createInventoryList(cpid);
        }catch(MissingInventoryException ex)
        {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewReceiptServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        List<Purchase> purchaseList = null;
        
        try {
            purchaseList = odao.createPurchase(uid, cpid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewReceiptServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        request.setAttribute("purchaseList", purchaseList); 
        request.setAttribute("inventoryList", inventoryList); 
      
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/carport/viewreceipt.jsp");
        dispatcher.forward(request, response);
    }   
}
