package com.teradata.workweekly.service;

import com.teradata.workweekly.common.config.Configuration;
import com.teradata.workweekly.dao.interfaces.StatisticsDao;
import com.teradata.workweekly.service.interfaces.StatisticsService;
import com.teradata.workweekly.service.interfaces.UserWorkService;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/10/21.
 */
public class StatisticsServiceImpl implements StatisticsService {
    private final String prefix = Configuration.get("EXCEL_PATH");
    @Autowired
    private StatisticsDao statisticsDao;

    @Autowired
    private UserWorkService userWorkService;

    @Override
    public boolean sendNotification(String users) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(Configuration.get("PUSH_SERVICE_URL"));
            String[] userArray = users.split(",");
            for (String user : userArray) {
                List<NameValuePair> params = new ArrayList();
                params.add(new BasicNameValuePair("title", Configuration.get("WARN_TITLE")));
                params.add(new BasicNameValuePair("description", Configuration.get("WARN_CONTENT")));
                params.add(new BasicNameValuePair("appid", Configuration.get("APP_ID")));
                params.add(new BasicNameValuePair("target", user));
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
                request.setEntity(uefEntity);
                client.execute(request);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Map> simplyStatisticsByStartEndDate(String startDate, String endDate) {
        return statisticsDao.selectSimplyByStartEndDate(startDate, endDate);
    }

    @Override
    public boolean checkExcelStatus(String startDate, String endDate) {
        String fileName = generateFileName(startDate, endDate);
        File file = new File(prefix.concat(fileName));
        System.out.println(file.canRead());
        return file != null && file.exists();
    }

    @Override
    public boolean generateExcel(String startDate, String endDate) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("个人周报");
        Sheet sheet2 = workbook.createSheet("汇总列表");

        Font fontBold = workbook.createFont();
        fontBold.setFontName("微软雅黑");
        fontBold.setFontHeightInPoints((short) 11);
        fontBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(fontBold);
        headerStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        // 生成个人周报
        appendCell(sheet1.createRow(0), headerStyle, "日期", "需求名称", "需求描述", "实际消耗人时", "参与人");
        List<Map> sheet1Data = statisticsDao.selectListByStartEndDate(startDate, endDate);
        appendRow(sheet1, sheet1Data, new String[]{"WEEKDAY", "REQUIREMENT", "DESCRIPTION", "HOURS", "WORKER"}, true);

        // 生成汇总统计
        appendCell(sheet2.createRow(0), null
                , "需求大类", "需求类型", "需求名称", "需求来源", "需求部门"
                , "需求提出人", "需求提出时间", "希望完成时间", "业支接口人", "需求启动时间"
                , "需求完成时间", "需求负责人", "结果反馈渠道", "需求内容", "工时", "参与人");
        List<Map> sheet2Data = statisticsDao.selectStatisticsByStartEndDate(startDate, endDate);
        appendRow(sheet2, sheet2Data, new String[]{"CATEGORY", "TYPE", "NAME", "SOURCE", "DEPARTMENT", "SPONSOR", "START_DATE", "EXCEPTED_FINISH_DATE",
                "YZ_AGENT", "START_DATE", "FINISH_DATE", "HANDLER", "FEEDBACK", "DESCRIPTION", "HOURS", "WORKERS"}, false);
        try (OutputStream os = new FileOutputStream(new File(Configuration.get("EXCEL_PATH").concat(generateFileName(startDate, endDate))))) {
            workbook.write(os);

            // 生成过周报的记录不允许修改
            userWorkService.disableEdit(startDate, endDate);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void appendCell(Row row, CellStyle style, String... value) {
        if (value == null)
            return;
        int currentCellNum = row.getLastCellNum();
        for (String str : value) {
            Cell cell = row.createCell(++currentCellNum);
            cell.setCellValue(str);
            if (style != null)
                cell.setCellStyle(style);
        }
    }

    private void appendRow(Sheet sheet, List<Map> datas, String[] fields, boolean autoSizeColumn) {
        if (datas == null || datas.isEmpty())
            return;
        int currentRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= datas.size(); i++) {
            Map data = datas.get(i - 1);
            Row row = sheet.createRow(currentRowNum + i);
            for (int j = 0; j < fields.length; j++)
                row.createCell(j).setCellValue(MapUtils.getString(data, fields[j], ""));
        }
        if (autoSizeColumn)
            for (int j = 0; j < fields.length; j++)
                sheet.autoSizeColumn(j);
    }

    private static String generateFileName(String startDate, String endDate) {
        return String.format(Configuration.get("EXCEL_NAME_FORMAT"), startDate, endDate);
    }
}
