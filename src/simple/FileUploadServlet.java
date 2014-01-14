package simple;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import simple.dao.*;
import simple.io.*;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {

    //private final BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/upload.jsp");
        rd.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final FileInfo fileInfo = parse(req);
        req.setAttribute("msg", fileInfo.getFileName() + ":" + fileInfo.getContents().size() + "件");

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/upload_result.jsp");
        rd.forward(req, res);
    }

    private FileInfo parse(HttpServletRequest req) {
        final FileStreamParser fileStreamParser = new FileStreamParser();
        final FileInfo fileInfo = fileStreamParser.parse(req);

        final EntityFacade entryFacade = new EntityFacade();
        entryFacade.entry(fileInfo);

        return fileInfo;
    }
}
