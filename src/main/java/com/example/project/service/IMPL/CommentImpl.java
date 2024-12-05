package com.example.project.service.IMPL;

import com.example.project.model.DTO.MapComment;
import com.example.project.model.entity.CommentsEntity;
import com.example.project.model.entity.UsersEntity;
import com.example.project.responsitory.CommentResponsitory;
import com.example.project.service.CommentService;
import com.example.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentImpl implements CommentService {
    private CommentResponsitory commentResponsitory;
    private UserService userService;

    @Override
    public List<MapComment> getAll(Long id) {
        List<CommentsEntity> commentsEntities = commentResponsitory.findAll();
        List<MapComment> mapComments = new ArrayList<>();

        for (CommentsEntity c : commentsEntities) {
            if(c.getPostId() == id){
            MapComment mapComment = new MapComment();
            mapComment.setCommentId(c.getCommentId());
            mapComment.setContent(c.getContent());
            mapComment.setCreatedAt(c.getCreatedAt());
            mapComment.setUserId(c.getUserId());
            mapComment.setPostId(c.getPostId());

            // Lấy tên người dùng
            Optional<UsersEntity> user = userService.findById(c.getUserId());
            user.ifPresent(u -> mapComment.setName(u.getName()));

            // Lọc các bình luận trả lời
            if (c.getParentId() == null || c.getParentId()==0) {
                // Lọc trả lời cho comment này
                List<MapComment> replies = new ArrayList<>();
                for (CommentsEntity reply : commentsEntities) {
                    if (reply.getParentId() != null&&reply.getParentId() != 0 && reply.getParentId().equals(c.getCommentId())) {
                        MapComment replyComment = new MapComment();
                        replyComment.setCommentId(reply.getCommentId());
                        replyComment.setContent(reply.getContent());
                        replyComment.setCreatedAt(reply.getCreatedAt());
                        replyComment.setUserId(reply.getUserId());

                        // Thêm tên người dùng trả lời
                        Optional<UsersEntity> replyUser = userService.findById(reply.getUserId());
                        replyUser.ifPresent(u -> replyComment.setName(u.getName()));

                        replies.add(replyComment);
                    }
                }
                mapComment.setMapComments(replies); // Gắn reply cho comment gốc
            }

            mapComments.add(mapComment);
        }}
        return mapComments;
    }

    @Override
    public List<CommentsEntity> getAllComment() {
        return commentResponsitory.findAll();
    }

    @Override
    public Long countCommentByPost(Long id) {
        return commentResponsitory.countCommentsByPostId(id);
    }

    @Override
    public void saveComment(Long id,String content, Long postId, Long userId, Long parentId) {
        CommentsEntity commentEntity = new CommentsEntity();
        commentEntity.setCommentId(id);
        commentEntity.setContent(content);
        commentEntity.setPostId(postId);
        commentEntity.setUserId(userId);
        commentEntity.setParentId(parentId);
        commentEntity.setCreatedAt(LocalDateTime.now());


        commentResponsitory.save(commentEntity);
    }

    @Override
    public Long maxId() {
        return commentResponsitory.findMaxCommentIdByPostId();
    }


}
