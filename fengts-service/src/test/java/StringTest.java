import org.junit.Test;

import java.util.Arrays;

/**
 * @author FTS
 * @date 2021/1/27
 */
public class StringTest {

    @Test
    public void test1(){
        String s="sdfsdfsdeaas";
        String[] split = s.split(",");
        System.out.println(Arrays.toString(split));
    }
}
