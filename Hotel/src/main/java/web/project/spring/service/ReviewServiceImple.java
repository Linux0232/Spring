package web.project.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.project.spring.domain.ReviewVO;
import web.project.spring.persistence.HotelDAO;
import web.project.spring.persistence.ReviewDAO;

@Service
public class ReviewServiceImple implements ReviewService{
	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	
	@Autowired
	private ReviewDAO review_dao;
	@Autowired
	private HotelDAO hotel_dao; // 리뷰수와 평점 업데이트를 위한 autowired 선언
	
	// @Transactional
	// - 두 개의 데이터베이스 변경이 생길 때
	// 	 위의 내용이 실행되었고, 아래 내용이 에러가 발생하면
	//   위의 내용은 rollback 되어야 한다.
	//   이러한 기능을 해주는 어노테이션
	// root-context.xml에서 설정
	
	@Transactional
	@Override
	public int createReview(ReviewVO review_vo) {
		// 리뷰가 증가하면
		// 호텔에 리뷰 개수가 변경되어야 함
		// hotel_review 테이블에 insert를 수행하면
		// hotel_info 테이블에 update를 수행한다.		
		// TODO Auto-generated method stub
		
		logger.info("createReview() 호출 : review_vo = " + review_vo.toString());
		review_dao.insertReview(review_vo);
		logger.info("리뷰 입력 성공");
		
		if(review_vo.getReview_star() != 0) {
			hotel_dao.updateHotelReviewCount(1, review_vo.getReview_hotel_no());
			logger.info("호텔 리뷰 개수 업데이트 성공");
		}
		
		hotel_dao.updateHotelStar(review_vo.getReview_hotel_no());
		logger.info("호텔 평균 별점 업데이트 성공");
		
		return 1;
	}

	@Override
	public List<ReviewVO> readAllByReviewHotelNo(int review_hotel_no) {
		logger.info("readAllByReviewHotelNo() 호출 : review_hotel_no = " + review_hotel_no);
		return review_dao.selectAllByReviewHotelNo(review_hotel_no);
	}

	@Override
	public ReviewVO readByReviewHotelNoReviewNo(int review_hotel_no, int review_no) {
		logger.info("readByReviewHotelNoReviewNo() 호출 : review_hotel_no = " + review_hotel_no + ", review_no = " + review_no);
		return review_dao.selectByReviewHotelNoReviewNo(review_hotel_no, review_no);
	}

	@Override
	public List<ReviewVO> readAllReviewByUserId(String review_user_id) {
		logger.info("readAllReviewByUserId() 호출 : review_user_id = " + review_user_id);
		return review_dao.selectAllReviewByUserId(review_user_id);
	}

	@Override
	public int updateReview(ReviewVO review_vo) {
		logger.info("updateReview() 호출 : review_vo = " + review_vo.toString());
		return review_dao.updateReview(review_vo);
	}

	@Override
	public int deleteReview(int review_no, int review_hotel_no) {
		logger.info("deleteReview() 호출 : review_no = " + review_no);
		review_dao.deleteReview(review_no);
		logger.info("리뷰 삭제 성공");
		
		hotel_dao.updateHotelReviewCount(-1, review_hotel_no);
		logger.info("호텔 리뷰 개수 업데이트 성공");
		
		hotel_dao.updateHotelStar(review_hotel_no);
		logger.info("호텔 평균 별점 업데이트 성공");
		
		return 1;
	}

	@Override
	public int deleteReply(int review_no) {
		logger.info("deleteReply() 호출 : review_no = " + review_no);
		return review_dao.deleteReply(review_no);
	}

}
