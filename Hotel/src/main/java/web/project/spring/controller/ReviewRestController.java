package web.project.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.project.spring.domain.ReviewVO;
import web.project.spring.service.ReviewService;
import web.project.spring.util.MediaUtil;

@RestController // ReseponseBody + controller
@RequestMapping(value = "/hotel/rest/review")
public class ReviewRestController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewRestController.class);
	private static final String uploadPath = "C:\\Study\\reviewImages\\";
	
	@Autowired
	private ReviewService review_service;
	
	// ---------------------------------------------------------------
	// RequestMapping
	// /hotel/rest/review (POST) : 댓글 추가(insert)
	// /hotel/rest/review/all/숫자 (GET) : 해당 호텔번호(review_hotel_no)의 모든 댓글 검색(select)
	// /hotel/rest/review/숫자 (PUT) : 해당 리뷰 번호(review_no)의 내용을 수정(update)
	// /hotel/rest/review/숫자 (DELETE) : 해당 리뷰 번호(review_no)의 댓글 삭제(delete)
	// ---------------------------------------------------------------
	
	// --------------------------------------------- 댓글(review) ---------------------------------------------
	// (GET) : 해당 호텔번호(review_hotel_no)의 모든 댓글 검색(select)
	// @PathVariable("hotel_no") : /all/{hotel_no}값을 설정된 변수에 저장
	@GetMapping("/all/{hotel_no}") 
	public ResponseEntity<List<ReviewVO>> readReviewList(@PathVariable("hotel_no") int hotel_no) {
		logger.info("readReviewList() 호출 : hotel_no = " + hotel_no);
		
		List<ReviewVO> list = review_service.readAllByReviewHotelNo(hotel_no);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}
	
	// (DELETE) : 해당 리뷰 번호(review_no)의 댓글 삭제(delete)
	@DeleteMapping("/{review_no}") 
	public ResponseEntity<String> deleteReview(@PathVariable("review_no") int review_no, @RequestBody ReviewVO review_vo){
		logger.info("deleteReview() 호출 : review_no = " + review_no + ", review_vo = " + review_vo.toString());
		
		// review_vo의 저장된 이미지 파일명(saved_file)을 꺼낸다.
		ReviewVO review_vo_temp = review_service.readByReviewHotelNoReviewNo(review_vo.getReview_hotel_no(), review_no);
		String saved_file = review_vo.getReview_hotel_no() + "_" + review_vo_temp.getReview_img_file();
		logger.info("저장된 이미지 파일명 : " + saved_file);
		
		logger.info("댓글 삭제");
		// 지울 이미지를 target변수에 저장하여 delete 해준다.
		File target = new File(uploadPath, saved_file);
		logger.info("target : " + target);
		Boolean delete_result = target.delete(); 
		logger.info("이미지 삭제 결과 : " + delete_result);
			
		try {
			if(review_vo.getReview_group() == review_no) {
				logger.info("댓글 삭제");
				review_service.deleteReview(review_no, review_vo.getReview_hotel_no());
			} else {	
				logger.info("대댓글 삭제");
				review_service.deleteReply(review_no);
			}
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	}
	
	@PutMapping("/imgdelete")
	public void imgDelete(@RequestBody ReviewVO review_vo) {
		logger.info("imgDelete()호출 : review_vo = " + review_vo);
		
		// review_vo의 저장된 이미지 파일명(saved_file)을 꺼낸다.
		String saved_file = review_vo.getReview_hotel_no() + "_" + review_vo.getReview_img_file();
		logger.info("저장된 이미지 파일명 : " + saved_file);
		
		// 지울 이미지를 target변수에 저장하여 delete 해준다.
	    File target = new File(uploadPath, saved_file);
	    logger.info("target : " + target);
	    Boolean delete_result = target.delete(); 
		logger.info("이미지 삭제 결과 : " + delete_result);
		
		review_vo.setReview_img_file("none");
		logger.info("review_vo : " + review_vo.toString());
		int result = review_service.updateReview(review_vo);
		logger.info(result + " : 업데이트 성공");
	}
	
	// 리뷰 이미지 불러오기
	@GetMapping("/display")
	public ResponseEntity<byte[]> dislayReviewImg(String file_name) throws Exception{
		logger.info("dislayReviewImg() 호출 : file_name = " + file_name);
		
		if(!file_name.contains("none")) {
			String file_path = uploadPath + file_name;
			logger.info("file_path : " +file_path);
			
			// file_path의 데이터를 읽어옴
			InputStream input_stream = new FileInputStream(file_path);
			
			// 파일 확장자
			String extension = file_path.substring(file_path.lastIndexOf(".") + 1); // 확장자의 마지막까지 들고오기 위해 +1을 해줌
			logger.info("파일 확장자 : " + extension);
			
			// 응답 헤더(response header)에 Content-Type 설정
			HttpHeaders http_headers = new HttpHeaders();
			http_headers.setContentType(MediaUtil.geMediaType(extension));
			
			// 데이터 전송
			//IOUtils.toByteArray(input_stream) : 파일에서 읽은 데이터 , http_headers : 응답 헤더)
			return new ResponseEntity<byte[]> (IOUtils.toByteArray(input_stream), http_headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]> (HttpStatus.OK);
		}
	}
	// -------------------------------------------------------------------------------------------------------------
	
	// --------------------------------------------------- 대댓글 (reply) ---------------------------------------------
	@PostMapping("/replies")
	public ResponseEntity<Integer> insertReply(HttpSession http_session, @RequestBody ReviewVO review_vo) {
		logger.info("insertReplyPOST() 호출 : review_vo = " + review_vo.toString());
		
		try {
			int result = review_service.createReview(review_vo);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
	}
		
	// (PUT) : 해당 리뷰 번호(review_no)의 내용을 수정(update)
	@PutMapping("/{review_no}")
	public ResponseEntity<String> updateReplyPUT(@PathVariable("review_no") int review_no, @RequestBody ReviewVO review_vo){
		logger.info("updateReplyPUT() 호출 : review_no = " + review_no + ", review_vo = " + review_vo.toString());
		
		int result = review_service.updateReview(review_vo);
		if(result == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	}
	
	// (Delete) : 리뷰삭제와 동일
	// -------------------------------------------------------------------------------------------------------------
	
}
