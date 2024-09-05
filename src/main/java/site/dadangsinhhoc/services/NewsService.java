package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.NewsModel;
import site.dadangsinhhoc.repositories.NewsRepository;

import java.util.List;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public boolean existById(Long id) {
        return newsRepository.existsById(id);
    }

    public ResponseObject findNewsById(Long id) {
        return newsRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public ResponseObject getAllNews() {
        List<NewsModel> newsModels = newsRepository.findAll();
        return ResponseObject.success(newsModels);
    }

    public ResponseObject countAllNews() {
        long quantity = newsRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveNews(NewsModel newsModels) {
        if (newsRepository.existsById(newsModels.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        newsModels.setId(null);
        NewsModel savedNews = newsRepository.save(newsModels);
        return ResponseObject.success(savedNews);
    }

    public ResponseObject updateNews(Long id, NewsModel newsModel) {
        if (!newsRepository.existsById(newsModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find News with id: " + newsModel.getId());
        }
        return newsRepository.findById(id)
                .map(existingNganh -> {
                    existingNganh.setTitle(newsModel.getTitle());
                    existingNganh.setImage(newsModel.getImage());
                    existingNganh.setRPH(newsModel.getRPH());
                    existingNganh.setContent(newsModel.getContent());
                    existingNganh.setStatus(newsModel.getStatus());
                    existingNganh.setAuthor(newsModel.getAuthor());
                    existingNganh.setUpdatedAt(newsModel.getUpdatedAt());
                    return ResponseObject.success(newsRepository.save(existingNganh));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find News with id: " + id));
    }

    public ResponseObject deleteByIdNews(Long id) {
        if (!newsRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            newsRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
