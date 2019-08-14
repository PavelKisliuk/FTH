package com.pavelkisliuk.fth.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*")
public class MutualFilter implements Filter {
	private enum ContentType {
		JSP("text/html"),
		CSS("text/css"),
		JS("application/javascript"),
		GIF("image/gif"),
		JPEG("image/jpeg"),
		PNG("image/png"),
		SVG("image/svg"),
		DEFAULT("application/json");
		private String type;

		ContentType(String type) {
			this.type = type;
		}
	}

	private static final String CHARACTER_ENCODING = "UTF-8";

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String extension = receiveExtension(request.getRequestURI());
		switch (extension) {
			case "jsp":
				servletResponse.setContentType(ContentType.JSP.type);
				break;
			case "css":
				servletResponse.setContentType(ContentType.CSS.type);
				break;
			case "js":
				servletResponse.setContentType(ContentType.JS.type);
				break;
			case "gif":
				servletResponse.setContentType(ContentType.GIF.type);
				break;
			case "jpeg":
				servletResponse.setContentType(ContentType.JPEG.type);
				break;
			case "png":
				servletResponse.setContentType(ContentType.PNG.type);
				break;
			case "svg":
				servletResponse.setContentType(ContentType.SVG.type);
				break;
			default:
				servletResponse.setContentType(ContentType.DEFAULT.type);
				break;
		}
		servletResponse.setCharacterEncoding(CHARACTER_ENCODING);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private String receiveExtension(String uri) {
		String[] uriGroup = uri.split("/");
		String[] lastElement = uriGroup[uriGroup.length - 1].split("\\.");
		return lastElement[lastElement.length - 1];
	}
}