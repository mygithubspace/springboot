package peng.cheng.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import peng.cheng.springboot.controller.ArticleRestController;
import peng.cheng.springboot.entity.Article;
import peng.cheng.springboot.entity.Reader;

import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRestControllerTests {
    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
    }

    /**
     * 测试saveArticle()接口
     * @throws Exception 测试失败异常
     */
    @Test
    public void saveArticle() throws Exception {
        // 对象转JSON
        String article = mapper.writeValueAsString(
                new Article().setId(6L)
                        .setAuthor("Jack6")
                        .setContent("test6")
                        .setTitle("Spring Boot6")
                        .setReaders(Arrays.asList(
                                new Reader().setId(1L).setName("John1").setAge(18),
                                new Reader().setId(2L).setName("John2").setAge(28)
                        ))
        );

        // Mock请求及断言返回结果设置
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.POST,"/v1/articles")
                    .contentType(MediaType.APPLICATION_JSON).content(article))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.owner").value("Jack6"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.readers[0].age").value(18))
                    .andDo(System.out::print).andReturn();

        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void updateArticle() throws Exception {
        String article = mapper.writeValueAsString(new Article().setId(2L).setAuthor("Jack2 Update").setTitle("Spring Boot2 Update"));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.PUT,"/v1/articles")
                .contentType(MediaType.APPLICATION_JSON).content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.owner").value("Jack2 Update"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content").value("test2"))
                .andDo(System.out::print)
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }

    /**
     * 测试getArticle()接口
     * @throws Exception 测试失败异常
     */
    @Test
    public void getArticle() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.GET,"/v1/articles/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.owner").value("Jack2"))
                .andDo(System.out::print)
                .andReturn();

        log.info(result.getResponse().getContentAsString());

    }
    /**
     * 测试getArticles()接口
     * @throws Exception 测试失败异常
     */
    @Test
    public void getArticles() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.GET,"/v1/articles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("Spring Boot1"))
                .andDo(System.out::print)
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }

    /**
     * 测试getArticles()接口
     * @throws Exception 测试失败异常
     */
    @Test
    public void deleteArticles() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.DELETE,"/v1/articles/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(System.out::print)
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }
}
