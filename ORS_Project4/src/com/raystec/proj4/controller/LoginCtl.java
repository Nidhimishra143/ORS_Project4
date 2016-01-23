package com.raystec.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.RoleBean;
import com.raystec.proj4.bean.UserBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.model.RoleModel;
import com.raystec.proj4.model.UserModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class LoginCtl
 */
@ WebServlet(name="LoginCtl",urlPatterns={"/ctl/LoginCtl"})
public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");
		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		String login = request.getParameter("login");
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

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("LoginCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setLogin(Datautility.getString(request.getParameter("login")));
		bean.setPassword(Datautility.getString(request.getParameter("password")));
		log.debug("LoginCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        log.debug(" Method doPost Started");

        String op = Datautility.getString(request.getParameter("operation"));

        // get model
        UserModel model = new UserModel();
        RoleModel role = new RoleModel();

        long id = Datautility.getLong(request.getParameter("id"));

        if (OP_SIGN_IN.equalsIgnoreCase(op)) {

            UserBean bean = (UserBean) populateBean(request);

            try {

                bean = model.authenticate(bean.getLogin(), bean.getPassword());

                if (bean != null) {
                    session.setAttribute("user", bean);
                    long rollId = bean.getRoleId();

                    RoleBean rolebean = role.findByPK(rollId);

                    if (rolebean != null) {
                        session.setAttribute("role", rolebean.getName());
                    }

                    ServletUtility.forward(ORSView.WELCOME_VIEW, request,
                            response);
                    return;
                } else {
                    bean = (UserBean) populateBean(request);
                    ServletUtility.setBean(bean, request);
                    ServletUtility.setErrorMessage(
                            "Invalid LoginId And Password", request);
                }

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_LOG_OUT.equals(op)) {
System.out.println("logout test");
            session = request.getSession();

            session.invalidate();

            ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

            return;

        } else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
                    response);
            return;

        }

        ServletUtility.forward(getView(), request, response);

        log.debug("UserCtl Method doPost Ended");
    }

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCtl() {
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
        log.debug(" Method doGet Started");

        String op = Datautility.getString(request.getParameter("operation"));

        // get model
        UserModel model = new UserModel();
        RoleModel role = new RoleModel();

        long id = Datautility.getLong(request.getParameter("id"));
        if (id > 0) {
            UserBean userbean;
            try {
                userbean = model.findByPK(id);
                ServletUtility.setBean(userbean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        if (OP_LOG_OUT.equals(op)) {
        	System.out.println("logout test");
        	
        	            session = request.getSession(false);
        	            
        	            ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

        	            return;

        	        } 
        
        ServletUtility.forward(getView(), request, response);

        log.debug("UserCtl Method doPost Ended");

    }

	}

