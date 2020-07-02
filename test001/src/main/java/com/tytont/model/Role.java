package com.tytont.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends Model<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
	private String rolename;

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + "]";
	}

}
