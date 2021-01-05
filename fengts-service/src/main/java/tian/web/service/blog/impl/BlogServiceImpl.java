package tian.web.service.blog.impl;

import tian.web.bean.blog.Blog;
import tian.web.dao.blog.BlogMapper;
import tian.web.service.blog.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
