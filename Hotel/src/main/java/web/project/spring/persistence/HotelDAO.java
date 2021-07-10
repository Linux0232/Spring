package web.project.spring.persistence;

import java.util.List;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.RoomVO;
import web.project.spring.pageutil.PageCriteria;

public interface HotelDAO {
	// 별점순 정렬기준으로 전체 호텔list 가져옴
	public abstract List<HotelVO> selectOrderByStar(PageCriteria c);
	// 리뷰순 정렬기준으로 전체 호텔list 가져옴
	public abstract List<HotelVO> selectOrderByReviewCount(PageCriteria c);
	// 최저가격순 정렬기준으로 전체 호텔list 가져옴
	public abstract List<HotelVO> selectOrderByLowPrice(PageCriteria c);
	// 최고가격순 정렬기준으로 전체 호텔list 가져옴
	public abstract List<HotelVO> selectOrderByHighPrice(PageCriteria c);
	// hotel_no의 전체 정보를 가져옴
	public abstract HotelVO selectHotelInfo(int hotel_no);
	// 조건(체크인,체크아웃,호텔번호,인원수)에 따른 detail페이지 hotel_room 검색결과
	public abstract List<RoomVO> selectDetailForRoom(String check_in, String check_out, int hotel_no, int person);
	// keyword로 검색한 list total개수
	public abstract int getTotalNumsOfRecords(PageCriteria c);
	// 쿠키의 hotel_info count횟수를 위한 total_count
	public abstract int getTotalHotelInfoNums();
	// 리뷰 등록/삭제후 hotel_review_count 개수 추가/삭제
	public abstract int updateHotelReviewCount(int amount, int hotel_no);
	// 리뷰 등록/삭제후 hotel_star 평점 update
	public abstract int updateHotelStar(int review_hotel_no); 
	// 호텔의 좋아요 개수
	public abstract int selectLikeCount(int hotel_no);
}
