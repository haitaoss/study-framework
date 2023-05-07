/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 07/05/2023 17:39:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '作者',
  `category_id` int DEFAULT NULL COMMENT '文章分类',
  `article_cover` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章缩略图',
  `article_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `article_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '文章类型 1原创 2转载 3翻译',
  `original_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原文链接',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶 0否 1是',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除  0否 1是',
  `is_comment` tinyint(1) NOT NULL COMMENT '是否开放评论 0否 1是',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态值 1公开 2私密 3评论可见',
  `create_time` datetime NOT NULL COMMENT '发表时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
BEGIN;
INSERT INTO `tb_article` (`id`, `user_id`, `category_id`, `article_cover`, `article_title`, `article_content`, `type`, `original_url`, `is_top`, `is_delete`, `is_comment`, `status`, `create_time`, `update_time`) VALUES (47, 1, 184, 'https://static.talkxj.com/articles/3dffb2fcbd541886616ab54c92570de3.jpg', '测试文章', '## 目录\n\n恭喜你已成功运行博客，开启你的文章之旅吧![Wallpaper 22.jpg](http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/aa7e907fc653d0a671df4b0e948cf5c6.jpg)', 1, '', 0, 0, 1, 1, '2021-08-12 15:50:57', '2022-07-24 22:43:40');
COMMIT;

-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL COMMENT '文章id',
  `tag_id` int NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_article_tag_1` (`article_id`) USING BTREE,
  KEY `fk_article_tag_2` (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_tag` (`id`, `article_id`, `tag_id`) VALUES (500, 47, 27);
COMMIT;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_category` (`id`, `category_name`, `create_time`, `update_time`) VALUES (184, '测试分类', '2021-08-12 15:50:57', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_chat_record`;
CREATE TABLE `tb_chat_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '聊天内容',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip来源',
  `type` tinyint NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1896 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_chat_record
-- ----------------------------
BEGIN;
INSERT INTO `tb_chat_record` (`id`, `user_id`, `nickname`, `avatar`, `content`, `ip_address`, `ip_source`, `type`, `create_time`, `update_time`) VALUES (1890, 1, '知食分子', 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/avatar/9992294ba1ffe3811f3d586b4415721e.jpg', '策划四十', '未知ip', '', 3, '2021-08-15 10:56:12', NULL);
INSERT INTO `tb_chat_record` (`id`, `user_id`, `nickname`, `avatar`, `content`, `ip_address`, `ip_source`, `type`, `create_time`, `update_time`) VALUES (1891, 1, '知食分子', 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/avatar/9992294ba1ffe3811f3d586b4415721e.jpg', '<img styl***rtical-ali***: middle\' src= \'https://www.static.talkxj.com/emoji/wy.jpg\' width=\'22\'height=\'20\' style=\'padding: 0 1px\'/>', '未知ip', '', 3, '2021-08-15 10:56:23', NULL);
INSERT INTO `tb_chat_record` (`id`, `user_id`, `nickname`, `avatar`, `content`, `ip_address`, `ip_source`, `type`, `create_time`, `update_time`) VALUES (1893, NULL, '未知ip', 'https://www.static.talkxj.com/photos/0bca52afdb2b9998132355d716390c9f.png', '浏览', '未知ip', '', 3, '2021-11-14 23:04:21', NULL);
INSERT INTO `tb_chat_record` (`id`, `user_id`, `nickname`, `avatar`, `content`, `ip_address`, `ip_source`, `type`, `create_time`, `update_time`) VALUES (1894, NULL, '未知ip', 'https://www.static.talkxj.com/photos/0bca52afdb2b9998132355d716390c9f.png', '的地方', '未知ip', '', 3, '2021-11-14 23:07:32', NULL);
INSERT INTO `tb_chat_record` (`id`, `user_id`, `nickname`, `avatar`, `content`, `ip_address`, `ip_source`, `type`, `create_time`, `update_time`) VALUES (1895, NULL, '未知ip', 'https://www.static.talkxj.com/photos/0bca52afdb2b9998132355d716390c9f.png', '<img style=\'vertical-align: middle\' src= \'https://www.static.talkxj.com/emoji/chigua.jpg\' width=\'22\'height=\'20\' style=\'padding: 0 1px\'/>', '未知ip', '', 3, '2021-11-14 23:10:43', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '评论用户Id',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `reply_user_id` int DEFAULT NULL COMMENT '回复用户id',
  `parent_id` int DEFAULT NULL COMMENT '父评论id',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除  0否 1是',
  `is_review` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `type` tinyint(1) NOT NULL COMMENT '评论类型 1.文章 2.友链 3.说说',
  `topic_id` int DEFAULT NULL COMMENT '评论主题id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_comment_user` (`user_id`) USING BTREE,
  KEY `fk_comment_parent` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=443 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
BEGIN;
INSERT INTO `tb_comment` (`id`, `user_id`, `comment_content`, `reply_user_id`, `parent_id`, `is_delete`, `is_review`, `create_time`, `update_time`, `type`, `topic_id`) VALUES (441, 1, '测试', NULL, NULL, 0, 1, '2022-06-12 14:02:46', NULL, 1, 47);
INSERT INTO `tb_comment` (`id`, `user_id`, `comment_content`, `reply_user_id`, `parent_id`, `is_delete`, `is_review`, `create_time`, `update_time`, `type`, `topic_id`) VALUES (442, 1, '知道了<img src= \'https://static.talkxj.com/emoji/goutou.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', 1, 441, 0, 1, '2022-06-12 14:04:51', NULL, 1, 47);
COMMIT;

-- ----------------------------
-- Table structure for tb_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend_link`;
CREATE TABLE `tb_friend_link` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接名',
  `link_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接头像',
  `link_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接地址',
  `link_intro` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_friend_link_user` (`link_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_friend_link
-- ----------------------------
BEGIN;
INSERT INTO `tb_friend_link` (`id`, `link_name`, `link_avatar`, `link_address`, `link_intro`, `create_time`, `update_time`) VALUES (20, '风丶宇的个人博客', 'https://static.talkxj.com/photos/b553f564f81a80dc338695acb1b475d2.jpg', 'https://www.talkxj.com/', '成事在人 谋事在天', '2021-08-12 15:56:48', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单icon',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `order_num` tinyint(1) NOT NULL COMMENT '排序',
  `parent_id` int DEFAULT NULL COMMENT '父id',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏  0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (1, '首页', '/', '/home/Home.vue', 'el-icon-myshouye', '2021-01-26 17:06:51', '2021-01-26 17:06:53', 1, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (2, '文章管理', '/article-submenu', 'Layout', 'el-icon-mywenzhang-copy', '2021-01-25 20:43:07', '2021-01-25 20:43:09', 2, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (3, '消息管理', '/message-submenu', 'Layout', 'el-icon-myxiaoxi', '2021-01-25 20:44:17', '2021-01-25 20:44:20', 3, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (4, '系统管理', '/system-submenu', 'Layout', 'el-icon-myshezhi', '2021-01-25 20:45:57', '2021-01-25 20:45:59', 5, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (5, '个人中心', '/setting', '/setting/Setting.vue', 'el-icon-myuser', '2021-01-26 17:22:38', '2021-01-26 17:22:41', 7, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (6, '发布文章', '/articles', '/article/Article.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:30:48', '2021-01-26 14:30:51', 1, 2, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (7, '修改文章', '/articles/*', '/article/Article.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:31:32', '2021-01-26 14:31:34', 2, 2, 1);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (8, '文章列表', '/article-list', '/article/ArticleList.vue', 'el-icon-mywenzhangliebiao', '2021-01-26 14:32:13', '2021-01-26 14:32:16', 3, 2, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (9, '分类管理', '/categories', '/category/Category.vue', 'el-icon-myfenlei', '2021-01-26 14:33:42', '2021-01-26 14:33:43', 4, 2, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (10, '标签管理', '/tags', '/tag/Tag.vue', 'el-icon-myicontag', '2021-01-26 14:34:33', '2021-01-26 14:34:36', 5, 2, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (11, '评论管理', '/comments', '/comment/Comment.vue', 'el-icon-mypinglunzu', '2021-01-26 14:35:31', '2021-01-26 14:35:34', 1, 3, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (12, '留言管理', '/messages', '/message/Message.vue', 'el-icon-myliuyan', '2021-01-26 14:36:09', '2021-01-26 14:36:13', 2, 3, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (13, '用户列表', '/users', '/user/User.vue', 'el-icon-myyonghuliebiao', '2021-01-26 14:38:09', '2021-01-26 14:38:12', 1, 202, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (14, '角色管理', '/roles', '/role/Role.vue', 'el-icon-myjiaoseliebiao', '2021-01-26 14:39:01', '2021-01-26 14:39:03', 2, 213, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (15, '接口管理', '/resources', '/resource/Resource.vue', 'el-icon-myjiekouguanli', '2021-01-26 14:40:14', '2021-08-07 20:00:28', 2, 213, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (16, '菜单管理', '/menus', '/menu/Menu.vue', 'el-icon-mycaidan', '2021-01-26 14:40:54', '2021-08-07 10:18:49', 2, 213, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (17, '友链管理', '/links', '/friendLink/FriendLink.vue', 'el-icon-mydashujukeshihuaico', '2021-01-26 14:41:35', '2021-01-26 14:41:37', 3, 4, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (18, '关于我', '/about', '/about/About.vue', 'el-icon-myguanyuwo', '2021-01-26 14:42:05', '2021-01-26 14:42:10', 5, 4, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (19, '日志管理', '/log-submenu', 'Layout', 'el-icon-myguanyuwo', '2021-01-31 21:33:56', '2021-01-31 21:33:59', 6, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (20, '操作日志', '/operation/log', '/log/Operation.vue', 'el-icon-myguanyuwo', '2021-01-31 15:53:21', '2021-01-31 15:53:25', 1, 19, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (201, '在线用户', '/online/users', '/user/Online.vue', 'el-icon-myyonghuliebiao', '2021-02-05 14:59:51', '2021-02-05 14:59:53', 7, 202, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (202, '用户管理', '/users-submenu', 'Layout', 'el-icon-myyonghuliebiao', '2021-02-06 23:44:59', '2021-02-06 23:45:03', 4, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (205, '相册管理', '/album-submenu', 'Layout', 'el-icon-myimage-fill', '2021-08-03 15:10:54', '2021-08-07 20:02:06', 5, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (206, '相册列表', '/albums', '/album/Album.vue', 'el-icon-myzhaopian', '2021-08-03 20:29:19', '2021-08-04 11:45:47', 1, 205, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (208, '照片管理', '/albums/:albumId', '/album/Photo.vue', 'el-icon-myzhaopian', '2021-08-03 21:37:47', '2021-08-05 10:24:08', 1, 205, 1);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (209, '页面管理', '/pages', '/page/Page.vue', 'el-icon-myyemianpeizhi', '2021-08-04 11:36:27', '2021-08-07 20:01:26', 2, 4, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (210, '照片回收站', '/photos/delete', '/album/Delete.vue', 'el-icon-myhuishouzhan', '2021-08-05 13:55:19', NULL, 3, 205, 1);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (213, '权限管理', '/permission-submenu', 'Layout', 'el-icon-myquanxianguanli', '2021-08-07 19:56:55', '2021-08-07 19:59:40', 4, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (214, '网站管理', '/website', '/website/Website.vue', 'el-icon-myxitong', '2021-08-07 20:06:41', NULL, 1, 4, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (215, '监控管理', '/monitor-sunmenu', 'Layout', 'el-icon-mymeter', '2021-08-14 15:37:00', '2021-08-14 15:37:23', 6, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (216, '系统监控', '/monitor/system', '/monitor/SystemMonitor', 'el-icon-mymeter2', '2021-11-19 21:53:11', '2021-11-19 21:53:15', 1, 215, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (217, 'Redis监控', '/monitor/redis', '/monitor/RedisMonitor', 'el-icon-myshield', '2021-11-19 21:55:32', NULL, 2, 215, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (218, '说说管理', '/talk-submenu', 'Layout', 'el-icon-mypinglun', '2022-01-23 20:17:59', '2022-01-23 20:38:06', 5, NULL, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (219, '发布说说', '/talks', '/talk/Talk.vue', 'el-icon-myfabusekuai', '2022-01-23 20:18:43', '2022-01-23 20:38:19', 1, 218, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (220, '说说列表', '/talk-list', '/talk/TalkList.vue', 'el-icon-mydongtaidianji', '2022-01-23 23:28:24', '2022-01-24 00:02:48', 2, 218, 0);
INSERT INTO `tb_menu` (`id`, `name`, `path`, `component`, `icon`, `create_time`, `update_time`, `order_num`, `parent_id`, `is_hidden`) VALUES (221, '修改说说', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myshouye', '2022-01-24 00:10:44', NULL, 3, 218, 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `message_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ip',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户地址',
  `time` tinyint(1) DEFAULT NULL COMMENT '弹幕速度',
  `is_review` tinyint NOT NULL DEFAULT '1' COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3459 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
BEGIN;
INSERT INTO `tb_message` (`id`, `nickname`, `avatar`, `message_content`, `ip_address`, `ip_source`, `time`, `is_review`, `create_time`, `update_time`) VALUES (3458, '知食分子', 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/avatar/9992294ba1ffe3811f3d586b4415721e.jpg', '你好啊', '127.0.0.1', '', 9, 1, '2021-08-15 10:55:58', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `opt_module` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作模块',
  `opt_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作类型',
  `opt_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作url',
  `opt_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作方法',
  `opt_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作描述',
  `request_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求参数',
  `request_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求方式',
  `response_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '返回数据',
  `user_id` int NOT NULL COMMENT '用户id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作ip',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=701 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------
BEGIN;
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (683, '标签模块', '新增或修改', '/admin/tags', 'com.wzl.blog.controller.TagController.saveOrUpdateTag', '添加或修改标签', '[{\"id\":27,\"tagName\":\"测试标签2\"}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '管理员', '127.0.0.1', '', '2021-08-14 11:30:43', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (684, '角色模块', '新增或修改', '/admin/role', 'com.wzl.blog.controller.RoleController.saveOrUpdateRole', '保存或更新角色', '[{\"id\":1,\"menuIdList\":[1,2,6,7,8,9,10,3,11,12,202,13,201,213,14,15,16,4,214,209,17,18,215,205,206,208,210,19,20,5],\"roleLabel\":\"admin\",\"roleName\":\"管理员\"}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-08-14 15:49:11', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (685, '角色模块', '新增或修改', '/admin/role', 'com.wzl.blog.controller.RoleController.saveOrUpdateRole', '保存或更新角色', '[{\"id\":1,\"resourceIdList\":[165,192,193,194,195,166,183,184,246,247,167,199,200,201,168,185,186,187,188,189,190,191,254,169,208,209,170,234,235,236,237,171,213,214,215,216,217,224,172,240,241,244,245,267,269,270,173,239,242,174,205,206,207,175,218,219,220,221,222,223,176,202,203,204,230,238,177,229,232,233,243,178,196,197,198,257,258,179,225,226,227,228,231,180,210,211,212,275,276],\"roleLabel\":\"admin\",\"roleName\":\"管理员\"}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-08-14 15:55:55', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (686, '相册模块', '新增或修改', '/admin/photos/albums', 'com.wzl.blog.controller.PhotoAlbumController.saveOrUpdatePhotoAlbum', '保存或更新相册', '[{\"albumCover\":\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/config/02bf8558f9e26b225ed198c6164df49e.jpg\",\"albumDesc\":\"测试插件\",\"albumName\":\"精选壁纸\",\"status\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-08-15 22:26:59', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (687, '照片模块', '新增', '/admin/photos', 'com.wzl.blog.controller.PhotoController.savePhotos', '保存照片', '[{\"albumId\":3,\"photoUrlList\":[\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/15022e6e99f21eaf653b694f7762f160.png\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/91fe114ab1b0fea8969f2a88de22b4f6.png\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/65a7d7f7e6d89451d3c0609b2a76206a.png\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/5dbf3ba4f0f4b2c9ddd541af30955bb2.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/2fdf3d5f2bba02601e6a932cd19f053a.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/847c98b7e48177fca9c92ec2d130ae94.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/3ff3d6a85edb2f19d343668d24ed9269.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/4096cf6b33bc2c42e476bb869b5250a5.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/0dde82862eb1ab13bf5a641e6dc7bf98.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/8f0380795feb2a1b2d820aa20ea63081.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/c79faae41edac5d5bb2bc95109998ed9.jpg\",\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/cad547b4c355dd8f3519c087783caecc.jpg\"]}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (688, '文章模块', '新增或修改', '/admin/articles', 'com.wzl.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"## 目录\\n\\n恭喜你已成功运行博客，开启你的文章之旅吧\",\"articleCover\":\"https://www.static.talkxj.com/articles/bd74062266c1fb04f3084968231c0580.jpg\",\"articleTitle\":\"测试文章\",\"categoryName\":\"测试分类\",\"id\":47,\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-11-11 21:37:24', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (689, '文章模块', '新增或修改', '/admin/articles', 'com.wzl.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"## 目录\\n\\n恭喜你已成功运行博客，开启你的文章之旅吧\",\"articleCover\":\"https://www.static.talkxj.com/articles/bd74062266c1fb04f3084968231c0580.jpg\",\"articleTitle\":\"测试文章\",\"categoryName\":\"测试分类\",\"id\":47,\"isComment\":1,\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-11-17 21:21:44', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (690, '文章模块', '修改', '/admin/articles/comment', 'com.wzl.blog.controller.ArticleController.updateArticleComment', '修改文章开放评论', '[{\"id\":47,\"isComment\":0}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-11-17 21:37:15', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (691, '文章模块', '修改', '/admin/articles/comment', 'com.wzl.blog.controller.ArticleController.updateArticleComment', '修改文章开放评论', '[{\"id\":47,\"isComment\":0}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-11-17 21:39:31', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (692, '文章模块', '新增或修改', '/admin/articles', 'com.wzl.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"## 目录\\n\\n恭喜你已成功运行博客，开启你的文章之旅吧\",\"articleCover\":\"https://www.static.talkxj.com/articles/bd74062266c1fb04f3084968231c0580.jpg\",\"articleTitle\":\"测试文章\",\"categoryName\":\"测试分类\",\"id\":47,\"isComment\":0,\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-11-17 21:40:26', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (693, '文章模块', '修改', '/admin/articles/comment', 'com.wzl.blog.controller.ArticleController.updateArticleComment', '修改文章开放评论', '[{\"id\":47,\"isComment\":1}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2021-11-20 16:27:03', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (694, '角色模块', '新增或修改', '/admin/role', 'com.wzl.blog.controller.RoleController.saveOrUpdateRole', '保存或更新角色', '[{\"id\":1,\"menuIdList\":[1,2,6,7,8,9,10,3,11,12,202,13,201,213,14,15,16,4,214,209,17,18,205,206,208,210,218,19,20,215,216,217,5],\"roleLabel\":\"admin\",\"roleName\":\"管理员\"}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2022-03-08 22:15:33', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (695, '角色模块', '新增或修改', '/admin/role', 'com.wzl.blog.controller.RoleController.saveOrUpdateRole', '保存或更新角色', '[{\"id\":1,\"resourceIdList\":[165,192,193,194,195,166,183,184,246,247,167,199,200,201,168,185,186,187,188,189,190,191,254,169,208,209,170,234,235,236,237,171,213,214,215,216,217,224,172,240,241,244,245,267,269,270,173,239,242,174,205,206,207,175,218,219,220,221,222,223,176,202,203,204,230,238,177,229,232,233,243,178,196,197,198,257,258,179,225,226,227,228,231,180,210,211,212,275,276,277,278,282,283,284,285,286,287],\"roleLabel\":\"admin\",\"roleName\":\"管理员\"}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2022-03-08 22:15:46', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (696, '角色模块', '新增或修改', '/admin/role', 'com.wzl.blog.controller.RoleController.saveOrUpdateRole', '保存或更新角色', '[{\"id\":1,\"menuIdList\":[1,2,6,7,8,9,10,3,11,12,202,13,201,213,14,15,16,4,214,209,17,18,205,206,208,210,218,219,220,221,19,20,215,216,217,5],\"roleLabel\":\"admin\",\"roleName\":\"管理员\"}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '', '2022-03-08 22:18:45', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (697, '博客信息模块', '修改', '/admin/about', 'com.wzl.blog.controller.BlogInfoController.updateAbout', '修改关于我信息', '[{\"aboutContent\":\"帅哥一枚~\"}]', 'PUT', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '未知来源', '2022-06-12 19:22:58', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (698, '文章模块', '新增或修改', '/admin/articles', 'com.wzl.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"## 目录\\n\\n恭喜你已成功运行博客，开启你的文章之旅吧![Wallpaper 22.jpg](http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/aa7e907fc653d0a671df4b0e948cf5c6.jpg)\",\"articleCover\":\"https://static.talkxj.com/articles/3dffb2fcbd541886616ab54c92570de3.jpg\",\"articleTitle\":\"测试文章\",\"categoryName\":\"测试分类\",\"id\":47,\"isComment\":1,\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '未知来源', '2022-07-24 22:29:24', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (699, '文章模块', '新增或修改', '/admin/articles', 'com.wzl.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"## 目录\\n\\n恭喜你已成功运行博客，开启你的文章之旅吧![Wallpaper 22.jpg](http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/aa7e907fc653d0a671df4b0e948cf5c6.jpg)\",\"articleCover\":\"https://static.talkxj.com/articles/3dffb2fcbd541886616ab54c92570de3.jpg\",\"articleTitle\":\"测试文章w\",\"categoryName\":\"测试分类\",\"id\":47,\"isComment\":1,\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '未知来源', '2022-07-24 22:35:45', NULL);
INSERT INTO `tb_operation_log` (`id`, `opt_module`, `opt_type`, `opt_url`, `opt_method`, `opt_desc`, `request_param`, `request_method`, `response_data`, `user_id`, `nickname`, `ip_address`, `ip_source`, `create_time`, `update_time`) VALUES (700, '文章模块', '新增或修改', '/admin/articles', 'com.wzl.blog.controller.ArticleController.saveOrUpdateArticle', '添加或修改文章', '[{\"articleContent\":\"## 目录\\n\\n恭喜你已成功运行博客，开启你的文章之旅吧![Wallpaper 22.jpg](http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/aa7e907fc653d0a671df4b0e948cf5c6.jpg)\",\"articleCover\":\"https://static.talkxj.com/articles/3dffb2fcbd541886616ab54c92570de3.jpg\",\"articleTitle\":\"测试文章\",\"categoryName\":\"测试分类\",\"id\":47,\"isComment\":1,\"isTop\":0,\"originalUrl\":\"\",\"status\":1,\"tagNameList\":[],\"type\":1}]', 'POST', '{\"code\":20000,\"flag\":true,\"message\":\"操作成功\"}', 1, '知食分子', '127.0.0.1', '未知来源', '2022-07-24 22:43:40', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_page
-- ----------------------------
DROP TABLE IF EXISTS `tb_page`;
CREATE TABLE `tb_page` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '页面id',
  `page_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '页面名',
  `page_label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '页面标签',
  `page_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '页面封面',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=904 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='页面';

-- ----------------------------
-- Records of tb_page
-- ----------------------------
BEGIN;
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (1, '首页', 'home', 'https://static.talkxj.com/config/0bee7ba5ac70155766648e14ae2a821f.jpg', '2021-08-07 10:32:36', '2021-10-04 15:42:46');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (2, '归档', 'archive', 'https://static.talkxj.com/config/643f28683e1c59a80ccfc9cb19735a9c.jpg', '2021-08-07 10:32:36', '2021-10-04 15:43:14');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (3, '分类', 'category', 'https://static.talkxj.com/config/83be0017d7f1a29441e33083e7706936.jpg', '2021-08-07 10:32:36', '2021-10-04 15:43:31');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (4, '标签', 'tag', 'https://static.talkxj.com/config/a6f141372509365891081d755da963a1.png', '2021-08-07 10:32:36', '2021-10-04 15:43:38');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (5, '相册', 'album', 'https://static.talkxj.com/config/1ecb6fc94e38c38713000efe37492e73.png', '2021-08-07 10:32:36', '2021-10-04 15:43:53');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (6, '友链', 'link', 'https://static.talkxj.com/config/9034edddec5b8e8542c2e61b0da1c1da.jpg', '2021-08-07 10:32:36', '2021-10-04 15:44:02');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (7, '关于', 'about', 'https://static.talkxj.com/config/2a56d15dd742ff8ac238a512d9a472a1.jpg', '2021-08-07 10:32:36', '2021-10-04 15:44:08');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (8, '留言', 'message', 'https://static.talkxj.com/config/acfeab8379508233fa7e4febf90c2f2e.png', '2021-08-07 10:32:36', '2021-10-04 15:44:17');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (9, '个人中心', 'user', 'https://static.talkxj.com/config/ebae4c93de1b286a8d50aa62612caa59.jpeg', '2021-08-07 10:32:36', '2021-10-04 15:45:17');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (10, '文章列表', 'articleList', 'https://static.talkxj.com/config/924d65cc8312e6cdad2160eb8fce6831.jpg', '2021-08-10 15:36:19', '2021-10-04 15:45:45');
INSERT INTO `tb_page` (`id`, `page_name`, `page_label`, `page_cover`, `create_time`, `update_time`) VALUES (903, '说说', 'talk', 'https://static.talkxj.com/config/a741b0656a9a3db2e2ba5c2f4140eb6c.jpg', '2022-03-08 22:55:27', '2022-03-08 22:55:30');
COMMIT;

-- ----------------------------
-- Table structure for tb_photo
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo`;
CREATE TABLE `tb_photo` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` int NOT NULL COMMENT '相册id',
  `photo_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '照片名',
  `photo_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '照片描述',
  `photo_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '照片地址',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='照片';

-- ----------------------------
-- Records of tb_photo
-- ----------------------------
BEGIN;
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (21, 3, '1426913822026870786', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/15022e6e99f21eaf653b694f7762f160.png', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (22, 3, '1426913822026870787', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/91fe114ab1b0fea8969f2a88de22b4f6.png', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (23, 3, '1426913822026870788', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/65a7d7f7e6d89451d3c0609b2a76206a.png', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (24, 3, '1426913822026870789', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/5dbf3ba4f0f4b2c9ddd541af30955bb2.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (25, 3, '1426913822026870790', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/2fdf3d5f2bba02601e6a932cd19f053a.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (26, 3, '1426913822026870791', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/847c98b7e48177fca9c92ec2d130ae94.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (27, 3, '1426913822026870792', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/3ff3d6a85edb2f19d343668d24ed9269.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (28, 3, '1426913822026870793', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/4096cf6b33bc2c42e476bb869b5250a5.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (29, 3, '1426913822026870794', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/0dde82862eb1ab13bf5a641e6dc7bf98.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (30, 3, '1426913822026870795', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/8f0380795feb2a1b2d820aa20ea63081.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (31, 3, '1426913822026870796', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/c79faae41edac5d5bb2bc95109998ed9.jpg', 0, '2021-08-15 22:29:01', NULL);
INSERT INTO `tb_photo` (`id`, `album_id`, `photo_name`, `photo_desc`, `photo_src`, `is_delete`, `create_time`, `update_time`) VALUES (32, 3, '1426913822026870797', NULL, 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/articles/cad547b4c355dd8f3519c087783caecc.jpg', 0, '2021-08-15 22:29:01', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_photo_album
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo_album`;
CREATE TABLE `tb_photo_album` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册名',
  `album_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册描述',
  `album_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册封面',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态值 1公开 2私密',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='相册';

-- ----------------------------
-- Records of tb_photo_album
-- ----------------------------
BEGIN;
INSERT INTO `tb_photo_album` (`id`, `album_name`, `album_desc`, `album_cover`, `is_delete`, `status`, `create_time`, `update_time`) VALUES (3, '精选壁纸', '测试插件', 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/config/02bf8558f9e26b225ed198c6164df49e.jpg', 0, 1, '2021-08-15 22:26:59', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限路径',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方式',
  `parent_id` int DEFAULT NULL COMMENT '父权限id',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否匿名访问 0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
BEGIN;
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (165, '分类模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (166, '博客信息模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (167, '友链模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (168, '文章模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (169, '日志模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (170, '标签模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (171, '照片模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (172, '用户信息模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (173, '用户账号模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (174, '留言模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (175, '相册模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (176, '菜单模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (177, '角色模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (178, '评论模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (179, '资源模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (180, '页面模块', NULL, NULL, NULL, 0, '2021-08-11 21:04:21', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (181, '查看博客信息', '/', 'GET', 166, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:29');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (182, '查看关于我信息', '/about', 'GET', 166, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:29');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (183, '查看后台信息', '/admin', 'GET', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (184, '修改关于我信息', '/admin/about', 'PUT', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (185, '查看后台文章', '/admin/articles', 'GET', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (186, '添加或修改文章', '/admin/articles', 'POST', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (187, '恢复或删除文章', '/admin/articles', 'PUT', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (188, '物理删除文章', '/admin/articles', 'DELETE', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (189, '上传文章图片', '/admin/articles/images', 'POST', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (190, '修改文章置顶', '/admin/articles/top', 'PUT', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (191, '根据id查看后台文章', '/admin/articles/*', 'GET', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (192, '查看后台分类列表', '/admin/categories', 'GET', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (193, '添加或修改分类', '/admin/categories', 'POST', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (194, '删除分类', '/admin/categories', 'DELETE', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (195, '搜索文章分类', '/admin/categories/search', 'GET', 165, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (196, '查询后台评论', '/admin/comments', 'GET', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (197, '删除评论', '/admin/comments', 'DELETE', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (198, '审核评论', '/admin/comments/review', 'PUT', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (199, '查看后台友链列表', '/admin/links', 'GET', 167, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (200, '保存或修改友链', '/admin/links', 'POST', 167, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (201, '删除友链', '/admin/links', 'DELETE', 167, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (202, '查看菜单列表', '/admin/menus', 'GET', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (203, '新增或修改菜单', '/admin/menus', 'POST', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (204, '删除菜单', '/admin/menus/*', 'DELETE', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (205, '查看后台留言列表', '/admin/messages', 'GET', 174, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (206, '删除留言', '/admin/messages', 'DELETE', 174, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (207, '审核留言', '/admin/messages/review', 'PUT', 174, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (208, '查看操作日志', '/admin/operation/logs', 'GET', 169, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (209, '删除操作日志', '/admin/operation/logs', 'DELETE', 169, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (210, '获取页面列表', '/admin/pages', 'GET', 180, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (211, '保存或更新页面', '/admin/pages', 'POST', 180, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (212, '删除页面', '/admin/pages/*', 'DELETE', 180, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (213, '根据相册id获取照片列表', '/admin/photos', 'GET', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (214, '保存照片', '/admin/photos', 'POST', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (215, '更新照片信息', '/admin/photos', 'PUT', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (216, '删除照片', '/admin/photos', 'DELETE', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (217, '移动照片相册', '/admin/photos/album', 'PUT', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (218, '查看后台相册列表', '/admin/photos/albums', 'GET', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (219, '保存或更新相册', '/admin/photos/albums', 'POST', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (220, '上传相册封面', '/admin/photos/albums/cover', 'POST', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (221, '获取后台相册列表信息', '/admin/photos/albums/info', 'GET', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (222, '根据id删除相册', '/admin/photos/albums/*', 'DELETE', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (223, '根据id获取后台相册信息', '/admin/photos/albums/*/info', 'GET', 175, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (224, '更新照片删除状态', '/admin/photos/delete', 'PUT', 171, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (225, '查看资源列表', '/admin/resources', 'GET', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (226, '新增或修改资源', '/admin/resources', 'POST', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (227, '导入swagger接口', '/admin/resources/import/swagger', 'GET', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (228, '删除资源', '/admin/resources/*', 'DELETE', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (229, '保存或更新角色', '/admin/role', 'POST', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (230, '查看角色菜单选项', '/admin/role/menus', 'GET', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (231, '查看角色资源选项', '/admin/role/resources', 'GET', 179, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (232, '查询角色列表', '/admin/roles', 'GET', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (233, '删除角色', '/admin/roles', 'DELETE', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (234, '查询后台标签列表', '/admin/tags', 'GET', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (235, '添加或修改标签', '/admin/tags', 'POST', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (236, '删除标签', '/admin/tags', 'DELETE', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (237, '搜索文章标签', '/admin/tags/search', 'GET', 170, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (238, '查看当前用户菜单', '/admin/user/menus', 'GET', 176, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (239, '查询后台用户列表', '/admin/users', 'GET', 173, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (240, '修改用户禁用状态', '/admin/users/disable', 'PUT', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (241, '查看在线用户', '/admin/users/online', 'GET', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (242, '修改管理员密码', '/admin/users/password', 'PUT', 173, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (243, '查询用户角色选项', '/admin/users/role', 'GET', 177, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (244, '修改用户角色', '/admin/users/role', 'PUT', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (245, '下线用户', '/admin/users/*/online', 'DELETE', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (246, '获取网站配置', '/admin/website/config', 'GET', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (247, '更新网站配置', '/admin/website/config', 'PUT', 166, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (248, '根据相册id查看照片列表', '/albums/*/photos', 'GET', 171, 1, '2021-08-11 21:04:22', '2021-08-11 21:06:35');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (249, '查看首页文章', '/articles', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:45');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (250, '查看文章归档', '/articles/archives', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:47');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (251, '根据条件查询文章', '/articles/condition', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:47');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (252, '搜索文章', '/articles/search', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:48');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (253, '根据id查看文章', '/articles/*', 'GET', 168, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:49');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (254, '点赞文章', '/articles/*/like', 'POST', 168, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (255, '查看分类列表', '/categories', 'GET', 165, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:26');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (256, '查询评论', '/comments', 'GET', 178, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:33');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (257, '添加评论', '/comments', 'POST', 178, 0, '2021-08-11 21:04:22', '2021-08-11 21:10:05');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (258, '评论点赞', '/comments/*/like', 'POST', 178, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (259, '查询评论下的回复', '/comments/*/replies', 'GET', 178, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:30');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (260, '查看友链列表', '/links', 'GET', 167, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:41');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (261, '查看留言列表', '/messages', 'GET', 174, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:14');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (262, '添加留言', '/messages', 'POST', 174, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:15');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (263, '获取相册列表', '/photos/albums', 'GET', 175, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:20');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (264, '用户注册', '/register', 'POST', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:01');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (265, '查询标签列表', '/tags', 'GET', 170, 1, '2021-08-11 21:04:22', '2021-08-11 21:06:30');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (267, '更新用户头像', '/users/avatar', 'POST', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (268, '发送邮箱验证码', '/users/code', 'GET', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:02');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (269, '绑定用户邮箱', '/users/email', 'POST', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (270, '更新用户信息', '/users/info', 'PUT', 172, 0, '2021-08-11 21:04:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (271, 'qq登录', '/users/oauth/qq', 'POST', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:06');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (272, '微博登录', '/users/oauth/weibo', 'POST', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:06');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (273, '修改密码', '/users/password', 'PUT', 173, 1, '2021-08-11 21:04:22', '2021-08-11 21:07:09');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (274, '上传语音', '/voice', 'POST', 166, 1, '2021-08-11 21:04:22', '2021-08-11 21:05:33');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (275, '监控模块', NULL, NULL, NULL, 0, '2021-08-14 15:41:29', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (276, '系统监控', '/admin/monitor/system', 'GET', 275, 0, '2021-08-14 15:42:00', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (277, 'redis监控', '/admin/monitor/redis', 'GET', 275, 0, '2021-11-19 20:51:01', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (278, '说说模块', NULL, NULL, NULL, 0, '2022-01-24 01:29:13', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (279, '查看首页说说', '/home/talks', 'GET', 278, 1, '2022-01-24 01:29:29', '2022-01-24 01:31:56');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (280, '查看说说列表', '/talks', 'GET', 278, 1, '2022-01-24 01:29:52', '2022-01-24 01:31:56');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (281, '根据id查看说说', '/talks/*', 'GET', 278, 1, '2022-01-24 01:30:10', '2022-01-24 01:31:57');
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (282, '点赞说说', '/talks/*/like', 'POST', 278, 0, '2022-01-24 01:30:30', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (283, '上传说说图片', '/admin/talks/images', 'POST', 278, 0, '2022-01-24 01:30:46', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (284, '保存或修改说说', '/admin/talks', 'POST', 278, 0, '2022-01-24 01:31:04', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (285, '删除说说', '/admin/talks', 'DELETE', 278, 0, '2022-01-24 01:31:22', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (286, '查看后台说说', '/admin/talks', 'GET', 278, 0, '2022-01-24 01:31:38', NULL);
INSERT INTO `tb_resource` (`id`, `resource_name`, `url`, `request_method`, `parent_id`, `is_anonymous`, `create_time`, `update_time`) VALUES (287, '根据id查看后台说说', '/admin/talks/*', 'GET', 278, 0, '2022-01-24 01:31:53', '2022-01-24 01:33:14');
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `role_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用  0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` (`id`, `role_name`, `role_label`, `is_disable`, `create_time`, `update_time`) VALUES (1, '管理员', 'admin', 0, '2021-03-22 14:10:21', '2022-03-08 22:18:45');
INSERT INTO `tb_role` (`id`, `role_name`, `role_label`, `is_disable`, `create_time`, `update_time`) VALUES (2, '用户', 'user', 0, '2021-03-22 14:25:25', '2021-08-11 21:12:21');
INSERT INTO `tb_role` (`id`, `role_name`, `role_label`, `is_disable`, `create_time`, `update_time`) VALUES (3, '测试', 'test', 0, '2021-03-22 14:42:23', '2021-08-11 21:17:27');
COMMIT;

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `menu_id` int DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2411 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1397, 8, 1);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1398, 8, 2);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1399, 8, 6);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1400, 8, 7);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1401, 8, 8);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1402, 8, 9);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1403, 8, 10);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1404, 8, 3);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1405, 8, 11);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1406, 8, 12);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1407, 8, 202);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1408, 8, 13);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1409, 8, 14);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1410, 8, 201);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1411, 8, 4);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1412, 8, 16);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1413, 8, 15);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1414, 8, 17);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1415, 8, 18);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1416, 8, 19);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1417, 8, 20);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1418, 8, 5);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1595, 9, 1);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1596, 9, 2);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1597, 9, 6);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1598, 9, 7);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1599, 9, 8);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1600, 9, 9);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1601, 9, 10);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1602, 9, 3);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1603, 9, 11);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1604, 9, 12);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1605, 9, 202);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1606, 9, 13);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1607, 9, 14);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1608, 9, 201);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1609, 9, 4);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1610, 9, 16);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1611, 9, 15);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1612, 9, 17);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1613, 9, 18);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1614, 9, 19);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1615, 9, 20);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1616, 9, 5);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1639, 13, 2);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1640, 13, 6);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1641, 13, 7);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1642, 13, 8);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1643, 13, 9);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1644, 13, 10);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1645, 13, 3);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1646, 13, 11);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1647, 13, 12);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2281, 3, 1);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2282, 3, 2);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2283, 3, 6);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2284, 3, 7);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2285, 3, 8);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2286, 3, 9);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2287, 3, 10);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2288, 3, 3);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2289, 3, 11);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2290, 3, 12);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2291, 3, 202);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2292, 3, 13);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2293, 3, 201);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2294, 3, 213);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2295, 3, 14);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2296, 3, 15);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2297, 3, 16);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2298, 3, 4);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2299, 3, 214);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2300, 3, 209);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2301, 3, 17);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2302, 3, 18);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2303, 3, 205);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2304, 3, 206);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2305, 3, 208);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2306, 3, 210);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2307, 3, 19);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2308, 3, 20);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2309, 3, 5);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2375, 1, 1);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2376, 1, 2);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2377, 1, 6);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2378, 1, 7);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2379, 1, 8);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2380, 1, 9);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2381, 1, 10);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2382, 1, 3);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2383, 1, 11);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2384, 1, 12);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2385, 1, 202);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2386, 1, 13);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2387, 1, 201);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2388, 1, 213);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2389, 1, 14);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2390, 1, 15);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2391, 1, 16);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2392, 1, 4);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2393, 1, 214);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2394, 1, 209);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2395, 1, 17);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2396, 1, 18);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2397, 1, 205);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2398, 1, 206);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2399, 1, 208);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2400, 1, 210);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2401, 1, 218);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2402, 1, 219);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2403, 1, 220);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2404, 1, 221);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2405, 1, 19);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2406, 1, 20);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2407, 1, 215);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2408, 1, 216);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2409, 1, 217);
INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2410, 1, 5);
COMMIT;

