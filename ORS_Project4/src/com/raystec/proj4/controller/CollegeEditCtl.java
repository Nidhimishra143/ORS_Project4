package com.raystec.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.CollegeBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.CollegeModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class CollegeEditCtl
 */
@ WebServlet(name="CollegeEditCtl",urlPatterns={"/ctl/CollegeEditCtl.do"})
public class CollegeEditCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CollegeCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("CollegeCtl Method validate Started");
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("name"))
				&& !DataValidator.isString(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.String", "Name"));
			pass = false;
		}

		/*
		 * if (Datautility.getInt(request.getParameter("physics")) > 100) {
		 * request.setAttribute("physics", "Marks can not be greater than 100");
		 * pass = false; }
		 */
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Phone No"));
			pass = false;
		}
	/*	if (DataValidator.isNotNull(request.getParameter("phoneNo"))
				&& !DataValidator.isInteger(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.integer", "PhoneNo"));
			pass = false;
		}*/
		if (DataValidator.isNotNull(request.getParameter("phoneNo"))
				&& !DataValidator.isPhone(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.Long", "Phone Number"));
			pass = false;
		}
		log.debug("CollegeCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("CollegeCtl Method populatebean Started");
		CollegeBean bean = new CollegeBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setName(Datautility.getString(request.getParameter("name")));
		bean.setAddress(Datautility.getString(request.getParameter("address")));
		bean.setState(Datautility.getString(request.getParameter("state")));
		bean.setCity(Datautility.getString(request.getParameter("city")));
		bean.setPhoneNo(Datautility.getString(request.getParameter("phoneNo")));
		log.debug("CollegeCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        log.debug("CollegeCtl Method doPost Started");

        String op = Datautility.getString(request.getParameter("operation"));

        // get model
        CollegeModel model = new CollegeModel();

        long id = Datautility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {

            CollegeBean bean = (CollegeBean) populateBean(request);

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
                e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("College Name already exists",
                        request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            CollegeBean bean = (CollegeBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,
                        response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility
                    .redirect(ORSView.COLLEGE_LIST_CTL, request, response);
            return;

        }

        ServletUtility.forward(getView(), request, response);

        log.debug("CollegeCtl Method doGet Ended");
    }

	@Override
	protected String getView() {
		return ORSView.COLLEGEEDIT_VIEW;
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        String op = Datautility.getString(request.getParameter("operation"));

        // get model
        CollegeModel model = new CollegeModel();

        long id = Datautility.getLong(request.getParameter("id"));

        if (id > 0) {
            CollegeBean bean;
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
    }

}
