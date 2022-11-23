package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.NewsRepository;
import com.workshop.carauctionsystem.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public boolean delete(Long id) throws NotFoundException {
        try {
            newsRepo.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public News findById(Long id) {
        Optional<News> optional = newsRepo.findById(id);
        return optional.get();
    }

    @Override
    public Page<News> findAllOrderById(Pageable pageable) {
        return newsRepo.findAllOrderById(pageable);
    }

    @Override
    public Page<News> findAllNewsOrderById(Pageable pageable, String search) {
        if(search != null){
            return newsRepo.findAllByNewsTitle(pageable,search);
        }else{
            return newsRepo.findAllOrderById(pageable);
        }
    }
}
