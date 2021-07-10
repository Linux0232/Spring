package web.project.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.project.spring.domain.ReservationVO;
import web.project.spring.service.ReservationService;

@RestController // ReseponseBody + controller
@RequestMapping(value = "/hotel/rest/reservation")
public class ReservationRestController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	private ReservationService reservation_service;
	
	//'호텔예약' 결재완료기능
	@PostMapping("/payment")
	public ResponseEntity<Integer> paymentPOST(@RequestBody ReservationVO reservation_vo){
		logger.info("paymentPOST() 호출 -- ReservationRestController");
		logger.info("reservation_vo : " + reservation_vo.toString());
		
		try {
			int result = reservation_service.createReservation(reservation_vo);
			logger.info("result : " + result + ", 삽입성공");
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
	}
	//'호텔예약' 예정된 예약목록 기능
	@GetMapping("/reservationbefore")
	public ResponseEntity<List<ReservationVO>> selectMyReserveBefore(HttpSession http_session){
		logger.info("selectMyReserveBefore() 호출 -- ReservationRestController");
		
		String reservation_user_id = (String) http_session.getAttribute("user_id");
		logger.info("reservation_user_id : " + reservation_user_id);
		
		List<ReservationVO> reservation_before = reservation_service.readByReservationUserIdBeforeCheckOut(reservation_user_id);
		logger.info("reservation_before_vo 확인!!!: " + reservation_before);
		return new ResponseEntity<List<ReservationVO>>(reservation_before, HttpStatus.OK);
	}
	//'호텔예약' 예약후 목록 기능
	@GetMapping("/reservationafter")
	public ResponseEntity<List<ReservationVO>> selectMyReserveAfter(HttpSession http_session){
		logger.info("selectMyReserveAfter() 호출 -- ReservationRestController");
		
		String reservation_user_id = (String) http_session.getAttribute("user_id");
		logger.info("reservation_user_id : " + reservation_user_id);
		
		List<ReservationVO> reservation_after = reservation_service.readByReservationUserIdAfterCheckOut(reservation_user_id);
		logger.info("reservation_after_vo 확인!!!: " + reservation_after);
		return new ResponseEntity<List<ReservationVO>>(reservation_after, HttpStatus.OK);
	}
	//'호텔예약' 결재취소 기능
	@DeleteMapping("canclepayment")
	public ResponseEntity<Integer> canclepaymentPOST(@RequestBody ReservationVO reservation_vo){
		logger.info("canclepaymentDELETE() 호출 : reservation_vo = " + reservation_vo.toString() );
		
		try {
			int result = reservation_service.deleteReservation(reservation_vo.getReservation_no());
			logger.info("result : " + result + ", 삭제 성공");
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
	}
}
