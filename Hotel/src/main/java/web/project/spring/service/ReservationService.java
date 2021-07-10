package web.project.spring.service;

import java.util.List;

import web.project.spring.domain.ReservationVO;

public interface ReservationService {
	// 예약자 insert
	public abstract int createReservation(ReservationVO reservation_vo);
	// 예약번호로 예약정보 검색
	public abstract ReservationVO readByReservatonNo(String reservation_no);
	// 완료된 나의 예약정보(오늘날짜기준)
	public abstract List<ReservationVO> readByReservationUserIdAfterCheckOut(String reservation_user_id);
	// 예정된 나의 예약정보(오늘날짜기준)
	public abstract List<ReservationVO> readByReservationUserIdBeforeCheckOut(String reservation_user_id);
	// 예약자 delete
	public abstract int deleteReservation(String reservation_no);
}
