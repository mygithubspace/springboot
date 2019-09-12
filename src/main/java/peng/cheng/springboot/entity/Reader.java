package peng.cheng.springboot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author by chengpeng
 * @description 订阅者实体类
 * @time 2019/9/12 10:19
 */
@Data
@Accessors(chain = true)
public class Reader {
    private Long id;
    private String name;
    private Integer age;
}
