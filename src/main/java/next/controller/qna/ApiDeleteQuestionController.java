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

public class ApiDeleteQuestionController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(ApiDeleteQuestionController.class);

	
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = jsonView();
		// FIXME: Alert the fail
		if (DeleteQuestion.deleteQuestion(req)) {
			mav.addObject("deleteResult", true);
			return mav;
		}
		
		mav.addObject("deleteResult", false);
		return mav;
	}
}