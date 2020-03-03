# Lovezz

#重要提醒！！
本项目前端已经成功分离，代码参考https://github.com/Li-Weijian/lovezz-web
本项目适配前后端分离架构的分支为https://github.com/Li-Weijian/Lovezz/tree/20200115%E5%88%86%E7%A6%BB%E5%89%8D%E7%AB%AF
请注意切换

### 项目说明
和峥峥相处过程中 发现的需求, 一直在迭代。
目前开发的功能有： 定时发送短信、纪念日、情侣待办、图库、登录功能。
每个页面都是单独展示，通过Fusion App进行内嵌，生成App。

## 技术栈 
Springboot 2.1.6.RELEASE + Spring + Mybatis-plus 2.1.8 + 
Mysql 5.7 + 阿里云OSS对象存储 + Thymeleaf模版引擎 + 腾讯短信

## 各个功能访问路径
### 1.访问时间纪念路径
http://localhost:8091/ticController/time

### 2.访问待办事项路径
http://localhost:8091/noteController/toNote

### 3. 发送短信路径
http://localhost:8091/sms/sendSmsByTX

### 4. 图库
http://localhost:8091/galleryController/toGallery

### 5.登录
http://localhost:8091/user/toLogin

### 6.朋友圈
http://localhost:8091/tops/toTops

### 7.首页
http://localhost:8091/menu/toMenu

## 运行说明
问： 如何运行
答： 配置好Mysql，运行即可。

## 打包
mvn clean package -Dmaven.test.skip=true


### 各位需要的话可以联系我呀，最后麻烦点赞~嘻嘻
