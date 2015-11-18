package com.teradata.workweekly.dao;

import com.teradata.workweekly.dao.common.AbstractCommonDao;
import com.teradata.workweekly.dao.interfaces.StatisticsDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/10/21.
 */
public class StatisticsDaoImpl extends AbstractCommonDao implements StatisticsDao {
    @Override
    public List<Map> selectSimplyByStartEndDate(String startDate, String endDate) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param().put("START_DATE", startDate).put("END_DATE", endDate);
            return sqlSession.selectList("Statistics.selectStatisticsSimplyByStartEndDate", param);
        }
    }

    @Override
    public List<Map> selectListByStartEndDate(String startDate, String endDate) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param().put("START_DATE", startDate).put("END_DATE", endDate);
            return sqlSession.selectList("Statistics.selectByStartDate", param);
        }
    }

    @Override
    public List<Map> selectStatisticsByStartEndDate(String startDate, String endDate) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param().put("START_DATE", startDate).put("END_DATE", endDate);
            return sqlSession.selectList("Statistics.selectStatisticsByStartEndDate", param);
        }
    }
}
