package web.project.spring.domain;

import java.sql.Date;

public class ReservedRoomVO {
	private int reserved_no; // 방 예약번호 PK
	private Date reserved_check_in; // 예약된 체크인날짜
	private Date reserved_check_out; // 예약된 체크아웃날짜
	private int reserved_room_no; // 예약된 방번호 FK(hotel_room)
	private int reserved_reservation_no; // 예약자번호 FK(reservation)
	
	// 기본생성자
	public ReservedRoomVO() {}

	// using fields
	public ReservedRoomVO(int reserved_no, Date reserved_check_in, Date reserved_check_out, int reserved_room_no,
			int reserved_reservation_no) {
		super();
		this.reserved_no = reserved_no;
		this.reserved_check_in = reserved_check_in;
		this.reserved_check_out = reserved_check_out;
		this.reserved_room_no = reserved_room_no;
		this.reserved_reservation_no = reserved_reservation_no;
	}
	
	// getter setter
	public int getReserved_no() {
		return reserved_no;
	}

	public void setReserved_no(int reserved_no) {
		this.reserved_no = reserved_no;
	}

	public Date getReserved_check_in() {
		return reserved_check_in;
	}

	public void setReserved_check_in(Date reserved_check_in) {
		this.reserved_check_in = reserved_check_in;
	}

	public Date getReserved_check_out() {
		return reserved_check_out;
	}

	public void setReserved_check_out(Date reserved_check_out) {
		this.reserved_check_out = reserved_check_out;
	}

	public int getReserved_room_no() {
		return reserved_room_no;
	}

	public void setReserved_room_no(int reserved_room_no) {
		this.reserved_room_no = reserved_room_no;
	}

	public int getReserved_reservation_no() {
		return reserved_reservation_no;
	}

	public void setReserved_reservation_no(int reserved_reservation_no) {
		this.reserved_reservation_no = reserved_reservation_no;
	}

	// toString()
	@Override
	public String toString() {
		return "ReservedRoomVO [reserved_no=" + reserved_no + ", reserved_check_in=" + reserved_check_in
				+ ", reserved_check_out=" + reserved_check_out + ", reserved_room_no=" + reserved_room_no
				+ ", reserved_reservation_no=" + reserved_reservation_no + "]";
	}
	
}
