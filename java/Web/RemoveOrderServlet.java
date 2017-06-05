/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */
@WebServlet(name = "RemoveOrder", urlPatterns = {"/carport/RemoveOrderServlet"})
public class RemoveOrderServlet extends BaseServlet {
    private final OrderDAO odao;
    
    public RemoveOrderServlet()
    {
        this.odao = new OrderDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        int oid = Integer.parseInt(request.getParameter("oid"));
        
        odao.updateOrderStatus(oid, "Cancelled");
        
        response.sendRedirect("/carport/cart");
        
    }
}
