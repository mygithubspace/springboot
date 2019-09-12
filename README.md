# Spring Boot NOTE

## REST接口开发常用的注解

- @RestController与@Controller

  > @RestController相当于 @Controller和@ResponseBody结合。
  >
  > 它有两层含义：一是作为控制器注入到Spring上下文环境，二是请求响应为数据序列化（默认序列化方式是JSON），而不是跳转到html或模板页面。

- @RequestMapping 与@GetMapping、@PutMapping、@PostMapping、@DeleteMapping

  > @RequestMapping(value = “/article”, method = RequestMethod.GET)
  > 新方法可以简写为： @GetMapping("/article")，其他同理。

- @RequestBody与@ResponseBody

  > 用于接收和响应序列化数据（JSON），可以支持嵌套JSON数据结构。

- @PathVariable 与@RequestParam

  > PathVariable用于URI上的{参数}
  > RequestParam用于接收普通方式提交的参数

## Jackson 使用方法

### 常用注解

- @JsonIgnore 排除属性不做序列化与反序列化
- @JsonProperty 为属性换一个名
- @JsonPropertyOrder(value={"pname1","pname2"}) 改变json子属性的默认定义的顺
- @JsonInclude(JsonInclude.Include.NON_NULL) 排除为空的元素不做序列化反序列化
- @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 指定属性格式