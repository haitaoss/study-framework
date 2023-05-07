package cn.haitaoss.parametricdynamicdatasource.mapper;

import cn.haitaoss.parametricdynamicdatasource.entity.TestUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 19:14
 *
 */
@Repository
public interface TableMapper extends BaseMapper<TestUser> {
    /**
     * 查询表信息
     */
    @Select("select table_name, table_comment, create_time, update_time " +
            " from information_schema.tables " +
            " where table_schema = (select database())")
    List<Map<String, Object>> selectTableList();
}
