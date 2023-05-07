package cn.haitaoss.springboot;

import org.springframework.scheduling.support.CronExpression;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Function;

public class TestSpringCronAPI {

    public static void main(String[] args) throws Exception {
        // String expression = "*/1 5 12 * * ?";
        // String expression = "* * 10 2 * ?";
        String expression = "0 0 10 2 * ?";
        CronExpression parse = CronExpression.parse(expression);

        Instant instant1 = new Date().toInstant();
        Function<Instant, ZonedDateTime> fun = instant -> ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        // 下一次执行时间
        for (int i = 0; i < 10; i++) {
            ZonedDateTime next = parse.next(fun.apply(instant1));
            System.out.println(next.format(DateTimeFormatter.ISO_DATE_TIME));
            instant1 = next.toInstant();
        }
    }

}