-- ----------------------------
-- Table structure for tb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `resource_id` int DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4263 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4011, 2, 254);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4012, 2, 267);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4013, 2, 269);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4014, 2, 270);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4015, 2, 257);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4016, 2, 258);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4048, 3, 192);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4049, 3, 195);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4050, 3, 183);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4051, 3, 246);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4052, 3, 199);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4053, 3, 185);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4054, 3, 191);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4055, 3, 254);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4056, 3, 208);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4057, 3, 234);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4058, 3, 237);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4059, 3, 213);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4060, 3, 241);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4061, 3, 239);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4062, 3, 205);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4063, 3, 218);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4064, 3, 223);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4065, 3, 202);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4066, 3, 230);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4067, 3, 238);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4068, 3, 232);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4069, 3, 243);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4070, 3, 196);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4071, 3, 257);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4072, 3, 258);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4073, 3, 225);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4074, 3, 231);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4075, 3, 210);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4166, 1, 165);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4167, 1, 192);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4168, 1, 193);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4169, 1, 194);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4170, 1, 195);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4171, 1, 166);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4172, 1, 183);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4173, 1, 184);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4174, 1, 246);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4175, 1, 247);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4176, 1, 167);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4177, 1, 199);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4178, 1, 200);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4179, 1, 201);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4180, 1, 168);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4181, 1, 185);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4182, 1, 186);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4183, 1, 187);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4184, 1, 188);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4185, 1, 189);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4186, 1, 190);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4187, 1, 191);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4188, 1, 254);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4189, 1, 169);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4190, 1, 208);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4191, 1, 209);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4192, 1, 170);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4193, 1, 234);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4194, 1, 235);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4195, 1, 236);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4196, 1, 237);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4197, 1, 171);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4198, 1, 213);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4199, 1, 214);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4200, 1, 215);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4201, 1, 216);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4202, 1, 217);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4203, 1, 224);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4204, 1, 172);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4205, 1, 240);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4206, 1, 241);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4207, 1, 244);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4208, 1, 245);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4209, 1, 267);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4210, 1, 269);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4211, 1, 270);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4212, 1, 173);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4213, 1, 239);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4214, 1, 242);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4215, 1, 174);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4216, 1, 205);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4217, 1, 206);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4218, 1, 207);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4219, 1, 175);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4220, 1, 218);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4221, 1, 219);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4222, 1, 220);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4223, 1, 221);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4224, 1, 222);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4225, 1, 223);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4226, 1, 176);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4227, 1, 202);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4228, 1, 203);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4229, 1, 204);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4230, 1, 230);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4231, 1, 238);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4232, 1, 177);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4233, 1, 229);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4234, 1, 232);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4235, 1, 233);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4236, 1, 243);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4237, 1, 178);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4238, 1, 196);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4239, 1, 197);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4240, 1, 198);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4241, 1, 257);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4242, 1, 258);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4243, 1, 179);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4244, 1, 225);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4245, 1, 226);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4246, 1, 227);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4247, 1, 228);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4248, 1, 231);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4249, 1, 180);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4250, 1, 210);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4251, 1, 211);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4252, 1, 212);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4253, 1, 275);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4254, 1, 276);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4255, 1, 277);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4256, 1, 278);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4257, 1, 282);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4258, 1, 283);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4259, 1, 284);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4260, 1, 285);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4261, 1, 286);
INSERT INTO `tb_role_resource` (`id`, `role_id`, `resource_id`) VALUES (4262, 1, 287);
COMMIT;

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
BEGIN;
INSERT INTO `tb_tag` (`id`, `tag_name`, `create_time`, `update_time`) VALUES (27, '测试标签2', '2021-08-12 15:50:57', '2021-08-14 11:30:42');
COMMIT;

