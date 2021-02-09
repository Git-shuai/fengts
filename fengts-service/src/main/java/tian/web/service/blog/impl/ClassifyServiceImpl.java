package tian.web.service.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.blog.Classify;
import tian.web.bean.blog.Tags;
import tian.web.dao.blog.ClassifyMapper;
import tian.web.enums.ResCode;
import tian.web.service.blog.ClassifyService;
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
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Transactional
    @Override
    public Result addClassify(Classify classify) {
        if (classify.getId()!=null){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        int addClassifyStatus = classifyMapper.insert(classify);
        if (addClassifyStatus<=0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return new Result<>(ResCode.SUCCESS_CODE,classify);
    }

    @Transactional
    @Override
    public Result deleteClassifyById(String classifyId) {
        if (StringUtils.isEmpty(classifyId)){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        int deleteById = classifyMapper.deleteById(classifyId);
        if (deleteById<=0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return new Result<>(ResCode.SUCCESS_CODE,null);
    }

    @Transactional
    @Override
    public Result editClassify(Classify classify) {
        if(classify.getId() == null){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        int updateTagStatus = classifyMapper.updateById(classify);
        if (updateTagStatus<=0){
            return new Result<>(ResCode.ERROR_CODE,null);
        }
        return new Result<>(ResCode.SUCCESS_CODE,null);
    }

    @Override
    public Result selectClassifyList(Map<String,Object> map) {
        String page = StringUtils.getString(map.get("page"));
        String size = StringUtils.getString(map.get("size"));
        long pageL;
        long sizeL;
        try {
            pageL = Long.parseLong(page);
            sizeL = Long.parseLong(size);
        }catch (Exception e){
            return new Result(ResCode.ERROR_CODE,null);
        }
        IPage<Map<String, Object>> classifyPage = classifyMapper.selectClassifyList(new Page<>(pageL,sizeL));
        return new Result<>(ResCode.SUCCESS_CODE,classifyPage);
    }

    @Override
    public Result selectParentClassify() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id",null);
        List<Classify> classifyList = classifyMapper.selectByMap(map);
        return new Result(ResCode.SUCCESS_CODE,classifyList);
    }

    @Override
    public Result selectClassifyList() {
        Result<Object> result = new Result<>();
        List<Classify> classifyList = classifyMapper.selectList(null);
        result.setMessage("查询成功");
        result.setCode(0);
        result.setData(classifyList);
        return result;
    }

    @Override
    public Result selectClassifyByName(Map<String, Object> param) {

        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(param)|| StringUtils.isEmpty(param.get("classifyName"))){
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }
        String classifyName = StringUtils.getString(param.get("classifyName"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("classify_name",classifyName);
        List<Classify> classifyList = classifyMapper.selectByMap(map);
        result.setMessage("查询成功");
        result.setCode(0);
        result.setData(classifyList);
        return result;
    }
}
