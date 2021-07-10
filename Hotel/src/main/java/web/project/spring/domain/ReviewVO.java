package web.project.spring.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReviewVO {
	private int review_no; // 리뷰 번호(PK)
	private String review_content; // 리뷰 내용
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul") // JSON data전송시 format맞추기 위해
	private Date review_date; // 리뷰 작성시간(default = sysdate)
	private int review_star; // 리뷰 평점
	private String review_img_file; // 리뷰 이미지 첨부파일 (default = null)
	private int review_hotel_no; // 리뷰쓰는 호텔번호 FK(hotel_info)
	private int review_room_no; // 리뷰쓰는 방번호 FK(hotel_room)
	private String review_user_id; // 리뷰쓰는 사용자 FK(hotel_user)
	private String review_reservation_no; // 리뷰가써지는 reservation_no FK(reservation)
	private int review_group; // 대댓글과 댓글의 group
	
	private HotelVO hotel_vo; // join할 hotel_vo
	private RoomVO room_vo; // join할 room_vo
	private ReservationVO reservation_vo; // join할 reservation_vo
	
	// 기본생성자
	public ReviewVO() {}

	// using fields
	public ReviewVO(int review_no, String review_content, Date review_date, int review_star, String review_img_file,
			int review_hotel_no, int review_room_no, String review_user_id, String review_reservation_no,
			HotelVO hotel_vo, RoomVO room_vo, ReservationVO reservation_vo, int review_group) {
		super();
		this.review_no = review_no;
		this.review_content = review_content;
		this.review_date = review_date;
		this.review_star = review_star;
		this.review_img_file = review_img_file;
		this.review_hotel_no = review_hotel_no;
		this.review_room_no = review_room_no;
		this.review_user_id = review_user_id;
		this.review_reservation_no = review_reservation_no;
		this.hotel_vo = hotel_vo;
		this.room_vo = room_vo;
		this.reservation_vo = reservation_vo;
		this.review_group = review_group;
	}

	// getter setter
	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	public int getReview_star() {
		return review_star;
	}

	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}

	public String getReview_img_file() {
		return review_img_file;
	}

	public void setReview_img_file(String review_img_file) {
		this.review_img_file = review_img_file;
	}

	public int getReview_hotel_no() {
		return review_hotel_no;
	}

	public void setReview_hotel_no(int review_hotel_no) {
		this.review_hotel_no = review_hotel_no;
	}

	public int getReview_room_no() {
		return review_room_no;
	}

	public void setReview_room_no(int review_room_no) {
		this.review_room_no = review_room_no;
	}

	public String getReview_user_id() {
		return review_user_id;
	}

	public void setReview_user_id(String review_user_id) {
		this.review_user_id = review_user_id;
	}

	public String getReview_reservation_no() {
		return review_reservation_no;
	}

	public void setReview_reservation_no(String review_reservation_no) {
		this.review_reservation_no = review_reservation_no;
	}

	public HotelVO getHotel_vo() {
		return hotel_vo;
	}

	public void setHotel_vo(HotelVO hotel_vo) {
		this.hotel_vo = hotel_vo;
	}

	public RoomVO getRoom_vo() {
		return room_vo;
	}

	public void setRoom_vo(RoomVO room_vo) {
		this.room_vo = room_vo;
	}

	public ReservationVO getReservation_vo() {
		return reservation_vo;
	}

	public void setReservation_vo(ReservationVO reservation_vo) {
		this.reservation_vo = reservation_vo;
	}
	
	public int getReview_group() {
		return review_group;
	}

	public void setReview_group(int review_group) {
		this.review_group = review_group;
	}

	// toString()
	@Override
	public String toString() {
		return "ReviewVO [review_no=" + review_no + ", review_content=" + review_content + ", review_date="
				+ review_date + ", review_star=" + review_star + ", review_img_file=" + review_img_file
				+ ", review_hotel_no=" + review_hotel_no + ", review_room_no=" + review_room_no + ", review_user_id="
				+ review_user_id + ", review_reservation_no=" + review_reservation_no + ", hotel_vo=" + hotel_vo
				+ ", room_vo=" + room_vo + ", reservation_vo=" + reservation_vo + ", review_group = " + review_group + "]";
	}
	
}
