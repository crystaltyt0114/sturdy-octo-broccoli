package com.tytont.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Model<User> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6504557290519364082L;
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String sex;
	private Integer roleid;
	private Date update_time;

	public static final String ID = "id";

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	public static final String SEX = "sex";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" + ", id=" + id + ", username=" + username + ", sex=" + sex + "}";
	}

}
