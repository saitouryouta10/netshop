package Model.items.db;

public class ItemsDto {

	
	private int id;
	private String name;
	private int price;
	private int stock;
	private int retention_stock;
	private String setumei;
	private String syousai;
	private String picture;
	private String created;
	
	private int favorite_user_id;
	private int favorite_item_id;
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	public int getId() {
		return id;
	}
	public int getFavorite_user_id() {
		return favorite_user_id;
	}
	public void setFavorite_user_id(int favorite_user_id) {
		this.favorite_user_id = favorite_user_id;
	}
	public int getFavorite_item_id() {
		return favorite_item_id;
	}
	public void setFavorite_item_id(int favorite_item_id) {
		this.favorite_item_id = favorite_item_id;
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
	public int getRetention_stock() {
		return retention_stock;
	}
	public void setRetention_stock(int retention_stock) {
		this.retention_stock = retention_stock;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getSetumei() {
		return setumei;
	}
	public void setSetumei(String setumei) {
		this.setumei = setumei;
	}
	public String getSyousai() {
		return syousai;
	}
	public void setSyousai(String syousai) {
		this.syousai = syousai;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
