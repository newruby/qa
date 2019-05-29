package com.ty.qa.async;

import com.alibaba.fastjson.JSONObject;
import com.ty.qa.util.JedisAdapter;
import com.ty.qa.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by TY on 2019/5/19.
 */
@Service
public class EventProducer {

    @Autowired
    JedisAdapter jedisAdapter;
    public boolean fireEvent(EventModel eventModel) {
        try {
            String json = JSONObject.toJSONString(eventModel);
            String key = RedisKeyUtil.getEventQueueKey();

            jedisAdapter.lpush(key, json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
