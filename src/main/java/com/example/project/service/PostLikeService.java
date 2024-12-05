package com.example.project.service;

public interface PostLikeService {
    public Boolean hasUserLikedPost(Long postId, Long userId);
    public void likePost(Long postId, Long userId);
    public void unlikePost(Long postId, Long userId);
    Long idMax();
}
