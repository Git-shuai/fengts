package tian.web.service.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.BlogClassify;
import tian.web.dao.blog.BlogClassifyMapper;
import tian.web.enums.ResCode;
import tian.web.service.blog.BlogClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
@Service
public class BlogClassifyServiceImpl extends ServiceImpl<BlogClassifyMapper, BlogClassify> implements BlogClassifyService {

    @Autowired
    private BlogClassifyMapper blogClassifyMapper;

    @Override
    public Result<Object> selectClassify() {
        List<Map<String, Object>> list = blogClassifyMapper.selectClassify();
        return new Result<>(ResCode.SUCCESS_CODE, list);
    }

    @Override
    public Result<Object> selectBlogListByClassify(Map<String, Object> param) {
        String classifyId = StringUtils.getString(param.get("classifyId"));
        if (StringUtils.isEmpty(classifyId)){
            classifyId="%%";
        }else {
            classifyId="%"+classifyId+"%";
        }
        long page = Long.parseLong( param.get("page").toString());
        long size = Long.parseLong( param.get("size").toString());
        IPage<Map<String,Object>> list=blogClassifyMapper.selectBlogListByClassify(new Page(page,size),classifyId);
        for (Map<String, Object> record : list.getRecords()) {
            String classifyName = StringUtils.getString(record.get("tagName"));
            String[] strings = classifyName.split(",");
            record.put("tagName",strings);
        }
        return new Result<>(ResCode.SUCCESS_CODE,list);
    }
}
