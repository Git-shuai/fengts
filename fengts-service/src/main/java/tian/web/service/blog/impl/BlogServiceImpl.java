package tian.web.service.blog.impl;
import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.*;
import tian.web.dao.blog.BlogClassifyDao;
import tian.web.dao.blog.BlogDao;
import tian.web.dao.blog.BlogTagDao;
import tian.web.enums.ResCode;
import tian.web.service.blog.BlogService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author FTS
 * @date 2021/1/4
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Resource
    private BlogTagDao blogTagDao;
    @Resource
    private BlogClassifyDao blogClassifyDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Blog> addBlog(Map<String,Object> params) {
        //判断入参是否为空
        if (params.isEmpty()){return new Result<>(ResCode.ERROR_CODE, null);}
        //得到入参blog

        Blog blog = (Blog) params.get("blog");
        //得到标签数组
        List<BlogTag> blogTag = (List<BlogTag>) params.get("tags");
        //得到分类数组
        List<BlogClassify> blogClassify = (List<BlogClassify>) params.get("classify");

        int blogFlag = blogDao.insert(blog);
        blogTagDao.insertBatch(blogTag);
        blogClassifyDao.insertBatch(blogClassify);

        if (blogFlag < 0) {
            return new Result<>(ResCode.ERROR_CODE, null);
        }
        return new Result<>(ResCode.SUCCESS_CODE, blog);
    }

    @Transactional
    @Override
    public Result<Blog> deleteBlog(String blogId) {
        return null;
    }

    @Override
    public Result<Blog> modifyBlog(Map<String,Object> params) {
        return null;
    }

    @Override
    public Result<Blog> selectBlogById(String blogId) {
        return null;
    }

    @Override
    public Result<List<Blog>> selectBlogList(String off, String limit) {
        return null;
    }
}
