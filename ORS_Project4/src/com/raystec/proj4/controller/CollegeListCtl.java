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
import com.raystec.proj4.bean.CollegeBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.model.CollegeModel;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class CollegeListCtl
 */
@ WebServlet(name="CollegeListCtl",urlPatterns={"/ctl/CollegeListCtl.do"})
public class CollegeListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CollegeListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CollegeBean bean = new CollegeBean();
		bean.setName(Datautility.getString(request.getParameter("name")));
		bean.setCity(Datautility.getString(request.getParameter("city")));
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	      log.debug("CollegeListCtl doPost Start");

	        List list = null;

	        int pageNo = Datautility.getInt(request.getParameter("pageNo"));
	        int pageSize = Datautility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;

	        pageSize = (pageSize == 0) ? Datautility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;

	        CollegeBean bean = (CollegeBean) populateBean(request);

	        String op = Datautility.getString(request.getParameter("operation"));

	        CollegeModel model = new CollegeModel();

	        try {

	            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
	                    || "Previous".equalsIgnoreCase(op)) {

	                if (OP_SEARCH.equalsIgnoreCase(op)) {
	                    pageNo = 1;
	                } else if (OP_NEXT.equalsIgnoreCase(op)) {
	                    pageNo++;
	                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
	                    pageNo--;
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
	        log.debug("CollegeListCtl doGet End");
	    }


	@Override
	protected String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}

	public CollegeListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   int pageNo = 1;
	        int pageSize = Datautility.getInt(PropertyReader.getValue("page.size"));

	        CollegeBean bean = (CollegeBean) populateBean(request);
	        CollegeModel model = new CollegeModel();

	        List list = null;

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
	    }

}
