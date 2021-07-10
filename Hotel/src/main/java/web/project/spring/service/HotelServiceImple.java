package web.project.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.RoomVO;
import web.project.spring.pageutil.PageCriteria;
import web.project.spring.persistence.HotelDAO;

@Service
public class HotelServiceImple implements HotelService{
	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImple.class);
	
	@Autowired
	private HotelDAO hotel_dao;
	
	@Override
	public List<HotelVO> readOrderByStar(PageCriteria c) {
		logger.info("readOrderByStar() 호출 : PageCriteria = " + c);
		return hotel_dao.selectOrderByStar(c);
	}

	@Override
	public List<HotelVO> readOrderByReviewCount(PageCriteria c) {
		logger.info("readOrderByReviewCount() 호출 : PageCriteria = " + c);
		return hotel_dao.selectOrderByReviewCount(c);
	}

	@Override
	public List<HotelVO> readOrderByLowPrice(PageCriteria c) {
		logger.info("readOrderByLowPrice() 호출 : PageCriteria = " + c);
		return hotel_dao.selectOrderByLowPrice(c);
	}

	@Override
	public List<HotelVO> readOrderByHighPrice(PageCriteria c) {
		logger.info("readOrderByHighPrice() 호출 : PageCriteria = " + c);
		return hotel_dao.selectOrderByHighPrice(c);
	}

	@Override
	public HotelVO readHotelInfo(int hotel_no) {
		logger.info("readHotelInfo() 호출 : hotel_no = " + hotel_no);
		return hotel_dao.selectHotelInfo(hotel_no);
	}
	
	@Override
	public List<RoomVO> readDetailForRoom(String check_in, String check_out, int hotel_no, int person) {
		logger.info("readByHotelNo() 호출 : check_in : " + check_in + ", check_out : " + check_out
				+ ", hotel_no : " + hotel_no + ", person : " + person);
		return hotel_dao.selectDetailForRoom(check_in, check_out, hotel_no, person);
	}

	@Override
	public int getTotalNumsOfRecords(PageCriteria c) {
		logger.info("getTotalNumsOfRecords() 호출 + keyword = " + c.getUrlKeyword() + ", check_in = " + c.getCheck_in() + ", check_out = " + c.getCheck_out() + ", person = " + c.getPerson());
		return hotel_dao.getTotalNumsOfRecords(c);
	}
	
	@Override
	public int getTotalHotelInfoNums() {
		logger.info("getTotalHotelInfoNums() 호출");
		return hotel_dao.getTotalHotelInfoNums();
	}

	@Override
	public int updateHotelReviewCount(int amount, int hotel_no) {
		logger.info("updateHotelReviewCount() 호출 : amount = " + amount + ", hotel_no = " + hotel_no);
		return hotel_dao.updateHotelReviewCount(amount, hotel_no);
	}

	@Override
	public int updateHotelStar(int review_hotel_no) {
		logger.info("updateHotelStar() 호출 : review_hotel_no = " + review_hotel_no);
		return hotel_dao.updateHotelStar(review_hotel_no);
	}

	@Override
	public int readLikeCount(int hotel_no) {
		logger.info("readLikeCount() 호출 : hotel_no = " + hotel_no);
		return hotel_dao.selectLikeCount(hotel_no);
	}

}
