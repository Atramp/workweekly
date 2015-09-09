package com.teradata.workweekly.service;

import com.teradata.workweekly.bean.entity.UserWork;
import com.teradata.workweekly.dao.interfaces.UserWorkDao;
import com.teradata.workweekly.service.interfaces.UserWorkService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alex on 15/8/13.
 */
public class UserWorkServiceImpl implements UserWorkService {
    @Autowired
    private UserWorkDao userWorkDao;

    public UserWork getUserWork(String id) {
        return null;
    }

    public List<Map> getUserWorks(String userID, String month) {
        try {
            Calendar calendar = Calendar.getInstance();
            // 获取第一天
            calendar.setTime(new SimpleDateFormat("yyyyMM").parse(month));
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = calendar.getTime();
            // 获取最后一天
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date endDate = calendar.getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            List<Map> userWorkList = userWorkDao.getUserWorkList(userID, dateFormat.format(startDate), dateFormat.format(endDate));
            if (userWorkList != null) {
                MultiValueMap userWorkByDay = MultiValueMap.multiValueMap(new LinkedHashMap<String, Collection<Map>>());
                for (Map userWork : userWorkList) {
                    userWorkByDay.put(userWork.get("DATE"), MapUtils.getString(userWork, "COLOR"));
                }
                List<Map> result = new ArrayList<>();
                Set<String> keySet = userWorkByDay.keySet();
                for (String date : keySet) {
                    Map map = new HashMap();
                    map.put("date", date);
                    Collection colors = userWorkByDay.getCollection(date);
                    if (colors.size() == 1)
                        map.put("colors", colors.toArray(new String[1])[0]);
                    else {
                        StringBuilder stringBuilder = new StringBuilder();
                        Iterator iterator = colors.iterator();
                        while (iterator.hasNext())
                            stringBuilder.append(iterator.next()).append(",");
                        map.put("colors", stringBuilder.substring(0, stringBuilder.length() - 1));
                    }
                    result.add(map);
                }
                return result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getUserWorks(String userID, String startDate, String endDate) {
        List<Map> userWorkList = userWorkDao.getUserWorkList(userID, startDate, endDate);
        if (userWorkList != null) {
            MultiValueMap<String, Map> userWorkByDay = MultiValueMap.multiValueMap(new LinkedHashMap<String, Collection<Map>>());
            for (Map userWork : userWorkList) {
                Map map = new HashMap();
                map.put("id", MapUtils.getString(userWork, "ID"));
                map.put("category", MapUtils.getString(userWork, "CATEGORY"));
                map.put("require", MapUtils.getString(userWork, "NAME"));
                map.put("desc", MapUtils.getString(userWork, "DESCRIPTION"));
                map.put("color", MapUtils.getString(userWork, "COLOR"));
                map.put("hours", MapUtils.getString(userWork, "HOURS"));
                map.put("date", MapUtils.getString(userWork, "DATE"));
                userWorkByDay.put(MapUtils.getString(userWork, "DATE"), map);
            }

            List<Map> result = new ArrayList<>();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Calendar startDateCalendar = Calendar.getInstance();
                startDateCalendar.setTime(dateFormat.parse(startDate));
                Calendar endDateCalendar = Calendar.getInstance();
                endDateCalendar.setTime(dateFormat.parse(endDate));
                while (!endDateCalendar.before(startDateCalendar)) {
                    String date = dateFormat.format(startDateCalendar.getTime());
                    Collection useWorks = userWorkByDay.getCollection(date);
                    if (useWorks == null)
                        useWorks = new ArrayList(0);
                    Map map = new HashMap();
                    map.put("date", date);
                    map.put("count", useWorks.size());
                    map.put("items", useWorks);
                    result.add(map);
                    startDateCalendar.add(Calendar.DATE, 1);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return result;
        }
        return null;
    }

    public boolean addUserWork(UserWork userWork) {
        return userWorkDao.addUserWork(userWork);
    }

    public boolean addUserWorks(List<UserWork> userWorkList) {
        return userWorkDao.addUserWorks(userWorkList);
    }

    public boolean updateUserWork(UserWork userWork) {
        return userWorkDao.updateUserWork(userWork);
    }

    public boolean deleteUserWork(String id) {
        return userWorkDao.deleteUserWork(id);
    }

    public static void main(String[] args) {
        int i = 0;
        do {
            i++;
            System.out.println(i);
        } while (i < 5);
    }

}
