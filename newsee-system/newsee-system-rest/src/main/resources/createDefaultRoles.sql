
-- 默认的角色组
-- role_group_id -1:管理员组     -2：公司负责人组       -3：收费员组        -4：财务组
INSERT INTO `newsee-system`.`system_role_group` 
(`role_group_id`,`enterprise_id`,`company_id`,`role_group_name`) 
VALUES
(-1,-1,-1,'管理员组'),
(-2,-1,-1,'公司负责人组'),
(-3,-1,-1,'收费员组'),
(-4,-1,-1,'财务组');

-- 默认角色
-- role_id -1:系统管理员     -2：公司负责人       -3：收费员         -4：财务
INSERT INTO `newsee-system`.`system_role` 
(`role_id`,`enterprise_id`,`company_id`,`role_group_id`,`role_name`) 
VALUES
(-1,-1,-1,-1,'系统管理员'),
(-2,-1,-1,-2,'公司负责人'),
(-3,-1,-1,-3,'收费员'),
(-4,-1,-1,-4,'财务');

-- 对应的角色组与角色的关系
INSERT INTO `newsee-system`.`system_user_rolegroup_role_relation` 
(`enterprise_id`,`company_id`,`role_group_id`,`role_id`) 
VALUES
(-1,-1,-1,0,-1),
(-1,-1,-2,0,-2),
(-1,-1,-3,0,-3),
(-1,-1,-4,0,-4);


-- 对应角色的功能权限
INSERT INTO `newsee-system`.`system_role_menu_button_relation` 
(`enterprise_id`, `company_id`, `role_id`, `menu_id`, `button_id`) 
VALUES 
-- ('所在的企业ID', '所在的公司ID', '角色ID', '菜单ID', '按钮ID' ),
('-1', '-1', '-1', '7', '4' ),
('-1', '-1', '-1', '7', '5' ),
('-1', '-1', '-2', '7', '4' ),
('-1', '-1', '-2', '7', '5' ),
('-1', '-1', '-3', '7', '4' ),
('-1', '-1', '-3', '7', '5' ),
('-1', '-1', '-4', '7', '4' ),
('-1', '-1', '-4', '7', '5' );

-- 对应角色的项目权限
INSERT INTO `newsee-system`.`system_role_precinct_relation` 
(`enterprise_id`,`role_id`,`company_id`,`precinct_id`) 
VALUES
-- ('所在的企业ID','角色ID','所在的公司ID','项目ID'),
('-1', '-1', '-1', '1'),
('-1', '-1', '-1', '2'),
('-1', '-2', '-1', '1'),
('-1', '-2', '-1', '2'),
('-1', '-3', '-1', '1'),
('-1', '-3', '-1', '2'),
('-1', '-4', '-1', '1'),
('-1', '-4', '-1', '2');