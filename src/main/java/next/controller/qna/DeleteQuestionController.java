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

	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		long questionId = Long.parseLong(req.getParameter("questionId"));
		log.debug("questionId : {}", questionId);
		
		if (questionDao.delete(questionId))
			return jspView("redirect:/");
		
		// FIXME: Alert the fail and Redirect to current page 
		return jspView("redirect:.");
	}

}
