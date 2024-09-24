package site.dadangsinhhoc.services;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NewsModel;

@Service
public interface INewsService {
     boolean existById(Long id);

     ResponseObject findNewsById(Long id);

     ResponseObject getAllNews();

     ResponseObject countAllNews();

     ResponseObject saveNews(NewsModel newsModels);

     ResponseObject updateNews(Long id, NewsModel newsModel);

     ResponseObject deleteByIdNews(Long id);

}
