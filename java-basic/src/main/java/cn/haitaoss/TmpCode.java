package cn.haitaoss;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class TmpCode {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        Pattern datePattern = Pattern.compile(".*\\d{4}[-|_]?\\d{2}[-|_]?\\d{2}.*");
        Arrays.asList("2022-11-11", "2022_11_11", "20221111", "fi20221111en","xx22out")
                .forEach(item -> System.out.println(datePattern.matcher(item)
                        .matches()));

    }

    public void 分页处理() {
        List<Object> datas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            datas.add(i);
        }

        deal(datas, 19, pageData -> {
            try {
                TimeUnit.SECONDS.sleep(Math.round(3));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(pageData);
        });
    }

    public static <T> void deal(List<T> datas, int pageSize, Consumer<List<T>> task) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int total = datas.size();
        int page = total / pageSize;

        CompletableFuture[] completableFutures = Stream.iterate(0, i -> i + 1)
                .limit(total % pageSize == 0 ? page : page + 1)
                .map(num -> {
                    List<T> pageData = datas.stream()
                            .skip((long) num * pageSize)
                            .limit(pageSize)
                            .collect(Collectors.toList());

                    return CompletableFuture.runAsync(() -> task.accept(pageData), executorService);
                })
                .collect(Collectors.toList())
                .toArray(new CompletableFuture[]{});
        CompletableFuture.allOf(completableFutures)
                .join();
    }
}
