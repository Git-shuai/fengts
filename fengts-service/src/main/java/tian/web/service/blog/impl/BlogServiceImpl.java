package tian.web.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.Blog;
import tian.web.bean.blog.BlogClassify;
import tian.web.bean.blog.BlogTag;
import tian.web.dao.blog.BlogClassifyMapper;
import tian.web.dao.blog.BlogMapper;
import tian.web.dao.blog.BlogTagMapper;
import tian.web.enums.ResCode;
import tian.web.service.blog.BlogService;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FTS
 * @since 2021-01-05
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogClassifyMapper blogClassifyMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;
    /**
     * 添加
     *
     * @param map 入参
     * @return Result
     */
    @Transactional
    @Override
    public Result addBlog(Map<String, Object> map) {
        if (map.isEmpty()){
           return new Result<>(ResCode.ERROR_CODE,null);
        }
        //添加博客详情
        Blog blog = makeBlog((Map) map.get("blog"));
        int bl = blogMapper.insert(blog);
        if (bl<0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        //添加分类
        List<BlogClassify> classifyList = makeClassify((List<String>) map.get("classify"), blog.getId());
        blogClassifyMapper.addBlogClassifyBatch(classifyList);
        //添加标签
        List<BlogTag> blogTagList = makeBlogTag((List<String>) map.get("tag"), blog.getId());
        blogTagMapper.addBlogTagBatch(blogTagList);
        return new Result<>(ResCode.SUCCESS_CODE,null);
    }

    private Blog makeBlog(Map param){
        Blog blog = new Blog();
        blog.setTitle(StringUtils.getString(param.get("title")));
        blog.setAuth(StringUtils.getString(param.get("auth")));
        blog.setCreateTime(new Date());
        blog.setUpdateTime(blog.getCreateTime());
        blog.setReadNum(Long.parseLong(param.get("readNum").toString()));
        blog.setContent(StringUtils.getString(param.get("content")));
        blog.setIsOriginal(Integer.parseInt(param.get("isOriginal").toString()) );
        blog.setIsAdmire(Integer.parseInt( param.get("isAdmire").toString()));
        blog.setIsComment(Integer.parseInt(param.get("isComment").toString()));
        return blog;
    }

    private List<BlogTag> makeBlogTag(List<String> param,Long blogId){
        ArrayList<BlogTag> list = new ArrayList<>();
        for (String tag : param) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBlogId(blogId);
            blogTag.setTagId(Long.parseLong(tag));
            list.add(blogTag);
        }
        return list;
    }

    private List<BlogClassify> makeClassify(List<String> param,Long blogId){
        ArrayList<BlogClassify> list = new ArrayList<>();
        for (String classify : param) {
            BlogClassify blogTag = new BlogClassify();
            blogTag.setBlogId(blogId);
            blogTag.setClassifyId(Long.parseLong(classify));
            list.add(blogTag);
        }
        return list;
    }


    /**
     * 删除
     *
     * @param blogId
     * @return
     */
    @Transactional
    @Override
    public Result deleteBlog(String blogId) {
        if(StringUtils.isEmpty(blogId)){
            return new Result(ResCode.ERROR_CODE,null);
        }
        //删除博客相关的标签
        HashMap<String, Object> param = new HashMap<>();
        param.put("blog_id",blogId);
        int bt = blogTagMapper.deleteByMap(param);
        //删除博客相关的分类
        int bc = blogClassifyMapper.deleteByMap(param);
        //删除博客
        param.remove("blog_id");
        param.put("id",blogId);
        int bl = blogMapper.deleteByMap(param);
        if (bl<=0){
            throw new RuntimeException();
        }
        return new Result(ResCode.SUCCESS_CODE,null);
    }

    /**
     * 修改
     *
     * @param map
     * @return
     */
    @Override
    public Result editBlog(Map<String, Object> map) {

        return null;
    }

    /**
     * 查询
     *
     * @param map
     * @return
     */
    @Override
    public Result selectBlog(Map<String, Object> map) {
        return null;
    }

    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    @Override
    public Result selectBlogList(Map<String, Object> map) {
        
        return null;
    }
}
