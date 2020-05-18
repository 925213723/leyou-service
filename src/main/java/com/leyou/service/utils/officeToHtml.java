package com.leyou.service.utils;


import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class officeToHtml {

   public void transform(InputStream in, OutputStream out, String fileName) throws IOException {
        UUID uuid = UUID.randomUUID();
        String cacheFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "-" + uuid;// 设置本地缓存的路径
        String tmpPath = cacheFileName + ".pdf";
        String lowerFileName = fileName.toLowerCase();
        try {
            if (!new File(tmpPath).exists()) {
                if (lowerFileName.endsWith(".xls") || lowerFileName.endsWith(".xlsx")
                        || lowerFileName.endsWith(".csv")) {
                    com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(in);
                    workbook.save(tmpPath, com.aspose.cells.SaveFormat.PDF);
                } else if (lowerFileName.endsWith(".doc") || lowerFileName.endsWith(".docx")
                        || lowerFileName.endsWith(".rtf")) {
                    com.aspose.words.Document doc = new com.aspose.words.Document(in);
                    doc.save(tmpPath, com.aspose.words.SaveFormat.PDF);
                } else if (lowerFileName.endsWith(".ppt") || lowerFileName.endsWith(".pptx")
                        || lowerFileName.endsWith(".pps") || lowerFileName.endsWith(".ppsx")) {
                    com.aspose.slides.Presentation ppt = new com.aspose.slides.Presentation(in);
                    ppt.save(tmpPath, com.aspose.slides.SaveFormat.Pdf);
                } else if (lowerFileName.endsWith(".pdf")) {
                    com.aspose.pdf.Document pdf = new com.aspose.pdf.Document(in);
                    pdf.save(tmpPath, com.aspose.pdf.SaveFormat.Pdf);
                }
            }
           /* if (out instanceof ResponseOutputStream) {
                HttpServletResponse response = ((ResponseOutputStream) out).getResponse();
                response.sendRedirect("/filePreview/officeHtmlFileViwer/" + this.cacheFileName + ".pdf");
            } */
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
