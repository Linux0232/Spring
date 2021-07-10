package web.project.spring.persistence;

import java.util.List;

import web.project.spring.domain.ReviewVO;

public interface ReviewDAO {
	// 리뷰 삽입
	public abstract int insertReview(ReviewVO review_vo);
	// 호텔 리뷰 불러오기(detail에 review목록 list up)
	public abstract List<ReviewVO> selectAllByReviewHotelNo(int review_hotel_no);
	// 해당 호텔의 리뷰번호를 찾아 수정페이지에 불러오기
	public abstract ReviewVO selectByReviewHotelNoReviewNo(int review_hotel_no, int review_no);
	// 내가 쓴 리뷰목록 읽어오기(join 사용)
	public abstract List<ReviewVO> selectAllReviewByUserId(String review_user_id);
	// 해당 호텔의 리뷰번호를 찾아 내용 수정하고 update
	public abstract int updateReview(ReviewVO review_vo);
	// 리뷰 삭제(연관된 대댓글 함께 삭제됨)
	public abstract int deleteReview(int review_no);
	// 대댓글 삭제(해당 대댓글만 삭제됨)
	public abstract int deleteReply(int review_no);
}
