package tian.web.bean.user;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * menu
 * @author FTS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    /**
     * 菜单列表主键
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单英文名名称
     */
    private String menuName;

    /**
     * 菜单英文名名称
     */
    private String menuItemName;

    /**
     * ICON名称
     */
    private String iconName;

    /**
     * 菜单路径
     */
    private String menuUrl;

    /**
     * 父路径id
     */
    private Long parentId;

    /**
     * 菜单描述
     */
    private String menuDes;

    private static final long serialVersionUID = 1L;

}