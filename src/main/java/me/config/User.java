/**
 * 
 */
package me.config;

import me.annotation.Column;
import me.annotation.Table;

/**
 * @author BlackStone
 *
 */

@Table(name = "user",dbClass="me.config.Auto")
public class User {
	/** 
	 * @ClassName: User 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月21日 下午2:28:55 
	 * 
	 */
	
	@Column(name="user_name")
	public String  userName;
	
	@Column(name="password")
	public String password;
	
	@Column(name="role_id")
	public String roleId;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	

	
}
