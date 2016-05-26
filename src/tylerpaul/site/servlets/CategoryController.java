package tylerpaul.site.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tylerpaul.site.daos.CategoryDAO;
import tylerpaul.site.daos.ICategoryDAO;
import tylerpaul.site.daos.IImageDAO;
import tylerpaul.site.daos.ImageDAO;
import tylerpaul.site.models.Category;
import tylerpaul.site.models.Image;
import tylerpaul.site.util.BeanGetter;
import tylerpaul.site.util.FileSystem;


@MultipartConfig
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICategoryDAO categoryDAO;
    private IImageDAO imageDAO;
    
    public CategoryController() {
        super();
        categoryDAO = new CategoryDAO();//(ICategoryDAO)BeanGetter.getBean("categoryDAO");
        imageDAO = new ImageDAO();//(IImageDAO)BeanGetter.getBean("imageDAO");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("Add")) {
			RequestDispatcher view = request.getRequestDispatcher("addCategory.jsp");
			view.forward(request, response);
		}
		else if (action.equals("Remove")) {
			request.setAttribute("categories", categoryDAO.getCategories());
			RequestDispatcher view = request.getRequestDispatcher("removeCategory.jsp");
			view.forward(request, response);
		}
		else if (action.equals("View")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("category", categoryDAO.getCategory(id));
			request.setAttribute("images", imageDAO.getImagesInCategory(id));
			RequestDispatcher view = request.getRequestDispatcher("viewCategory.jsp");
			view.forward(request, response);
		} else if (action.equals("Edit")) {
			request.setAttribute("categories", categoryDAO.getCategories());
			RequestDispatcher view = request.getRequestDispatcher("editCategory.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("DoAdd")) {
			String categoryName = request.getParameter("categoryName");
			
		    //save image to db
			Category category = new Category();
			category.setName(categoryName);
			int id = categoryDAO.addCategory(category);
			
			//save image to disc
			Part filePart = request.getPart("fileName");
			FileSystem.addFiles(request, filePart, FileSystem.Type.CATEGORY, id);
			
			RequestDispatcher view = request.getRequestDispatcher("/Index");
			view.forward(request, response);
		}
		else if (action.equals("DoRemove")) {
			String[] idStrings = request.getParameterValues("todelete[]");
			for (String idString : idStrings) {
				int id = Integer.parseInt(idString);
				
				//delete from disc
				FileSystem.deleteFiles(request, FileSystem.Type.CATEGORY, id);
				
				List<Image> images = imageDAO.getImagesInCategory(id);
				for (Image image : images) {
					FileSystem.deleteFiles(request, FileSystem.Type.IMAGE, image.getId());
				}
				
				//delete from db
				categoryDAO.deleteCategory(id);
			}
			RequestDispatcher view = request.getRequestDispatcher("/Index");
			view.forward(request, response);
		}
		else if (action.equals("DoEdit")) {
			Part filePart = request.getPart("filename");
		    String fileName = filePart.getSubmittedFileName();
		    
		    int id = Integer.parseInt(request.getParameter("id"));
		    System.out.println("id:" + id);
		    //update file
		    if (!fileName.equals("")) {
		    	//delete old images
				FileSystem.deleteFiles(request, FileSystem.Type.CATEGORY, id);
				
				//save new images
				FileSystem.addFiles(request, filePart, FileSystem.Type.CATEGORY, id);
		    }
		    String category = request.getParameter("category");
		    if (!category.equals("")) {
		    	categoryDAO.updateCategory(id, category);
		    }
		    response.sendRedirect("CategoryController?action=Edit");
		}

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
