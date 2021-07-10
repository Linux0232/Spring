package web.project.spring.service;

import web.project.spring.domain.UserVO;

public interface UserService {
	// 회원가입시 User정보 insert
	public abstract int createUser(UserVO user_vo);
	// user_id를 통해 User정보 검색
	public abstract UserVO readByUserId(String user_id);
	// 로그인시 user_id, user_pw 검색하여 정보일치 확인
	public abstract UserVO readByUserIdUserPw(String user_id, String user_pw);
	// 이름과 이메일로 아이디찾기
	public abstract UserVO readByUserNameUserEmail(String user_name, String user_email);
	// 이메일로 비밀번호 찾기
	public abstract UserVO readByUserIdUserEmail(String user_id, String user_email);
	// 아이디 중복 확인
	public abstract int readByUserIdCheck(String user_id);
	// 이메일 중복 확인
	public abstract int readByUserEmailCheck(String user_email);
	// 회원탈퇴시 계정정보 확인(user_id, user_pw, user_email)
	public abstract UserVO readByUserIdUserPwUserEmail(String user_id, String user_pw, String user_email);
	// User정보 업데이트
	public abstract int updateUser(UserVO user_vo);
	// 비밀번호 찾기시 User정보 업데이트
	public abstract int updateUserPw(UserVO user_vo);
	// User정보 삭제
	public abstract int deleteUser(String user_id);
}
