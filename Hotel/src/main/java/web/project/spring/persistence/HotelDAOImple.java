package web.project.spring.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.RoomVO;
import web.project.spring.pageutil.PageCriteria;

@Repository // 영속계층의 DB관련 기능을 담당
public class HotelDAOImple implements HotelDAO{
	private static final Logger logger = LoggerFactory.getLogger(HotelDAOImple.class);
	private static final String NAMESPACE = "web.project.spring.HotelMapper";
	// hotel-mapper.xml파일의 <mapper namespace=" 경로 "> 와 동일
	
	@Autowired // Mybatis의 SqlSession을 사용하기 위해서 스프링 프레임워크가 생성한 bean을 주입받음
	private SqlSession sqlSession;
	
	@Override
	public List<HotelVO> selectOrderByStar(PageCriteria c) {
		logger.info("selectOrderByStar() 호출 : keyword = " + c.getKeyword() + ", check_in = " + c.getCheck_in() + ", check_out = " + c.getCheck_out() + ", person = " + c.getPerson());
		// 키워드 DB 들어가기 전 % 처리 
		c.setKeyword("%"+c.getKeyword()+"%");
		return sqlSession.selectList(NAMESPACE + ".select_order_by_star", c);
	}

	@Override
	public List<HotelVO> selectOrderByReviewCount(PageCriteria c) {
		logger.info("selectOrderByReviewCount() 호출 : keyword = " + c.getKeyword() + ", check_in = " + c.getCheck_in() + ", check_out = " + c.getCheck_out() + ", person = " + c.getPerson());
		c.setKeyword("%"+c.getKeyword()+"%");
		return sqlSession.selectList(NAMESPACE + ".select_order_by_review_count", c);
	}

	@Override
	public List<HotelVO> selectOrderByLowPrice(PageCriteria c) {
		logger.info("selectOrderByLowPrice() 호출 : keyword = " + c.getKeyword() + ", check_in = " + c.getCheck_in() + ", check_out = " + c.getCheck_out() + ", person = " + c.getPerson());
		c.setKeyword("%"+c.getKeyword()+"%");
		return sqlSession.selectList(NAMESPACE + ".select_order_by_low_price", c);
	}

	@Override
	public List<HotelVO> selectOrderByHighPrice(PageCriteria c) {
		logger.info("selectOrderByHighPrice() 호출 : keyword = " + c.getKeyword() + ", check_in = " + c.getCheck_in() + ", check_out = " + c.getCheck_out() + ", person = " + c.getPerson());
		c.setKeyword("%"+c.getKeyword()+"%");
		return sqlSession.selectList(NAMESPACE + ".select_order_by_high_price", c);
	}

	@Override
	public HotelVO selectHotelInfo(int hotel_no) {
		logger.info("selectHotelInfo() 호출 : hotel_no = " + hotel_no);
		return sqlSession.selectOne(NAMESPACE + ".select_hotel_info", hotel_no);
	}

	@Override
	public List<RoomVO> selectDetailForRoom(String check_in, String check_out, int hotel_no, int person) {
		logger.info("selectDetailForRoom() 호출 : check_in : " + check_in + ", check_out : " + check_out
				+ ", hotel_no : " + hotel_no + ", person : " + person);
		
		Map<String, Object> Info = new HashMap<String,Object>();
		Info.put("check_in", check_in);
		Info.put("check_out", check_out);
		Info.put("hotel_no", hotel_no);
		Info.put("person", person);
		logger.info("Info : " + Info);
		
		return sqlSession.selectList(NAMESPACE + ".select_detail_for_room", Info);
	}

	@Override
	public int getTotalNumsOfRecords(PageCriteria c) {
		logger.info("getTotalNumsOfRecords() 호출 + keyword = " + c.getUrlKeyword() + ", check_in = " + c.getCheck_in() + ", check_out = " + c.getCheck_out() + ", person = " + c.getPerson());
		return sqlSession.selectOne(NAMESPACE + ".total_count", c);
	}

	@Override
	public int getTotalHotelInfoNums() {
		logger.info("getTotalHotelInfoNums() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_hotel_count");
	}
	
	@Override
	public int updateHotelReviewCount(int amount, int hotel_no) {
		logger.info("updateHotelReviewCount() 호출 : amount = " + amount + ", hotel_no = " + hotel_no);
		
		Map<String, Integer> updateInfo = new HashMap<String, Integer>();
		updateInfo.put("amount", amount);
		updateInfo.put("hotel_no", hotel_no);
		
		return sqlSession.update(NAMESPACE + ".update_hotel_review_count", updateInfo);
	}

	@Override
	public int updateHotelStar(int review_hotel_no) {
		logger.info("updateHotelStar() 호출 : review_hotel_no = " + review_hotel_no);
		return sqlSession.update(NAMESPACE + ".update_hotel_star", review_hotel_no);
	}

	@Override
	public int selectLikeCount(int hotel_no) {
		logger.info("selectLikeCount() 호출 : hotel_no = " + hotel_no);
		return sqlSession.selectOne(NAMESPACE + ".select_like_count", hotel_no);
	}

}
