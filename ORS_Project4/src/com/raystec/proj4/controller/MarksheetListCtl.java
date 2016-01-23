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
 * Servlet implementation class MarksheetListCtl
 */
@ WebServlet(name="MarksheetListCtl",urlPatterns={"/ctl/MarksheetListCtl.do"})
public class MarksheetListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();
		bean.setRollNo(Datautility.getString(request.getParameter("rollNo")));
		bean.setName(Datautility.getString(request.getParameter("name")));
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   log.debug("MarksheetListCtl doPost Start");

	        List list = null;

	        int pageNo = Datautility.getInt(request.getParameter("pageNo"));
	        int pageSize = Datautility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;

	        pageSize = (pageSize == 0) ? Datautility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;

	        MarksheetBean bean = (MarksheetBean) populateBean(request);

	        String op = Datautility.getString(request.getParameter("operation"));

	        // get the selected checkbox ids array for delete list
	        String[] ids = request.getParameterValues("ids");

	        MarksheetModel model = new MarksheetModel();

	        try {

	            if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op)
	                    || OP_PREVIOUS.equalsIgnoreCase(op)) {

	                if (OP_SEARCH.equalsIgnoreCase(op)) {
	                    pageNo = 1;
	                } else if (OP_NEXT.equalsIgnoreCase(op)) {
	                    pageNo++;
	                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
	                    pageNo--;
	                }

	            } else if (OP_NEW.equalsIgnoreCase(op)) {
	                ServletUtility.redirect(ORSView.MARKSHEET_CTL, request,
	                        response);
	                return;
	            } else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                    MarksheetBean deletebean = new MarksheetBean();
	                    for (String id : ids) {
	                        deletebean.setId(Datautility.getInt(id));
	                        model.delete(deletebean);
	                    }
	                } else {
	                    ServletUtility.setErrorMessage(
	                            "Select at least one record", request);
	                }
	            }
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }

	        log.debug("MarksheetListCtl doPost End");
	  	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_LIST_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarksheetListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  int pageNo = Datautility.getInt(request.getParameter("pageNo"));
	        int pageSize = Datautility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;

	        pageSize = (pageSize == 0) ? Datautility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;

	        MarksheetBean bean = (MarksheetBean) populateBean(request);

	        List list = null;
	        MarksheetModel model = new MarksheetModel();
	        try {
	            list = model.search(bean, pageNo, pageSize);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }

	        if (list == null || list.size() == 0) {
	            ServletUtility.setErrorMessage("No record found ", request);
	        }
	        ServletUtility.setList(list, request);
	        ServletUtility.setPageNo(pageNo, request);
	        ServletUtility.setPageSize(pageSize, request);
	        ServletUtility.forward(getView(), request, response);
	        log.debug("MarksheetListCtl doGet End");


	}

}
