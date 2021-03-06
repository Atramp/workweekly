package com.teradata.workweekly.dao.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 15-3-26.
 */
public abstract class AbstractCommonDao {
    protected class Param extends HashMap {
        public Param() {
            super();
            put("dbName", "workweekly");
        }

        @Override
        public Param put(Object key, Object value) {
            super.put(key, value);
            return this;
        }
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    protected SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    protected SqlSession openSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }
}
