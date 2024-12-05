package com.example.project.service.IMPL;

import com.example.project.model.DTO.SavePort;
import com.example.project.model.entity.PostsEntity;
import com.example.project.model.entity.PosttagsEntity;
import com.example.project.model.entity.TagsEntity;
import com.example.project.responsitory.PostResponsitory;
import com.example.project.responsitory.PosttagsResponsitory;
import com.example.project.responsitory.TagResponsitory;
import com.example.project.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostImpl implements PostService {
    private PostResponsitory postResponsitory;
    private TagResponsitory tagResponsitory;
    private PosttagsResponsitory posttagsResponsitory;
    @Override
    public PostsEntity getPostById(long id) {
       return postResponsitory.getPostById(id);
    }

    @Override
    public List<PostsEntity> getPostByCategory(Long categoryId) {
        return postResponsitory.getPostByCategory(categoryId);
    }

    @Override
    public void create(SavePort port) {
        // Bước 1: Tạo bài viết mới
        PostsEntity newPost = new PostsEntity();
        newPost.setPostId(port.getPostId());
        newPost.setContent(port.getContent());
        newPost.setTitle(port.getTitle());
        newPost.setImageUrl(port.getImageUrl());
        newPost.setCreatedAt(port.getCreatedAt());
        newPost.setUpdatedAt(port.getUpdatedAt());
        newPost.setRepostCount(port.getRepostCount());
        newPost.setUserId(port.getUserId());
        newPost.setCategoryId(port.getCategoryId());

        // Bước 2: Lưu bài viết vào cơ sở dữ liệu
        postResponsitory.save(newPost);

        // Bước 3: Tạo các PosttagsEntity và liên kết với bài viết
        List<PosttagsEntity> postTags = new ArrayList<>();

        // Tách chuỗi tags từ SavePort thành mảng tagName
        String[] tagNames = port.getTags().split("\\s*,\\s*"); // Tách chuỗi theo dấu phẩy
        int i = 1;
        for (String tagName : tagNames) {
            tagName = tagName.trim();  // Loại bỏ khoảng trắng thừa
            System.out.println("Tag: '" + tagName + "'");

            // Kiểm tra nếu tag đã tồn tại trong cơ sở dữ liệu, nếu chưa thì thêm mới
            TagsEntity existingTag = tagResponsitory.getByName(tagName);

            if (existingTag == null) {
                // Nếu tag chưa tồn tại, tạo mới tag
                existingTag = new TagsEntity();
                existingTag.setTagId(tagResponsitory.maxId() + i);
                existingTag.setName(tagName);
                tagResponsitory.save(existingTag);  // Lưu tag mới vào cơ sở dữ liệu
            }

            // Tạo PosttagsEntity để liên kết bài viết với tag (đã có hoặc mới tạo)
            PosttagsEntity postTag = new PosttagsEntity();
            postTag.setPostTagId(posttagsResponsitory.maxId() + i);
            postTag.setPostId(newPost.getPostId());
            postTag.setTagId(existingTag.getTagId());
            postTags.add(postTag);
            i++;
        }

        // Bước 4: Lưu tất cả các liên kết giữa bài viết và tag
        posttagsResponsitory.saveAll(postTags);
    }




    @Override
    public Boolean update(PostsEntity postsEntity) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Long increaseViewCount(Long postId) {
        PostsEntity postsEntity= postResponsitory.getPostById(postId);
        postsEntity.setRepostCount(postsEntity.getRepostCount()!=null? postsEntity.getRepostCount()+ 1: 1);
        postResponsitory.save(postsEntity);
        return postsEntity.getRepostCount();
    }

    @Override
    public Page<PostsEntity> getAll(Pageable pageable) {
        return postResponsitory.findAllSortedByCreatedAtDesc(pageable);
    }


    @Override
    public List<PostsEntity> recentPost() {
        Pageable pageable = PageRequest.of(0, 3); // Lấy 3 bài viết gần đây nhất
        List<PostsEntity> recentPosts = postResponsitory.recentPost(pageable);
        return recentPosts;
    }

    @Override
    public Long idMax() {
        return postResponsitory.maxId();
    }


}
