package com.teradata.workweekly.dao;

import com.teradata.workweekly.bean.entity.Requirement;
import com.teradata.workweekly.dao.common.AbstractCommonDao;
import com.teradata.workweekly.dao.interfaces.RequirementDao;
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
    public List<Requirement> getRequirementListByCategory(String categoryID) {
        return null;
    }

    @Override
    public Map getRequirementRawByID(String id) {
        try (SqlSession session = openSession()) {
            return session.selectOne("Requirement.selectRequireRawById", new Param().put("ID", id));
        }
    }

    @Override
    public boolean addRequirement(Requirement requirement) {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.update("Requirement.addRequirement", requirement) == 1;
        }
    }

    @Override
    public boolean updateRequirement(Requirement requirement) {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.update("Requirement.updateRequirementByID", requirement) == 1;
        }
    }

    @Override
    public boolean deleteRequirement(String id) {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.delete("Requirement.deleteRequirementByID", new Param().put("ID", id)) == 1;
        }
    }

    @Override
    public List<Map> getCategoryColors() {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.selectList("Requirement.selectAllCategories");
        }
    }

    @Override
    public List<Map> getAllOptions() {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.selectList("Requirement.selectAllOptions");
        }
    }

    @Override
    public List<Map> getOptionsByField(String type) {
        try (SqlSession sqlSession = openSession()) {
            return sqlSession.selectList("Requirement.selectOptionsByField", new Param().put("TYPE", type));
        }
    }

    @Override
    public boolean addOption(String type, String name) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("TYPE", type).put("NAME", name);
            return sqlSession.insert("Requirement.insertOption", param) == 1;
        }
    }

    @Override
    public boolean deleteOption(String id) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("ID", id).put("STATUS", "1");
            return sqlSession.update("Requirement.updateOptionStatus", param) == 1;
        }
    }

    @Override
    public boolean deleteOption(String type, String value) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("TYPE", type).put("VALUE", value).put("STATUS", "1");
            return sqlSession.update("Requirement.updateOptionStatusByTypeValue", param) == 1;
        }
    }
}
