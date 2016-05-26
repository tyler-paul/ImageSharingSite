package tylerpaul.site.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import tylerpaul.site.daos.CategoryDAO;
import tylerpaul.site.daos.UserDAO;


@WebFilter("/*")
public class MyFilter implements Filter {
    private CategoryDAO categoryDAO;

    public MyFilter() {
        categoryDAO = new CategoryDAO();
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("categories", categoryDAO.getCategories());
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
