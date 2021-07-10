package web.project.spring.domain;

public class LikeVO {
	private int like_no;
	private int like_hotel_no;
	private String like_user_id;
	
	public LikeVO() {}

	// using fields()
	public LikeVO(int like_no, int like_hotel_no, String like_user_id) {
		super();
		this.like_no = like_no;
		this.like_hotel_no = like_hotel_no;
		this.like_user_id = like_user_id;
	}

	// getter setter
	public int getLike_no() {
		return like_no;
	}

	public void setLike_no(int like_no) {
		this.like_no = like_no;
	}

	public int getLike_hotel_no() {
		return like_hotel_no;
	}

	public void setLike_hotel_no(int like_hotel_no) {
		this.like_hotel_no = like_hotel_no;
	}

	public String getLike_user_id() {
		return like_user_id;
	}

	public void setLike_user_id(String like_user_id) {
		this.like_user_id = like_user_id;
	}

	// toString()
	@Override
	public String toString() {
		return "LikeVO [like_no=" + like_no + ", like_hotel_no=" + like_hotel_no + ", like_user_id=" + like_user_id
				+ "]";
	}
	
}
