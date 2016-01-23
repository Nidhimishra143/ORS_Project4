package com.raystec.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.RoleBean;
import com.raystec.proj4.bean.UserBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.UserModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class UserRegistrationCtl
 */
@ WebServlet(name="UserRegistrationCtl",urlPatterns={"/ctl/UserRegistrationCtl"})

public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "SignUp";
	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method validate Started");
		boolean pass = true;
		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else
			try {
				if (!DataValidator.isDate(dob)) {
					request.setAttribute("dob",
							PropertyReader.getValue("error.date", "Date Of Birth"));
					pass = false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage(
					"Confirm  Password  should not be matched.", request);
			pass = false;
		}
		log.debug("UserRegistrationCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setRoleId(RoleBean.STUDENT);
		bean.setFirstName(Datautility.getString(request
				.getParameter("firstName")));
		bean.setLastName(Datautility.getString(request.getParameter("lastName")));
		bean.setLogin(Datautility.getString(request.getParameter("login")));
		bean.setPassword(Datautility.getString(request.getParameter("password")));
		bean.setConfirmPassword(Datautility.getString(request
				.getParameter("confirmPassword")));
		bean.setGender(Datautility.getString(request.getParameter("gender")));
		try {
			bean.setDob(Datautility.getDate(request.getParameter("dob")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("UserRegistrationCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  log.debug("UserRegistrationCtl Method doGet Started");
	        ServletUtility.forward(getView(), request, response);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get method");
		 log.debug("UserRegistrationCtl Method doPost Started");
	        String op = Datautility.getString(request.getParameter("operation"));
	        // get model
	        UserModel model = new UserModel();
	        long id = Datautility.getLong(request.getParameter("id"));
	        if (OP_SIGN_UP.equalsIgnoreCase(op)) {
	            UserBean bean = (UserBean) populateBean(request);
	            try {
	                long pk = model.registerUser(bean);
	                bean.setId(pk);
	                request.getSession().setAttribute("UserBean", bean);
	                ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
	                return;
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                log.error(e);
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	                ServletUtility.forward(getView(), request, response);
	            }
	        }
	        log.debug("UserRegistrationCtl Method doPost Ended");
	    }


	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistrationCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	}


