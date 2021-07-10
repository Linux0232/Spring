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
import web.project.spring.domain.LikeVO;

@Repository
public class LikeDAOImple implements LikeDAO{
   private static final Logger logger = LoggerFactory.getLogger(LikeDAOImple.class);
   private static final String NAMESPACE = "web.project.spring.LikeMapper";
   
   @Autowired
   private SqlSession sqlSession;
      
   @Override
   public int insertLike(LikeVO like_vo) {
      logger.info("insertByLike() 호출 : like_vo = " + like_vo.toString());
      return sqlSession.insert(NAMESPACE+".insert_like", like_vo);
   }

   @Override
   public List<HotelVO> selectLikeListByUserId(String like_user_id) {
      logger.info("selectByLike() 호출 : like_user_id = " + like_user_id);
      return sqlSession.selectList(NAMESPACE+".select_like_list_by_user_id", like_user_id);
   }

   @Override
   public int deleteLike(int like_hotel_no, String like_user_id) {
      logger.info("deleteByLike() 호출 : like_hotel_no = " + like_hotel_no + ", like_user_id = " + like_user_id);
      
      Map<String, Object> like = new HashMap<String, Object>();
      like.put("like_hotel_no", like_hotel_no);
      like.put("like_user_id", like_user_id);
      
      return sqlSession.delete(NAMESPACE+".delete_like", like);
   }

   @Override
   public int likeBtnPushOrNot(int like_hotel_no, String like_user_id) {
	   logger.info("likeBtnPushOrNot() 호출 : like_user_id = " + like_user_id + ", like_hotel_no" + like_hotel_no);
	   
	   Map<String, Object> like = new HashMap<String, Object>();
	   like.put("like_hotel_no", like_hotel_no);
	   like.put("like_user_id", like_user_id);
	   
	   return sqlSession.selectOne(NAMESPACE+".like_btn_push_or_not", like);
   }

   @Override
   public int selectCountByLikeUserId(String like_user_id) {
      logger.info("selectCountByLikeUserId()호출 : like_user_id = "  + like_user_id);
      return sqlSession.selectOne(NAMESPACE + ".select_count_by_like_user_id", like_user_id);
   }

   @Override
   public int insertLike2(List<LikeVO> like_vo) {
      logger.info("insertByLike2() 호출 : like_vo = " + like_vo.toString());
      return sqlSession.insert(NAMESPACE+".insert_like", like_vo);
   }
      

}