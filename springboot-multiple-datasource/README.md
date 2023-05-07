> 参考资料 https://github.com/haitaoss/my-example/tree/master/multi-datasource

## basic-multi-datasource

示例功能： 使用多套数据源的策略，实现一主一从的数据库逻辑。

- 文章：[搞定SpringBoot多数据源(1)：多套源策略](https://mianshenglee.github.io/2020/01/13/multi-datasource-1.html)
- 思路：多个数据源多个SqlSessionFactory，如果多数据源是主从关系，那么就要写多个相同的mapper

## dynamic-datasource

示例功能： 使用动态数据源策略进行数据源切换，也可使用 AOP 方式进行数据源切换，实现一主一从的数据库逻辑。

-
文章：[搞定SpringBoot多数据源(2)：动态数据源](https://mianshenglee.github.io/2020/01/16/multi-datasource-2.html)文章：[搞定SpringBoot多数据源(2)：动态数据源](https://mianshenglee.github.io/2020/01/16/multi-datasource-2.html)
- 使用 spring提供的AbstractRoutingDataSource 实现

## parametric-dynamic-datasource

示例功能： 根据参数动态添加数据源以及切换数据源，解决不确定数据源的问题。

- 文章：[搞定SpringBoot多数据源(3)：参数化变更源](https://mianshenglee.github.io/2020/01/21/multi-datasource-3.html)
- 使用 spring提供的AbstractRoutingDataSource 实现