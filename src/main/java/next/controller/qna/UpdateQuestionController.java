package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class UpdateQuestionController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// FIXME: Only writer can modify
		if (!isLogin(req))
			return jspView("redirect:/");
			
		Question question = new Question(req.getParameter("writer"), 
				req.getParameter("contents"), 
				req.getParameter("title"));
		log.debug("question : {}", question);
		
		questionDao.update(question);
		return jspView("redirect:/qna/show?questionId=" + question.getQuestionId());
	}

}
