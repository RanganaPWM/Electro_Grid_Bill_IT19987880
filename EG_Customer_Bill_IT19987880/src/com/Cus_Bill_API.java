package com;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cus_Bill")
public class Cus_Bill_API extends HttpServlet {

	private static final long serialVersionUID = -8821173037461710172L;
	Cus_Bill Bill = new Cus_Bill();

	public Cus_Bill_API() {
		super();
		// TODO Auto-generated constructor stub
	}

	// INSERT
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = Bill.insertDetails(request.getParameter("AcNumber"),
				request.getParameter("days"), request.getParameter("units"), request.getParameter("month"),
				request.getParameter("amount"));
		response.getWriter().write(output);
	}

	// UPDATE
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = Bill.updateDetails(paras.get("hidCustomerIDSave").toString(), paras.get("AcNumber").toString(),
				paras.get("days").toString(), paras.get("units").toString(), paras.get("month").toString(),
				paras.get("amount").toString());
		response.getWriter().write(output);
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = Bill.deleteDetails(paras.get("Bill_id").toString());
		response.getWriter().write(output);
	}

	public static Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
