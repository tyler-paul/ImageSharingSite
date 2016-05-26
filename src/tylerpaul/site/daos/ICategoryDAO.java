package tylerpaul.site.daos;

import java.util.List;

import tylerpaul.site.models.Category;

public interface ICategoryDAO {
	public int addCategory(Category category);
	public void deleteCategory(int id);
	public void updateCategory(int id, String name);
	public Category getCategory(int id) ;
	public List<Category> getCategories();
}
