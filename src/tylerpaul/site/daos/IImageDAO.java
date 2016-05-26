package tylerpaul.site.daos;

import java.util.List;

import tylerpaul.site.models.Image;

public interface IImageDAO {
	public int addImage(Image image);
	public void updateImage(int id, int categoryId, String comment);
	public void deleteImage(int id);
	public Image getImage(int id) ;
	public List<Image> getImages();
	public List<Image> getImagesInCategory(int categoryId);
	public List<Image> getLatestImages(int numImages);
}
