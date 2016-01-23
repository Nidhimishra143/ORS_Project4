package com.raystec.proj4.bean;

public class RoleBean extends BaseBean implements DropdownListBean {

	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE_SCHOOL = 3;
	public static final int KIOSK = 4;

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getvalue() {
		// TODO Auto-generated method stub
		return name;
	}

}
