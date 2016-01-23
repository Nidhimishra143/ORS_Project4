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

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.RoleBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.exception.DuplicateRecordException;
import com.raystec.proj4.model.RoleModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class RoleCtl
 */
@ WebServlet(name="RoleEditCtl",urlPatterns={"/ctl/RoleEditCtl.do"})
public class RoleEditCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(RoleEditCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("RoleCtl Method validate Started");
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("name"))
				&& !DataValidator.isString(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.String", "Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("description"))
				&& !DataValidator.isString(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.String", "Description"));
			pass = false;
		}
		log.debug("RoleCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("RoleCtl Method populatebean Started");
		RoleBean bean = new RoleBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setName(Datautility.getString(request.getParameter("name")));
		bean.setDescription(Datautility.getString(request
				.getParameter("description")));
		log.debug("RoleCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    log.debug("RoleEditCtl Method doGet Started");

        System.out.println("In do get");

        String op = Datautility.getString(request.getParameter("operation"));

        // get model
        RoleModel model = new RoleModel();

        long id = Datautility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {

            RoleBean bean = (RoleBean) populateBean(request);

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
                ServletUtility.setErrorMessage("Role already exists", request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            RoleBean bean = (RoleBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
            return;

        }

        ServletUtility.forward(getView(), request, response);

        log.debug("RoleCtl Method doPOst Ended");
	}
	@Override
	protected String getView() {
		return ORSView.ROLEEDIT_VIEW;
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleEditCtl Method doGet Started");

        System.out.println("In do get");

        String op = Datautility.getString(request.getParameter("operation"));

        // get model
        RoleModel model = new RoleModel();

        long id = Datautility.getLong(request.getParameter("id"));
        if (id > 0 || op != null) {
            RoleBean bean;
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
        log.debug("RoleEditCtl Method doGetEnded");
   
	}
}
