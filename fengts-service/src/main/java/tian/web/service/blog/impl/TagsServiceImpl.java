package tian.web.service.blog.impl;

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
        return new Result<>(ResCode.SUCCESS_CODE,null);
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
    public Result selectTagsList(Long page, Long size) {
        if (page==null||size==null){
            page=0L;
            size=10L;
        }

        Page<Map<String, Object>> tagPage = tagsMapper.selectMapsPage(new Page<>(page, size), null);
        return new Result<>(ResCode.SUCCESS_CODE,tagPage);
    }

    @Override
    public Result selectParentTag() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id",null);
        List<Tags> tags = tagsMapper.selectByMap(map);
        return new Result(ResCode.SUCCESS_CODE,tags);
    }


}
