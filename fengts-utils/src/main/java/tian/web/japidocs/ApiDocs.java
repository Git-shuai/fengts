package tian.web.japidocs;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * @author tian
 * @date 2020/12/6
 */
public class ApiDocs {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("D:\\IDEAWORK\\fengts");
        // 项目名称
        config.setProjectName("毕业设计");
        // 声明该API的版本
        config.setApiVersion("V1.3.0");
        // 生成API 文档所在目录
        config.setDocsPath("D:\\IDEAWORK\\fengts\\apidocs");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
