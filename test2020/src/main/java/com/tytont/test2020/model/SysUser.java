package com.tytont.test2020.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cpatrol
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends Model<SysUser> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "uid", type = IdType.AUTO)
	private Integer uid;

	/**
	 * 登陆名
	 */
	@TableField("username")
	private String username;

	/**
	 * 密码
	 */
	@TableField("password")
	private String password;

	/**
	 * 密码加密盐
	 */
	@TableField("password_salt")
	private String passwordSalt;

	/**
	 * 用户名
	 */
	@TableField("realname")
	private String realname;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

	/**
	 * 创建时间
	 */
	@TableField("createtime")
	private Date createtime;

	public static final String UID = "uid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_SALT = "password_salt";
	public static final String REALNAME = "realname";
	public static final String TYPE = "type";
	public static final String CID = "cid";
	public static final String CANTEEN_NAME = "canteen_name";
	public static final String OID = "oid";
	public static final String ORG_NAME = "org_name";
	public static final String REMARK = "remark";
	public static final String CREATETIME = "createtime";

	@Override
	protected Serializable pkVal() {
		return this.uid;
	}

	public void updatePassword(String password) {
		int hashIterations = 2;
		String salt = this.genSalt();
		Md5Hash md5Hash = new Md5Hash(password, salt, hashIterations);
		this.setPasswordSalt(salt);
		this.setPassword(md5Hash.toString());
	}

	// 判断密码是否相等
	public boolean passwordEqual(String pwd) {
		int hashIterations = 2; // 加盐次数
		Md5Hash md5Hash = new Md5Hash(pwd, this.getPasswordSalt(), hashIterations);
		String encryptedPassword = md5Hash.toString();
		return this.getPassword().equals(encryptedPassword);
	}

	private String genSalt() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

}
