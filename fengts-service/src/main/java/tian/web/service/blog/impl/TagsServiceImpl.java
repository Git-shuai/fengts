package tian.web.service.blog.impl;

import tian.web.bean.blog.Tags;
import tian.web.dao.blog.TagsMapper;
import tian.web.service.blog.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
