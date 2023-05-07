create
database  urule charset=utf8mb4;
use
urule;
DROP TABLE IF EXISTS URULE_FILE;
create table URULE_FILE
(
    ID_             INT PRIMARY KEY                    not null, -- 主键
    NAME_           VARCHAR(255)                       not null, -- 文件名称
    TYPE_           VARCHAR(30)                        not null, -- 文件类型，ruleset,scorecard,decisioncard...
    LATEST_VERSION_ VARCHAR(30) null,                            -- 当前文件对应最大版本号
    PACKAGE_ID_     INT      default 0                 not null, -- 所属目录
    PROJECT_ID_     INT      default 0                 not null, -- 所属项目
    CONTENT_        LONGTEXT                           not null, -- 规则内容
    DIGEST_         VARCHAR(32) null,                            -- 摘要
    LOCKED_USER_    VARCHAR(255),                                -- 文件锁定人
    CREATE_USER_    VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_    VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_    DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_    DATETIME default CURRENT_TIMESTAMP not null, -- 更新日期
    DELETED_        TINYINT  default 0                 not null  -- 删除标记
) comment '文件';

DROP TABLE IF EXISTS URULE_VERSION_FILE;
create table URULE_VERSION_FILE
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    NAME_        VARCHAR(255)                       not null, -- 文件名称
    FILE_ID_     INT                                not null, -- 当前版本文件所属文件
    PROJECT_ID_  INT                                not null, -- 项目编号
    VERSION_     VARCHAR(30)                        not null, -- 当前文件版本号
    CONTENT_     LONGTEXT                           not null, -- 规则内容
    DIGEST_      VARCHAR(32) null,                            -- 摘要
    NOTE_        VARCHAR(255),                                -- 版本备注
    CREATE_USER_ VARCHAR(255),                                -- 文件创建人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 新建日期
) comment '版本文件';

DROP TABLE IF EXISTS URULE_PACKAGE;
create table URULE_PACKAGE
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    NAME_        VARCHAR(255)                       not null, -- 目录名称
    TYPE_        VARCHAR(255)                       not null, -- 目录类型
    PARENT_ID_   INT      default 0                 not null, -- 所属目录
    PROJECT_ID_  INT      default 0                 not null, -- 所属项目
    CREATE_USER_ VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 更新日期
    DELETED_     TINYINT  default 0                 not null  -- 删除标记
) comment '目录';

