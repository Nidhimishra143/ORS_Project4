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
import com.raystec.proj4.bean.StudentBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.CollegeModel;
import com.raystec.proj4.model.StudentModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class StudentCtl
 */
@ WebServlet(name="StudentCtl",urlPatterns={"/ctl/StudentCtl.do"})

public class StudentCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel model = new CollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("StudentCtl Method validate Started");
		boolean pass = true;
		String op = Datautility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
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
			request.setAttribute("lastName", PropertyReader.getValue("error.String", "LastName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("mobileNo"))
				&& !DataValidator.isPhone(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.Long", "Mobile No"));
			pass = false;
		}
		if (DataValidator.isNull(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.email", "Email "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId",
					PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (Datautility.getInt(request.getParameter("collegeId")) <= 0) {
			request.setAttribute("collegeId", "College Name is Required");
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
		log.debug("StudentCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("StudentCtl Method populatebean Started");
		StudentBean bean = new StudentBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setFirstName(Datautility.getString(request
				.getParameter("firstName")));
		bean.setLastName(Datautility.getString(request.getParameter("lastName")));
		try {
			bean.setDob(Datautility.getDate(request.getParameter("dob")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setMobileNo(Datautility.getString(request.getParameter("mobileNo")));
		bean.setEmail(Datautility.getString(request.getParameter("email")));
		bean.setCollegeId(Datautility.getLong(request.getParameter("collegeId")));
		log.debug("StudentCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 log.debug("StudentCtl Method doPost Started");

	        String op = Datautility.getString(request.getParameter("operation"));

	        // get model

	        StudentModel model = new StudentModel();

	        long id = Datautility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)) {

	            StudentBean bean = (StudentBean) populateBean(request);

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
	                ServletUtility.setErrorMessage(
	                        "Student Email Id already exists", request);
	            }

	        }

	        else if (OP_DELETE.equalsIgnoreCase(op)) {

	            StudentBean bean = (StudentBean) populateBean(request);
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
	                        response);
	                return;

	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility
	                    .redirect(ORSView.STUDENT_LIST_CTL, request, response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("StudentCtl Method doPost Ended");
	 
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 log.debug("StudentCtl Method doGet Started");

	        String op = Datautility.getString(request.getParameter("operation"));
	        long id = Datautility.getLong(request.getParameter("id"));

	        // get model

	        StudentModel model = new StudentModel();
	        if (id > 0 || op != null) {
	            StudentBean bean;
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
	        log.debug("StudentCtl Method doGett Ended");
	}

}
