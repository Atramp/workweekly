package com.teradata.workweekly.service.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/10/21.
 */
public interface StatisticsService {
    boolean sendNotification(String users);
    List<Map> simplyStatisticsByStartEndDate(String startDate, String endDate);
    boolean checkExcelStatus(String startDate,String endDate);
    boolean generateExcel(String startDate,String endDate);
}
