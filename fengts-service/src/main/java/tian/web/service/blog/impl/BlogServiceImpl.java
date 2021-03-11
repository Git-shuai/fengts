package tian.web.service.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
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
 * 服务实现类
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
        Result<Object> result = new Result<>();
        if (map.isEmpty()) {
            result.setCode(-999);
            result.setMessage("请编写内容再提交");
        }
        map.put("createTime",new Date());
        map.put("updateTime",map.get("createTime"));
        //添加博客详情
        int addBlogStatus = blogMapper.insertBlog(map);
        if (addBlogStatus <= 0) {
            result.setCode(-999);
            result.setMessage("添加博客失败");
        }

        long blogId = Long.parseLong(StringUtils.getString(map.get("id")));
        //添加分类
        if (!StringUtils.isEmpty(map.get("classify"))) {
            List<BlogClassify> classifyList = makeClassify((List<String>) map.get("classify"), blogId);
            blogClassifyMapper.addBlogClassifyBatch(classifyList);
        }


        if (!StringUtils.isEmpty(map.get("tag"))) {
            //添加标签
            List<BlogTag> blogTagList = makeBlogTag((List<String>) map.get("tag"), blogId);
            blogTagMapper.addBlogTagBatch(blogTagList);
        }

        result.setCode(0);
        result.setMessage("添加成功");
        result.setData(map);
        return result;
    }

    private List<BlogTag> makeBlogTag(List<String> param, Long blogId) {
        ArrayList<BlogTag> list = new ArrayList<>();
        for (String tag : param) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBlogId(blogId);
            blogTag.setTagId(Long.parseLong(tag));
            list.add(blogTag);
        }
        return list;
    }

    private List<BlogClassify> makeClassify(List<String> param, Long blogId) {
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
        if (StringUtils.isEmpty(blogId)) {
            return new Result(ResCode.ERROR_CODE, null);
        }
        //修改博客状态
        Blog blog = new Blog();
        blog.setId(Long.parseLong(blogId));
        blog.setBlogStatus("回收站");
        int i = blogMapper.updateById(blog);
        if (i<=0){
            return new Result(ResCode.ERROR_CODE, null);
        }
        Result result = new Result<>();
        result.setCode(0);
        result.setMessage("该文章已经放入回收站,可以在回收站永久删除或者恢复");
        return result;
    }

    /**
     * 删除
     *
     * @param blogId
     * @return
     */
    @Transactional
    @Override
    public Result deleteRecycleBlogById(String blogId) {
        if (StringUtils.isEmpty(blogId)) {
            return new Result(ResCode.ERROR_CODE, null);
        }
        //删除博客相关的标签
        HashMap<String, Object> param = new HashMap<>();
        param.put("blog_id", blogId);
        int bt = blogTagMapper.deleteByMap(param);
        //删除博客相关的分类
        int bc = blogClassifyMapper.deleteByMap(param);
        //删除博客
        param.remove("blog_id");
        param.put("id", blogId);
        int bl = blogMapper.deleteByMap(param);
        if (bl <= 0) {
            throw new RuntimeException();
        }
        Result result = new Result();
        result.setCode(0);
        result.setMessage("删除成功");
        return result;
    }

    @Override
    public Result deleteBatchIdList(Map<String, Object> param) {
        List<Integer> batchRecycleId=new ArrayList<>();
        List<Integer> batchId=new ArrayList<>();

        Result<Object> result = new Result<>();
        if (!StringUtils.isEmpty(param.get("batchRecycleId"))){
            batchRecycleId =(List<Integer>)param.get("batchRecycleId");
            if (batchRecycleId.size()!=0) {
                int ids = blogMapper.deleteBatchIds(batchRecycleId);
                if (ids <= 0) {
                    result.setCode(-999);
                    result.setMessage("删除失败");
                    return result;
                }
                result.setCode(0);
                result.setMessage("删除成功");
                return result;
            }
        }
        if (!StringUtils.isEmpty(param.get("batchId"))){
            batchId =(List<Integer>) param.get("batchId");
            if (batchId.size()!=0) {
                int ids = blogMapper.updateBlogBatch(batchId);
                if (ids <= 0) {
                    result.setCode(-999);
                    result.setMessage("删除失败");
                    return result;
                }
                result.setCode(0);
                result.setMessage("文章已经放入回收站");
                return result;
            }
        }
        return result;
    }

    /**
     * 修改
     *
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Result editBlog(Map<String, Object> map) {
        Result<Object> result = new Result<>();
        //判断是否有ID
        String ID = "id";
        if (StringUtils.isEmpty(map.get(ID))) {
            result.setCode(-999);
            result.setMessage("更新失败");
            return result;
        }
        long blogId = Long.parseLong(StringUtils.getString(map.get("id")));
        //更新博客内容
        map.put("updateTime",new Date());
        int i = blogMapper.updateBlog(map);
        if (i <= 0) {
            throw new RuntimeException("更新失败");
        }
        //更新标签
        String tag = "tag";
        if (!StringUtils.isEmpty(map.get(tag))) {
            HashMap<String, Object> param = new HashMap<>();
            param.put("blog_id", blogId);
            int bt = blogTagMapper.deleteByMap(param);
            //添加标签
            List<BlogTag> blogTagList = makeBlogTag((List<String>) map.get(tag), blogId);
            int i1 = blogTagMapper.addBlogTagBatch(blogTagList);
            if (bt <= 0 || i1 <= 0) {
                throw new RuntimeException("更新失败");
            }
        }
        String classify = "classify";
        if (!StringUtils.isEmpty(map.get(classify))) {
            HashMap<String, Object> param = new HashMap<>();
            param.put("blog_id", blogId);
            //先删除
            int i1 = blogClassifyMapper.deleteByMap(param);
            //添加分类
            List<BlogClassify> classifyList = makeClassify((List<String>) map.get("classify"), blogId);
            int i2 = blogClassifyMapper.addBlogClassifyBatch(classifyList);
            if (i1 <= 0 || i2 <= 0) {
                throw new RuntimeException("更新失败");
            }
        }
        //更新分类
        result.setCode(0);
        result.setMessage("更新成功");
        return result;
    }

    /**
     * 查询
     *
     * @param map
     * @return
     */
    @Override
    public Result selectBlogById(Map<String, Object> map) {
        Result<Object> result = new Result<>();
        String blogId = StringUtils.getString(map.get("blogId"));
        if (StringUtils.isEmpty(blogId)) {
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        Map<String, Object> blog = blogMapper.selectBlogById(blogId);
        //处理tag标签
        String[] targets = {"tagId", "tagName", "classifyId", "classifyName"};
        Map<String, Object> blogB = StringUtils.mapStringToList(blog, targets);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(blogB);
        return result;
    }


    @Override
    public Result selectRecycleBlogList(Long page, Long size) {
        Page<Blog> blogPage = new Page<>();
        //取到分页信息
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)) {
            //当前页码
            //当前页的数量
            blogPage.setCurrent(page);
            blogPage.setSize(size);
        }
        //进行分页查询
        IPage<Map<String, Object>> blogList = blogMapper.selectRecycleBlogList(blogPage);
        if (StringUtils.isEmpty(blogList.getRecords())) {
            return new Result(ResCode.SUCCESS_CODE, null);
        }
        for (Map<String, Object> map : blogList.getRecords()) {
            String[] targets = {"tagId", "tagName", "classifyId", "classifyName"};
            StringUtils.mapStringToList(map, targets);
        }
        //封装出参
        return new Result(ResCode.SUCCESS_CODE, blogList);
    }

    /**
     * 查询列表
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Result selectBlogList(Long page, Long size) {
        Page<Blog> blogPage = new Page<>();
        //取到分页信息
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)) {
            //当前页码
            //当前页的数量
            blogPage.setCurrent(page);
            blogPage.setSize(size);
        }
        //进行分页查询
        IPage<Map<String, Object>> blogList = blogMapper.selectBlogList(blogPage);
        if (StringUtils.isEmpty(blogList.getRecords())) {
            return new Result(ResCode.SUCCESS_CODE, null);
        }
        for (Map<String, Object> map : blogList.getRecords()) {
            String[] targets = {"tagId", "tagName", "classifyId", "classifyName"};
            StringUtils.mapStringToList(map, targets);
        }
        //封装出参
        return new Result(ResCode.SUCCESS_CODE, blogList);
    }

    /**
     * 根据条件查询
     *
     * @param map
     * @return
     */
    @Override
    public Result selectBlogListByParam(Map<String, Object> map) {
        Result<Object> result = new Result<>();
        String keyWord = StringUtils.getString(map.get("keyWord"));
        String blogStatus = StringUtils.getString(map.get("blogStatus"));
        String classifyId = StringUtils.getString(map.get("classifyId"));
        String page = StringUtils.getString(map.get("page"));
        String size = StringUtils.getString(map.get("size"));
        long pageL;
        long sizeL;
        try {
            pageL = Long.parseLong(page);
            sizeL = Long.parseLong(size);
        } catch (Exception e) {
            pageL = 1;
            sizeL = 10;
        }
        if (StringUtils.isEmpty(keyWord) && StringUtils.isEmpty(blogStatus) && StringUtils.isEmpty(classifyId)) {
            result.setCode(-999);
            result.setMessage("请输入查询添加");
        }
        if (!StringUtils.isEmpty(classifyId) && !StringUtils.isEmpty(keyWord)) {
            map.put("classifyId", "%" + classifyId + "%");
            map.put("keyWord", "%" + keyWord + "%");
        }
        IPage<Map<String, Object>> paramBlogList = blogMapper.selectBlogListByParam(new Page(pageL, sizeL), map);
        result.setMessage("查询成功");
        result.setCode(0);
        result.setData(paramBlogList);
        return result;
    }

    @Override
    public Result<Object> selectBlogListOfEcharts() {
        List<Map<String,Object>> list=blogMapper.selectBlogListOfEcharts();
        return new Result<>(ResCode.SUCCESS_CODE, list);
    }

}
