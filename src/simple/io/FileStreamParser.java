package simple.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import simple.model.FileType;

public class FileStreamParser {

    private static final String PARAM_FILE_TYPE = "file_type";

    public FileInfo parse(HttpServletRequest req) {
        final ServletFileUpload fileUpload = new ServletFileUpload();

        try {
            final FileItemIterator itemIterator = fileUpload.getItemIterator(req);
            FileType fileType = null;

            while (itemIterator.hasNext()) {
                final FileItemStream itemStream = itemIterator.next();
                final InputStream inputStream = itemStream.openStream();

                if (itemStream.isFormField()) {
                    final String filedName = itemStream.getFieldName();

                    if (filedName.equals(PARAM_FILE_TYPE)) {

                        final String value = Streams.asString(inputStream);
                        System.out.println("filedName = " + filedName + ", value = " + value);

                        fileType = FileType.convert(value);

                        continue;
                    }
                }

                String contentType = itemStream.getContentType();
                System.out.println("contentType:" + contentType);
                if (contentType == null) {
                    continue;
                }

                if (itemStream.getName() == null) {
                    continue;
                }

                final String fileName = itemStream.getName();
                System.out.println("file name:" + fileName);
                if (contentType.contains("text") || fileName.endsWith(".csv")) {
                    final List<String> lines = new ArrayList<String>();
                    final BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(inputStream, "UTF-8"));
                    try {
                        String line = null;
                        while ((line = buffer.readLine()) != null) {
                            lines.add(line);
                        }

                        System.out.println(lines.size() + "件");

                        return new FileInfo(fileType, fileName, lines);
                    } finally {
                        buffer.close();
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
