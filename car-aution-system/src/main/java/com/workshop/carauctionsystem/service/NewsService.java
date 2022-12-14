package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    public void saveNews(News news);
    public boolean delete(Long id) throws NotFoundException;
    public News findById(Long id) throws NotFoundException;
    public Page<News> findAllOrderById(Pageable pageable);
    public Page<News> findAllNewsOrderById(Pageable pageable,String search);
    Page<News> findPaginated(int pageNo, int pageSize);
    News getNewsById(long id);
    Page<News> getTop5(int pageable);
}
