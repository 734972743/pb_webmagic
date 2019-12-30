package com.pb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pb.vo.Video;

public interface IVideoDao extends JpaRepository<Video, Integer>{

}
