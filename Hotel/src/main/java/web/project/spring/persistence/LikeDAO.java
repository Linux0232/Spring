package web.project.spring.persistence;

import java.util.List;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.LikeVO;

public interface LikeDAO {
	// 좋아요 insert
    public abstract int insertLike(LikeVO like_vo);
    public abstract int insertLike2(List<LikeVO> like_vo); // 임시
    // 호텔 리스트에서 like 검색
    public abstract List<HotelVO> selectLikeListByUserId(String like_user_id);
    // 좋아요 delete
    public abstract int deleteLike(int like_hotel_no, String like_user_id);
    // like_user_id가 like_hotel_no에 좋아요를 눌렀는지(1) 아닌지(0)
    public abstract int likeBtnPushOrNot(int like_hotel_no, String like_user_id);
    // like_user_id의 좋아요 개수 검색
    public abstract int selectCountByLikeUserId(String like_user_id);
}