DROP TABLE IF EXISTS URULE_PROJECT;
create table URULE_PROJECT
(
    ID_                   INT PRIMARY KEY                    not null, -- 主键
    NAME_                 VARCHAR(255)                       not null, -- 项目名称
    DESC_                 VARCHAR(255) null,                           -- 项目备注
    TYPE_                 VARCHAR(50)                        not null, -- 项目类型
    GROUP_ID_             VARCHAR(50)                        not null, -- 所属团队ID
    APPROVE_USER_ENABLE_  VARCHAR(50)                        not null, -- 启用审批用户
    APPROVE_USER_DISABLE_ VARCHAR(50)                        not null, -- 禁用审批用户
    APPROVE_USER_DEPLOY_  VARCHAR(50)                        not null, -- 发布审批用户
    CREATE_USER_          VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_          VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_          DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_          DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '项目表';


DROP TABLE IF EXISTS URULE_URL_CONFIG;
create table URULE_URL_CONFIG
(
    ID_          INT PRIMARY KEY                     not null, -- 主键
    NAME_        VARCHAR(255)                        not null, -- 名称
    URL_         VARCHAR(255)                        not null, -- URL
    TYPE_        VARCHAR(10)                         not null, -- 类型：cluster,client
    GROUP_ID_    VARCHAR(50)                         not null, -- 所属团队ID
    CREATE_USER_ VARCHAR(255),                                 -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                 -- 文件更新人
    CREATE_DATE_ TIMESTAMP default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ TIMESTAMP default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '集群服务器及客户端URL配置表';


DROP TABLE IF EXISTS URULE_DYNAMIC_JAR;
create table URULE_DYNAMIC_JAR
(
    ID_          INT PRIMARY KEY                     not null, -- 主键
    DESC_        VARCHAR(255)                        not null, -- 描述
    NAME_        VARCHAR(255),                                 -- jar文件名
    GROUP_ID_    VARCHAR(50)                         not null, -- 所属团队ID
    JAR_         MEDIUMBLOB,                                   -- 具体jar文件
    CREATE_USER_ VARCHAR(255),                                 -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                 -- 文件更新人
    CREATE_DATE_ TIMESTAMP default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ TIMESTAMP default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '动态JAR文件存储表';

DROP TABLE IF EXISTS URULE_PACKET;
create table URULE_PACKET
(
    ID_                     INT PRIMARY KEY                    not null, -- 主键
    PROJECT_ID_             INT      default 0                 not null, -- 所属项目
    NAME_                   VARCHAR(255)                       not null, -- 知识包名称
    DESC_                   VARCHAR(255)                       not null, -- 知识包描述
    INPUT_DATA_             LONGTEXT,                                    -- 快速测试时留下的JSON格式输入数据
    OUTPUT_DATA_            LONGTEXT,                                    -- 快速测试时留下的JSON格式输出数据
    AUDIT_ENABLE_           BOOLEAN  default false             not null, -- 调用当前知识包是否需要调用信息
    AUDIT_INPUT_            LONGTEXT,                                    -- 调用当前知识包记录下的JSON格式输入数据
    AUDIT_OUTPUT_           LONGTEXT,                                    -- 调用当前知识包记录下的JSON格式输出数据
    REST_ENABLE_            BOOLEAN  default false             not null, -- REST服务是否启用
    REST_SECURITY_ENABLE_   BOOLEAN  default false             not null, -- REST服务是否需要安全验证
    REST_SECURITY_USER_     VARCHAR(255),                                -- REST服务需要安全验证的用户名
    REST_SECURITY_PASSWORD_ VARCHAR(255),                                -- REST服务需要安全验证的密码
    REST_INPUT_             LONGTEXT,                                    -- REST服务需要的JSON格式输入数据
    REST_OUTPUT_            LONGTEXT,                                    -- REST服务需要的JSON格式输出数据
    CREATE_USER_            VARCHAR(255),                                -- 创建人
    UPDATE_USER_            VARCHAR(255),                                -- 更新人
    CREATE_DATE_            DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_            DATETIME default CURRENT_TIMESTAMP not null, -- 更新日期
    ENABLE_                 BOOLEAN  default false             not null  -- 启用标记
) comment '知识包定义主表';

DROP TABLE IF EXISTS URULE_PACKET_FILE;
create table URULE_PACKET_FILE
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    PACKET_ID_   INT      default 0                 not null, -- 所属知识包
    PROJECT_ID_  INT                                not null, -- 项目编号
    FILE_ID_     INT      default 0                 not null, -- 关联到的文件ID
    PATH_        VARCHAR(255)                       not null, -- 关联文件的路径
    VERSION_     VARCHAR(30),                                 -- 关联到的文件版本号
    DESC_        VARCHAR(255),                                -- 描述
    CREATE_USER_ VARCHAR(255),                                -- 创建人
    UPDATE_USER_ VARCHAR(255),                                -- 更新人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '知识包定义从表，文件表';

DROP TABLE IF EXISTS URULE_DEPLOYED_PACKET;
create table URULE_DEPLOYED_PACKET
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    PACKET_ID_   INT      default 0                 not null, -- 所属知识包
    PROJECT_ID_  INT                                not null, -- 项目编号
    DESC_        VARCHAR(255)                       not null, -- 描述
    APPLY_ID_    INT      default 0                 not null, -- 发布此知识包的审批流ID
    CONTENT_     LONGTEXT                           not null, -- 编译后的知识包内容
    DIGEST_      VARCHAR(32) null,                            -- 知识包内容摘要
    VERSION_     VARCHAR(30)                        not null, -- 发布的知识包版本号
    STATUS_      VARCHAR(30)                        not null, -- 当前发布的知识包审批状态
    ENABLE_      BOOLEAN  default false             not null, -- 启用标记
    CREATE_USER_ VARCHAR(255),                                -- 创建人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 新建日期
) comment '已发布知识包主表';

DROP TABLE IF EXISTS URULE_DEPLOYED_PACKET_FILE;
create table URULE_DEPLOYED_PACKET_FILE
(
    ID_                 INT PRIMARY KEY                    not null, -- 主键
    DEPLOYED_PACKET_ID_ INT      default 0                 not null, -- 所属发布知识包ID
    PROJECT_ID_         INT                                not null, -- 项目编号
    FILE_ID_            INT      default 0                 not null, -- 原文件ID
    DIGEST_             VARCHAR(32)                        not null, -- 摘要
    PATH_               VARCHAR(255)                       not null, -- 文件路径
    VERSION_            VARCHAR(30),                                 -- 文件版本号
    CONTENT_            LONGTEXT                           not null, -- 文件内容
    CREATE_USER_        VARCHAR(255),                                -- 创建人
    CREATE_DATE_        DATETIME default CURRENT_TIMESTAMP not null  -- 新建日期
) comment '已发布知识包从表，记录包含的文件快照';

DROP TABLE IF EXISTS URULE_PACKET_APPLY;
create table URULE_PACKET_APPLY
(
    ID_                 INT PRIMARY KEY                    not null, -- 主键
    PACKET_ID_          INT      default 0                 not null, -- 所属知识包ID
    DEPLOYED_PACKET_ID_ INT      default 0                 not null, -- 所属发布知识包ID
    PROJECT_ID_         INT                                not null, -- 项目编号
    TYPE_               VARCHAR(30)                        not null, -- 申请类型，enable:表示知识包启用申请；deploy:表示知识包发布申请
    TITLE_              VARCHAR(255)                       not null, -- 审核标题
    DESC_               VARCHAR(255)                       not null, -- 审核描述
    APPROVER_           VARCHAR(255),                                -- 审核人
    STATUS_             VARCHAR(30)                        not null, -- 审核结果，pass-通过,reject-驳回,fail-不通过
    CREATE_USER_        VARCHAR(255),                                -- 创建人
    CREATE_DATE_        DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_        DATETIME default CURRENT_TIMESTAMP not null  -- 新建日期
) comment '发布知识包审核主表';

DROP TABLE IF EXISTS URULE_PACKET_APPLY_DETAIL;
create table URULE_PACKET_APPLY_DETAIL
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    APPLY_ID_    INT      default 0                 not null, -- 所属申请项ID
    PROJECT_ID_  INT                                not null, -- 项目编号
    DESC_        VARCHAR(255)                       not null, -- 审核描述
    CREATE_USER_ VARCHAR(255),                                -- 创建人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 新建日期
) comment '发布知识包审核从表，记录审核过程';


DROP TABLE IF EXISTS URULE_PACKET_SCENARIO;
create table URULE_PACKET_SCENARIO
(
    ID_              INT PRIMARY KEY                    not null, -- 主键
    PACKET_ID_       INT                                not null, -- 所属知识包ID
    PROJECT_ID_      INT                                not null, -- 项目编号
    NAME_            VARCHAR(255)                       not null, -- 名称
    DESC_            VARCHAR(255)                       not null, -- 描述
    EXCEL_FILE_      MEDIUMBLOB,                                  -- 上传的Excel文件
    EXCEL_FILE_NAME_ VARCHAR(255),                                -- 上传的Excel文件名
    INPUT_DATA_      MEDIUMTEXT,                                  -- JSON格式输入数据
    OUTPUT_DATA_     MEDIUMTEXT,                                  -- JSON格式输出数据
    CREATE_USER_     VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_     VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_     DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_     DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '场景批量测试';

DROP TABLE IF EXISTS URULE_PROPERTY;
create table URULE_PROPERTY
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    KEY_         VARCHAR(255)                       not null, -- 配置键
    VALUE_       VARCHAR(255)                       not null, -- 键值
    LABEL_       VARCHAR(255)                       not null, -- 说明
    TYPE_        VARCHAR(50)                        not null, -- 类型 system,custom
    CREATE_USER_ VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment 'URULE属性表';

DROP TABLE IF EXISTS URULE_GROUP;
create table URULE_GROUP
(
    ID_          VARCHAR(50) PRIMARY KEY            not null, -- 主键
    NAME_        VARCHAR(255)                       not null, -- 团队名称
    DESC_        VARCHAR(255) null,                           -- 备注
    CREATE_USER_ VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '团队';

DROP TABLE IF EXISTS URULE_GROUP_ROLE;
create table URULE_GROUP_ROLE
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    NAME_        VARCHAR(255)                       not null, -- 团队用户组
    TYPE_        VARCHAR(50)                        not null, -- 类型system,custom
    GROUP_ID_    VARCHAR(50)                        not null, -- 团队编号
    CREATE_USER_ VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '团队角色';


DROP TABLE IF EXISTS URULE_USER;
create table URULE_USER
(
    ID_          VARCHAR(50) PRIMARY KEY            not null, -- 账号ID,主键
    NAME_        VARCHAR(255)                       not null, -- 用户名
    PASSWORD_    VARCHAR(255)                       not null, -- 密码
    EMAIL_       VARCHAR(255) null,                           -- 邮件
    SECRET_KEY_  VARCHAR(6) null,                             -- 密码找回密钥
    DESC_        VARCHAR(255) null,                           -- 备注
    CREATE_USER_ VARCHAR(255) null,                           -- 创建人
    UPDATE_USER_ VARCHAR(255) null,                           -- 更新人
    EXPIR_DATE_  DATETIME default CURRENT_TIMESTAMP null,     -- 密码找回过期时间
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '用户';

DROP TABLE IF EXISTS URULE_GROUP_USER;
create table URULE_GROUP_USER
(
    ID_          INT PRIMARY KEY                     not null, -- 主键
    USER_ID_     VARCHAR(50)                         not null, -- 用户
    USER_NAME_   VARCHAR(255)                        not null, -- 用户名
    GROUP_ID_    VARCHAR(50)                         not null, -- 团队
    CREATE_DATE_ TIMESTAMP default CURRENT_TIMESTAMP not null  -- 加入日期
) comment '团队用户';

DROP TABLE IF EXISTS URULE_PROJECT_USER;
create table URULE_PROJECT_USER
(
    ID_          INT PRIMARY KEY                     not null, -- 主键
    USER_ID_     VARCHAR(50)                         not null, -- 用户
    USER_NAME_   VARCHAR(255)                        not null, -- 用户名
    PROJECT_ID_  INT                                 not null, -- 项目
    CREATE_DATE_ TIMESTAMP default CURRENT_TIMESTAMP not null  -- 加入日期
) comment '项目用户';

DROP TABLE IF EXISTS URULE_PROJECT_ROLE;
create table URULE_PROJECT_ROLE
(
    ID_          INT PRIMARY KEY                    not null, -- 主键
    NAME_        VARCHAR(255)                       not null, -- 项目用户组
    DESC_        VARCHAR(255) null,                           -- 备注
    PROJECT_ID_  INT                                not null, -- 所属项目
    TYPE_        VARCHAR(50)                        not null, -- 类型system,custom
    CREATE_USER_ VARCHAR(255),                                -- 文件创建人
    UPDATE_USER_ VARCHAR(255),                                -- 文件更新人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null, -- 新建日期
    UPDATE_DATE_ DATETIME default CURRENT_TIMESTAMP not null  -- 更新日期
) comment '项目角色';

DROP TABLE IF EXISTS URULE_PROJECT_USER_ROLE;
create table URULE_PROJECT_USER_ROLE
(
    ID_         INT PRIMARY KEY not null, -- 主键
    PROJECT_ID_ INT             not null, -- 项目
    USER_ID_    VARCHAR(50)     not null, -- 用户
    ROLE_ID_    INT             not null  -- 角色ID
) comment '用户项目角色关系表';

DROP TABLE IF EXISTS URULE_GROUP_USER_ROLE;
create table URULE_GROUP_USER_ROLE
(
    ID_       INT PRIMARY KEY not null, -- 主键
    GROUP_ID_ VARCHAR(50)     not null, -- 团队
    USER_ID_  VARCHAR(50)     not null, -- 用户
    ROLE_ID_  INT             not null  -- 角色ID
) comment '用户团队角色关系表';

DROP TABLE IF EXISTS URULE_INVITE;
create table URULE_INVITE
(
    ID_          INT PRIMARY KEY not null,                -- 主键
    GROUP_ID_    VARCHAR(50)     not null,                -- 团队编号
    TYPE_        VARCHAR(50)     not null,                -- 类型,可以为TIME, DISPOSABLE
    SECRET_KEY_  VARCHAR(6) null,                         -- 密钥
    EXPIR_DATE_  DATETIME default CURRENT_TIMESTAMP null, -- 过期时间
    CREATE_USER_ VARCHAR(255),                            -- 创建人
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP null  -- 创建日期
) comment '邀请表';

DROP TABLE IF EXISTS URULE_AUTHORITY;
create table URULE_AUTHORITY
(
    ID_            INT PRIMARY KEY   not null, -- 主键
    ROLE_ID_       INT               not null, -- 角色ID
    ROLE_TYPE_     VARCHAR(50)       not null, -- 角色类型,可以为GROUP, PROJECT
    RESOURCE_CODE_ VARCHAR(50)       not null, -- 资源编码
    RESOURCE_TYPE_ VARCHAR(50)       not null, -- 资源类型,可以为:URL, RULE_FILE, FUNC
    AUTH_          TINYINT default 1 not null  -- 授权信息
) comment '角色授权表';

DROP TABLE IF EXISTS URULE_LOG_USERLOGIN;
create table URULE_LOG_USERLOGIN
(
    ID_          INT PRIMARY KEY not null,               -- 主键
    USER_ID_     VARCHAR(50)     not null,               -- 用户账号
    USER_NAME_   VARCHAR(50)     not null,               -- 用户名
    GROUP_ID_    VARCHAR(50) null,                       -- 团队编号
    IP_          VARCHAR(50) null,                       -- 登录地址
    USER_AGENT_  VARCHAR(255) null,                      -- 用户代理
    CREATE_DATE_ DATETIME default CURRENT_TIMESTAMP null -- 创建时间
) comment '登录日志表';

DROP TABLE IF EXISTS URULE_LOG_OPERATION;
create table URULE_LOG_OPERATION
(
    ID_           INT PRIMARY KEY not null,               -- 主键
    USER_ID_      VARCHAR(50)     not null,               -- 用户账号
    USER_NAME_    VARCHAR(50)     not null,               -- 用户名
    GROUP_ID_     VARCHAR(50)     not null,               -- 团队编号
    GROUP_NAME_   VARCHAR(255) null,                      -- 团队名称
    PROJECT_ID_   INT null,                               -- 项目编号
    PROJECT_NAME_ VARCHAR(255) null,                      -- 项目名称
    ITEM_ID_      VARCHAR(50) null,                       -- 业务对象编号
    CATEGORY_     VARCHAR(50) null,                       -- 模块
    ACTION_       VARCHAR(100) null,                      -- 动作
    CONTENT_      VARCHAR(255) null,                      -- 操作内容
    CREATE_DATE_  DATETIME default CURRENT_TIMESTAMP null -- 创建时间
) comment '操作日志表';

DROP TABLE IF EXISTS URULE_LOG_KNOWLEDGE;
create table URULE_LOG_KNOWLEDGE
(
    ID_             INT PRIMARY KEY not null,                -- 主键
    USER_           VARCHAR(50) null,                        -- 执行用户
    IP_             VARCHAR(50) null,                        -- 登录地址
    USER_AGENT_     VARCHAR(255) null,                       -- 用户代理
    KNOWLEDGE_ID_   INT null,                                -- 知识包ID
    KNOWLEDGE_NAME_ VARCHAR(100) null,                       -- 知识包Name
    VERSION_        VARCHAR(30) null,                        -- 版本号
    IN_PARAMS_      LONGTEXT null,                           -- 输入参数
    OUT_PARAMS_     LONGTEXT null,                           -- 输出参数
    LOGS_           LONGTEXT null,                           -- 执行日志
    START_TIME_     DATETIME default CURRENT_TIMESTAMP null, -- 调用开始时间
    END_TIME_       DATETIME default CURRENT_TIMESTAMP null, -- 调用结束时间
    TIME_           INT null,                                -- 耗时
    GROUP_ID_       VARCHAR(50)     not null,                -- 团队编号
    GROUP_NAME_     VARCHAR(255) null,                       -- 团队名称
    PROJECT_ID_     INT             not null,                -- 项目编号
    PROJECT_NAME_   VARCHAR(255) null,                       -- 项目名称
    CREATE_DATE_    DATETIME default CURRENT_TIMESTAMP null  -- 创建时间
) comment '规则执行日志表';


insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (1, 'DEFAULT.dbid', 0, '默认ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (2, 'URULE_PROPERTY.dbid', 1000, '属性ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (3, 'URULE_GROUP_ROLE.dbid', 0, '团队角色ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (4, 'URULE_GROUP_USER.dbid', 0, '团队用户ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (5, 'URULE_GROUP_USER_ROLE.dbid', 0, '团队角色用户ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (6, 'URULE_PROJECT.dbid', 0, '项目ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (7, 'URULE_PROJECT_ROLE.dbid', 0, '项目角色ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (8, 'URULE_PROJECT_USER.dbid', 0, '项目用户ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (9, 'URULE_PROJECT_USER_ROLE.dbid', 0, '项目角色用户ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (10, 'URULE_INVITE.dbid', 0, '团队邀请ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (11, 'URULE_AUTHORITY.dbid', 0, '权限ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (12, 'URULE_LOG_USERLOGIN.dbid', 0, '登录日志ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (13, 'URULE_LOG_OPERATION.dbid', 0, '操作日志ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (14, 'URULE_LOG_KNOWLEDGE.dbid', 0, '知识包执行日志ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (51, 'URULE_FILE.dbid', 0, '规则文件ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (53, 'URULE_PACKET.dbid', 0, '知识包ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (54, 'URULE_PACKET_FILE.dbid', 0, '知识包文件ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (55, 'URULE_DEPLOYED_PACKET.dbid', 0, '已发布知识包ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (56, 'URULE_DEPLOYED_PACKET_FILE.dbid', 0, '已发布知识包文件ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (57, 'URULE_PACKET_APPLY.dbid', 0, '审批ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (58, 'URULE_PACKET_APPLY_DETAIL.dbid', 0, '审批明细ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (59, 'URULE_PACKET_SCENARIO.dbid', 0, '知识包测试ID', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (60, 'URULE_URL_CONFIG.dbid', 0, 'URL配置', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (61, 'URULE_DYNAMIC_JAR.dbid', 0, '动态JAR文件', 'system');

insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (101, 'urule.application.title', 'URule Console Pro 4', 'Application Title', 'system');

insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (201, 'urule.security.useConservativeStrategy', 'false', '是否启用权限验证的保守策略', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (301, 'urule.mail.smtp.host', 'smtp.163.com', 'SMTP服务器', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (302, 'urule.mail.smtp.port', '25', 'SMTP端口', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (303, 'urule.mail.smtp.auth', 'true', '需要验证用户名密码', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (304, 'urule.mail.smtp.user', 'urule@163.com', '发件人', 'system');
insert into URULE_PROPERTY (ID_, KEY_, VALUE_, LABEL_, TYPE_)
values (305, 'urule.mail.smtp.pass', 'urule', '授权码', 'system');
