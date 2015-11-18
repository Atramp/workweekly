package com.teradata.workweekly.service;

import com.teradata.workweekly.bean.entity.Requirement;
import com.teradata.workweekly.dao.interfaces.RequirementDao;
import com.teradata.workweekly.service.interfaces.RequirementService;
import com.teradata.workweekly.service.interfaces.UserService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by alex on 15/8/20.
 */
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    private RequirementDao requirementDao;

    @Autowired
    private UserService userService;

    @Override
    public List<Requirement> getAllRequirement() {
        return requirementDao.selectAllRequirement();
    }

    @Override
    public List<Map> getAllSimple() {
        List<Requirement> requirementList = requirementDao.selectAllRequirement();
        MultiValueMap<String, String> categoryMap = MultiValueMap.multiValueMap(new LinkedHashMap());
        for (Requirement requirement : requirementList) {
            Map _requirement = new HashMap();
            _requirement.put("id", requirement.getId());
            _requirement.put("name", requirement.getName());
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
    public Map getRequirementRaw(String id) {
        return requirementDao.selectRequirementRawByID(id);
    }

    @Override
    public List<Requirement> getRequirementsByCategory(String categoryID) {
        return null;
    }

    @Override
    public boolean addRequirement(Requirement requirement) {
        return requirementDao.selectRequirement(requirement);
    }

    @Override
    public boolean addRequirements(List<Requirement> requirements) {
        return false;
    }

    @Override
    public boolean updateRequirement(Requirement requirement) {
        return requirementDao.updateRequirement(requirement);
    }

    @Override
    public boolean deleteRequirement(String id) {
        return requirementDao.deleteRequirement(id);
    }

    @Override
    public List<Map> getAllCategories() {
        return requirementDao.selectCategoryColors();
    }

    @Override
    public Map<String, List> getAllOptions() {
        List<Map> optionList = requirementDao.selectAllOptions();
        if (optionList != null && !optionList.isEmpty()) {
            MultiValueMap map = MultiValueMap.multiValueMap(new HashMap());
            for (Map temp : optionList) {
                map.put(MapUtils.getString(temp, "type"), temp);
            }
            return map;
        }
        return null;
    }

    @Override
    public List<Map> getOptionsByField(String id) {
        if("user".equals(id)){
            return null;
        }
        return requirementDao.selectOptionsByField(id);
    }

    @Override
    public synchronized String addOption(String type, String name) {
        if (requirementDao.insertOption(type, name)) {
            List<Map> options = requirementDao.selectOptionsByField(type);
            return MapUtils.getString(options.get(options.size() - 1), "value");
        }
        return "-1";
    }

    @Override
    public boolean deleteOption(String id) {
        return requirementDao.deleteOption(id);
    }

    @Override
    public boolean deleteOption(String type, String value) {
        return requirementDao.deleteOption(type, value);
    }


}
