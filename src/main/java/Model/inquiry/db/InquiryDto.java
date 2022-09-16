package Model.inquiry.db;

public class InquiryDto {

	private int id;
	private String email;
	private String inquery_post;
	private String name;
	private String kenmei;
	private String created;
	
	public String getKenmei() {
		return kenmei;
	}
	public void setKenmei(String kenmei) {
		this.kenmei = kenmei;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInquery_post() {
		return inquery_post;
	}
	public void setInquery_post(String inquery_post) {
		this.inquery_post = inquery_post;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
}
