package web.project.spring.persistence;

import java.util.List;

import web.project.spring.domain.ReservationVO;

public interface ReservationDAO {
	// 예약자 insert
	public abstract int insertReservation(ReservationVO reservation_vo);
	// 예약번호로 예약정보 검색
	public abstract ReservationVO selectByReservatonNo(String reservation_no);
	// 완료된 나의 예약정보(오늘날짜기준)
	public abstract List<ReservationVO> selectByReservationUserIdAfterCheckOut(String reservation_user_id);
	// 예정된 나의 예약정보(오늘날짜기준)
	public abstract List<ReservationVO> selectByReservationUserIdBeforeCheckOut(String reservation_user_id);
	// 예약자 delete
	public abstract int deleteReservation(String reservation_no);
}
