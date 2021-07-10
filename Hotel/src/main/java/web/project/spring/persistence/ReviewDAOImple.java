package web.project.spring.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.project.spring.domain.ReviewVO;

@Repository // 영속계층의 DB관련 기능을 담당
public class ReviewDAOImple implements ReviewDAO{
	private static final Logger logger = LoggerFactory.getLogger(ReviewDAOImple.class);
	private static final String NAMESPACE = "web.project.spring.ReviewMapper";
	// review-mapper.xml파일의 <mapper namespace=" 경로 "> 와 동일
	
	@Autowired // Mybatis의 SqlSession을 사용하기 위해서 스프링 프레임워크가 생성한 bean을 주입받음
	private SqlSession sqlSession;
	
	@Override
	public int insertReview(ReviewVO review_vo) {
		logger.info("insertReview() 호출 : review_vo = " + review_vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert_review", review_vo);
	}

	@Override
	public List<ReviewVO> selectAllByReviewHotelNo(int review_hotel_no) {
		logger.info("selectAllByReviewHotelNo() 호출 : review_hotel_no = " + review_hotel_no);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_review_hotel_no", review_hotel_no);
	}

	@Override
	public ReviewVO selectByReviewHotelNoReviewNo(int review_hotel_no, int review_no) {
		logger.info("selectByReviewHotelNoReviewNo() 호출 : review_hotel_no = " + review_hotel_no + ", review_no = " + review_no);
		
		Map<String, Integer> ReviewInfo = new HashMap<String, Integer>();
		ReviewInfo.put("review_hotel_no", review_hotel_no);
		ReviewInfo.put("review_no", review_no);
		
		return sqlSession.selectOne(NAMESPACE + ".select_by_review_hotel_no_review_no", ReviewInfo);
	}

	@Override
	public List<ReviewVO> selectAllReviewByUserId(String review_user_id) {
		logger.info("selectAllReviewByUserId() 호출 : review_user_id = " + review_user_id);
		return sqlSession.selectList(NAMESPACE + ".select_all_review_by_user_id", review_user_id);
	}

	@Override
	public int updateReview(ReviewVO review_vo) {
		logger.info("updateReview() 호출 : review_vo = " + review_vo.toString());
		return sqlSession.update(NAMESPACE + ".update_review", review_vo);
	}

	@Override
	public int deleteReview(int review_no) {
		logger.info("deleteReview() 호출 : review_no = " + review_no);
		return sqlSession.delete(NAMESPACE + ".delete_review", review_no);
	}

	@Override
	public int deleteReply(int review_no) {
		logger.info("deleteReply() 호출 : review_no = " + review_no);
		return sqlSession.delete(NAMESPACE + ".delete_reply", review_no);
	}

}
