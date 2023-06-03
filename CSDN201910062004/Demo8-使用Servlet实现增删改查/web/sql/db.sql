# 创建数据库
create database servletdemo;
# 创建users表
create table users(
  id int not null primary key auto_increment comment '编号',
  username varchar(20) not null comment '用户名',
  password varchar(50) not null comment '密码'
);
# 插入一些测试数据
insert into users(username,password) values ('张三','123456');
insert into users(username,password) values ('李四','123456');
insert into users(username,password) values ('王五','123456');
insert into users(username,password) values ('赵六','123456');