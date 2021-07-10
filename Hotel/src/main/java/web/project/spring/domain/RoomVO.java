package web.project.spring.domain;

public class RoomVO {
	private int room_no; // 방 번호(PK)
	private String room_name; // 방 이름(타입 ex,스탠다드룸)
	private int room_person; // 방 수용 인원
	private int room_per_price; // 1박 기준 요금
	private int room_hotel_no; // 호텔 번호(FK)
	
	// 기본생성자
	public RoomVO() { }

	// using fields()
	public RoomVO(int room_no, String room_name, int room_person, int room_per_price, int room_hotel_no) {
		super();
		this.room_no = room_no;
		this.room_name = room_name;
		this.room_person = room_person;
		this.room_per_price = room_per_price;
		this.room_hotel_no = room_hotel_no;
	}

	// getter setter
	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public int getRoom_person() {
		return room_person;
	}

	public void setRoom_person(int room_person) {
		this.room_person = room_person;
	}

	public int getRoom_per_price() {
		return room_per_price;
	}

	public void setRoom_per_price(int room_per_price) {
		this.room_per_price = room_per_price;
	}

	public int getRoom_hotel_no() {
		return room_hotel_no;
	}

	public void setRoom_hotel_no(int room_hotel_no) {
		this.room_hotel_no = room_hotel_no;
	}

	// toString()
	@Override
	public String toString() {
		return "RoomVO [room_no=" + room_no + ", room_name=" + room_name + ", room_person=" + room_person
				+ ", room_per_price=" + room_per_price + ", room_hotel_no=" + room_hotel_no + "]";
	}
	
}
