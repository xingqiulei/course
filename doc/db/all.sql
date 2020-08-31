drop table   if exists  'chapter';
create table  'chapter'(
   'id' char (8) not null  comment 'id',
   course_id char (8) comment '课程id',
   name varchar (50) comment '名称',
   primary  key ('id')
)engine=innodb  default charset=utf8mb4 comment='大章';

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