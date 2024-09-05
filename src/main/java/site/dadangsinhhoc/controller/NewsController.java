package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NewsModel;
import site.dadangsinhhoc.services.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/getAllNews")
    public ResponseObject getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/getNewsById/{id}")
    public ResponseObject getNewsById(@PathVariable Long id) {
        return newsService.findNewsById(id);
    }

    @GetMapping("/countAllNews")
    public ResponseObject countAllNews() {
        return newsService.countAllNews();
    }

    @PostMapping("/addNewNews")
    public ResponseObject addNewNews(@RequestBody NewsModel newsModel) {
        return newsService.saveNews(newsModel);
    }

    @PutMapping("/updateNews/{id}")
    public ResponseObject updateNews(@PathVariable Long id, @RequestBody NewsModel newsModel) {
        newsModel.setId(id);
        return newsService.updateNews(id, newsModel);
    }

    @DeleteMapping("/deleteNews/{id}")
    public ResponseObject deleteNews(@PathVariable Long id) {
        return newsService.deleteByIdNews(id);
    }

}
