package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class UpdateQuestionController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (!isLogin(req))
			return jspView("redirect:/user/loginForm");
			
		// Only the writer can modify
		User reqUser = UserSessionUtils.getUserFromSession(req.getSession());
		long questionId = Long.parseLong(req.getParameter("questionId"));
		Question question = questionDao.findById(questionId);
		if (!question.isSameUser(reqUser)) {
			throw new IllegalStateException("Only the writer can modify the question.");			
		}
		Question newQuestion = new Question(req.getParameter("writer"), 
				req.getParameter("title"),
				req.getParameter("contents"));
		log.debug("question : {}", question);
		
		question.update(newQuestion);
		questionDao.update(question);
		return jspView("redirect:/qna/show?questionId=" + question.getQuestionId());
	}

}
