/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.ProductionDAO;
import Domain.Product;
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

/**
 *
 * @author Sean
 */
@WebServlet(name = "ProductionView", urlPatterns = {"/production/ProductionView"})
public class ProductionViewServlet extends BaseServlet {
    ProductionDAO pdao;
    
    public ProductionViewServlet()
    {
        this.pdao = new ProductionDAO();
    }
    
   @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException 
    { 
        response.setContentType("text/html;charset=UTF-8");
        int oid = Integer.parseInt(request.getParameter("oid"));

        List<Product> productviewList = null;
        try {
            productviewList = pdao.selectProduct(oid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductionViewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
