package com.ty.qa.service;

import com.ty.qa.dao.CommentDAO;

import com.ty.qa.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


/**
 * created by TY on 2019/5/19.
 */
@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;


    @Autowired
    SensitiveService sensitiveService;
    public int addComment(Comment comment) {
        //html 敏感词
        comment.setContent((HtmlUtils.htmlEscape(comment.getContent())));
        comment.setContent(sensitiveService.filter(comment.getContent()));

        return commentDAO.addComment(comment) > 0 ? comment.getId() : 0;
    }

    public Comment getCommentById(int id) {
        return commentDAO.getCommentById(id);
    }

    public List<Comment> getCommentsByEntity(int entityId, int entityType) {
        return commentDAO.selectCommentByEntity(entityId, entityType);
    }

    public int getCommentCount(int entityId, int entityType) {
        return commentDAO.getCommentCount(entityId, entityType);
    }

    /**
     * commentDAO updateStatus
     * @param
     * @return boolean
     */
    public boolean deleteComment(int commentId) {
        return commentDAO.updateStatus(commentId, 1) > 0;
    }

    public int getUserCommentCount(int userId) {
        return commentDAO.getUserCommentCount(userId);
    }
}
