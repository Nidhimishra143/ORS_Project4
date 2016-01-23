package com.raystec.proj4.model.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.raystec.proj4.bean.RoleBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.RoleModel;

public class RoleModelTest {

	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws ParseException {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByName();
		// testSearch();
		testList();

	}

	public static void testAdd() throws ParseException {

		try {
			RoleBean bean = new RoleBean();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setName("Nidhi");
			bean.setDescription("kumarI");
			long pk = model.add(bean);
			System.out.println("TEST Role Succ..");
			RoleBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	// Tests delete a Role

	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 3L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Role
	 */
	public static void testUpdate() {

		try {
			RoleBean bean = model.findByPK(6L);
			bean.setName("12");
			bean.setDescription("Ejjjjjjjjng");
			model.update(bean);

			RoleBean updatedbean = model.findByPK(6L);
			if (!"12".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			RoleBean bean = new RoleBean();
			long pk = 2L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Name.
	 */
	public static void testFindByName() {
		try {
			RoleBean bean = new RoleBean();
			bean = model.findByName("Nidhi");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("Nidhi");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
