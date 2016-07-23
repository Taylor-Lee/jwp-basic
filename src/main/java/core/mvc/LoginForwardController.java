package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class LoginForwardController extends ForwardController {
	public LoginForwardController(String forwardUrl) {
		super(forwardUrl);
	}
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Modify question only for login user
		if (forwardUrl.equals("/qna/reform.jsp")) {
			// Check if writer and login user is same
			QuestionDao qDao = new QuestionDao();
			UserDao uDao = new UserDao();
			long questionId = Long.parseLong(request.getParameter("questionId"));
			Question question = qDao.findById(questionId);
			User user = uDao.findByUserName(question.getWriter());
			HttpSession session = request.getSession();
			if (!UserSessionUtils.isSameUser(session, user))
				return jspView("redirect:/users/loginForm");
			
			ModelAndView mav = jspView(forwardUrl);
			mav.addObject("question", question);
			return mav;
		}

		if (!isLogin(request))
			return jspView("redirect:/");
		
		return jspView(forwardUrl);
	}
}
