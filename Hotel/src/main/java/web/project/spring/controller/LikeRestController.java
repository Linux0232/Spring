package web.project.spring.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.LikeVO;
import web.project.spring.service.HotelService;
import web.project.spring.service.LikeService;

@RestController
@RequestMapping(value="/hotel/rest/like")
public class LikeRestController {
	 private static final Logger logger = LoggerFactory.getLogger(LikeRestController.class);
	   
	 @Autowired
	 private LikeService like_service;
	   
	 @Autowired
	 private HotelService hotel_service;
	   
	 @PostMapping("/insert") 
	 public ResponseEntity<Integer> createlike(@RequestBody LikeVO like_vo, HttpSession session){
	    // - 클라이언트에서 전송받은 json 데이터를 자바 객체로 변환해주는 annotation
		logger.info("createlike() 호출 : like_vo = " + like_vo.toString());
	    
		//두줄은 필요없없음
		String like_user_id = (String) session.getAttribute("user_id");
	    like_vo.setLike_user_id(like_user_id);
	    
	    int result;
	    int count = like_service.likeBtnPushOrNot(like_vo.getLike_hotel_no(), like_user_id);
	    if(count == 0) {
	       try {
	          result = like_service.createLike(like_vo);
	          return new ResponseEntity<Integer>(result, HttpStatus.OK);
	       } catch (Exception e) {
	          return new ResponseEntity<Integer>(0, HttpStatus.OK);
	       }         
	    } else {
	    	try {
	          result = like_service.deleteLike(like_vo.getLike_hotel_no(),like_vo.getLike_user_id());
	          result = 2;
	          return new ResponseEntity<Integer>(result, HttpStatus.OK);
	       } catch (Exception e) {
	          return new ResponseEntity<Integer>(0, HttpStatus.OK);
	       }                  
	    }
	 }
	   
	 @PostMapping("/select/{like_hotel_no}/user_id/{like_user_id}")
	   public ResponseEntity<Integer> readLikeHeart(@PathVariable("like_hotel_no") int like_hotel_no, @PathVariable("like_user_id") String like_user_id) {
		 logger.info("readLikeHeart() 호출 : like_hotel_no = " + like_hotel_no + ", like_user_id = " + like_user_id);
	      int result = like_service.likeBtnPushOrNot(like_hotel_no, like_user_id);
	      if(result == 1) {
	         return new ResponseEntity<Integer>(result, HttpStatus.OK);
	      }else {
	         return new ResponseEntity<Integer>(0, HttpStatus.OK);
	      }
	   }
	 
	 @GetMapping("/list/{like_user_id}") 
	 public ResponseEntity<List<HotelVO>> readLikes(@PathVariable("like_user_id") String like_user_id){
		   logger.info("readLikes() 호출 : like_user_id = " + like_user_id);
		   
	      List<HotelVO> list = like_service.readLikeListByUserId(like_user_id);
	      logger.info("list : " + list);
	      return new ResponseEntity<List<HotelVO>>(list, HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/{like_hotel_no}/{like_user_id}")
	 public ResponseEntity<String> deleteLike(@PathVariable("like_hotel_no") int like_hotel_no, @PathVariable("like_user_id") String like_user_id) {
		 logger.info("deleteLike() 호출 : like_hotel_no = " + like_hotel_no + ", like_user_id = " + like_user_id);
		 
		 try {
			 like_service.deleteLike(like_hotel_no, like_user_id);
	         return new ResponseEntity<String>("success", HttpStatus.OK);
		 } catch(Exception e) {
			 return new ResponseEntity<String>("fail", HttpStatus.OK);
		 }
	 }
	 
	 @PostMapping("/list/countuser/{like_user_id}")
	 public ResponseEntity<Integer> getLike(@PathVariable("like_user_id") String like_user_id) {
	     logger.info("getLike() 호출 : like_user_id = " + like_user_id);
	     
		 int result = like_service.readCountByLikeUserId(like_user_id);
	     return new ResponseEntity<Integer>(result,HttpStatus.OK);
	 }
	 
	 @GetMapping("/likecount/{like_hotel_no}")
	 public ResponseEntity<Integer> readLikecount(@PathVariable("like_hotel_no") int hotel_no) {
		 logger.info("readLikecount() 호출 : like_hotel_no = " + hotel_no);
		 
		 int result = hotel_service.readLikeCount(hotel_no);
		 return new ResponseEntity<Integer>(result,HttpStatus.OK);
	 }
	 
	 @GetMapping("/test/{userid}")
	 public ResponseEntity<List<HotelVO>> aaa(@PathVariable("userid") String like_user_id){
		 logger.info(like_user_id);
		 List<HotelVO> list = like_service.readLikeListByUserId(like_user_id);
		 logger.info(list + "");
		 ResponseEntity<List<HotelVO>> a = new ResponseEntity<List<HotelVO>>(list, HttpStatus.OK);
		 logger.info(a + "");
		 return new ResponseEntity<List<HotelVO>>(list, HttpStatus.OK); // String 형태의 JSON데이터로 변환
	 }
	 
	 
	 
	 @PostMapping("/json-handler")
	 public ResponseEntity<Integer> bbb(@RequestBody List<LikeVO> vo){
		 
		 logger.info(vo+"");
		 String like_user_id = null;
		 List<LikeVO> c = vo;
		 int result = 1;
//		 for (int i = 0; i < 2; i++) {
//			LikeVO b = c.get(i);
//			logger.info(b + "");
//			int result2 = like_service.createLike(b);
//		}
		 int result2 = like_service.createLike2(vo);
		 //List<HotelVO> list = like_service.readLikeListByUserId(like_user_id);

		 //ResponseEntity<List<HotelVO>> a = new ResponseEntity<List<HotelVO>>(list, HttpStatus.OK);
		 return new ResponseEntity<Integer>(result2, HttpStatus.OK); // String 형태의 JSON데이터로 변환
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
