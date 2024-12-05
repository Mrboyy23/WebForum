package com.example.project.service;

public interface CommentLikeSevice {
    public Boolean hasUserLikedComment(Long commentId, Long userId);
    public void likeComment(Long commentId, Long userId);
    public void unlikeComment(Long commentId, Long userId);
    Long idMax();
}
