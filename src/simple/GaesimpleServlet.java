package simple;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import simple.dao.UserDao;

@SuppressWarnings("serial")
public class GaesimpleServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/welcome.jsp");
        rd.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        final boolean result = userDao.entry(req);

        final String message = result ? "success" : "[error] this id exists";
        req.setAttribute("msg", message);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
        rd.forward(req, res);
    }
}
