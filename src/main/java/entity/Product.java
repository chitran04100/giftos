package entity;

public class Product {
	private int id;
	private String name;
	private int price;
	private String imgName;
	private int isNew;
	private String description;
	private int categoryId;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, int price, String imgName, int isNew, String description, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgName = imgName;
		this.isNew = isNew;
		this.description = description;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public int isisNew() {
		return isNew;
	}

	public void setisNew(int isNew) {
		this.isNew = isNew;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
