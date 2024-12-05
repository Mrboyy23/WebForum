package com.example.project.controller.user;

import com.example.project.model.DTO.MessDTO;
import com.example.project.model.DTO.SavePort;
import com.example.project.model.entity.MessagesEntity;
import com.example.project.model.entity.PostsEntity;
import com.example.project.model.entity.RepostsEntity;
import com.example.project.model.entity.UsersEntity;
import com.example.project.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private TagService tagService;
    private CommentService commentService;
    private MessService messService;
    private HttpSession session;
    private PostLikeService postLikeService;
    private CommentLikeSevice commentLikeSevice;
    private RepostService repostService;
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("category",categoryService.getAll());
        model.addAttribute("alluser",userService.getAll());
        model.addAttribute("tag",tagService.getAll());
        model.addAttribute("recentpost",postService.recentPost());

    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        // Tạo Pageable để định nghĩa phân trang, 5 bài viết mỗi trang
        Pageable pageable = PageRequest.of(page, 5);
        Page<PostsEntity> postsPage = postService.getAll(pageable); // Giả sử postService trả về Page<PostsEntity>

        Map<Long, Long> commentCounts = new HashMap<>();
        // Đếm số lượng bình luận cho mỗi bài viết
        for (PostsEntity post : postsPage.getContent()) {
            Long commentCount = commentService.countCommentByPost(post.getPostId());
            commentCounts.put(post.getPostId(), commentCount);
        }

        model.addAttribute("commentcount", commentCounts);
        model.addAttribute("post", postsPage.getContent()); // Truyền danh sách bài viết của trang hiện tại
        model.addAttribute("currentPage", page); // Truyền trang hiện tại tới view
        model.addAttribute("totalPages", postsPage.getTotalPages()); // Truyền tổng số trang
        return "user/index";
    }


    @GetMapping("/index/{categoryid}")
    public String indexByCategory(@PathVariable Long categoryid, Model model){
        List<PostsEntity> posts = postService.getPostByCategory(categoryid);
        Map<Long, Long> commentCounts = new HashMap<>();
        // Đếm bình luận cho từng bài viết
        for (PostsEntity post : posts) {
            Long commentCount = commentService.countCommentByPost(post.getPostId());
            commentCounts.put(post.getPostId(), commentCount);
        }
        model.addAttribute("cpmmentcount",commentCounts);
        model.addAttribute("post",posts);
        return "user/index";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");
        model.addAttribute("user",usersEntity);
        return "user/user";
    }
    @PostMapping("/uploadImage/{userId}")
    public String uploadImage(@PathVariable("userId") long userId,
                              @RequestParam("file") MultipartFile file,
                              Model model) throws IOException {

        if (!file.isEmpty()) {
            // Lưu ảnh vào thư mục static/img
            String uploadDir = "src/main/resources/static/img/"; // Thư mục lưu hình ảnh
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();  // Tạo thư mục nếu chưa có
            }

            // Lưu ảnh vào thư mục
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, file.getBytes());

            // Cập nhật đường dẫn hình ảnh trong cơ sở dữ liệu
            UsersEntity user = userService.findById(userId).get();
            user.setImg(fileName);  // Đường dẫn tới ảnh sẽ là /img/{fileName}
            userService.save(user);

            model.addAttribute("user", user);
        }
        return "user/user";
    }
    @GetMapping("/profile/{id}")
    public String profilebyuser(Model model, @PathVariable Long id){
        Optional<UsersEntity> usersEntity = userService.findById(id);
        model.addAttribute("user",usersEntity.get());
        return "user/user";
    }
    @GetMapping("/ask_question")
    public String ask_question(){
        return "user/ask_question";
    }
    @PostMapping("/submit_post")
    public String submitPost(
            @RequestParam String questionTitle,
            @RequestParam Long category,
            @RequestParam String tags,
            @RequestParam String content,
            HttpSession session,
            Model model) {
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");

        // Tạo đối tượng SavePort
        SavePort savePort = new SavePort();
        savePort.setPostId(postService.idMax()+1);
        savePort.setTitle(questionTitle);
        savePort.setCategoryId(category);
        savePort.setTags(tags);  // Nhận chuỗi tags từ form
        savePort.setContent(content);
        savePort.setRepostCount(0L);
        savePort.setCreatedAt(LocalDateTime.now());  // Gán thời gian tạo (có thể lấy thời gian hiện tại)
        savePort.setUpdatedAt(LocalDateTime.now());  // Gán thời gian cập nhật (có thể lấy thời gian hiện tại)
        savePort.setUserId(usersEntity.getUserId());
        // Gọi service để xử lý lưu bài viết và các liên kết tag
        postService.create(savePort);

        model.addAttribute("message", "Post created successfully");
        return "redirect:/user/post_detail/" + savePort.getPostId();
    }


    @GetMapping("/mess")
    public String mess(Model model){
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");
        model.addAttribute("users", userService.findUsersWhoSentOrReceivedMessages(usersEntity.getUserId()));
        model.addAttribute("senderId", usersEntity.getUserId());  // Truyền senderId vào model

        return "user/mess";
    }
    @GetMapping("/mess/{userId}")
    public String messwwithfriend(Model model,@PathVariable Long userId){
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");
        model.addAttribute("users", userService.findById(userId).get());
        model.addAttribute("senderId", usersEntity.getUserId());  // Truyền senderId vào model

        return "user/mess";
    }
    @GetMapping("/messages/{userId}")
    @ResponseBody
    public List<MessDTO> getMessages(@PathVariable Long userId) {
        UsersEntity u = (UsersEntity) session.getAttribute("user");
        Long myid = u.getUserId();
        List<MessagesEntity> messagesEntities =messService.getAllWithFriend(myid,userId);
        List<MessDTO> messageDTOs = messagesEntities.stream().map(message -> {
            MessDTO dto = new MessDTO();
            dto.setMessageId(message.getMessageId());
            dto.setContent(message.getContent());
            dto.setCreatedAt(message.getCreatedAt());
            dto.setSenderId(message.getSenderId());
            dto.setReceiverId(message.getReceiverId());

            // Giả sử bạn có dịch vụ để lấy tên người gửi
            Optional<UsersEntity> usersEntity = userService.findById(userId);
            UsersEntity friend = usersEntity.get();

            dto.setSenderName(friend.getName());

            return dto;
        }).collect(Collectors.toList());
        return messageDTOs ;
    }


    @GetMapping("/post_detail/{postId}")
    public String post_detail(Model model, @PathVariable Long postId){
        PostsEntity postsEntity = postService.getPostById(postId);
        model.addAttribute("tags", tagService.getByPostId(postId));
        model.addAttribute("postsEntity", postsEntity);
        model.addAttribute("comments", commentService.getAll(postId));
        return "user/post_deatils";
    }
    @PostMapping("/post_detail/{postId}")
    @ResponseBody
    public String updateViews(@PathVariable Long postId) {
        Long updatedViews = postService.increaseViewCount(postId);
        return updatedViews + " views" ;
    }
    @PostMapping("/submitcomment")
    @ResponseBody
    public String submitComment(@RequestParam String content, @RequestParam Long postId, @RequestParam Long userId, @RequestParam Long parentId) {

        Long id =commentService.maxId()+1;
        commentService.saveComment(id,content, postId, userId, parentId);

        return "Comment successfully submitted";
    }
    @PostMapping("/repost")
    @ResponseBody
    public String repost(@RequestParam String content, @RequestParam Long postId){
        UsersEntity  usersEntity = (UsersEntity) session.getAttribute("user");
        RepostsEntity repostsEntity = new RepostsEntity();
        repostsEntity.setRepostId(repostService.idMax()!=null? repostService.idMax()+1 : 1 );
        repostsEntity.setUserId(usersEntity.getUserId());
        repostsEntity.setPostId(postId);
        repostsEntity.setContent(content);
        repostsEntity.setCreatedAt(LocalDateTime.now());
        repostService.save(repostsEntity);
        return "Repost successfully submitted";

    }
    // Kiểm tra người dùng đã thích bài viết hay chưa
    @GetMapping("/hasLiked/{postId}/{userId}")
    public ResponseEntity<Boolean> hasUserLikedPost(@PathVariable Long postId, @PathVariable Long userId) {
        Boolean hasLiked = postLikeService.hasUserLikedPost(postId, userId);
        return ResponseEntity.ok(hasLiked);
    }

    @GetMapping("/hasLikedcomment/{commentId}/{userId}")
    public ResponseEntity<Boolean> hasUserLikedComment(@PathVariable Long commentId, @PathVariable Long userId) {
        Boolean hasLikedcomment = commentLikeSevice.hasUserLikedComment(commentId, userId);
        return ResponseEntity.ok(hasLikedcomment);
    }


    // Thêm hoặc xóa lượt thích (like/unlike)
    @PostMapping("/like")
    public ResponseEntity<Void> likePost(@RequestParam Long postId) {
        // Lấy userId từ session
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");
        Long userId = usersEntity.getUserId(); // Lấy userId từ session

        // Kiểm tra xem người dùng đã thích bài viết chưa
        if (postLikeService.hasUserLikedPost(postId, userId)) {
            // Nếu đã thích rồi, thực hiện xóa lượt thích (unlike)
            postLikeService.unlikePost(postId, userId);
        } else {
            // Nếu chưa thích, thực hiện thêm lượt thích (like)
            postLikeService.likePost(postId, userId);
        }
        // Return a success status
        return ResponseEntity.ok().build(); // HTTP 200 OK
    }
    @PostMapping("/likecomment")
    public ResponseEntity<Void> likeComment(@RequestParam Long commentId) {
        // Lấy userId từ session
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");
        Long userId = usersEntity.getUserId(); // Lấy userId từ session

        // Kiểm tra xem người dùng đã thích bài viết chưa
        if (commentLikeSevice.hasUserLikedComment(commentId, userId)) {
            // Nếu đã thích rồi, thực hiện xóa lượt thích (unlike)
            commentLikeSevice.unlikeComment(commentId, userId);
        } else {
            // Nếu chưa thích, thực hiện thêm lượt thích (like)
            commentLikeSevice.likeComment(commentId, userId);
        }
        // Return a success status
        return ResponseEntity.ok().build(); // HTTP 200 OK
    }

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }
    @PostMapping("/register")
    public String checkRegister(@RequestParam String fullName,
                                @RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "user/register";
        }


        if (userService.findByUsername(username) != null) {
            model.addAttribute("error", "Username is already taken.");
            return "user/register";
        }
        if (userService.findByEmail(email) != null) {
            model.addAttribute("error", "Email is already taken.");
            return "user/register";
        }


        UsersEntity newUser = new UsersEntity();
        newUser.setUserId(userService.idMax()+1);
        newUser.setName(fullName);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPasswordHash(password);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        newUser.setRole(1L);
        newUser.setStatus("active");
        newUser.setPoints(0L);



        userService.save(newUser);


        return "user/login";  // Redirect to login page after successful registration
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam String username, @RequestParam String password, Model model) {
        UsersEntity usersEntity = userService.findByUsername(username);
        if (usersEntity != null && usersEntity.getPasswordHash().equals(password)) {
            session.setAttribute("user", usersEntity);
            return "redirect:/user/index";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "user/login"; // Trả về trang đăng nhập với thông báo lỗi
        }
    }


}
