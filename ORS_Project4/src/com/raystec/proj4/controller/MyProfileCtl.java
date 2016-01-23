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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.UserBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.UserModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class MyProfileCtl
 */
@ WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl.do"})
public class MyProfileCtl extends BaseCtl {
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MyProfileCtl Method validate Started");
		boolean pass = true;
		String op = Datautility.getString(request.getParameter("operation"));
		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {
			return pass;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			System.out.println("firstName" + request.getParameter("firstName"));
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("firstName"))
				&& !DataValidator.isString(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.String", "FirstName"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("lastName"))
				&& !DataValidator.isString(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.String", "LastName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("mobileNo"))
				&& !DataValidator.isPhone(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.Long", "Mobile No"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}
		log.debug("MyProfileCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MyProfileCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setLogin(Datautility.getString(request.getParameter("login")));
		bean.setFirstName(Datautility.getString(request
				.getParameter("firstName")));
		bean.setLastName(Datautility.getString(request.getParameter("lastName")));
		bean.setMobileNo(Datautility.getString(request.getParameter("mobileNo")));
		bean.setGender(Datautility.getString(request.getParameter("gender")));
		try {
			bean.setDob(Datautility.getDate(request.getParameter("dob")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession(true);
	        log.debug("MyprofileCtl Method doPost Started");

	        UserBean UserBean = (UserBean) session.getAttribute("user");
	        long id = UserBean.getId();
	        String op = Datautility.getString(request.getParameter("operation"));

	        // get model
	        UserModel model = new UserModel();

	        if (OP_SAVE.equalsIgnoreCase(op)) {
	            UserBean bean = (UserBean) populateBean(request);
	            try {
	                if (id > 0) {
	                    UserBean.setFirstName(bean.getFirstName());
	                    UserBean.setLastName(bean.getLastName());
	                    UserBean.setGender(bean.getGender());
	                    UserBean.setMobileNo(bean.getMobileNo());
	                    UserBean.setDob(bean.getDob());
	                    model.update(UserBean);

	                }
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage(
	                        "Profile has been updated Successfully. ", request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	            }
	        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
	                    response);
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	        log.debug("MyProfileCtl Method doPost Ended");
	    }

	

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyProfileCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession(true);
	        log.debug("MyprofileCtl Method doGet Started");

	        UserBean UserBean = (UserBean) session.getAttribute("user");
	        long id = UserBean.getId();
	        String op = Datautility.getString(request.getParameter("operation"));

	        // get model
	        UserModel model = new UserModel();
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

	        log.debug("MyProfileCtl Method doGet Ended");

	}

}
