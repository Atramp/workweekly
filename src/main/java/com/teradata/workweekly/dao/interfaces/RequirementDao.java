package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.Requirement;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface RequirementDao {
    List<Requirement> selectAllRequirement();

    List<Requirement> selectRequirementListByCategory(String categoryID);

    Map selectRequirementRawByID(String id);

    boolean selectRequirement(Requirement requirement);

    boolean updateRequirement(Requirement requirement);

    boolean deleteRequirement(String id);

    List<Map> selectCategoryColors();

    List<Map> selectAllOptions();

    List<Map> selectOptionsByField(String type);

    boolean insertOption(String type, String name);

    boolean deleteOption(String id);

    boolean deleteOption(String type, String value);

}
