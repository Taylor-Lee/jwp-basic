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

public class DeleteQuestionController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(DeleteQuestionController.class);

	
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// FIXME: Alert the fail
		if (!DeleteQuestion.deleteQuestion(req))
			return jspView("redirect:/qna/show?questionId=" + req.getParameter("questionId"));
		
		return jspView("redirect:/");
	}
}