package peng.cheng.springboot.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author by chengpeng
 * @description 文章实体类
 * @time 2019/9/11 19:23
 */
@Data
@Accessors(chain = true)
public class Article {

    // 排除属性不做序列化与反序列化
//    @JsonIgnore
    private Long  id;

    // 为属性换一个名
    @JsonProperty("owner")
    private String author;
    private String title;
    private String content;
    private List<Reader> readers;
    // 排除为空的元素不做序列化反序列化
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // 指定属性格式,若在application.properties中配置了全局格式化,则删除
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
