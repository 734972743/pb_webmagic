package com.pb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pb.vo.Img;


//JpaRepository 这个是jpa自带的对数据库的操作方法
//JpaRepository<实体类，id的类型>
public interface IImgDao extends JpaRepository<Img, Integer>{

}
