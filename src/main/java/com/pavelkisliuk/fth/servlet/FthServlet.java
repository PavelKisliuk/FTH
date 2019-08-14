package com.pavelkisliuk.fth.servlet;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.command.impl.CommandGetType;
import com.pavelkisliuk.fth.command.impl.CommandPostType;
import com.pavelkisliuk.fth.exception.ConnectionPoolException;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.pool.ConnectionPool;
import com.pavelkisliuk.fth.protection.XssProtector;
import com.pavelkisliuk.fth.service.FthService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/start")
@MultipartConfig
public class FthServlet extends HttpServlet {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String COMMAND = "command";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String TRY_AGAIN_MESSAGE = "Something wrong! Try again later.";
	private static final XssProtector XSS_PROTECTOR = new XssProtector();

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			if (!ConnectionPool.INSTANCE.isOpen()) {
				throw new ServletException("Pool damaged.");
			}
		} catch (ConnectionPoolException e) {
			throw new ServletException("Pool damaged.");
		}
	}

	@Override
	public void destroy() {
		try {
			ConnectionPool.INSTANCE.destroyPool();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException(
					"Can't destroy a pool of connections!", e);
		}
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> messageJson = new HashMap<>();

		String commandType = request.getParameter(COMMAND);
		FthServletCommand command;
		try {
			command = CommandGetType.valueOf(commandType).create();
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.FATAL,
					"INCORRECT DATA FROM CLIENT!!!", e);
			messageJson.put(FthService.KEY_ERROR, PageType.ERROR_400.get());
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		}
		execute(command, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> messageJson = new HashMap<>();

		String commandType = request.getParameter(COMMAND);
		FthServletCommand command;
		try {
			command = CommandPostType.valueOf(commandType).create();
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.FATAL,
					"INCORRECT DATA FROM CLIENT!!!", e);
			messageJson.put(FthService.KEY_ERROR, PageType.ERROR_400.get());
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		}
		execute(command, request, response);
	}

	private void execute(FthServletCommand command, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> messageJson = new HashMap<>();
		String message;
		try {
			message = command.execute(request);
		} catch (FthCommandException e) {
			LOGGER.log(Level.ERROR,
					"NOT CRITICAL ERROR OCCURRED!!!", e);
			messageJson.put(ERROR_MESSAGE, TRY_AGAIN_MESSAGE);
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		} catch (NullPointerException e) {
			LOGGER.log(Level.FATAL,
					"SERVER ERROR OCCURRED!!! NULL POINTER EXCEPTION!", e);
			messageJson.put(FthService.KEY_ERROR, PageType.ERROR_500.get());
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		} catch (ClassCastException e) {
			LOGGER.log(Level.FATAL,
					"SERVER ERROR OCCURRED!!! CLASS CAST EXCEPTION!", e);
			messageJson.put(FthService.KEY_ERROR, PageType.ERROR_500.get());
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.FATAL,
					"SERVER ERROR OCCURRED!!! ILLEGAL ARGUMENT EXCEPTION!", e);
			messageJson.put(FthService.KEY_ERROR, PageType.ERROR_500.get());
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		} catch (RuntimeException e) {
			LOGGER.log(Level.FATAL,
					"SERVER ERROR OCCURRED!!! ANOTHER RUNTIME EXCEPTION OCCURRED!", e);
			messageJson.put(FthService.KEY_ERROR, PageType.ERROR_500.get());
			response.getWriter().write(FthService.GSON.toJson(messageJson));
			return;
		}
		response.getWriter().write(XSS_PROTECTOR.protect(message));
		if (!request.getSession().isNew()) {
			request.changeSessionId();
		}
	}
}