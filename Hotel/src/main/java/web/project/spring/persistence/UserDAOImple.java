package web.project.spring.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.project.spring.domain.UserVO;

@Repository // 영속계층의 DB관련 기능을 담당
public class UserDAOImple implements UserDAO{
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImple.class);
	private static final String NAMESPACE = "web.project.spring.UserMapper";
	// user-mapper.xml파일의 <mapper namespace=" 경로 "> 와 동일
	
	@Autowired // Mybatis의 SqlSession을 사용하기 위해서 스프링 프레임워크가 생성한 bean을 주입받음
	private SqlSession sqlSession;
	
	@Override
	public int insertUser(UserVO user_vo) {
		logger.info("insertUser() 호출 : user_vo = " + user_vo);
		return sqlSession.insert(NAMESPACE + ".insert_user", user_vo);
	}
	
	@Override
	public UserVO selectByUserId(String user_id) {
		logger.info("selectByUserId() 호출 : user_id = " + user_id);
		return sqlSession.selectOne(NAMESPACE + ".select_by_user_id", user_id);
	}
	
	@Override
	public UserVO selectByUserIdUserPw(String user_id, String user_pw) {
		logger.info("selectByUserIdUserPw() 호출 : user_id = " + user_id + " user_pw = " + user_pw);
		 
		Map<String, String> UserInfo = new HashMap<String,String>();
		UserInfo.put("user_id", user_id);
		UserInfo.put("user_pw", user_pw);
	      
	    return sqlSession.selectOne(NAMESPACE + ".select_by_user_id_user_pw", UserInfo);
	}

	@Override
	public UserVO selectByUserNameUserEmail(String user_name, String user_email) {
		logger.info("selectByUserNameUserEmail() 호출 : user_name = " + user_name + " user_email = " + user_email);
		
		Map<String, String> UserInfo = new HashMap<String,String>();
		UserInfo.put("user_name", user_name);
		UserInfo.put("user_email", user_email);
		
		return sqlSession.selectOne(NAMESPACE + ".select_by_user_name_user_email", UserInfo);
	}
	
	@Override
	public UserVO selectByUserIdUserEmail(String user_id, String user_email) {
		logger.info("selectByUserIdUserEmail() 호출 : user_id = " + user_id + " user_email = " + user_email);
		
		Map<String, String> UserInfo = new HashMap<String,String>();
		UserInfo.put("user_id", user_id);
		UserInfo.put("user_email", user_email);
	      
	    return sqlSession.selectOne(NAMESPACE + ".select_by_user_id_user_email", UserInfo);
	}
	
	@Override
	public int selectByUserIdCheck(String user_id) {
		logger.info("selectByUserIdCheck() 호출 : user_id = " + user_id);
		return sqlSession.selectOne(NAMESPACE + ".select_by_user_id_check", user_id);
	}
	
	@Override
	public int selectByUserEmailCheck(String user_email) {
		logger.info("selectByUserEmailCheck() 호출 : user_email = " + user_email);
		return sqlSession.selectOne(NAMESPACE + ".select_by_user_email_check", user_email);
	}
	
	@Override
	public UserVO selectByUserIdUserPwUserEmail(String user_id, String user_pw, String user_email) {
		logger.info("selectByUserIdUserPwUserEmail() 호출 : user_id = " + user_id + " user_pw = " + user_pw + " user_email = " + user_email);
		
		Map<String, String> UserInfo = new HashMap<String, String>();
		UserInfo.put("user_id", user_id);
		UserInfo.put("user_pw", user_pw);
		UserInfo.put("user_email", user_email);
		return sqlSession.selectOne(NAMESPACE + ".select_by_user_id_user_pw_user_email", UserInfo);
	}
	
	@Override
	public int updateUser(UserVO user_vo) {
		logger.info("updateUser() 호출 : user_vo = " + user_vo);
		return sqlSession.update(NAMESPACE + ".update_user", user_vo);
	}
	
	@Override
	public int updateUserPw(UserVO user_vo) {
		logger.info("updateUserPw() 호출 : user_vo = " + user_vo);
		return sqlSession.update(NAMESPACE + ".update_user_pw", user_vo);
	}
	
	@Override
	public int deleteUser(String user_id) {
		logger.info("deleteUser() 호출 : user_id = " + user_id);
		return sqlSession.delete(NAMESPACE + ".delete_user", user_id);
	}

}
