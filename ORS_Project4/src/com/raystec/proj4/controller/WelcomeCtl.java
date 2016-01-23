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

import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class WelcomeCtl
 */
@ WebServlet(name="WelcomeCtl",urlPatterns={"/ctl/WelcomeCtl.do"})

public class WelcomeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("WelcomeCtl Method doGet Started");

        ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);

        log.debug("WelcomeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WelcomeCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
/*	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("WelcomeCtl Method doGet Started");
		ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
		log.debug("WelcomeCtl Method doGet Ended");
	}*/

}
