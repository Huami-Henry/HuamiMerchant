package com.huami.merchant.util;

import com.huami.merchant.bean.TaskCondition;
import com.huami.merchant.bean.TaskInfo;

import org.json.JSONArray;

/**
 * Created by henry on 2018/1/22.
 */

public class ConditionUtil {
    private static String json = "[\n" +
            "  {'level': 1, 'name': '白丁', 'need_exp': 1000, 'total_exp': 0},\n" +
            "  {'level': 2, 'name': '从九品', 'need_exp': 1000, 'total_exp': 1000},\n" +
            "  {'level': 3, 'name': '正九品', 'need_exp': 2000, 'total_exp': 2000},\n" +
            "  {'level': 4, 'name': '从八品', 'need_exp': 3000, 'total_exp': 4000},\n" +
            "  {'level': 5, 'name': '正八品', 'need_exp': 5000, 'total_exp': 7000},\n" +
            "  {'level': 6, 'name': '从七品', 'need_exp': 8000, 'total_exp': 12000},\n" +
            "  {'level': 7, 'name': '正七品', 'need_exp': 13000, 'total_exp': 20000},\n" +
            "  {'level': 8, 'name': '从六品', 'need_exp': 21000, 'total_exp': 33000},\n" +
            "  {'level': 9, 'name': '正六品', 'need_exp': 34000, 'total_exp': 54000},\n" +
            "  {'level': 10, 'name': '从五品', 'need_exp': 55000, 'total_exp': 88000},\n" +
            "  {'level': 11, 'name': '正五品', 'need_exp': 89000, 'total_exp': 143000},\n" +
            "  {'level': 12, 'name': '从四品', 'need_exp': 144000, 'total_exp': 232000},\n" +
            "  {'level': 13, 'name': '正四品', 'need_exp': 233000, 'total_exp': 376000},\n" +
            "  {'level': 14, 'name': '从三品', 'need_exp': 377000, 'total_exp': 609000},\n" +
            "  {'level': 15, 'name': '正三品', 'need_exp': 610000, 'total_exp': 986000},\n" +
            "  {'level': 16, 'name': '从二品', 'need_exp': 987000, 'total_exp': 1596000},\n" +
            "  {'level': 17, 'name': '正二品', 'need_exp': 1597000, 'total_exp': 2583000},\n" +
            "  {'level': 18, 'name': '从一品', 'need_exp': 2584000, 'total_exp': 4180000},\n" +
            "  {'level': 19, 'name': '正一品', 'need_exp': 4181000, 'total_exp': 6764000}\n" +
            "]";
    public static String getCondition(TaskCondition con){
        try {
            JSONArray array = new JSONArray(json);
            switch (con.getCondition_id()) {
                case 1:
                    return con.getParam_text();
                case 2:
                    return con.getParam1() + "及以上";
                case 3:
                    return con.getParam1() + "及以下";
                case 4:
                    return con.getParam1() + "-" + con.getParam2() + "岁";
                case 5:
                    for (int i = 0; i < array.length(); i++) {
                        if (con.getParam1() == array.getJSONObject(i).getInt("level")) {
                            return array.getJSONObject(i).getString("name") + "以上";
                        }
                    }
                case 6:
                    if (con.getParam1() == 1) {
                        return "男";
                    } else if (con.getParam1() == 2) {
                        return "女";
                    }
            }
        } catch (Exception e) {

        }
        return "";
    }
}
