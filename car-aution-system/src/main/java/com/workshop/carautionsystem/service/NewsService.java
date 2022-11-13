package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.entity.News;
import com.workshop.carautionsystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    public void saveNews(News news);
    public void delete(Long id) throws NotFoundException;
    public News findById(Long id) throws NotFoundException;
    public Page<News> findAllOrderById(Pageable pageable);

}
