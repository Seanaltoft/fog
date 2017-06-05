/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.Filters;

import DAL.Repositories.EmployeeDAO;
import Domain.UserRole;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */

//Filter for employee pages

@WebFilter(urlPatterns = {"/production/production"})
public class EmployeeFilter extends BaseFilter {
    
      @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        
              super.doFilter(request, response, chain);
              EmployeeDAO edao = new EmployeeDAO();
              
              // If the user is a visitor, redirect to home page
              if (!edao.isUserEmployee(this.currentUser.getId()))
              {
                  HttpServletResponse httpResponse = (HttpServletResponse) response;
                  httpResponse.sendRedirect("/");
              }
              // Pass the request to other filters or the servlet
              else{
             chain.doFilter(request, response);
              }
             
         
    }

    @Override
    public void destroy()
    {
        super.destroy();
    }
    
}
