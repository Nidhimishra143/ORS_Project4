package com.raystec.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.StudentBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.model.StudentModel;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class StudentListCtl
 */
@ WebServlet(name="StudentListCtl",urlPatterns={"/ctl/StudentListCtl.do"})

public class StudentListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(StudentListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		StudentBean bean = new StudentBean();
		bean.setFirstName(Datautility.getString(request
				.getParameter("firstName")));
		bean.setLastName(Datautility.getString(request.getParameter("lastName")));
		bean.setEmail(Datautility.getString(request.getParameter("email")));
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("StudentListCtl doGet Start");
		List list = null;
		int pageNo = Datautility.getInt(request.getParameter("pageNo"));
		int pageSize = Datautility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? Datautility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;
		StudentBean bean = (StudentBean) populateBean(request);
		String op = Datautility.getString(request.getParameter("operation"));
		StudentModel model = new StudentModel();
		try {
			
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			}
			list = model.search(bean, pageNo, pageSize);
			
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility
					.forward(ORSView.STUDENT_LIST_VIEW, request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("StudentListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_LIST_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

}
