package com.ty.qa.async.handler;

import com.ty.qa.async.EventHandler;
import com.ty.qa.async.EventModel;
import com.ty.qa.async.EventType;
import com.ty.qa.model.Message;
import com.ty.qa.model.User;
import com.ty.qa.service.MessageService;
import com.ty.qa.service.UserService;
import com.ty.qa.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * created by TY on 2019/5/20.
 */
@Component
public class LikeHandler implements EventHandler{
    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;
    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(WendaUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());
        message.setContent("用户" + user.getName()
                + "赞了你的评论,http://127.0.0.1:8080/question/" + model.getExt("questionId"));

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
