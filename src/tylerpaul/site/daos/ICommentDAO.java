package tylerpaul.site.daos;

import java.util.List;

import tylerpaul.site.models.Comment;

public interface ICommentDAO {
	public int addComment(Comment comment);
	public void deleteComment(int id);
	public List<Comment> getComments(int imageId);
}
