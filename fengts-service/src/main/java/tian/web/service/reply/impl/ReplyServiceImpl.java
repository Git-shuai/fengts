package tian.web.service.reply.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.bean.reply.Reply;
import tian.web.dao.reply.ReplyMapper;
import tian.web.service.reply.ReplyService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author FTS
 * @since 2021-03-22
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Transactional
    @Override
    public Result<Object> addReply(Reply reply) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(reply)) {
            result.setCode(-999);
            result.setMessage("留言失败");
            return result;
        }
        if (StringUtils.isEmpty(reply.getContent())){
            result.setCode(-999);
            result.setMessage("请填写留言在提交");
            return result;
        }
        reply.setCreateTime(new Date());
        //初始点赞数
        reply.setPraiseNum(0);
        //开启评论（控制评论框）
        reply.setReplyStatus(0);
        //默认没有点赞（是否给父评论点赞）
        reply.setPraiseStatus(0);
        int insert = replyMapper.insert(reply);
        if (insert <= 0) {
            result.setCode(-999);
            result.setMessage("留言失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("你已成功留言");
        return result;
    }

    @Transactional
    @Override
    public Result<Object> deleteReply(String replyId) {
        Result<Object> result = new Result<>();
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("parent_id", replyId);
        List<Reply> replyList = replyMapper.selectByMap(map);
        if (replyList.size() == 0) {
            HashMap<String, Object> deleteMap = new HashMap<>(1);
            deleteMap.put("id", replyId);
            int i = replyMapper.deleteByMap(deleteMap);
            if (i <= 0) {
                result.setCode(-999);
                result.setMessage("删除失败");
                return result;
            }
        } else {
            Reply reply = new Reply();
            reply.setId(Long.parseLong(replyId));
            reply.setContent("该用户留言已被删除");
            int i = replyMapper.updateById(reply);
            if (i <= 0) {
                result.setCode(-999);
                result.setMessage("删除失败");
                return result;
            }
        }
        result.setCode(0);
        result.setMessage("删除成功");
        return result;

    }

    @Override
    public Result<Object> selectReplyById(String replyId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(replyId)) {
            result.setCode(-999);
            result.setMessage("请输入你要查询的人");
            return result;
        }
        Reply reply = replyMapper.selectById(replyId);
        result.setCode(0);
        result.setData(reply);
        result.setMessage("成功");
        return result;
    }

    @Override
    public Result<Object> selectReplyListParentIsNull() {
        Result<Object> result = new Result<>();
        List<Map<String,Object>> replyList = replyMapper.selectReplyListParentIsNull();
        result.setData(replyList);
        result.setCode(0);
        result.setMessage("成功");
        return result;
    }

    @Override
    public Result<Object> selectReplyListParentIsNotNull(Map<String, Object> param) {
        Result<Object> result = new Result<>();
        String replyId = StringUtils.getString(param.get("replyId"));
        if (StringUtils.isEmpty(replyId)) {
            result.setCode(-999);
            result.setMessage("查询失败");
            return result;
        }

        HashMap<String, Object> map = new HashMap<>(1);
        map.put("parent_id",replyId);
        List<Reply> list = replyMapper.selectByMap(map);
        List<Long> listId = list.stream().map(Reply::getId).collect(Collectors.toList());
        while (listId.size()!=0){
            List<Reply> replies=replyMapper.selectReplyListParentIsNotNull(listId);
            listId.clear();
            listId=replies.stream().map(Reply::getId).collect(Collectors.toList());
            list.addAll(replies);
        }
        if (list.size() == 0) {
            result.setCode(0);
            result.setData(list);
            result.setMessage("没有人回复他，快来回复吧");
            return result;
        }
        result.setCode(0);
        result.setData(list);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result<Object> addPraise(String replyId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(replyId)) {
            result.setCode(-999);
            result.setMessage("点赞失败");
            return result;
        }
        Reply reply = replyMapper.selectById(replyId);
        if (StringUtils.isEmpty(reply)) {
            result.setCode(-999);
            result.setMessage("没有这个评论");
            return result;
        }
        Reply upReply = new Reply();
        upReply.setPraiseNum(reply.getPraiseNum() + 1);
        upReply.setId(reply.getId());
        int i = replyMapper.updateById(upReply);
        if (i <= 0) {
            result.setCode(-999);
            result.setMessage("点赞失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("点赞成功");
        return result;
    }

    @Transactional
    @Override
    public Result<Object> deletePraise(String replyId) {
        Result<Object> result = new Result<>();
        if (StringUtils.isEmpty(replyId)) {
            result.setCode(-999);
            result.setMessage("取消失败");
            return result;
        }
        Reply reply = replyMapper.selectById(replyId);
        if (StringUtils.isEmpty(reply)) {
            result.setCode(-999);
            result.setMessage("没有这个评论");
            return result;
        }
        Reply upReply = new Reply();
        upReply.setPraiseNum(reply.getPraiseNum() - 1);
        upReply.setId(reply.getId());
        int i = replyMapper.updateById(upReply);
        if (i <= 0) {
            result.setCode(-999);
            result.setMessage("取消失败");
            return result;
        }
        result.setCode(0);
        result.setMessage("取消成功");
        return result;
    }
}
