package com.ty.qa.model;

import java.util.HashMap;
import java.util.Map;

/**
 * created by TY on 2019/5/4.
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<>();

    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
