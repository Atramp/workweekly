package com.teradata.workweekly.dao;

import com.teradata.workweekly.bean.entity.Requirement;
import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.dao.common.AbstractCommonDao;
import com.teradata.workweekly.dao.interfaces.RequirementDao;
import com.teradata.workweekly.dao.interfaces.UserDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public class RequirementDaoImp extends AbstractCommonDao implements RequirementDao {


    @Override
    public List<Requirement> getAllRequirement() {
        try (SqlSession session = openSession()) {
            return session.selectList("Requirement.selectAll");
        }
    }

    @Override
    public List<Requirement> getRequirementListByCatagory(String catagoryID) {
        return null;
    }

    @Override
    public Requirement getRequirementByID(String id) {
        return null;
    }

    @Override
    public boolean addRequirement(Requirement requirement) {
        return false;
    }

    @Override
    public boolean updateRequirement(Requirement requirement) {
        return false;
    }

    @Override
    public boolean deleteRequirement(String id) {
        return false;
    }

    @Override
    public List<Map> getCategoryColors() {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.selectList("Requirement.selectAllCategories");
        }
    }
}
