package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.Requirement;

import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
public interface RequirementService {
    public List<Requirement> getAllRequirement();

    public Requirement getRequirement(String id);

    public List<Requirement> getRequirementsByCatagory(String catagoryID);

    public boolean addRequirement(Requirement requirement);

    public boolean addRequirements(List<Requirement> requirements);

    public boolean updateRequirement(Requirement requirement);

    public boolean deleteRequirement(String id);
}
