package tylerpaul.site.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tylerpaul.site.daos.CategoryDAO;
import tylerpaul.site.daos.ICategoryDAO;
import tylerpaul.site.daos.IImageDAO;
import tylerpaul.site.daos.ImageDAO;

public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICategoryDAO categoryDAO;
    private IImageDAO imageDAO;
    
    public IndexController() {
        super();
        categoryDAO = new CategoryDAO();//(ICategoryDAO)BeanGetter.getBean("categoryDAO");
        imageDAO = new ImageDAO();//(IImageDAO)BeanGetter.getBean("imageDAO");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories", categoryDAO.getCategories());
		request.setAttribute("latestImages", imageDAO.getLatestImages(10));
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public IImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(IImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

}
