package tylerpaul.site.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tylerpaul.site.models.Comment;

public class CommentDAO implements ICommentDAO {
	private Connection connection;

	public CommentDAO() {
		connection = DBConnection.getConnection();
	}
	
	public int addComment(Comment comment) {
		PreparedStatement preparedStatement;
		int id = -1;
		try {
			preparedStatement = connection.prepareStatement("insert into sitedb.comments(user, image, time, comment) values (?, ?, NOW(), ?)", 
					Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setInt(1, comment.getUserId());
	        preparedStatement.setInt(2, comment.getImageId());
	        preparedStatement.setString(3, comment.getComment());
	        preparedStatement.executeUpdate();
	        ResultSet result = preparedStatement.getGeneratedKeys();
	        if(result.next() && result != null){
	        	id = result.getInt(1);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public void deleteComment(int id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("delete from sitedb.comments WHERE id = ?");
	        preparedStatement.setInt(1, id);
	        preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Comment> getComments(int imageId) {
		List<Comment> comments = new ArrayList<Comment>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from sitedb.comments where image=?");
			preparedStatement.setInt(1, imageId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user"));
                comment.setImageId(rs.getInt("image"));
                comment.setComment(rs.getString("comment"));
                comment.setTime(convertTime(rs.getString("time")));
                comments.add(comment);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	private String convertTime(String time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = df.parse(time);
			df = new SimpleDateFormat("HH:mm MM/dd/yy");
			return df.format(d);
			
		} catch (ParseException e) {
			return "";
		}
	}
	
}
