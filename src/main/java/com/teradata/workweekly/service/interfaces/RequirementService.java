package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.Requirement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface RequirementService {
    List<Requirement> getAllRequirement();

    List<Map> getAllSimple();

    List<Map> getAllSimpleByUser(String username);

    Map getRequirementRaw(String id);

    List<Requirement> getRequirementsByCategory(String categoryID);

    boolean addRequirement(Requirement requirement);

    boolean addRequirements(List<Requirement> requirements);

    boolean updateRequirement(Requirement requirement);

    boolean deleteRequirement(String id);

    List<Map> getAllCategories();

    Map<String, List> getAllOptions();

    List<Map> getOptionsByField(String id);

    /**
     * 返回新选项的value
     *
     * @param type
     * @param name
     * @return
     */
    String addOption(String type, String name);

    boolean deleteOption(String id);

    boolean deleteOption(String type, String value);
}
