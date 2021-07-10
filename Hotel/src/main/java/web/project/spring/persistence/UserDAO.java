package web.project.spring.persistence;

import web.project.spring.domain.UserVO;

public interface UserDAO {
	// 회원가입시 User정보 insert
	public abstract int insertUser(UserVO user_vo);
	// user_id를 통해 User정보 검색
	public abstract UserVO selectByUserId(String user_id);
	// 로그인시 user_id, user_pw 검색하여 정보일치 확인
	public abstract UserVO selectByUserIdUserPw(String user_id, String user_pw);
	// 이름과 이메일로 아이디찾기
	public abstract UserVO selectByUserNameUserEmail(String user_name, String user_email);
	// 이메일로 비밀번호 찾기
	public abstract UserVO selectByUserIdUserEmail(String user_id, String user_email);
	// 아이디 중복 확인
	public abstract int selectByUserIdCheck(String user_id);
	// 이메일 중복 확인
	public abstract int selectByUserEmailCheck(String user_email);
	// 회원탈퇴시 계정정보 확인(user_id, user_pw, user_email)
	public abstract UserVO selectByUserIdUserPwUserEmail(String user_id, String user_pw, String user_email);
	// User정보 업데이트
	public abstract int updateUser(UserVO user_vo);
	// 비밀번호 찾기시 User정보 업데이트
	public abstract int updateUserPw(UserVO user_vo);
	// User정보 삭제
	public abstract int deleteUser(String user_id);
}
