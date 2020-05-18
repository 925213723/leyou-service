package com.leyou.service.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
//import com.hrsj.mcc.base.system.service.IFileOperateService;

public class TransferToHtmlThread extends Thread{

    private static final Logger logger = LoggerFactory.getLogger(TransferToHtmlThread.class);

    private String sourceFileRoot;
    private String htmlPathRoot;
    private List<Map<String, Object>> fileList;
    private HttpServletRequest req;
    private String tranFileType;
    private String fontPath;

    public TransferToHtmlThread(String sourceFileRoot, String htmlPathRoot, List<Map<String, Object>> fileList,
                                HttpServletRequest req, String tranFileType,String fontPath) {
        this.sourceFileRoot = sourceFileRoot;
        this.htmlPathRoot = htmlPathRoot + File.separator + "filedownload" + File.separator;
        this.fileList = fileList;
        this.req = req;
        this.tranFileType = tranFileType;
        this.fontPath = fontPath;
    }
    @Override
    public void run() {
        String sourceFilePath = "";
        String htmlPathPath = "";
        String filePath = "";
        File file = null;
        List<Map<String,String>> files = null;
        InputStream is = null;
        String fileName = null;
        String fileType = null;
        try {
            files = new ArrayList<Map<String,String>>();
            for(Map<String, Object> fileInfo: fileList) {
                filePath = String.valueOf(fileInfo.get("file_path")); // 存储在DB的文件路径
                fileName = String.valueOf(fileInfo.get("saved_name")); // 文件名称
                fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase(); // 获取文件后缀
                List<String> tranTypeList = Arrays.asList(this.tranFileType.split(","));
                if (!tranTypeList.contains(fileType)) { // 不属于xlsx,xls,docx,doc,pptx,ppt,pdf类型文件不转HTML
                    continue;
                }
                sourceFilePath = sourceFileRoot + File.separator  + filePath; // 原文件路径
                htmlPathPath = htmlPathRoot + filePath + ".html"; // 生成的HTML文件路径
                is = getClass().getResourceAsStream("/Aspose.Total.Java.lic"); // License认证文件，如果没有此认证，最多只能转3页
                //readContent(is);
                if (StringUtils.startsWith(fileType, "doc")) {
                    com.aspose.words.License wordLicense = new com.aspose.words.License();
                    wordLicense.setLicense(is);
                    Document doc = new Document(sourceFilePath);
                    doc.save(htmlPathPath, SaveFormat.HTML);
                }else if (StringUtils.startsWith(fileType, "xls")) {
                    com.aspose.cells.License excelLicense = new com.aspose.cells.License();
                    excelLicense.setLicense(is);
                    Workbook book = new Workbook(sourceFilePath);
                    book.save(htmlPathPath, com.aspose.cells.SaveFormat.HTML);
                }else if (StringUtils.startsWith(fileType, "ppt")) {
                    com.aspose.slides.License pptLicense = new com.aspose.slides.License();
                    pptLicense.setLicense(is);
                    Presentation doc = new Presentation(sourceFilePath);
                    doc.save(htmlPathPath, com.aspose.slides.SaveFormat.Html);
                }else if (StringUtils.startsWith(fileType, "pdf")) {
                    com.aspose.pdf.License pdfLicense = new com.aspose.pdf.License();
                    pdfLicense.setLicense(is);
                    com.aspose.pdf.Document.addLocalFontPath(fontPath);
                    com.aspose.pdf.Document pdfDoc = new com.aspose.pdf.Document(sourceFilePath);
                    pdfDoc.save(htmlPathPath, com.aspose.pdf.SaveFormat.Html);
                }
                htmlPathPath = htmlPathPath.replace(htmlPathRoot, ""); // 去掉配置文件指定路径
                Map<String,String> map = new HashMap<String, String>();
                map.put("id", String.valueOf(fileInfo.get("id")));
                map.put("tranPath", htmlPathPath);
                files.add(map);
            }
           /* if (CollectionUtils.isNotEmpty(files)) {
                IFileOperateService fileOperateService = (IFileOperateService) SpringUtils.getBean("fileOperateService");
                fileOperateService.updateTransFilePath(files);//保存转换后的静态资源文件路径到DB
            }*/
        }catch (Exception e) {
            logger.error("Transfer File to Html fail,The message is:", e);
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
