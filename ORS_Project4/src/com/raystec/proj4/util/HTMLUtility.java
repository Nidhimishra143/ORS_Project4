package com.raystec.proj4.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.raystec.proj4.bean.DropdownListBean;

public class HTMLUtility {

	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;
		String select = "Select";
		sb.append("<option selected value=' '>" + select + "</option>");
		for (String key : keys) {
			val = map.get(key);

			if (key.trim().equals(selectedVal)) {

				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {

				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	public static String getList(String name, String selectedVal, List list) {
		Collections.sort(list);
		List<DropdownListBean> dd = (List<DropdownListBean>) list;
		StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "'>");

		String key = null;
		String val = null;
		String select = "Select";
		sb.append("<option selected value='0'>" + select + "</option>");
		for (DropdownListBean obj : dd) {
			key = obj.getkey();
			val = obj.getvalue();

			if (key.trim().equals(selectedVal)) {

				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {

				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

}
