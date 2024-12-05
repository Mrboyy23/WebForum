package com.example.project.service.IMPL;

import com.example.project.model.entity.CommentlikesEntity;
import com.example.project.responsitory.CommentLikeResponsitory;
import com.example.project.service.CommentLikeSevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentLikeImpl implements CommentLikeSevice {
    private CommentLikeResponsitory commentLikeResponsitory;
    @Override
    public Boolean hasUserLikedComment(Long commentId, Long userId) {
        if( commentLikeResponsitory.countLikesByCommentIdAndUserId(commentId, userId) > 0)
            return true;
        return false;
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        CommentlikesEntity commentlikesEntity = new CommentlikesEntity();
        commentlikesEntity.setCommentLikeId(commentLikeResponsitory.maxId()+1);
        commentlikesEntity.setCommentId(commentId);
        commentlikesEntity.setUserId(userId);
        commentlikesEntity.setCreatedAt(LocalDateTime.now());
        commentLikeResponsitory.save(commentlikesEntity);



    }

    @Override
    public void unlikeComment(Long commentId, Long userId) {
        commentLikeResponsitory.deleteLike(commentId,userId);
    }

    @Override
    public Long idMax() {
        return commentLikeResponsitory.maxId();
    }
}
