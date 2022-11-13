package com.workshop.carauctionsystem.service.impl;

import com.workshop.carautionsystem.entity.News;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.NewsRepository;
import com.workshop.carauctionsystem.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepo;

    @Override
    public List<News> getAllNews() {
        return (List) newsRepo.findAll();
    }

    @Override
    public void saveNews(News news) {
        newsRepo.save(news);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Long count = newsRepo.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find any with ID" + id);
        }
        newsRepo.deleteById(id);
    }

    @Override
    public News findById(Long id){
        Optional<News> optional = newsRepo.findById(id);
        News news = null;
        if(optional.isPresent()){
            news = optional.get();
        }
        return news;
    }

    @Override
    public Page<News> findAllOrderById(Pageable pageable) {
        return newsRepo.findAllOrderById(pageable);
    }
}
