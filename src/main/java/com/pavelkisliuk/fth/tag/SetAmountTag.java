package com.pavelkisliuk.fth.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SetAmountTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private static final String OPTION_OPEN = "<option>";
	private static final String OPTION_CLOSE = "</option>";

	private static final String[] TIME =
			{"0:40", "0:45", "0:50", "1:00", "1:10", "1:20", "1:30", "2:00", "3:00", "5:00"};
	private static final String[] VALUE =
			{"40", "45", "50", "60", "70", "80", "90", "120", "180", "300"};

	private int from;
	private int to;
	private boolean restTime;

	public void setFrom(int from) {
		this.from = from;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public void setRestTime(boolean restTime) {
		this.restTime = restTime;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if (!restTime) {
				for (int i = from; i <= to; i++) {
					pageContext.getOut().print(OPTION_OPEN + i + OPTION_CLOSE);
				}
			} else {
				for (int i = 0; i < TIME.length; i++) {
					pageContext.getOut().print("<option value=\"" + VALUE[i] + "\">" + TIME[i] + OPTION_CLOSE);
				}
			}
		} catch (IOException ioException) {
			throw new JspException("Error: " + ioException.getMessage());
		}
		return SKIP_BODY;
	}
}
