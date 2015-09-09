package com.teradata.workweekly.service;

import com.teradata.workweekly.bean.entity.Requirement;
import com.teradata.workweekly.dao.interfaces.RequirementDao;
import com.teradata.workweekly.service.interfaces.RequirementService;
import org.apache.commons.collections4.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by alex on 15/8/20.
 */
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    private RequirementDao requirementDao;

    @Override
    public List<Requirement> getAllRequirement() {
        return requirementDao.getAllRequirement();
    }

    @Override
    public List<Map> getAllSimple() {
        List<Requirement> requirementList = requirementDao.getAllRequirement();
        MultiValueMap<String, String> categoryMap = MultiValueMap.multiValueMap(new LinkedHashMap());
        for (Requirement requirement : requirementList) {
            Map _requirement = new HashMap();
            _requirement.put("id",requirement.getId());
            _requirement.put("name",requirement.getName());
            categoryMap.put(requirement.getCategory(), _requirement);
        }
        List<Map> result = new ArrayList<Map>();
        for (String category : categoryMap.keySet()) {
            Collection requires = categoryMap.getCollection(category);
            if (requires != null) {
                Map map = new HashMap();
                map.put("category", category);
                map.put("requires", requires);
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<Map> getAllSimpleByUser(String username) {
        return getAllSimple();
    }

    @Override
    public Requirement getRequirement(String id) {
        return null;
    }

    @Override
    public List<Requirement> getRequirementsByCategory(String categoryID) {
        return null;
    }

    @Override
    public boolean addRequirement(Requirement requirement) {
        return false;
    }

    @Override
    public boolean addRequirements(List<Requirement> requirements) {
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
    public List<Map> getAllCategories() {
        return requirementDao.getCategoryColors();
    }
}
