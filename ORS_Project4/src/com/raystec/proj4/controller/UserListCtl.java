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
import com.raystec.proj4.bean.UserBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.model.UserModel;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class UserListCtl
 */
@ WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl.do"})

public class UserListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(UserListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		bean.setFirstName(Datautility.getString(request
				.getParameter("firstName")));
		bean.setLastName(Datautility.getString(request.getParameter("lastName")));
		bean.setLogin(Datautility.getString(request.getParameter("login")));
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserListCtl doPost Start");
        List list = null;
        int pageNo = Datautility.getInt(request.getParameter("pageNo"));
        int pageSize = Datautility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? Datautility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        UserBean bean = (UserBean) populateBean(request);
        String op = Datautility.getString(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        String[] ids = request.getParameterValues("ids");
        UserModel model = new UserModel();
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

            } else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.USER_CTL, request, response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    UserBean deletebean = new UserBean();
                    for (String id : ids) {
                        deletebean.setId(Datautility.getInt(id));
                        model.delete(deletebean);
                    }
                } else {
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
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
            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("UserListCtl doGet End");
  }

	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 log.debug("UserListCtl doGet Start");
	        List list = null;
	        int pageNo = 1;
	        int pageSize = Datautility.getInt(PropertyReader.getValue("page.size"));
	        UserBean bean = (UserBean) populateBean(request);
	        String op = Datautility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        String[] ids = request.getParameterValues("ids");
	        UserModel model = new UserModel();
	        try {
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("UserListCtl doPOst End");
	  
	}

}
