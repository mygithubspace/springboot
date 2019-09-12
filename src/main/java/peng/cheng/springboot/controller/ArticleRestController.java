package peng.cheng.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import peng.cheng.springboot.common.Response;
import peng.cheng.springboot.entity.Article;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by chengpeng
 * @description
 * @time 2019/9/11 19:25
 */
@Slf4j
@RestController
@RequestMapping("/v1/articles")
public class ArticleRestController {

    static List<Article> articles = new LinkedList<>();
    static {
        articles.add(new Article().setId(1L).setAuthor("Jack1").setContent("test1").setCreateTime(new Date()).setTitle("Spring Boot1"));
        articles.add(new Article().setId(2L).setAuthor("Jack2").setContent("test2").setCreateTime(new Date()).setTitle("Spring Boot2"));
        articles.add(new Article().setId(3L).setAuthor("Jack3").setContent("test3").setCreateTime(new Date()).setTitle("Spring Boot3"));
        articles.add(new Article().setId(4L).setAuthor("Jack4").setContent("test4").setCreateTime(new Date()).setTitle("Spring Boot4"));
        articles.add(new Article().setId(5L).setAuthor("Jack5").setContent("test5").setCreateTime(new Date()).setTitle("Spring Boot5"));
    }


    @PostMapping
    public Response saveArticle(@RequestBody Article article){
        log.info("saveArticle:{}",article);
        article.setCreateTime(new Date());
        articles.add(article);
        return Response.success(article);
    }

    @PutMapping
    public Response updateArticle(@RequestBody Article article){
        log.info("updateArticle:{}", article);
        articles.add(article);
        return Response.success(article);
    }

    @GetMapping("{id}")
    public Response getArticle(@PathVariable Long id){
        List<Article> collect = articles.stream().filter(entity -> id.equals(entity.getId())).collect(Collectors.toList());
        Article article = collect.get(0);
        return Response.success(article);
    }

    @GetMapping
    public Response getArticles(){
        return Response.success(articles);
    }
}
