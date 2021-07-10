package web.project.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.spring.domain.ReservationVO;
import web.project.spring.persistence.ReservationDAO;

@Service
public class ReservationServiceImple implements ReservationService{
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImple.class);

	@Autowired
	private ReservationDAO reservation_dao;
	
	@Override
	public int createReservation(ReservationVO reservation_vo) {
		logger.info("createReservation() 호출 : reservation_vo = " + reservation_vo.toString());
		return reservation_dao.insertReservation(reservation_vo);
	}

	@Override
	public ReservationVO readByReservatonNo(String reservation_no) {
		logger.info("readByReservatonNo() 호출 : reservation_no = " + reservation_no);
		return reservation_dao.selectByReservatonNo(reservation_no);
	}

	@Override
	public List<ReservationVO> readByReservationUserIdAfterCheckOut(String reservation_user_id) {
		logger.info("readByReservationUserIdAfterCheckOut() 호출 : reservation_user_id = " + reservation_user_id);
		return reservation_dao.selectByReservationUserIdAfterCheckOut(reservation_user_id);
	}

	@Override
	public List<ReservationVO> readByReservationUserIdBeforeCheckOut(String reservation_user_id) {
		logger.info("readByReservationUserIdBeforeCheckOut() 호출 : reservation_user_id = " + reservation_user_id);
		return reservation_dao.selectByReservationUserIdBeforeCheckOut(reservation_user_id);
	}

	@Override
	public int deleteReservation(String reservation_no) {
		logger.info("deleteReservation() 호출 : reservation_no = " + reservation_no);
		return reservation_dao.deleteReservation(reservation_no);
	}
}
