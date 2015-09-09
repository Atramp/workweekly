package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.Requirement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface RequirementService {
    public List<Requirement> getAllRequirement();

    public List<Map> getAllSimple();

    public List<Map> getAllSimpleByUser(String username);

    public Requirement getRequirement(String id);

    public List<Requirement> getRequirementsByCategory(String categoryID);

    public boolean addRequirement(Requirement requirement);

    public boolean addRequirements(List<Requirement> requirements);

    public boolean updateRequirement(Requirement requirement);

    public boolean deleteRequirement(String id);

    public List<Map> getAllCategories();

}
