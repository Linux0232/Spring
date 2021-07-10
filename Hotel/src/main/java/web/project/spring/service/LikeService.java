package web.project.spring.service;

import java.util.List;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.LikeVO;

public interface LikeService {
	// 좋아요 insert
    public abstract int createLike(LikeVO like_vo);
    public abstract int createLike2(List<LikeVO> like_vo);
    // 호텔 리스트에서 like 검색
    public abstract List<HotelVO> readLikeListByUserId(String like_user_id);
    // 좋아요 delete
    public abstract int deleteLike(int like_hotel_no, String like_user_id);
    // like_user_id가 like_hotel_no에 좋아요를 눌렀는지(1) 아닌지(0)
    public abstract int likeBtnPushOrNot(int like_hotel_no, String like_user_id);
    // like_user_id의 좋아요 개수 검색
    public abstract int readCountByLikeUserId(String like_user_id);
}
