package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController implements Controller {
	protected ModelAndView jspView(String forwardUrl) {
		return new ModelAndView(new JspView(forwardUrl));
	}
	
	protected ModelAndView jsonView() {
		return new ModelAndView(new JsonView());
	}
	
	protected boolean isLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null) {
			return true;
		}
		
		return false;
	}
}
