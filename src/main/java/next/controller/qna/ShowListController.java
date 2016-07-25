package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ShowListController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		
		questions = questionDao.findAll();
				
		ModelAndView mav = jsonView();
		mav.addObject("questions", questions);

		return mav;
	}
}
