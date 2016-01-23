package com.raystec.proj4.bean;

import java.sql.*;
import java.util.Date;
import java.sql.Timestamp;

public class UserBean extends BaseBean {

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private Date dob;
	private String mobileNo;
	private long roleId;
	private int unSuccessfulLogin;

	private String gender;
	private Timestamp lastLogin;

	// private String userLock;
	private String registerdIp;
	private String lastLoginIp;
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	private String lock = INACTIVE;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}

	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getRegisterdIp() {
		return registerdIp;
	}

	public void setRegisterdIp(String registerdIp) {
		this.registerdIp = registerdIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public static String getActive() {
		return ACTIVE;
	}

	public static String getInactive() {
		return INACTIVE;
	}

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getvalue() {
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}

}
