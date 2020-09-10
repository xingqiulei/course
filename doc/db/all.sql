/**
  大章表
 */
drop table   if exists  'chapter';
create table  'chapter'(
   'id' char (8) not null  comment 'id',
   course_id char (8) comment '课程id',
   name varchar (50) comment '名称',
   primary  key ('id')
)engine=innodb  default charset=utf8mb4 comment='大章';

/**
  小节表
 */
drop table if exists 'section';
create table 'section'(
  'id' CHAR(8) not null  default '' comment 'id'，
      'title'  varchar(50) not null comment '标题',
  'course_id' char(8) comment '课程|course.id',
  'chapter_id' char(8) comment '大章|chapter.id',
  'video' varchar(200) comment '视频',
  'time' int comment '时长|单位秒'，
      'charge' char(1) comment '收费|c 收费；F 免费',
  'sort' int comment '顺序',
  'created_at' DATETIME(3) comment '创建时间',
  'update_at' DATETIME(3) comment '修改时间',
  primary key ('id')
)engine=INNODB default charset=utf8mb4 comment='小节';
#课程表
drop table if exists course;
create table course(
   id char(8)not null primary key default '' comment "id",
   name varchar(255)  comment '名称',
   summary varchar(255) comment '概述',
   time int default 0 comment '时长|单位秒',
   price decimal(8,2) default 0.00 comment '价格',
   image varchar(255)comment '封面',
   level char(1)  comment '级别|枚举[CourseLevelEnum]:ONE（"1","初级"）,TWO("2","中级"),THREE("3","高级")',
   charge char (1)comment '收费|枚举[CourseChargeEnum]:charge("C","收费"),FREE("F","免费")',
   status char(1) comment '状态|枚举[CourseStatusEnum]:PUBLISH("P","发布"),DRAFT("D","草稿")',
   enroll INTEGER default 0 comment '报名数',
   sort int comment '顺序',
   created_at datetime(3) comment '创建时间',
   updated_at datetime(3) comment  '修改时间'
)engine=innodb default charset='utf8mb4' comment="课程表";

/**
  分类表
 */
create table category(
 id char(8)not null  primary key default '' comment 'id',
 parent char(8) not null default '' comment '父id',
 name varchar(255) not null comment '分类名称',
 sort  int comment '顺序'
)engine=innodb default charset='utf8mb4' comment="分类表";


/**
  课程分类表
 */
drop table if exists course_category;
create table course_category(
    id char(8)not null  primary key default '' comment 'id',
    course_id char(8)  comment '课程|course.id',
    category_id char(8)  comment '分类|course.id'
)engine=innodb default charset='utf8mb4' comment="课程分类表";

#课程内容表
drop table if exists course_content;
create table course_content(
   id char(8)not null  primary key default '' comment '课程id',
   content MEDIUMTEXT not null  comment '课程内容'
)engine=innodb default charset='utf8mb4' comment="课程内容";

#讲师表
drop table if exists teacher;
create table teacher(
    id char(8)not null  primary key default '' comment '教师d',
    name varchar(50)not null comment '姓名',
    nickname varchar(50) comment '昵称',
    image varchar(100)comment '头像',
    position varchar(50) comment '职位',
    motto varchar(50) comment '座右铭',
    intro varchar(500) comment '简介'
)engine=innodb default charset='utf8mb4' comment="讲师";


#文件
drop table if exists file;
create table file(
     id char(8)not null  primary key default '' comment 'id',
     path varchar(100) not null  comment '相对路劲',
     name varchar(100) comment '文件名',
     suffix varchar(10) comment'后缀',
     size int comment '大小|字节B',
     `use` char(1) comment '用途|枚举[FileUseEnum]:COURSE("C","讲师"),TEACHER("T","课程")',
     created_at datetime(3) comment '创建时间',
     updated_at datetime(3) comment '修改时间'
)engine=innodb default charset='utf8mb4' comment="文件";