package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.Requirement;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface RequirementDao {
    List<Requirement> getAllRequirement();

    List<Requirement> getRequirementListByCatagory(String catagoryID);

    Requirement getRequirementByID(String id);

    boolean addRequirement(Requirement requirement);

    boolean updateRequirement(Requirement requirement);

    boolean deleteRequirement(String id);

    List<Map> getCategoryColors();
}
