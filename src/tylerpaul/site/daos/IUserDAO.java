package tylerpaul.site.daos;

public interface IUserDAO {
	public boolean addUser(String username, String password) ;
	public boolean authenticateUser(String username, String password);
	public int getUserId(String username);
	public String getUsername(int id);
}
