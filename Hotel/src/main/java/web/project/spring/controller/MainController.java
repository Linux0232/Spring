package web.project.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hotel") // url : /spring/hotel/
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 메인페이지
	@GetMapping("/main")
	public void main() {
	   logger.info("main() 호출");
	}
}
