package peng.cheng.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
        Article oldArticle = articles.stream().filter(entity -> article.getId().equals(entity.getId())).collect(Collectors.toList()).get(0);
        if (!StringUtils.isEmpty(article.getAuthor())){
            oldArticle.setAuthor(article.getAuthor());
        }
        if (!StringUtils.isEmpty(article.getContent())){
            oldArticle.setContent(article.getContent());
        }
        if (!StringUtils.isEmpty(article.getTitle())){
            oldArticle.setTitle(article.getTitle());
        }
        if (!CollectionUtils.isEmpty(article.getReaders())){
            oldArticle.setReaders(article.getReaders());
        }
        articles.add(oldArticle);
        return Response.success(oldArticle);
    }

    @GetMapping("{id}")
    public Response getArticle(@PathVariable Long id){
        return Response.success(articles.stream().filter(entity -> id.equals(entity.getId())).collect(Collectors.toList()).get(0));
    }

    @GetMapping
    public Response getArticles(){
        return Response.success(articles);
    }

    @DeleteMapping("{id}")
    public Response deleteArticles(@PathVariable String id){
        log.info("deleteArticles{}",id);
        articles.remove(articles.stream().filter(entity -> Long.parseLong(id)==entity.getId()).collect(Collectors.toList()).get(0));
        return Response.success(articles);
    }
}
