package com.raystec.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.ServletUtility;

/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 * 
 * @author HP
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public abstract class BaseCtl extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	// public static final String OP_BACK = "BACK";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User
	 * 
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Load the pre_loaded data required to display at HTML form
		preload(request);

		String op = Datautility.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages

			if (!validate(request)) {
				BaseBean bean = (BaseBean) populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		super.service(request, response);
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected abstract String getView();

}
