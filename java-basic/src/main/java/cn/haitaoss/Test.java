package cn.haitaoss;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-22 14:12
 *
 */
public class Test {
    public static void main(String[] args) {
			/*	Test test = new Test();
				test.queryData(new SonQueryInputDomain<HashMap>());*/

        System.out.println(new Double(100.2D).toString());
    }

    public <T> List<HashMap> queryData(QueryInputDomain<T> domain) {
        Type[] genericInterfaces = domain.getClass().getGenericInterfaces();
        return null;
    }
}

class QueryInputDomain<T> {
    T data;

    public QueryInputDomain() {
    }
}

class SonQueryInputDomain<T> extends QueryInputDomain<T> {

}
