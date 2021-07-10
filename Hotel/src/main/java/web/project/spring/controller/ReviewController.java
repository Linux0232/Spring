package web.project.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.project.spring.domain.ReservationVO;
import web.project.spring.domain.ReviewVO;
import web.project.spring.service.HotelService;
import web.project.spring.service.ReservationService;
import web.project.spring.service.ReviewService;

@Controller
@RequestMapping(value = "/hotel/review") // url : /spring/hotel/review/
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	// 댓글 이미지 첨부파일 업로드 될 경로
	private static final String uploadPath = "C:\\Study\\reviewImages\\";

	@Autowired
	private ReviewService review_service;
	@Autowired
	private ReservationService reservation_service;
	@Autowired
	private HotelService hotel_service;
	
	// '호텔리뷰' 작성페이지
	@GetMapping("/insertreview")
	public void insertReviewGET(String reservation_no, Model model, HttpSession http_session) {
		logger.info("registerReviewGET() 호출 : reservation_no = " + reservation_no);
		model.addAttribute("reservation_no", reservation_no);
		
		ReservationVO reservation_vo = reservation_service.readByReservatonNo(reservation_no);
		logger.info(reservation_vo.toString());
		model.addAttribute("reservation_vo", reservation_vo);
		
		String review_user_id = (String) http_session.getAttribute("user_id");
		model.addAttribute("review_user_id", review_user_id);
	}// end insertReviewGET
	
	// '호텔리뷰' 작성기능
	@PostMapping("/insertreview")
	public String insertReviewPOST(MultipartFile file, ReviewVO review_vo, RedirectAttributes redirect_attributes) throws Exception {
		logger.info("registerreviewPOST() 호출 : review_vo = " + review_vo.toString());
		
		// file = 이미지 파일이 있을 때
		if(file.isEmpty() == false) {
			// uuid 생성
			UUID uuid = UUID.randomUUID(); // 파일명을 위한 랜덤함수
			String review_img_file = uuid + "";
			review_img_file = review_img_file.substring(0, 8); // 8자리
			
			// 기존의 파일에서 확장자 추출
			String file_name = file.getOriginalFilename(); 
			String extension = file_name.substring(file_name.lastIndexOf(".") + 1); // 확장자의 마지막까지 들고오기 위해 +1을 해줌
			review_vo.setReview_img_file(review_img_file + "." + extension); // 이미지명 + . + 확장자

			// 저장되는 이미지 파일 이름 : review_hotel_no + _ + review_img_file
			String saved_file = review_vo.getReview_hotel_no() + "_" + review_vo.getReview_img_file();
			
			// 업로드 될 target
			File target = new File(uploadPath, saved_file);
			try {
				FileCopyUtils.copy(file.getBytes(), target);
				logger.info("파일 저장 성공. 저장된 파일 이름 : " + saved_file);
			} catch (IOException e){
				logger.info("파일 저장 실패");
			}
		} else { // 이미지 파일이 없을 떄 file명을 none으로 하여 리뷰 insert
			review_vo.setReview_img_file("none");
		}
		
		int result = review_service.createReview(review_vo);
		if(result == 1) {
			redirect_attributes.addFlashAttribute("insert_result", "success");
			return "redirect:/hotel/reservation/reservation";
		} else {
			redirect_attributes.addFlashAttribute("insert_result", "fail");
			return "redirect:/hotel/review/insertreview";
		}
	}// end insertReviewPOST
	
	// '호텔리뷰' 수정페이지
	@GetMapping("/updatereview")
	public void updateReviewGET(int review_hotel_no, int review_no, String page, String check_in, String check_out, Model model) {
		logger.info("updateReviewGET() 호출 : review_hotel_no = " + review_hotel_no + ", review_no = " + review_no
				+ ", page = " + page + ", check_in = " + check_in + ", check_out = " + check_out);
		
		ReviewVO review_vo = review_service.readByReviewHotelNoReviewNo(review_hotel_no, review_no);
		// 수정 전의 조건을 페이지에 보여주기 위해 model로 보냄
		model.addAttribute("review_vo", review_vo);
		model.addAttribute("page", page);
		model.addAttribute("check_in", check_in);
		model.addAttribute("check_out", check_out);
	}// end updateReviewGET
	
	// '호텔리뷰' 수정기능
	@PostMapping("/updatereview")
	public String updateReviewPOST(MultipartFile file, ReviewVO review_vo, RedirectAttributes redirect_attributes,
			String page, String check_in, String check_out) throws Exception {
		logger.info("updateReviewPOST() 호출 : review_vo = " + review_vo.toString() 
				+ ", page = " + page + ", check_in = " + check_in + ", check_out = " + check_out);
		
		// 수정 전 review_vo
		ReviewVO old_review_vo = review_service.readByReviewHotelNoReviewNo(review_vo.getReview_hotel_no(), review_vo.getReview_no()); 
		// 수정전 이미지 파일 유무 확인
		String old_review_img_file = old_review_vo.getReview_img_file();
		
		// file = 수정하는 이미지 파일이 있을때 새로운 이미지파일(review_img_file)을 생성 후 review_vo 업데이트
		if(file.isEmpty() == false) {
			// uuid 생성
			UUID uuid = UUID.randomUUID(); // 파일명을 위한 랜덤함수
			String review_img_file = uuid + "";
			review_img_file = review_img_file.substring(0, 8); // 8자리
			
			// 기존의 파일에서 확장자 추출
			String file_name = file.getOriginalFilename(); 
			String extension = file_name.substring(file_name.lastIndexOf(".") + 1); // 확장자의 마지막까지 들고오기 위해 +1을 해줌
			review_vo.setReview_img_file(review_img_file + "." + extension); // 이미지명 + . + 확장자
			logger.info("수정 review_img_file : " + review_img_file);
			
			// 저장되는 이미지 파일 이름 : review_hotel_no + _ + review_img_file
			String saved_file = review_vo.getReview_hotel_no() + "_" + review_vo.getReview_img_file();
			// 수정전 저장된 이미지 파일 이름 : review_hotel_no + _ + old_review_img_file
			String old_saved_file = review_vo.getReview_hotel_no() + "_" + old_review_img_file;
			
			// 업로드 될 target
			File target = new File(uploadPath, saved_file);
			// 지워질 old_target
			File old_target = new File(uploadPath, old_saved_file);
			try {
				// target 저장
				FileCopyUtils.copy(file.getBytes(), target);
				logger.info("파일 저장 성공. 저장된 파일 이름 : " + saved_file);
				// old target 삭제
				Boolean delete_result = old_target.delete();
				logger.info("이미지 삭제 결과 : " + delete_result);
			} catch (IOException e){
				logger.info("파일 저장 실패");
			}
		} else { 
			if(old_review_img_file.equals("none")){ // 수정하는 이미지가 없고 수정 전에도 이미지 파일이 없을 떄 file명을 none으로 하여 리뷰 update
				review_vo.setReview_img_file("none");
			} else { // 수정하는 이미지가 없고 수정 전에 이미지 파일이 있을 떄 file명을 가져와 리뷰 update
				review_vo.setReview_img_file(old_review_img_file);
				logger.info("수정 이미지 파일 : " + old_review_img_file);
			}
		}
		
		int result = review_service.updateReview(review_vo);
		logger.info(result + " : 업데이트 성공");
		
		hotel_service.updateHotelStar(review_vo.getReview_hotel_no());
		logger.info("호텔 평점 업데이트 성공");
		
		if(result == 1 && !page.isEmpty()) { // detail에서 넘어왔을 때(page 데이터가 있다.) 
			redirect_attributes.addFlashAttribute("update_result", "success");
			return "redirect:/hotel/board/detail?hotel_no="+review_vo.getReview_hotel_no()+"&page="+page+"&check_in="+check_in+"&check_out="+check_out;
		} else if(result == 1 && page.isEmpty()){ // my review list에서 넘어왔을 때
			redirect_attributes.addFlashAttribute("update_result", "success");
			return "redirect:/hotel/review/myreviewlist?user_id="+review_vo.getReview_user_id(); // my review list로 리턴
		} else {
			redirect_attributes.addFlashAttribute("update_result", "fail");
			return "redirect:/hotel/review/updatereview";
		}
	} // end updateReviewPOST
	
	// '호텔리뷰' 목록페이지
	@GetMapping("/myreviewlist")
    public void myReviewListGET(Model model, RedirectAttributes redirect_attributes, HttpSession http_session, String user_id) {
       logger.info("myReviewListGET() 호출 : user_id = " + user_id);
       
       List<ReviewVO> list = review_service.readAllReviewByUserId(user_id);
       logger.info(list.toString());
       model.addAttribute("list", list);
    } //end myReviewListGET
	
	// '호텔리뷰' 삭제페이지
    @GetMapping("/myreviewdelete")
    public String myReviewDeleteGET(int review_no, int review_hotel_no, String user_id, RedirectAttributes redirect_attributes) throws Exception {
       logger.info("myReviewDeleteGET()호출 : review_no = " + review_no + ", review_hotel_no = " + review_hotel_no + ", user_id : " + user_id);
       
       // 들어온 review_no와 review_hotel_no를 통해 review_vo를 읽어 저장된 이미지 파일명(saved_file)을 꺼낸다. 
       ReviewVO review_vo = review_service.readByReviewHotelNoReviewNo(review_hotel_no, review_no);
       String saved_file = review_hotel_no+"_"+review_vo.getReview_img_file();
       logger.info("저장된 이미지 파일명 : " + saved_file);
       
       // 지울 이미지를 target변수에 저장하여 delete 해준다.
       File target = new File(uploadPath, saved_file);
       logger.info("target : " + target);
       Boolean delete_result = target.delete(); 
	   logger.info("이미지 삭제 결과 : " + delete_result);
       
       int result = review_service.deleteReview(review_no, review_hotel_no);
       if (result == 1) {
    	   redirect_attributes.addFlashAttribute("msg", "삭제되었습니다.");
           return "redirect:myreviewlist?user_id=" + user_id;
       } else {
    	   redirect_attributes.addFlashAttribute("msg", "삭제실패하였습니다.");
          return "redirect:myreviewlist?user_id=" + user_id; 
       }
    } // end myReviewDeleteGET
}
