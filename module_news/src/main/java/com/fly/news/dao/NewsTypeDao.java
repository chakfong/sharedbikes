package com.fly.news.dao;


import com.fly.news.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsTypeDao extends JpaRepository<NewsType, Long> {
}
