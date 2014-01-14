package simple;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import simple.dao.*;
import simple.model.*;

@SuppressWarnings("serial")
public class QueryServlet extends HttpServlet {

    private static final String PARAM_FILE_TYPE = "file_type";
    private static final String PARAM_SORT_TYPE = "sort_type";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/query.jsp");
        rd.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final FileType fileType = FileType.convert(req.getParameter(PARAM_FILE_TYPE));
        final SortType sortType = SortType.convert(req.getParameter(PARAM_SORT_TYPE));

        final EntityFacade facade = new EntityFacade();
        final EntityDao entityDao = facade.createDao(fileType);
        final String json = entityDao.getFlexigridJson(sortType);

        res.setContentType("application/json; charset=utf-8");
        PrintWriter out = res.getWriter();
        out.print(json);
    }
};
