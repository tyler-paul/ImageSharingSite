package tylerpaul.site.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

import tylerpaul.site.daos.CategoryDAO;
import tylerpaul.site.daos.CommentDAO;
import tylerpaul.site.daos.ICategoryDAO;
import tylerpaul.site.daos.ICommentDAO;
import tylerpaul.site.daos.IImageDAO;
import tylerpaul.site.daos.IUserDAO;
import tylerpaul.site.daos.ImageDAO;
import tylerpaul.site.daos.UserDAO;
import tylerpaul.site.models.Comment;
import tylerpaul.site.models.Image;
import tylerpaul.site.util.BeanGetter;
import tylerpaul.site.util.FileSystem;


@MultipartConfig
@WebServlet("/ImageController")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICategoryDAO categoryDAO;
    private IImageDAO imageDAO;
    private ICommentDAO commentDAO;
    private IUserDAO userDAO;
    
    public ImageController() {
        super();
        commentDAO = new CommentDAO();// (ICommentDAO)BeanGetter.getBean("commentDAO");
        userDAO = new UserDAO();//(IUserDAO)BeanGetter.getBean("userDAO");
        categoryDAO = new CategoryDAO();//(ICategoryDAO)BeanGetter.getBean("categoryDAO");
        imageDAO = new ImageDAO();//(IImageDAO)BeanGetter.getBean("imageDAO");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("Add")) {
			request.setAttribute("categories", categoryDAO.getCategories());
			RequestDispatcher view = request.getRequestDispatcher("addImage.jsp");
			view.forward(request, response);
		}
		else if (action.equals("Remove")) {
			request.setAttribute("categories", categoryDAO.getCategories());
			request.setAttribute("images", imageDAO.getImages());
			RequestDispatcher view = request.getRequestDispatcher("removeImage.jsp");
			view.forward(request, response);
		}
		else if (action.equals("View")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Image image = imageDAO.getImage(id);
			request.setAttribute("image", image);
			List<Comment> comments = commentDAO.getComments(id);
			for (Comment comment : comments) {
				comment.setUser(userDAO.getUsername(comment.getUserId()));
			}
			request.setAttribute("comments", comments);
			RequestDispatcher view = request.getRequestDispatcher("viewImage.jsp");
			view.forward(request, response);
		} else if (action.equals("Edit")) {
			List<Image> images = imageDAO.getImages();
			for (Image image : images) {
				image.setCategory(categoryDAO.getCategory(image.getCategoryId()).getName());
			}
			request.setAttribute("images", images);
			request.setAttribute("categories", categoryDAO.getCategories());
			RequestDispatcher view = request.getRequestDispatcher("editImage.jsp");
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("DoAdd")) { 
			Part filePart = request.getPart("filename");
			if (!filePart.getSubmittedFileName().equals("")) {
			    //save to db
				Image image = new Image();
				image.setComment(request.getParameter("comment"));
				image.setCategoryId(Integer.parseInt(request.getParameter("category")));
				System.out.println("about to add" + image.getCategoryId());
				int id = imageDAO.addImage(image);
				
				//save image
				FileSystem.addFiles(request, filePart, FileSystem.Type.IMAGE, id);
			}
			RequestDispatcher view = request.getRequestDispatcher("/Index");
			view.forward(request, response);
		} else if (action.equals("DoRemove")) {
			String[] idStrings = request.getParameterValues("todelete[]");
			for (String idString : idStrings) {
				int id = Integer.parseInt(idString);
				
				//delete from db
				imageDAO.deleteImage(id);
				
				//delete from disc
				FileSystem.deleteFiles(request, FileSystem.Type.IMAGE, id);
			}
			RequestDispatcher view = request.getRequestDispatcher("/Index");
			view.forward(request, response);
		} else if (action.equals("DoEdit")) {
		    int id = Integer.parseInt(request.getParameter("id"));
		    String comment = request.getParameter("comment");
		    int categoryId = Integer.parseInt(request.getParameter("categories"));
		    
		    imageDAO.updateImage(id, categoryId, comment);
		    
		    response.sendRedirect("ImageController?action=Edit");
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

	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
