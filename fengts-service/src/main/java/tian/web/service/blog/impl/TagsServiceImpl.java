package tian.web.service.blog.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.Tags;
import tian.web.dao.blog.TagsMapper;
import tian.web.enums.ResCode;
import tian.web.service.blog.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Transactional
    @Override
    public Result<Tags> addTags(Tags tags) {
        if(tags.getId() != null){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        int addTagStatus = tagsMapper.insert(tags);
        if (addTagStatus<=0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return new Result<>(ResCode.SUCCESS_CODE,tags);
    }

    @Transactional
    @Override
    public Result<Tags> deleteTags(String tagsId) {
        if (StringUtils.isEmpty(tagsId)){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        int deleteTagStatus = tagsMapper.deleteById(tagsId);
        if (deleteTagStatus<=0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return new Result<>(ResCode.SUCCESS_CODE,null);
    }

    @Transactional
    @Override
    public Result<Tags> editTags(Tags tags) {
        if(tags.getId() == null){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        int updateTagStatus = tagsMapper.updateById(tags);
        if (updateTagStatus<=0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return new Result<>(ResCode.SUCCESS_CODE,null);
    }

    @Override
    public Result selectTagsList(long page, long size) {
//        Integer start=(page-1)*size;
        IPage<Map<String, Object>> tagPage = tagsMapper.selectTagList(new Page<>(page, size));
        return new Result<>(ResCode.SUCCESS_CODE,tagPage);
    }

    @Override
    public Result selectTagsList() {
        Result result = new Result<>();
        List<Tags> tags = tagsMapper.selectList(null);
        result.setCode(0);
        result.setMessage("查询成功");
        result.setData(tags);
        return result;
    }

    @Override
    public Result selectParentTag() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id",null);
        List<Tags> tags = tagsMapper.selectByMap(map);
        return new Result(ResCode.SUCCESS_CODE,tags);
    }

    @Override
    public Result selectTagByName(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(param)|| StringUtils.isEmpty(param.get("tagName"))){
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        String tagName = StringUtils.getString(param.get("tagName"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("tag_name",tagName);
        List<Tags> tags = tagsMapper.selectByMap(map);
        result.setMessage("查询成功");
        result.setCode(0);
        result.setData(tags);
        return result;
    }


}
