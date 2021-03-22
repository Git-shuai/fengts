package tian.web.controller.reply;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tian.web.Result;
import tian.web.bean.reply.Reply;
import tian.web.service.reply.ReplyService;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FTS
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 添加评论
     *
     * @param reply
     * @return
     */
    @PostMapping("/addReply")
    public Result<Object> addReply(@RequestBody Reply reply){
        return replyService.addReply(reply);
    }

    /**
     * 删除评论（把他评论的东西修改不是真的删除）
     *
     * @param replyId
     * @return
     */
    @DeleteMapping("/deleteReply/{replyId}")
    public Result<Object> deleteReply(@PathVariable("replyId") String replyId){
        return replyService.deleteReply(replyId);
    }

    /**
     * 查询指定评论（把他评论的东西修改不是真的删除）
     *
     * @param replyId
     * @return
     */
    @GetMapping("/selectReplyById/{replyId}")
    public Result<Object> selectReplyById(@PathVariable("replyId") String replyId){
        return replyService.selectReplyById(replyId);
    }


    /**
     * 查询父评论
     *
     * @return
     */
    @PostMapping("/selectReplyListParentIsNull")
    public Result<Object> selectReplyListParentIsNull(){
        return replyService.selectReplyListParentIsNull();
    }

    /**
     * 插叙父评论的子评论
     *
     * @param param
     * @return
     */
    @PostMapping("/selectReplyListParentIsNotNull")
    public Result<Object> selectReplyListParentIsNotNull(@RequestBody Map<String, Object> param){
        return replyService.selectReplyListParentIsNotNull(param);
    }

    /**
     * 点赞
     *
     * @param replyId
     * @return
     */
    @GetMapping("/addPraise/{replyId}")
    public Result<Object> addPraise(@PathVariable("replyId") String replyId){
        return replyService.addPraise(replyId);
    }

    /**
     * 取消点赞
     *
     * @param replyId
     * @return
     */
    @DeleteMapping("/deletePraise/{replyId}")
    public Result<Object> deletePraise(@PathVariable("replyId")String replyId){
        return replyService.deletePraise(replyId);
    }

}

