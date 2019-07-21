package com.pavelkisliuk.fth.servlet;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.command.CommandType;
import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.ConnectionPoolException;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServletException;
import com.pavelkisliuk.fth.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/start")
public class FthServlet extends HttpServlet {
	private static final String COMMAND = "command";

	@Override
	public void init() throws ServletException {
		try {
			ConnectionPool.INSTANCE.createPool();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("Can't create a pool of connections!");
		}
	}

	@Override
	public void destroy() {
		try {
			ConnectionPool.INSTANCE.destroyPool();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("Can't destroy a pool of connections!");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("http://localhost:63342/FTH/html/auth/index.html");

		/*response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		HashMap<String, String> mess1 = new HashMap<>();
		String id = "1";
		String name = "Pavel";
		String surname = "Kisliuk";
		String photo = "https://pp.userapi.com/c638330/v638330874/419bb/AzS3PYR_kf0.jpg";
		String message1 = "{ id : " + id + ", " +
				"name : " + name + ", " +
				"surname : " + surname + ", " +
				"photo : " + photo +
				"}";
		mess1.put("id", id);
		mess1.put("name", name);
		mess1.put("surname", surname);
		mess1.put("photo", photo);
		//------------
		HashMap<String, String> mess2 = new HashMap<>();
		id = "2";
		name = "Роман";
		surname = "Жминько";
		photo = "https://pp.userapi.com/c855216/v855216470/94258/dYssd2RWmhQ.jpg";
		String message2 = "{ id : " + id + ", " +
				"name : " + name + ", " +
				"surname : " + surname + ", " +
				"photo : " + photo +
				"}";
		mess2.put("id", id);
		mess2.put("name", name);
		mess2.put("surname", surname);
		mess2.put("photo", photo);
		//-----------------
		HashMap<String, HashMap<String, String>> mess = new HashMap<>();
		mess.put("1", mess1);
		mess.put("2", mess2);
		String s = new Gson().toJson(mess);
		writer.write(s);*/
		Map<String, String> messageJson = new HashMap<>();
		String message;
		String commandType = request.getParameter(COMMAND);
		if(commandType != null) {
			try {
				FthServletCommand command = CommandType.valueOf(commandType).create();
				message = command.execute(request);
			} catch (IllegalArgumentException | NullPointerException e) {
				messageJson.put("redirect", "http://localhost:8080/FTH/jsp/CustomErrorPage.jsp");
				message = new Gson().toJson(messageJson);
			} catch (FthCommandException e) {
				messageJson.put("redirect", "http://localhost:8080/FTH/jsp/CustomErrorPage.jsp");
				message = new Gson().toJson(messageJson);
			}
		} else {
			messageJson.put("redirect", "http://localhost:8080/FTH/jsp/CustomErrorPage.jsp");
			message = new Gson().toJson(messageJson);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(message);
		request.changeSessionId();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}