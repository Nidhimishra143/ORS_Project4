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
import com.raystec.proj4.bean.MarksheetBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.model.MarksheetModel;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class MarksheetMeritListCtl
 */
@ WebServlet(name="MarksheetMeritListCtl",urlPatterns={"/ctl/MarksheetMeritListCtl"})
public class MarksheetMeritListCtl extends BaseCtl {
	public static final String OP_BACK = "BACK";

	private static Logger log = Logger.getLogger(MarksheetMeritListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();
		return bean;
	}

	
	protected String getView() {
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarksheetMeritListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  log.debug("MarksheetMeritListCtl doGet Start");

	        int pageNo = 1;

	        int pageSize = Datautility.getInt(PropertyReader.getValue("page.size"));

	        MarksheetBean bean = (MarksheetBean) populateBean(request);

	        String op = Datautility.getString(request.getParameter("operation"));

	        MarksheetModel model = new MarksheetModel();
	        List list = null;
	        try {
	            list = model.getMeritList(pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(ORSView.MARKSHEET_MERIT_LIST_VIEW, request,
	                    response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("MarksheetMeritListCtl doGet End");
	   
	    }

	    /**
	     * Contains Submit logics
	     */
	    @Override
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	    	log.debug("MarksheetMeritListCtl doGet Start");
	        List list = null;
	        int pageNo = Datautility.getInt(request.getParameter("pageNo"));
	        int pageSize = Datautility.getInt(request.getParameter("pageSize"));
	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? Datautility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;
	        MarksheetBean bean = (MarksheetBean) populateBean(request);
	        String op = Datautility.getString(request.getParameter("operation"));
	        MarksheetModel model = new MarksheetModel();
	        try {
	            if (OP_BACK.equalsIgnoreCase(op)) {
	                ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
	                return;
	            }
	            list = model.getMeritList(pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(ORSView.MARKSHEET_MERIT_LIST_VIEW, request,
	                    response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("MarksheetMeritListCtl doPost End");
}
}
