package tian.web.service.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.BlogTag;
import tian.web.dao.blog.BlogTagMapper;
import tian.web.enums.ResCode;
import tian.web.service.blog.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
@Service("blogTagService")
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;
    @Override
    public Result<Object> selectTag() {
        List<Map<String,Object>> list=blogTagMapper.selectTag();
        Result<Object> result = new Result<>();
        result.setMessage("成功");
        result.setCode(0);
        result.setData(list);
        return result;
    }

    @Override
    public Result<Object> selectBlogListByTag(Map<String, Object> param) {
        String tagId = StringUtils.getString(param.get("tagId"));
        if (StringUtils.isEmpty(tagId)){
            tagId="%%";
        }else {
            tagId="%"+tagId+"%";
        }
        long page = Long.parseLong( param.get("page").toString());
        long size = Long.parseLong( param.get("size").toString());
        IPage<Map<String,Object>> list=blogTagMapper.selectBlogListByTag(new Page(page,size),tagId);
        for (Map<String, Object> record : list.getRecords()) {
            String classifyName = StringUtils.getString(record.get("classifyName"));
            String[] strings = classifyName.split(",");
            record.put("classifyName",strings);
        }
        return new Result<>(ResCode.SUCCESS_CODE,list);
    }
}
