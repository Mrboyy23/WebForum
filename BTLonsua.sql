
-- B?ng Users
CREATE TABLE Users (
    user_id NUMBER PRIMARY KEY,
	name VARCHAR2(50) NOT NULL,
    username VARCHAR2(50) NOT NULL UNIQUE,
    email VARCHAR2(100) NOT NULL UNIQUE,
    password_hash VARCHAR2(255) NOT NULL,
    created_at DATE DEFAULT SYSDATE,
    updated_at DATE DEFAULT SYSDATE,
    role NUMBER,
    status NVARCHAR2(20) DEFAULT 'active',
    DESCRIPTION VARCHAR2(255),
    IMG NVARCHAR2(255),
    points NUMBER DEFAULT 0 -- Tr??ng ?i?m
);

-- B?ng Categories
CREATE TABLE Categories (
    category_id NUMBER PRIMARY KEY,
    parent_category NUMBER,
    name NVARCHAR2(100) NOT NULL UNIQUE,
    description NCLOB
);

-- B?ng Posts
CREATE TABLE Posts (
    post_id NUMBER PRIMARY KEY,
    user_id NUMBER,
    content NCLOB NOT NULL,
    image_url NVARCHAR2(255), -- URL cho h�nh ?nh b�i vi?t
    created_at DATE DEFAULT SYSDATE,
    updated_at DATE DEFAULT SYSDATE,
    category_id NUMBER,
    repost_count NUMBER DEFAULT 0, --số view, e quên chưa đổi tên
    title NVARCHAR2(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);

-- B?ng Comments
CREATE TABLE Comments (
    comment_id NUMBER PRIMARY KEY,
    post_id NUMBER,
    user_id NUMBER,
    content CLOB NOT NULL,
    created_at DATE DEFAULT SYSDATE,
    PARENTID NUMBER,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);



-- B?ng CommentLikes
CREATE TABLE CommentLikes (
    comment_like_id NUMBER PRIMARY KEY,
    comment_id NUMBER,
    user_id NUMBER,
    created_at DATE DEFAULT SYSDATE,
    FOREIGN KEY (comment_id) REFERENCES Comments(comment_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- B?ng PostLikes
CREATE TABLE PostLikes (
    post_like_id NUMBER PRIMARY KEY,
    post_id NUMBER,
    user_id NUMBER,
    created_at DATE DEFAULT SYSDATE,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- B?ng Follows
CREATE TABLE Follows (
    follow_id NUMBER PRIMARY KEY,
    follower_id NUMBER,
    followed_id NUMBER,
    created_at DATE DEFAULT SYSDATE,
    FOREIGN KEY (follower_id) REFERENCES Users(user_id),
    FOREIGN KEY (followed_id) REFERENCES Users(user_id)
);

-- B?ng Messages
CREATE TABLE Messages (
    message_id NUMBER PRIMARY KEY,
    sender_id NUMBER,
    receiver_id NUMBER,
    content CLOB NOT NULL,
    created_at DATE DEFAULT SYSDATE,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);

-- B?ng Reposts
CREATE TABLE Reposts (
    repost_id NUMBER PRIMARY KEY,
    post_id NUMBER,
    user_id NUMBER,
	content NVARCHAR2(250) NOT NULL,
    created_at DATE DEFAULT SYSDATE,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- B?ng Tags
CREATE TABLE Tags (
    tag_id NUMBER PRIMARY KEY,
    name NVARCHAR2(50) NOT NULL UNIQUE
);

-- B?ng PostTags
CREATE TABLE PostTags (
    post_tag_id NUMBER PRIMARY KEY,
    post_id NUMBER,
    tag_id NUMBER,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (tag_id) REFERENCES Tags(tag_id)
);

INSERT INTO Users (user_id, name, username, email, password_hash, created_at, updated_at, role, status, description, img, points) 
VALUES (1, 'John Doe', 'johndoe', 'johndoe@example.com', 'hashedpassword1', SYSDATE, SYSDATE, 1, 'active', 'Perfume enthusiast and blogger', 'johndoe.jpg', 100);

INSERT INTO Users (user_id, name, username, email, password_hash, created_at, updated_at, role, status, description, img, points) 
VALUES (2, 'Jane Smith', 'janesmith', 'janesmith@example.com', 'hashedpassword2', SYSDATE, SYSDATE, 2, 'active', 'Loves trying new fragrances', 'janesmith.jpg', 150);

INSERT INTO Users (user_id, name, username, email, password_hash, created_at, updated_at, role, status, description, img, points) 
VALUES (3, 'Alice Brown', 'alicebrown', 'aliceb@example.com', 'hashedpassword3', SYSDATE, SYSDATE, 3, 'active', 'Fragrance lover and reviewer', 'aliceb.jpg', 200);

INSERT INTO Users (user_id, name, username, email, password_hash, created_at, updated_at, role, status, description, img, points) 
VALUES (4, 'Robert White', 'robertwhite', 'robertw@example.com', 'hashedpassword4', SYSDATE, SYSDATE, 1, 'inactive', 'Perfume critic and enthusiast', 'robertw.jpg', 50);

INSERT INTO Users (user_id, name, username, email, password_hash, created_at, updated_at, role, status, description, img, points) 
VALUES (5, 'Lily Green', 'lilygreen', 'lilyg@example.com', 'hashedpassword5', SYSDATE, SYSDATE, 2, 'active', 'New to the world of perfumes', 'lilyg.jpg', 75);

INSERT INTO Categories (category_id, parent_category, name, description) 
VALUES (1, NULL, 'Perfume Reviews', 'Reviews of different perfumes and fragrances.');

INSERT INTO Categories (category_id, parent_category, name, description) 
VALUES (2, NULL, 'Fragrance Tips', 'Tips on choosing the best perfume for different occasions.');

INSERT INTO Categories (category_id, parent_category, name, description) 
VALUES (3, NULL, 'Luxury Perfumes', 'Top-tier luxury perfumes and their collections.');

INSERT INTO Categories (category_id, parent_category, name, description) 
VALUES (4, NULL, 'Men Fragrances', 'Perfume recommendations for men.');

INSERT INTO Categories (category_id, parent_category, name, description) 
VALUES (5, NULL, 'women Fragrances', 'Perfume recommendations for women.');

INSERT INTO Posts (post_id, user_id, content, image_url, created_at, updated_at, category_id, repost_count, title) 
VALUES (1, 1, 'Exploring the best fragrances of 2024. Top perfumes for men and women.', 'perfume_review_2024.jpg', SYSDATE, SYSDATE, 1, 5, 'Best Perfumes of 2024');

INSERT INTO Posts (post_id, user_id, content, image_url, created_at, updated_at, category_id, repost_count, title) 
VALUES (2, 2, 'How to choose the right perfume based on your personality type.', 'perfume_tips.jpg', SYSDATE, SYSDATE, 2, 2, 'Choosing the Right Fragrance');

INSERT INTO Posts (post_id, user_id, content, image_url, created_at, updated_at, category_id, repost_count, title) 
VALUES (3, 3, 'Top 10 luxury perfumes for 2024.', 'luxury_perfumes.jpg', SYSDATE, SYSDATE, 3, 3, 'Luxury Perfumes for 2024');

INSERT INTO Posts (post_id, user_id, content, image_url, created_at, updated_at, category_id, repost_count, title) 
VALUES (4, 4, 'Best perfumes for men this winter season.', 'mens_winter_perfumes.jpg', SYSDATE, SYSDATE, 4, 1, 'Winter Fragrances for Men');

INSERT INTO Posts (post_id, user_id, content, image_url, created_at, updated_at, category_id, repost_count, title) 
VALUES (5, 5, 'A beginner’s guide to women’s fragrances: what to look for in a scent.', 'beginners_guide.jpg', SYSDATE, SYSDATE, 5, 0, 'Women’s Fragrance Guide for Beginners');

INSERT INTO Comments (comment_id, post_id, user_id, content, created_at, parentid) 
VALUES (1, 1, 2, 'Great review! I loved the fragrance you mentioned for spring.', SYSDATE, NULL);

INSERT INTO Comments (comment_id, post_id, user_id, content, created_at, parentid) 
VALUES (2, 2, 1, 'This article helped me a lot. I’ll try the suggestions next time!', SYSDATE, NULL);

INSERT INTO Comments (comment_id, post_id, user_id, content, created_at, parentid) 
VALUES (3, 3, 4, 'I totally agree! Luxury perfumes are worth the investment.', SYSDATE, NULL);

INSERT INTO Comments (comment_id, post_id, user_id, content, created_at, parentid) 
VALUES (4, 4, 3, 'This post made me want to try a few of these perfumes.', SYSDATE, NULL);

INSERT INTO Comments (comment_id, post_id, user_id, content, created_at, parentid) 
VALUES (5, 5, 2, 'I’m definitely going to check out the perfumes you mentioned for women.', SYSDATE, NULL);

INSERT INTO CommentLikes (comment_like_id, comment_id, user_id, created_at) 
VALUES (1, 1, 3, SYSDATE);

INSERT INTO CommentLikes (comment_like_id, comment_id, user_id, created_at) 
VALUES (2, 2, 4, SYSDATE);

INSERT INTO CommentLikes (comment_like_id, comment_id, user_id, created_at) 
VALUES (3, 3, 5, SYSDATE);

INSERT INTO CommentLikes (comment_like_id, comment_id, user_id, created_at) 
VALUES (4, 4, 1, SYSDATE);

INSERT INTO CommentLikes (comment_like_id, comment_id, user_id, created_at) 
VALUES (5, 5, 2, SYSDATE);

INSERT INTO PostLikes (post_like_id, post_id, user_id, created_at) 
VALUES (1, 1, 2, SYSDATE);

INSERT INTO PostLikes (post_like_id, post_id, user_id, created_at) 
VALUES (2, 2, 3, SYSDATE);

INSERT INTO PostLikes (post_like_id, post_id, user_id, created_at) 
VALUES (3, 3, 4, SYSDATE);

INSERT INTO PostLikes (post_like_id, post_id, user_id, created_at) 
VALUES (4, 4, 5, SYSDATE);

INSERT INTO PostLikes (post_like_id, post_id, user_id, created_at) 
VALUES (5, 5, 1, SYSDATE);

INSERT INTO Follows (follow_id, follower_id, followed_id, created_at) 
VALUES (1, 1, 2, SYSDATE);

INSERT INTO Follows (follow_id, follower_id, followed_id, created_at) 
VALUES (2, 2, 3, SYSDATE);

INSERT INTO Follows (follow_id, follower_id, followed_id, created_at) 
VALUES (3, 3, 4, SYSDATE);

INSERT INTO Follows (follow_id, follower_id, followed_id, created_at) 
VALUES (4, 4, 5, SYSDATE);

INSERT INTO Follows (follow_id, follower_id, followed_id, created_at) 
VALUES (5, 5, 1, SYSDATE);

INSERT INTO Messages (message_id, sender_id, receiver_id, content, created_at) 
VALUES (1, 1, 2, 'Hey, I loved your recent post on perfumes!', SYSDATE);

INSERT INTO Messages (message_id, sender_id, receiver_id, content, created_at) 
VALUES (2, 2, 3, 'Thanks! I appreciate your thoughts on fragrance suggestions.', SYSDATE);

INSERT INTO Messages (message_id, sender_id, receiver_id, content, created_at) 
VALUES (3, 3, 4, 'I just wanted to know your thoughts on luxury perfumes.', SYSDATE);

INSERT INTO Messages (message_id, sender_id, receiver_id, content, created_at) 
VALUES (4, 4, 5, 'Have you tried the new men’s fragrance collection? It’s amazing.', SYSDATE);

INSERT INTO Messages (message_id, sender_id, receiver_id, content, created_at) 
VALUES (5, 5, 1, 'Can you recommend some beginner perfumes for women? I’m new to this.', SYSDATE);

INSERT INTO Reposts (repost_id, post_id, user_id, content, created_at) 
VALUES (1, 1, 2, 'Reposting this because I totally agree with the recommendations!', SYSDATE);

INSERT INTO Reposts (repost_id, post_id, user_id, content, created_at) 
VALUES (2, 2, 3, 'Love this guide, so useful for anyone new to fragrances!', SYSDATE);

INSERT INTO Reposts (repost_id, post_id, user_id, content, created_at) 
VALUES (3, 3, 4, 'These luxury perfumes are a must-have for anyone looking for elegance.', SYSDATE);

INSERT INTO Reposts (repost_id, post_id, user_id, content, created_at) 
VALUES (4, 4, 5, 'Perfect post for finding the right winter fragrance!', SYSDATE);

INSERT INTO Reposts (repost_id, post_id, user_id, content, created_at) 
VALUES (5, 5, 1, 'This guide helped me pick the perfect perfume for my girlfriend.', SYSDATE);

INSERT INTO Tags (tag_id, name) 
VALUES (1, 'Perfume');

INSERT INTO Tags (tag_id, name) 
VALUES (2, 'Fragrance');

INSERT INTO Tags (tag_id, name) 
VALUES (3, 'Beauty');

INSERT INTO Tags (tag_id, name) 
VALUES (4, 'Luxury');

INSERT INTO Tags (tag_id, name) 
VALUES (5, 'Spring Fragrances');

INSERT INTO PostTags (post_tag_id, post_id, tag_id) 
VALUES (1, 1, 1);

INSERT INTO PostTags (post_tag_id, post_id, tag_id) 
VALUES (2, 1, 2);

INSERT INTO PostTags (post_tag_id, post_id, tag_id) 
VALUES (3, 2, 1);

INSERT INTO PostTags (post_tag_id, post_id, tag_id) 
VALUES (4, 2, 3);

INSERT INTO PostTags (post_tag_id, post_id, tag_id) 
VALUES (5, 2, 5);



