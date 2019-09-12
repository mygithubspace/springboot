package peng.cheng.springboot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author by chengpeng
 * @description 文章实体类
 * @time 2019/9/11 19:23
 */
@Data
@Accessors(chain = true)
public class Article {
    private Long  id;
    private String author;
    private String title;
    private String content;
    private Date createTime;
}
