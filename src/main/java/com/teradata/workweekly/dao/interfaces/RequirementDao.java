package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.Requirement;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface RequirementDao {
    List<Requirement> getAllRequirement();

    List<Requirement> getRequirementListByCategory(String categoryID);

    Map getRequirementRawByID(String id);

    boolean addRequirement(Requirement requirement);

    boolean updateRequirement(Requirement requirement);

    boolean deleteRequirement(String id);

    List<Map> getCategoryColors();

    List<Map> getAllOptions();

    List<Map> getOptionsByField(String type);

    boolean addOption(String type, String name);

    boolean deleteOption(String id);

    boolean deleteOption(String type, String value);

}