-- ----------------------------
-- Table structure for tb_talk
-- ----------------------------
DROP TABLE IF EXISTS `tb_talk`;
CREATE TABLE `tb_talk` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '说说id',
  `user_id` int NOT NULL COMMENT '用户id',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '说说内容',
  `images` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1.公开 2.私密',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_talk
-- ----------------------------
BEGIN;
INSERT INTO `tb_talk` (`id`, `user_id`, `content`, `images`, `is_top`, `status`, `create_time`, `update_time`) VALUES (49, 1, '测试说说<img src=\"https://static.talkxj.com/emoji/goutou.jpg\" width=\"24\" height=\"24\" alt=\"[狗头]\" style=\"margin: 0 1px;vertical-align: text-bottom\">', NULL, 0, 1, '2022-01-24 23:34:59', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_unique_view
-- ----------------------------
DROP TABLE IF EXISTS `tb_unique_view`;
CREATE TABLE `tb_unique_view` (
  `id` int NOT NULL AUTO_INCREMENT,
  `views_count` int NOT NULL COMMENT '访问量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_unique_view
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_info_id` int NOT NULL COMMENT '用户信息id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `login_type` tinyint(1) NOT NULL COMMENT '登录类型',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_auth` (`id`, `user_info_id`, `username`, `password`, `login_type`, `ip_address`, `ip_source`, `create_time`, `update_time`, `last_login_time`) VALUES (1, 1, 'admin@qq.com', '$2a$10$AkxkZaqcxEXdiNE1nrgW1.ms3aS9C5ImXMf8swkWUJuFGMqDl.TPW', 1, '127.0.0.1', '未知来源', '2021-08-12 15:43:18', '2022-07-24 22:25:42', '2022-07-24 22:25:42');
INSERT INTO `tb_user_auth` (`id`, `user_info_id`, `username`, `password`, `login_type`, `ip_address`, `ip_source`, `create_time`, `update_time`, `last_login_time`) VALUES (552, 562, '1152831376@qq.com', '$2a$10$uCecpOOvCkVKZxeWw4InTuyoSi0G5L7/2wuVpwnQGwboM52ufapkO', 1, '127.0.0.1', '', '2021-11-22 22:33:45', '2022-03-10 21:12:13', '2022-03-10 21:12:13');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱号',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户简介',
  `web_site` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '个人网站',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=563 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_info` (`id`, `email`, `nickname`, `avatar`, `intro`, `web_site`, `is_disable`, `create_time`, `update_time`) VALUES (1, 'admin@qq.com', '知食分子', 'http://wzl-myblog.oss-cn-beijing.aliyuncs.com/avatar/9992294ba1ffe3811f3d586b4415721e.jpg', '爱学习', 'http://www.istudy.love', 0, '2021-08-12 15:43:17', '2021-08-14 14:13:34');
INSERT INTO `tb_user_info` (`id`, `email`, `nickname`, `avatar`, `intro`, `web_site`, `is_disable`, `create_time`, `update_time`) VALUES (562, '1152831376@qq.com', '用户1462791412289433601', 'https://static.talkxj.com/avatar/user.png', NULL, NULL, 0, '2021-11-22 22:33:45', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (577, 1, 1);
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (578, 562, 2);
COMMIT;

-- ----------------------------
-- Table structure for tb_website_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_website_config`;
CREATE TABLE `tb_website_config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `config` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '配置信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_website_config
-- ----------------------------
BEGIN;
INSERT INTO `tb_website_config` (`id`, `config`, `create_time`, `update_time`) VALUES (1, '{\"alipayQRCode\":\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/config/a51f2fe3b9cca5fa53b231392511e934.png\",\"gitee\":\"https://gitee.com/feng_meiyu\",\"github\":\"https://github.com/X1192176811\",\"isChatRoom\":1,\"isCommentReview\":0,\"isEmailNotice\":0,\"isMessageReview\":0,\"isMusicPlayer\":0,\"isReward\":1,\"qq\":\"1192176811\",\"socialLoginList\":[\"qq\",\"weibo\",\"gitee\"],\"socialUrlList\":[\"qq\",\"github\",\"gitee\"],\"touristAvatar\":\"https://static.talkxj.com/photos/0bca52afdb2b9998132355d716390c9f.png\",\"websiteAuthor\":\"知食分子\",\"websiteAvatar\":\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/config/ebfd2445dd353022186aa5c31d01ec9a.jpg\",\"websiteCreateTime\":\"2021-11-17\",\"websiteIntro\":\"网站简介\",\"websiteName\":\"个人博客\",\"websiteNotice\":\"请前往后台管理修改博客信息\",\"websiteRecordNo\":\"备案号\",\"websocketUrl\":\"ws://127.0.0.1:8080/websocket\",\"weiXinQRCode\":\"http://wzl-myblog.oss-cn-beijing.aliyuncs.com/config/e5cc10c6a931f527c0a42fca34585cfe.png\"}', '2021-08-09 19:37:30', '2022-06-12 19:20:46');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
