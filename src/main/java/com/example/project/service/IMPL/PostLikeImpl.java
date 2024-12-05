package com.example.project.service.IMPL;

import com.example.project.model.entity.PostlikesEntity;
import com.example.project.responsitory.PostlikesResponsitory;
import com.example.project.service.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostLikeImpl implements PostLikeService {
    private PostlikesResponsitory postlikesResponsitory;
    @Override
    public Boolean hasUserLikedPost(Long postId, Long userId) {
        if( postlikesResponsitory.countLikesByPostIdAndUserId(postId, userId) > 0)
            return true;
        return false;
    }

    @Override
    public void likePost(Long postId, Long userId) {

            PostlikesEntity postlikesEntity = new PostlikesEntity();
            postlikesEntity.setPostLikeId(postlikesResponsitory.maxId()+1);
            postlikesEntity.setPostId(postId);
            postlikesEntity.setUserId(userId);
            postlikesEntity.setCreatedAt(LocalDateTime.now());
            postlikesResponsitory.save(postlikesEntity);

    }




    @Override
    public void unlikePost(Long postId, Long userId) {

            postlikesResponsitory.deleteLike(postId, userId);

    }

    @Override
    public Long idMax() {
        return postlikesResponsitory.maxId();
    }
}
