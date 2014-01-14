package simple.io;

import java.io.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import simple.model.FileType;

public class FileTypeParser {

    private static final String PARAM_FILE_TYPE = "file_type";

    public FileType parse(HttpServletRequest req) {
        final ServletFileUpload fileUpload = new ServletFileUpload();

        try {
            final FileItemIterator itemIterator = fileUpload.getItemIterator(req);

            while (itemIterator.hasNext()) {
                final FileItemStream itemStream = itemIterator.next();

                if (itemStream.isFormField()) {
                    final String filedName = itemStream.getFieldName();

                    if (filedName.equals(PARAM_FILE_TYPE)) {
                        final InputStream inputStream = itemStream.openStream();

                        try {
                            final String value = Streams.asString(inputStream);
                            System.out.println("filedName = " + filedName + ", value = " + value);

                            return FileType.convert(value);
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
