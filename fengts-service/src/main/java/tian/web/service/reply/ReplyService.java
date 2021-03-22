package tian.web.service.reply;

import tian.web.Result;
import tian.web.bean.reply.Reply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author FTS
 * @since 2021-03-22
 */
public interface ReplyService {
    /**
     * 添加评论
     *
     * @param reply
     * @return
     */
    public Result<Object> addReply(Reply reply);

    /**
     * 删除评论（把他评论的东西修改不是真的删除）
     *
     * @param replyId
     * @return
     */
    public Result<Object> deleteReply(String replyId);

    /**
     * 删除评论（把他评论的东西修改不是真的删除）
     *
     * @param replyId
     * @return
     */
    public Result<Object> selectReplyById(String replyId);


    /**
     * 查询父评论
     *
     * @return
     */
    public Result<Object> selectReplyListParentIsNull();

    /**
     * 插叙父评论的子评论
     *
     * @param param
     * @return
     */
    public Result<Object> selectReplyListParentIsNotNull(Map<String, Object> param);

    /**
     * 点赞
     *
     * @param replyId
     * @return
     */
    public Result<Object> addPraise(String replyId);

    /**
     * 点赞
     *
     * @param replyId
     * @return
     */
    public Result<Object> deletePraise(String replyId);


}
