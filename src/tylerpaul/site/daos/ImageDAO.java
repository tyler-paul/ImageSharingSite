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

import tylerpaul.site.models.Category;
import tylerpaul.site.models.Image;

public class ImageDAO implements IImageDAO {
	private Connection connection;

	public ImageDAO() {
		connection = DBConnection.getConnection();
	}
	
	public int addImage(Image image) {
		PreparedStatement preparedStatement;
		int id = -1;
		try {
			preparedStatement = connection.prepareStatement("insert into sitedb.images(time, comment, category) values (NOW(), ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, image.getComment());
	        preparedStatement.setInt(2, image.getCategoryId());
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
	
	public void updateImage(int id, int categoryId, String comment) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("update sitedb.images set category=?,comment=? where id = ?");
	        preparedStatement.setInt(1, categoryId);
	        preparedStatement.setString(2, comment);
	        preparedStatement.setInt(3, id);
	        preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteImage(int id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("delete from sitedb.images WHERE id = ?");
	        preparedStatement.setInt(1, id);
	        preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(int id) {
		Image image = new Image();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from sitedb.images where id = ?");
	        preparedStatement.setInt(1, id);
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                image.setId(rs.getInt("id"));
                image.setCategoryId(rs.getInt("category"));
                image.setComment(rs.getString("comment"));
                image.setTime(convertTime(rs.getString("time")));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public List<Image> getImages() {
		List<Image> images = new ArrayList<Image>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from sitedb.images");
			while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("id"));
                image.setCategoryId(rs.getInt("category"));
                image.setComment(rs.getString("comment"));
                images.add(image);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}
	
	public List<Image> getImagesInCategory(int categoryId) {
		List<Image> images = new ArrayList<Image>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from sitedb.images where category = ?");
	        preparedStatement.setInt(1, categoryId);
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("id"));
                image.setCategoryId(rs.getInt("category"));
                image.setComment(rs.getString("comment"));
                images.add(image);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}
	
	public List<Image> getLatestImages(int numImages) {
		List<Image> images = new ArrayList<Image>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from sitedb.images order by time desc limit ?");
	        preparedStatement.setInt(1, numImages);
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("id"));
                image.setCategoryId(rs.getInt("category"));
                image.setComment(rs.getString("comment"));
                images.add(image);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
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
