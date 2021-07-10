package web.project.spring.service;

import java.util.List;

import web.project.spring.domain.ReviewVO;

public interface ReviewService {
	// 리뷰 삽입
	public abstract int createReview(ReviewVO review_vo);
	// 호텔 리뷰 불러오기(detail에 review목록 list up)
	public abstract List<ReviewVO> readAllByReviewHotelNo(int review_hotel_no);
	// 해당 호텔의 리뷰번호를 찾아 수정페이지에 불러오기
	public abstract ReviewVO readByReviewHotelNoReviewNo(int review_hotel_no, int review_no);
	// 내가 쓴 리뷰목록 읽어오기(join 사용)
	public abstract List<ReviewVO> readAllReviewByUserId(String review_user_id);
	// 해당 호텔의 리뷰번호를 찾아 내용 수정하고 update
	public abstract int updateReview(ReviewVO review_vo);
	// 리뷰 삭제
	public abstract int deleteReview(int review_no, int review_hotel_no);
	// 대댓글 삭제(해당 대댓글만 삭제됨)
	public abstract int deleteReply(int review_no);
}
