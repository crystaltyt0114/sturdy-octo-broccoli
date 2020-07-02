package com.tytont.test2020.model;

  import com.baomidou.mybatisplus.annotation.IdType;
  import com.baomidou.mybatisplus.extension.activerecord.Model;
  import java.util.Date;
  import com.baomidou.mybatisplus.annotation.TableId;
  import com.baomidou.mybatisplus.annotation.TableField;
  import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cpatrol
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WxUser extends Model<WxUser> {

    private static final long serialVersionUID=1L;

        @TableId(value = "id", type = IdType.AUTO)
      	private Integer id;

    /**
     * 小程序微信openid
     */
    @TableField("openid")
    	private String openid;

    /**
     * 微信昵称
     */
    @TableField("nickname")
    	private String nickname;

    /**
     * 创建时间
     */
    @TableField("create_time")
    	private Date createTime;

    /**
     * 公众号是否关注 0取消关注 1已关注
     */
    @TableField("follow_status")
    	private Integer followStatus;

    /**
     * 备注
     */
    @TableField("remark")
    	private String remark;

    /**
     * 巡查员id
     */
    @TableField("inspid")
    	private Integer inspid;

    /**
     * 0=未绑定 ；1=绑定
     */
    @TableField("bind_status")
    	private Integer bindStatus;


    public static final String ID = "id";
    public static final String OPENID = "openid";
    public static final String NICKNAME = "nickname";
    public static final String CREATE_TIME = "create_time";
    public static final String FOLLOW_STATUS = "follow_status";
    public static final String REMARK = "remark";
    public static final String INSPID = "inspid";
    public static final String BIND_STATUS = "bind_status";
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
