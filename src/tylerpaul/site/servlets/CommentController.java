package tylerpaul.site.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tylerpaul.site.daos.CommentDAO;
import tylerpaul.site.daos.ICategoryDAO;
import tylerpaul.site.daos.ICommentDAO;
import tylerpaul.site.daos.IImageDAO;
import tylerpaul.site.daos.IUserDAO;
import tylerpaul.site.daos.UserDAO;
import tylerpaul.site.models.Comment;
import tylerpaul.site.util.BeanGetter;

@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICommentDAO commentDAO;
    private IUserDAO userDAO;
	
    public CommentController() {
        super();
        commentDAO = new CommentDAO();// (ICommentDAO)BeanGetter.getBean("commentDAO");
        userDAO = new UserDAO();//(IUserDAO)BeanGetter.getBean("userDAO");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("DoAdd")) { 
			int imageId = Integer.parseInt(request.getParameter("picId"));
			int userId = userDAO.getUserId((String)request.getSession().getAttribute("user"));
			String commentText = request.getParameter("comment");
			Comment comment = new Comment();
			comment.setImageId(imageId);
			comment.setUserId(userId);
			comment.setComment(commentText);
			commentDAO.addComment(comment);
			
			response.sendRedirect("ImageController?action=View&id=" + imageId);
		}
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
