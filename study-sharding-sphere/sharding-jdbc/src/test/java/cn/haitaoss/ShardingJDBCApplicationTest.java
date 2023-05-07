package cn.haitaoss;

import cn.haitaoss.entity.Course;
import cn.haitaoss.entity.Udict;
import cn.haitaoss.entity.User;
import cn.haitaoss.mapper.CourseMapper;
import cn.haitaoss.mapper.UdictMapper;
import cn.haitaoss.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-05-10 15:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCApplicationTest {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UdictMapper udictMapper;

    @Test
    public void testFindUser() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //添加操作
    @Test
    public void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    //删除操作
    @Test
    public void deleteDict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        //设置 userid 值
        wrapper.eq("dictid", 465191484111454209L);
        udictMapper.delete(wrapper);
    }

    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("lucy");
        user.setUstatus("a");
        userMapper.insert(user);
    }

    @Test
    public void addCourseDb() {
        Course course = new Course();
        course.setCname("javademo1");
        //分库根据 user_id
        course.setUserId(101L);
        course.setCstatus("Normal1");
        course.setCid(30L);
        courseMapper.insert(course);
    }

    //查询操作
    @Test
    public void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //设置 userid 值
        wrapper.eq("user_id", 100L);
        //设置 cid 值
        wrapper.eq("cid", 465162909769531393L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    @Test
    public void testAddCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100L);
            course.setCstatus("Normal" + i);
            courseMapper.insert(course);
        }

    }

    //查询课程的方法
    @Test
    public void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 730808227939221505L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

}
