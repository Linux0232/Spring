package web.project.spring.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationVO {
	private String reservation_no; // 예약자 예약번호(PK)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul") // JSON data전송시 format맞추기 위해
	private Date reservation_check_in; // 예약 체크인 날짜
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul") // JSON data전송시 format맞추기 위해
	private Date reservation_check_out; // 예약 체크아웃 날짜
	private int reservation_total_price; // 예약 최종 가격
	private int reservation_hotel_no; // 예약하는 호텔번호 FK(hotel_info)
	private int reservation_room_no;  // 예약하는 방번호 FK(hotel_room)
	private String reservation_user_id; // 예약하는 사람 FK(hotel_user)
	
	private HotelVO hotel_vo; // join할 hotel_vo
	private RoomVO room_vo; // join할 room_vo
	private ReviewVO review_vo; // join할 review_vo
	
	// 기본생성자
	public ReservationVO() {}

	// using fields
	public ReservationVO(String reservation_no, Date reservation_check_in, Date reservation_check_out,
			int reservation_total_price, int reservation_hotel_no, int reservation_room_no, String reservation_user_id,
			HotelVO hotel_vo, RoomVO room_vo, ReviewVO review_vo) {
		super();
		this.reservation_no = reservation_no;
		this.reservation_check_in = reservation_check_in;
		this.reservation_check_out = reservation_check_out;
		this.reservation_total_price = reservation_total_price;
		this.reservation_hotel_no = reservation_hotel_no;
		this.reservation_room_no = reservation_room_no;
		this.reservation_user_id = reservation_user_id;
		this.hotel_vo = hotel_vo;
		this.room_vo = room_vo;
		this.review_vo = review_vo;
	}

	// getter setter
	public String getReservation_no() {
		return reservation_no;
	}

	public void setReservation_no(String reservation_no) {
		this.reservation_no = reservation_no;
	}

	public Date getReservation_check_in() {
		return reservation_check_in;
	}

	public void setReservation_check_in(Date reservation_check_in) {
		this.reservation_check_in = reservation_check_in;
	}

	public Date getReservation_check_out() {
		return reservation_check_out;
	}

	public void setReservation_check_out(Date reservation_check_out) {
		this.reservation_check_out = reservation_check_out;
	}

	public int getReservation_total_price() {
		return reservation_total_price;
	}

	public void setReservation_total_price(int reservation_total_price) {
		this.reservation_total_price = reservation_total_price;
	}

	public int getReservation_hotel_no() {
		return reservation_hotel_no;
	}

	public void setReservation_hotel_no(int reservation_hotel_no) {
		this.reservation_hotel_no = reservation_hotel_no;
	}

	public int getReservation_room_no() {
		return reservation_room_no;
	}

	public void setReservation_room_no(int reservation_room_no) {
		this.reservation_room_no = reservation_room_no;
	}

	public String getReservation_user_id() {
		return reservation_user_id;
	}

	public void setReservation_user_id(String reservation_user_id) {
		this.reservation_user_id = reservation_user_id;
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

	public ReviewVO getReview_vo() {
		return review_vo;
	}

	public void setReview_vo(ReviewVO review_vo) {
		this.review_vo = review_vo;
	}

	// toString()
	@Override
	public String toString() {
		return "ReservationVO [reservation_no=" + reservation_no + ", reservation_check_in=" + reservation_check_in
				+ ", reservation_check_out=" + reservation_check_out + ", reservation_total_price="
				+ reservation_total_price + ", reservation_hotel_no=" + reservation_hotel_no + ", reservation_room_no="
				+ reservation_room_no + ", reservation_user_id=" + reservation_user_id + ", hotel_vo=" + hotel_vo
				+ ", room_vo=" + room_vo + ", review_vo=" + review_vo + "]";
	}

}
