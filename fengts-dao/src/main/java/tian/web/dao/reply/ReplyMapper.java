package tian.web.dao.reply;

import org.apache.ibatis.annotations.Param;
import tian.web.bean.reply.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FTS
 * @since 2021-03-22
 */
public interface ReplyMapper extends BaseMapper<Reply> {

    public List<Map<String,Object>> selectReplyListParentIsNull();

    List<Reply> selectReplyListParentIsNotNull(List<Long> listId);
}
