/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderDAO;
import DAL.Repositories.ProductionDAO;
import Domain.Product;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
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

@WebServlet(name = "CompleteProduct", urlPatterns = {"/production/CompleteProduct"})
public class CompleteProductServlet extends BaseServlet {
    ProductionDAO pdao;
    OrderDAO odao;
    
    public CompleteProductServlet()
    {
        this.pdao = new ProductionDAO();
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
        int oid = Integer.parseInt(request.getParameter("oid"));
        int empno = 0;
        try {
            empno = pdao.getEMPNO(uid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
        odao.updateorderline(oid, "Confirmed");
        odao.updateorderlineemp(oid, empno);
        }
        catch(Exception ex){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        
        List<Product> productviewList = null;
        try {
            productviewList = pdao.selectProduct(oid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Product prod = productviewList.get(0);
        String stat = prod.getStatus();
        
        request.setAttribute("oid", oid);
        request.setAttribute("stat", stat);
        request.setAttribute("productviewList", productviewList); 
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/production/view.jsp");
        dispatcher.forward(request, response);
   }

}
