package tian.web.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tian.web.Result;
import tian.web.enums.ResCode;

/**
 * @author FTS
 * @date 2021/3/31 12:44
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 处理其他异常
     *
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> exceptionHandler(Exception e) {
        Result<Object> result = new Result<>();
        result.setCode(-999);
        result.setMessage("后台异常");
        result.setData(e.getMessage());
        return result;
    }
}
