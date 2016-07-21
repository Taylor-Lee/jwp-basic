package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DeleteAnswerController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Result result = Result.ok();
		try {
			long answerId = Long.parseLong(req.getParameter("answerId"));
			AnswerDao answerDao = new AnswerDao();
			answerDao.deleteById(answerId);
		} catch (Exception e) {
			result = Result.fail("Fail to delete (In Server)");
		} finally {
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(mapper.writeValueAsString(Result.ok()));
		}
		return null;
	}
}
