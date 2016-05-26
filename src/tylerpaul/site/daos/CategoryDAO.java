package tylerpaul.site.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tylerpaul.site.models.Category;

public class CategoryDAO implements ICategoryDAO {
	private Connection connection;

	public CategoryDAO() {
		connection = DBConnection.getConnection();
	}
	
	public int addCategory(Category category) {
		PreparedStatement preparedStatement;
		int id = -1;
		try {
			preparedStatement = connection.prepareStatement("insert into sitedb.categories(name) values (?)", 
					Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, category.getName());
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
	
	public void deleteCategory(int id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("delete from sitedb.categories WHERE id = ?");
	        preparedStatement.setInt(1, id);
	        preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCategory(int id, String name) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("update sitedb.categories set name = ? where id = ?");
	        preparedStatement.setString(1, name);
	        preparedStatement.setInt(2, id);
	        preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Category getCategory(int id) {
		Category category = new Category();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from sitedb.categories WHERE id = ?");
	        preparedStatement.setInt(1, id);
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from sitedb.categories");
			while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
}
