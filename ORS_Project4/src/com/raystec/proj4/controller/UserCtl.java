package com.raystec.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.UserBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.RoleModel;
import com.raystec.proj4.model.UserModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class UserCtl
 */
@ WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl.do"})

public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserCtl Method validate Started");
		boolean pass = true;
		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("firstName"))
				&& !DataValidator.isString(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.String", "FirstName"));
			pass = false;
		}
		if (!DataValidator.name(request.getParameter("firstName"))) {
			request.setAttribute("firstName","First Name does not contain space");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("lastName"))
				&& !DataValidator.isString(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.String", "LirstName"));
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
		/*if (Datautility.getInt(request.getParameter("gender")) == 0) {
			request.setAttribute("gender", "Gender is Required");
			pass = false;
		}*/
		if (Datautility.getInt(request.getParameter("roleId")) <= 0) {
			request.setAttribute("roleId", "Role is Required");
			pass = false;
		}
		
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else
			try {
				if (!DataValidator.isDOB(dob)) {
					request.setAttribute("dob",
							PropertyReader.getValue("error.date", "Date Of Birth"));
					pass = false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage(
					"Confirm Password should be matched.", request);
			pass = false;
		}
		log.debug("UserCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setRoleId(Datautility.getLong(request.getParameter("roleId")));
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
		log.debug("UserCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   log.debug("UserCtl Method doPost Started");
	        String op = Datautility.getString(request.getParameter("operation"));
	        // get model
	        UserModel model = new UserModel();
	        long id = Datautility.getLong(request.getParameter("id"));
	        if (OP_SAVE.equalsIgnoreCase(op)) {
	            UserBean bean = (UserBean) populateBean(request);

	            try {
	                if (id > 0) {
	                    model.update(bean);
	                } else {
	                    long pk = model.add(bean);
	                    bean.setId(pk);
	                }
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("Data is successfully saved",
	                        request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	            }
	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	            UserBean bean = (UserBean) populateBean(request);
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
	                        response);
	                return;
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
	            return;
	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("UserCtl Method doPostEnded");
	 
	}

	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");
        String op = Datautility.getString(request.getParameter("operation"));
        // get model
        UserModel model = new UserModel();
        long id = Datautility.getLong(request.getParameter("id"));
        if (id > 0 || op != null) {
            System.out.println("in id > 0  condition");
            UserBean bean;
            try {
                bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("UserCtl Method doGet Ended");
 
	}

}
