package web.project.spring.domain;

public class UserVO {
	private String user_id; // 사용자 아이디
	private String user_pw; // 사용자 비밀번호
	private String user_name; // 사용자 이름
	private String user_phone; // 사용자 폰번호
	private String user_email; // 사용자 이메일
	private int user_birth; // 사용자 생년월일
	
	// 기본생성자
	public UserVO() {}

	// using fields
	public UserVO(String user_id, String user_pw, String user_name, String user_phone, String user_email,
			int user_birth) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_birth = user_birth;
	}

	// getter setter
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(int user_birth) {
		this.user_birth = user_birth;
	}

	// toString()
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_phone="
				+ user_phone + ", user_email=" + user_email + ", user_birth=" + user_birth + "]";
	}

}
