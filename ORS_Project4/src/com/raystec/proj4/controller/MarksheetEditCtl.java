package com.raystec.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.MarksheetBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.MarksheetModel;
import com.raystec.proj4.model.StudentModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class MarksheetEditCtl
 */
@ WebServlet(name="MarksheetEditCtl",urlPatterns={"/ctl/MarksheetEditCtl.do"})
public class MarksheetEditCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(MarksheetEditCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModel model = new StudentModel();
		try {
			List l = model.list();
			// System.out.println("test");
			request.setAttribute("studentList", l);
			// System.out.println("test"+l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MarksheetEditCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("physics"))
				&& !DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics",
					PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}
		if (Datautility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics", "Marks can not be greater than 100");
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("chemistry"))
				&& !DataValidator.isInteger(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry",
					PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}
		if (Datautility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry",
					"Marks can not be greater than 100");
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("maths"))
				&& !DataValidator.isInteger(request.getParameter("maths"))) {
			request.setAttribute("maths",
					PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}
		if (Datautility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks can not be greater than 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId",
					PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}
		log.debug("MarksheetCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MarksheetEditCtl Method populatebean Started");
		MarksheetBean bean = new MarksheetBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setRollNo(Datautility.getString(request.getParameter("rollNo")));
		bean.setName(Datautility.getString(request.getParameter("name")));
		bean.setPhysics(Datautility.getInt(request.getParameter("physics")));
		bean.setChemistry(Datautility.getInt(request.getParameter("chemistry")));
		bean.setMaths(Datautility.getInt(request.getParameter("maths")));
		bean.setStudentId(Datautility.getLong(request.getParameter("studentId")));
		System.out.println("Population done");
		log.debug("MarksheetEditCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   log.debug("MarksheetEditCtl Method doPost Started");

	        String op = Datautility.getString(request.getParameter("operation"));
	        // get model
	        MarksheetModel model = new MarksheetModel();

	        long id = Datautility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)) {

	            MarksheetBean bean = (MarksheetBean) populateBean(request);
	            try {
	                if (id > 0) {
	                    model.update(bean);
	                } else {
	                    long pk = model.add(bean);
	                    bean.setId(pk);
	                }
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("Data has been successfully Updated",
	                        request);

	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Roll no already exists",
	                        request);
	            }

	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	            MarksheetBean bean = (MarksheetBean) populateBean(request);
	            System.out.println("in try");
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
	                        response);
	                System.out.println("in try");
	                return;
	            } catch (ApplicationException e) {
	                System.out.println("in catch");
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
	                    response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("MarksheetEditCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEETEDIT_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 log.debug("MarksheetEditCtl Method doGet Started");

	        String op = Datautility.getString(request.getParameter("operation"));
	        MarksheetModel model = new MarksheetModel();
	        long id = Datautility.getLong(request.getParameter("id"));
	        if (id > 0) {
	            MarksheetBean bean;
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
	        log.debug("MarksheetEditCtl Method doGet Ended");
	}

}
