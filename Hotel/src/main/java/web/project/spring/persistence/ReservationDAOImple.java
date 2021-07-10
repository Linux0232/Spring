package web.project.spring.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.project.spring.domain.ReservationVO;

@Repository // 영속계층의 DB관련 기능을 담당
public class ReservationDAOImple implements ReservationDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReservationDAOImple.class);
	private static final String NAMESPACE = "web.project.spring.ReservationMapper";
	// hotel-mapper.xml파일의 <mapper namespace=" 경로 "> 와 동일
	
	@Autowired // Mybatis의 SqlSession을 사용하기 위해서 스프링 프레임워크가 생성한 bean을 주입받음
	private SqlSession sqlSession;
	
	@Override
	public int insertReservation(ReservationVO reservation_vo) {
		logger.info("insertReservation() 호출 : reservation_vo = " + reservation_vo);
		return sqlSession.insert(NAMESPACE + ".insert_reservation", reservation_vo);
	}

	@Override
	public ReservationVO selectByReservatonNo(String reservation_no) {
		logger.info("selectByReservatonNo() 호출 : reservation_no = " + reservation_no);
		return sqlSession.selectOne(NAMESPACE + ".select_by_reservation_no", reservation_no);
	}

	@Override
	public List<ReservationVO> selectByReservationUserIdAfterCheckOut(String reservation_user_id) {
		logger.info("selectByReservationUserIdAfterCheckOut() 호출 : reservation_user_id = " + reservation_user_id);
		return sqlSession.selectList(NAMESPACE + ".select_by_reservation_user_id_after_check_out", reservation_user_id);
	}

	@Override
	public List<ReservationVO> selectByReservationUserIdBeforeCheckOut(String reservation_user_id) {
		logger.info("selectByReservationUserIdBeforeCheckOut() 호출 : reservation_user_id = " + reservation_user_id);
		return sqlSession.selectList(NAMESPACE + ".select_by_reservation_user_id_before_check_out", reservation_user_id);
	}

	@Override
	public int deleteReservation(String reservation_no) {
		logger.info("deleteReservation() 호출 : reservation_no = " + reservation_no);
		return sqlSession.delete(NAMESPACE + ".delete_reservation", reservation_no);
	}
}
