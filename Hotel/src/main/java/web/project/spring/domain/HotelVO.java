package web.project.spring.domain;

public class HotelVO {
	private int hotel_no; // 호텔 번호(PK)
	private String hotel_name; // 호텔 이름
	private String hotel_addr; // 호텔 주소(지역)
	private String hotel_phone; // 호텔 전화번호
	private String hotel_comment; // 호텔 한마디
	private int hotel_low_price; // 호텔 룸 최저가격
	private int hotel_star; // 호텔의 평균 별점수
	private int hotel_review_count; // 호텔의 리뷰수
	private float hotel_latitude; // 호텔의 위도
	private float hotel_longitude; // 호텔의 경도
	private int hotel_like_count; // 호텔의 좋아요 개수

	// 기본생성자
	public HotelVO() { }

	// field()
	public HotelVO(int hotel_no, String hotel_name, String hotel_addr, String hotel_phone, String hotel_comment,
			int hotel_low_price, int hotel_star, int hotel_review_count, float hotel_latitude, float hotel_longitude, int hotel_like_count) {
		super();
		this.hotel_no = hotel_no;
		this.hotel_name = hotel_name;
		this.hotel_addr = hotel_addr;
		this.hotel_phone = hotel_phone;
		this.hotel_comment = hotel_comment;
		this.hotel_low_price = hotel_low_price;
		this.hotel_star = hotel_star;
		this.hotel_review_count = hotel_review_count;
		this.hotel_latitude = hotel_latitude;
		this.hotel_longitude = hotel_longitude;
		this.hotel_like_count = hotel_like_count;
	}

	// getter setter
	public int getHotel_no() {
		return hotel_no;
	}

	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_addr() {
		return hotel_addr;
	}

	public void setHotel_addr(String hotel_addr) {
		this.hotel_addr = hotel_addr;
	}

	public String getHotel_phone() {
		return hotel_phone;
	}

	public void setHotel_phone(String hotel_phone) {
		this.hotel_phone = hotel_phone;
	}

	public String getHotel_comment() {
		return hotel_comment;
	}

	public void setHotel_comment(String hotel_comment) {
		this.hotel_comment = hotel_comment;
	}

	public int getHotel_low_price() {
		return hotel_low_price;
	}

	public void setHotel_low_price(int hotel_low_price) {
		this.hotel_low_price = hotel_low_price;
	}

	public int getHotel_star() {
		return hotel_star;
	}

	public void setHotel_star(int hotel_star) {
		this.hotel_star = hotel_star;
	}

	public int getHotel_review_count() {
		return hotel_review_count;
	}

	public void setHotel_review_count(int hotel_review_count) {
		this.hotel_review_count = hotel_review_count;
	}

	public float getHotel_latitude() {
		return hotel_latitude;
	}

	public void setHotel_latitude(float hotel_latitude) {
		this.hotel_latitude = hotel_latitude;
	}

	public float getHotel_longitude() {
		return hotel_longitude;
	}

	public void setHotel_longitude(float hotel_longitude) {
		this.hotel_longitude = hotel_longitude;
	}
	
	public int getHotel_like_count() {
		return hotel_like_count;
	}

	public void setHotel_like_count(int hotel_like_count) {
		this.hotel_like_count = hotel_like_count;
	}

	// toString()
	@Override
	public String toString() {
		return "HotelVO [hotel_no=" + hotel_no + ", hotel_name=" + hotel_name + ", hotel_addr=" + hotel_addr
				+ ", hotel_phone=" + hotel_phone + ", hotel_comment=" + hotel_comment + ", hotel_low_price="
				+ hotel_low_price + ", hotel_star=" + hotel_star + ", hotel_review_count=" + hotel_review_count
				+ ", hotel_latitude=" + hotel_latitude + ", hotel_longitude=" + hotel_longitude + ", hotel_like_count="
				+ hotel_like_count + "]";
	}
	
}
