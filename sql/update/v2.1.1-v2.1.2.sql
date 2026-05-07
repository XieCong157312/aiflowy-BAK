SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `tb_wiki`
(
    `id`               bigint unsigned NOT NULL COMMENT '主键ID',
    `parent_id`        bigint unsigned NOT NULL COMMENT '父ID',
    `category_id`      bigint unsigned DEFAULT NULL COMMENT '分类ID',
    `type`             int    NOT NULL COMMENT '类型1目录2正文',
    `file_url`         varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原文件地址',
    `title`            varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
    `description`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
    `content`          longtext COMMENT '内容',
    `recognition_mode` int                                                           DEFAULT NULL COMMENT '识别方式1普通识别2OCR识别',
    `ocr_model_id`     bigint unsigned DEFAULT NULL COMMENT 'ocr模型id',
    `task_id`          varchar(100)                                                  DEFAULT NULL COMMENT '任务ID',
    `task_status`      int                                                           DEFAULT '0' COMMENT '任务状态0无任务1进行中2任务完成3任务失败',
    `fail_reason`      varchar(255)                                                  DEFAULT NULL COMMENT '任务失败原因',
    `dept_id`          bigint unsigned NOT NULL COMMENT '部门ID',
    `tenant_id`        bigint unsigned NOT NULL COMMENT '租户ID',
    `status`           int                                                           DEFAULT '0' COMMENT '数据状态',
    `options`          text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '选项',
    `created`          datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`       bigint unsigned DEFAULT NULL COMMENT '创建者ID',
    `modified`         datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `modified_by`      bigint unsigned DEFAULT NULL COMMENT '修改者ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='wiki';

CREATE TABLE `tb_wiki_category`
(
    `id`            bigint unsigned NOT NULL COMMENT '主键',
    `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
    `sort_no`       int                                                                   DEFAULT '0' COMMENT '排序',
    `status`        int                                                          NOT NULL DEFAULT '0' COMMENT '数据状态',
    `created`       datetime                                                     NOT NULL COMMENT '创建时间',
    `created_by`    bigint unsigned NOT NULL COMMENT '创建者',
    `modified`      datetime                                                     NOT NULL COMMENT '修改时间',
    `modified_by`   bigint unsigned NOT NULL COMMENT '修改者',
    `dept_id`       bigint unsigned NOT NULL COMMENT '部门ID',
    `tenant_id`     bigint unsigned NOT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='wiki分类';

CREATE TABLE `tb_bot_wiki`
(
    `id`      bigint unsigned NOT NULL,
    `bot_id`  bigint unsigned DEFAULT NULL,
    `wiki_id` bigint unsigned DEFAULT NULL,
    `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='bot绑定的wiki';

INSERT INTO `tb_sys_menu` (`id`, `parent_id`, `menu_type`, `menu_title`, `menu_url`, `component`, `menu_icon`,
                           `is_show`, `permission_tag`, `sort_no`, `created`, `created_by`, `modified`, `modified_by`,
                           `remark`)
VALUES (404439821679489024, 0, 0, 'Wiki', '/wiki', '/wiki/WikiList', 'svg:log', 1, '', 2, '2026-04-22 14:00:28', 1,
        '2026-04-22 14:00:28', 1, '');
INSERT INTO `tb_sys_menu` (`id`, `parent_id`, `menu_type`, `menu_title`, `menu_url`, `component`, `menu_icon`,
                           `is_show`, `permission_tag`, `sort_no`, `created`, `created_by`, `modified`, `modified_by`,
                           `remark`)
VALUES (404440412006805504, 404439821679489024, 1, 'wiki-查询', '', '', '', 0, '/api/v1/wiki/query', 0,
        '2026-04-22 14:02:48', 1, '2026-04-22 14:02:48', 1, '');
INSERT INTO `tb_sys_menu` (`id`, `parent_id`, `menu_type`, `menu_title`, `menu_url`, `component`, `menu_icon`,
                           `is_show`, `permission_tag`, `sort_no`, `created`, `created_by`, `modified`, `modified_by`,
                           `remark`)
VALUES (404440611320131584, 404439821679489024, 1, 'wiki-保存', '', '', '', 0, '/api/v1/wiki/save', 0,
        '2026-04-22 14:03:36', 1, '2026-04-22 14:03:36', 1, '');
INSERT INTO `tb_sys_menu` (`id`, `parent_id`, `menu_type`, `menu_title`, `menu_url`, `component`, `menu_icon`,
                           `is_show`, `permission_tag`, `sort_no`, `created`, `created_by`, `modified`, `modified_by`,
                           `remark`)
VALUES (404440742773813248, 404439821679489024, 1, 'wiki-删除', '', '', '', 0, '/api/v1/wiki/remove', 0,
        '2026-04-22 14:04:07', 1, '2026-04-22 14:04:23', 1, '');

INSERT INTO `tb_sys_role_menu` (`id`, `role_id`, `menu_id`)
VALUES (404439821780152320, 1, 404439821679489024);
INSERT INTO `tb_sys_role_menu` (`id`, `role_id`, `menu_id`)
VALUES (404440412094885888, 1, 404440412006805504);
INSERT INTO `tb_sys_role_menu` (`id`, `role_id`, `menu_id`)
VALUES (404440611412406272, 1, 404440611320131584);
INSERT INTO `tb_sys_role_menu` (`id`, `role_id`, `menu_id`)
VALUES (404440742861893632, 1, 404440742773813248);

SET
FOREIGN_KEY_CHECKS = 1;