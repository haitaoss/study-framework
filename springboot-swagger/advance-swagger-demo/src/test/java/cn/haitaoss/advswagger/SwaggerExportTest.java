package cn.haitaoss.advswagger;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Paths;

/**
 * 导出asciidocs及markdown文档测试
 *
 * @author mason
 * @since 2019/6/6
 */
public class SwaggerExportTest {

    private String url = "http://localhost:8080/v2/api-docs?group=default";

    //输出到目录，分割到各个文件

    /**
     * 生成AsciiDocs格式文档
     * @throws Exception
     */
    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        // swagger-ui.html页面中能找到此链接
        Swagger2MarkupConverter
                .from(new URL(url))
                .withConfig(config)
                .build()
                // .toFolder(Paths.get("./docs/asciidoc"));
                .toFile(Paths.get("./docs/asciidoc-all")); // 汇总到一个文件
    }


    /**
     * 生成Markdown文档
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocs() throws Exception {
        //    输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter
                .from(new URL(url))
                .withConfig(config)
                .build()
                // .toFolder(Paths.get("./docs/mk"));
                .toFile(Paths.get("./docs/mk-all")); // 汇总到一个文件
    }
}
