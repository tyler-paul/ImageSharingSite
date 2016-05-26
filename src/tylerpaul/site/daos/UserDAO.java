package tylerpaul.site.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
	private Connection connection;

	public UserDAO() {
		connection = DBConnection.getConnection();
	}
	
	public boolean addUser(String username, String password) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into sitedb.users(name, password) values (?, ?)");
	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean authenticateUser(String username, String password) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select password from sitedb.users where name=?");
	        preparedStatement.setString(1, username);
	        
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String actualPassword = (rs.getString("password"));
				if (actualPassword.equals(password))
					return true;
            }
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public int getUserId(String username) {
		int id = -1;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select id from sitedb.users where name=?");
	        preparedStatement.setString(1, username);
	        
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getUsername(int id) {
		String username = "";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select name from sitedb.users where id=?");
	        preparedStatement.setInt(1, id);
	        
	        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				username = rs.getString("name");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}
}
