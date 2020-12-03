package tian.web.bean.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * permission
 * @author 
 */
@Data
public class Permission implements Serializable {
    /**
     * 权限id
     */
    private Long perId;

    /**
     * 权限名称
     */
    private String perName;

    /**
     * 父权限id
     */
    private Long parentPerId;

    /**
     * 权限描述
     */
    private String des;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Permission other = (Permission) that;
        return (this.getPerId() == null ? other.getPerId() == null : this.getPerId().equals(other.getPerId()))
            && (this.getPerName() == null ? other.getPerName() == null : this.getPerName().equals(other.getPerName()))
            && (this.getParentPerId() == null ? other.getParentPerId() == null : this.getParentPerId().equals(other.getParentPerId()))
            && (this.getDes() == null ? other.getDes() == null : this.getDes().equals(other.getDes()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPerId() == null) ? 0 : getPerId().hashCode());
        result = prime * result + ((getPerName() == null) ? 0 : getPerName().hashCode());
        result = prime * result + ((getParentPerId() == null) ? 0 : getParentPerId().hashCode());
        result = prime * result + ((getDes() == null) ? 0 : getDes().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", perId=").append(perId);
        sb.append(", perName=").append(perName);
        sb.append(", parentPerId=").append(parentPerId);
        sb.append(", des=").append(des);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}