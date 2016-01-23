package com.raystec.proj4.model.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.raystec.proj4.bean.CollegeBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.CollegeModel;

public class CollegeModelTest {

	public static CollegeModel model = new CollegeModel();

	public static void main(String[] args) throws DuplicateRecordException {
		// testAdd();
		 testDelete();
		// testUpdate();
		// testFindByName();
		//testFindByPK();
		// testSearch();
		// testList();

	}

	public static void testAdd() throws DuplicateRecordException {

		try {
			CollegeBean bean = new CollegeBean();
			// bean.setId(2L);
			bean.setName("GSITS");
			bean.setAddress("REGAL");
			bean.setState("mp");
			bean.setCity("indore");
			bean.setPhoneNo("0731242222");
			bean.setCreatedBy("USER");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			System.out.println("Test add succ");
			CollegeBean addedBean = model.findByPK(pk);
			if (addedBean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a College
	 */
	public static void testDelete() {

		try {
			CollegeBean bean = new CollegeBean();
			long pk = 2L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ");
			CollegeBean deletedBean = model.findByPK(pk);
			if (deletedBean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a College
	 */
	public static void testUpdate() {

		try {
			CollegeBean bean = new CollegeBean();
			bean = model.findByPK(1L);
			bean.setName("IIM");
			bean.setAddress("vv");
			bean.setCity("Indore");
			bean.setPhoneNo("457852695");
			//

			model.update(bean);
			System.out.println("Test Update succ");
			CollegeBean updateBean = model.findByPK(1L);
			if (!"oit".equals(updateBean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a College by Name.
	 */

	public static void testFindByName() {

		try {
			CollegeBean bean = model.findByName("IIM");
			if (bean == null) {
				System.out.println("Test Find By Name fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a College by PK.
	 */
	public static void testFindByPK() {
		try {
			CollegeBean bean = new CollegeBean();
			long pk = 1L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests search a College by Name
	 */

	public static void testSearch() {
		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			bean.setName("IPS");
			// bean.setAddress("borawan");
			list = model.search(bean, 1, 5);
			System.out.println("Test Search Succ");
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get List a College.
	 */
	public static void testList() {

		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
