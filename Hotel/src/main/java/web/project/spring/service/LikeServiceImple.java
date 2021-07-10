package web.project.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.LikeVO;
import web.project.spring.persistence.LikeDAO;

@Service
public class LikeServiceImple implements LikeService{
   private static final Logger logger = LoggerFactory.getLogger(LikeServiceImple.class);
   
   @Autowired
   public LikeDAO like_dao;
   
   @Override
   public int createLike(LikeVO like_vo) {
      logger.info("createLike()호출 : like_vo = " + like_vo.toString());
      return like_dao.insertLike(like_vo);
   }

   @Override
   public List<HotelVO> readLikeListByUserId(String like_user_id) {
      logger.info("readLikeListByUserId()호출 : like_user_id = " + like_user_id);
      return like_dao.selectLikeListByUserId(like_user_id);
   }

   @Override
   public int deleteLike(int like_hotel_no, String like_user_id) {
      logger.info("deleteLike() 호출 : like_hotel_no = " + like_hotel_no + ", like_user_id = " + like_user_id);
      return like_dao.deleteLike(like_hotel_no, like_user_id);
   }

   @Override
   public int likeBtnPushOrNot(int like_hotel_no, String like_user_id) {
      logger.info("likeBtnPushOrNot() 호출 : like_hotel_no = " + like_hotel_no + ", like_user_id = " + like_user_id);
      return like_dao.likeBtnPushOrNot(like_hotel_no, like_user_id);
   }

   @Override
   public int readCountByLikeUserId(String like_user_id) {
      logger.info("readCountByLikeUserId() 호출 : like_user_id = " + like_user_id);
      return like_dao.selectCountByLikeUserId(like_user_id);
   }
   @Override
   public int createLike2(List<LikeVO> like_vo) {
      logger.info("createLike()호출 : like_vo = " + like_vo.toString());
      return like_dao.insertLike2(like_vo);
   }

}