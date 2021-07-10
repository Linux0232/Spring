package web.project.spring.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.project.spring.domain.ReservationVO;
import web.project.spring.service.ReservationService;

@Controller
@RequestMapping(value = "/hotel/reservation") // url : /spring/hotel/board/
public class ReservationController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private ReservationService reservation_service;
	
	// '호텔예약' 결재완료 페이지
	@GetMapping("/paysuccess")
	public void paysuccessGET(Model model, String reservation_no, HttpSession http_session) {
		logger.info("paysuccessGet() 호출 : reservation_no = " + reservation_no);
		model.addAttribute("reservation_no", reservation_no);
		
		String reservation_id = (String) http_session.getAttribute("user_id");
		logger.info("reservation_id : " + reservation_id);
		model.addAttribute("reservation_id", reservation_id);
		
		ReservationVO reservation_vo = reservation_service.readByReservatonNo(reservation_no);
		logger.info("reservation_vo : " + reservation_vo.toString());
		model.addAttribute("reservation_vo", reservation_vo);
	}
	
	// '호텔예약'목록
	@GetMapping("/reservation")
	public void reservationGet(HttpSession http_session, Model model) {
		logger.info("reservationGet() 호출");
		
		String reservation_user_id = (String) http_session.getAttribute("user_id");
		logger.info("reservation_user_id : " + reservation_user_id);
		model.addAttribute("reservation_user_id" , reservation_user_id);
	}
	
	// '호텔예약' 결재취소 페이지
	@GetMapping("/canclecheck")
	public void canclecheckGET(Model model, String reservation_no, HttpSession http_session) {
		logger.info("canclecheckPOST() 호출 : reservation_no = " + reservation_no);
		model.addAttribute("reservation_no", reservation_no);
		
		String reservation_id = (String) http_session.getAttribute("user_id");
		logger.info("reservation_id : " + reservation_id);
		model.addAttribute("reservation_id", reservation_id);
		
		ReservationVO reservation_vo = reservation_service.readByReservatonNo(reservation_no);
		logger.info("reservation_vo : " + reservation_vo.toString());
		model.addAttribute("reservation_vo", reservation_vo);
	}
}
