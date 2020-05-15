package com.leyou.service.utils;

import com.leyou.service.pojo.DataScore;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DownExcel {
    //导出数据
    @SuppressWarnings("deprecation")
    public static void exportRecordTask(HttpServletResponse response, List<String> heardlist,List<Map<String,String>> dataScore) throws Exception {
        Map<String, Object> mp = new ConcurrentHashMap<>(10000000);

        //这里默认是从前台传过来的，自定义列
		@SuppressWarnings("unchecked")
        List<String> headers = heardlist;
        //List<String> headers = (List<String>) param.get("headers");
		if(CollectionUtils.isEmpty(heardlist)){
			throw new Exception("空");
		}

        @SuppressWarnings("resource")
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("导出数据");
        sheet.setDefaultColumnWidth(30);//默认列宽
        sheet.setColumnWidth(1, 30*256);//设置第几个列宽
        sheet.setColumnWidth(5, 30*256);
        sheet.setColumnWidth(8, 50*256);
        sheet.setColumnWidth(9, 50*256);
        sheet.setColumnWidth(14, 100*256);
        //sheet.protectSheet("123456");//设置密码
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setFillForegroundColor((short)43);
        style.setFillForegroundColor(HSSFColor.YELLOW.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setLocked(true);

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setColor(HSSFColor.RED.index);
        font.setFontName("宋体");
        font.setBold(true);

        HSSFFont font2 = workbook.createFont();
        font2.setFontHeightInPoints((short) 12);
        font2.setFontName("宋体");

        style.setFont(font);

        List<Map<String,String>> list = dataScore;//这里是查出来的动态数据，需要跟你的列名对应
        //获取数据长度，获取列长度
        int listSize = list.size();
        int headSize = headers.size();

        //sheet.createFreezePane(headSize, 1);//设置冰冻列
        sheet.createFreezePane( 1,1,1,1);//设置冰冻列
        //导出excel名称，这里可以用UUID
        String fileName = "excel3.xls";

        //String[] headers = { "列1", "列2", "列3"};

        HSSFRow row = sheet.createRow(0);//设置第一行
        row.setHeightInPoints(40);//默认行高


        //在表中存放查询到的数据放入对应的列
        for (int i = 0;i < listSize; i++) {
            HSSFRow row1 = sheet.createRow(i+1);//这里之所以 i+1是避免加入数据将第一行表头给替换
            row1.setHeightInPoints(50);
            for(int j = 0;j < headSize;j++) {
                if(i==0){
                    HSSFCell cell = row.createCell(j);//遍历列
                    HSSFRichTextString text = new HSSFRichTextString(headers.get(j));//获取列名
                    cell.setCellValue(text);//加内容到列中
                    cell.setCellStyle(style);//加上面定义的样式
                    char letter = (char)(0+65);
                    char letterTo = (char)(65+headSize-1);
                    CellRangeAddress c = CellRangeAddress.valueOf(letter+"1"+":"+letterTo+"1");//使B1列添加筛选功能
                    sheet.setAutoFilter(c);//功能填入sheet中
                }
               // row1.createCell(j).getCellStyle().setWrapText(true);//设置自动换行
                row1.createCell(j).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);//设置居中
                row1.createCell(j).getCellStyle().setFont(font2);//设置样式
                Map<String, String> map = list.get(i);
                for(String key : map.keySet()){
                    String value = map.get(key);
                    if(key.equals(headers.get(j))){
                        row1.createCell(j).setCellValue(value);
                        break;
                    }
                }
            }
            //row1.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        }

        /*for (int i = 0;i < listSize; i++) {
            HSSFRow row1 = sheet.createRow(i+1);//这里之所以 i+1是避免加入数据将第一行表头给替换
            row1.setHeightInPoints(50);
            for(int j = 0;j < headSize;j++) {
                HSSFCell cell = row.createCell(j);//遍历列
                HSSFRichTextString text = new HSSFRichTextString(headers.get(j));//获取列名
                cell.setCellValue(text);//加内容到列中
                cell.setCellStyle(style);//加上面定义的样式
                row1.createCell(j).getCellStyle().setWrapText(true);//设置自动换行
                row1.createCell(j).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);//设置居中
                row1.createCell(j).getCellStyle().setFont(font2);//设置样式
                if("语文".equals(headers.get(j))) {
                    row1.createCell(j).setCellValue(list.get(i).getScorel());
                }else if("数学".equals(headers.get(j))) {
                    row1.createCell(j).setCellValue(list.get(i).getScorel());
                }else if("英语".equals(headers.get(j))) {
                    row1.createCell(j).setCellValue(list.get(i).getScorel());
                }
            }
            //row1.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        }*/


        //response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setContentType("application/octet-stream");//设置打印
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());//输出
            response.getOutputStream().close();
            mp.put("msg", 200);
        } catch (IOException e) {
            //mp.put("msg", 500);
        }
        //return mp;//最后返回map ，方法可以直接 无返回类型
    }
}
