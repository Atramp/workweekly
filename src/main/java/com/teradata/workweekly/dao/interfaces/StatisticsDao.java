package com.teradata.workweekly.dao.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/10/21.
 */
public interface StatisticsDao {
    List<Map> selectSimplyByStartEndDate(String startDate, String endDate);

    List<Map> selectListByStartEndDate(String startDate, String endDate);

    List<Map> selectStatisticsByStartEndDate(String startDate, String endDate);
}
