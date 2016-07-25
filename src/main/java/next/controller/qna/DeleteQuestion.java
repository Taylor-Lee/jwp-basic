package next.controller.qna;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;

public class DeleteQuestion {
	private static final Logger log = LoggerFactory.getLogger(DeleteQuestion.class);

	static private QuestionDao questionDao = new QuestionDao();

	static boolean deleteQuestion(HttpServletRequest req) {
		String strId = req.getParameter("questionId");
		if (strId != null) {
			long questionId = Long.parseLong(strId);
			log.debug("questionId : {}", questionId);
			
			return questionDao.delete(questionId);
		}
		
		return false;
	}
}
