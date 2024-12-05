package com.example.project.controller.admin;

import com.example.project.model.entity.PostsEntity;
import com.example.project.model.entity.RepostsEntity;
import com.example.project.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private TagService tagService;
    private CategoryService categoryService;
    private CommentService commentService;
    private RepostService repostService;
    @GetMapping("/index")
    public String index(Model model){


        return "admin/index";
    }

    @GetMapping("/user")
    public String user(Model model){

        model.addAttribute("users",userService.getAll());
        return "admin/user_table";
    }
    @GetMapping("/tag")
    public String tag(Model model){

        model.addAttribute("tags",tagService.getAll());
        return "admin/tag_table";
    }
    @GetMapping("/category")
    public String category(Model model){

        model.addAttribute("categories",categoryService.getAll());
        return "admin/category_table";
    }
    @GetMapping("/comment")
    public String comment(Model model){

        model.addAttribute("comments",commentService.getAllComment());
        return "admin/comment_table";
    }
    @GetMapping("/repost")
    public String repost(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        // Tạo Pageable để định nghĩa phân trang, 5 bài viết mỗi trang
        Pageable pageable = PageRequest.of(page, 5);
        Page<RepostsEntity> postsPage = repostService.getAll(pageable); // Giả sử postService trả về Page<PostsEntity>
        for (RepostsEntity repost : postsPage.getContent()) {
            System.out.println("Repost Content: " + repost.getContent()+"\n");  // In ra giá trị của repost.content
        }
        model.addAttribute("reposts", postsPage.getContent()); // Truyền danh sách bài viết của trang hiện tại
        model.addAttribute("currentPage", page); // Truyền trang hiện tại tới view
        model.addAttribute("totalPages", postsPage.getTotalPages()); // Truyền tổng số trang
        return "admin/repost_table";
    }
    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }
}
