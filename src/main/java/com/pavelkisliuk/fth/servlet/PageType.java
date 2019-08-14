package com.pavelkisliuk.fth.servlet;

public enum PageType {
	CLIENT_AUTH("ClientAuth.jsp"),
	TRAINER_AUTH("TrainerAuth.jsp"),
	CLIENT_PAGE("ClientPage.jsp"),
	TRAINER_PAGE("TrainerPage.jsp"),
	TRAINER_REG("TrainerReg.jsp"),
	ERROR_400("error400.jsp"),
	ERROR_404("error404.jsp"),
	ERROR_500("error500.jsp"),
	INDEX("../index.jsp");

	private String url;

	PageType(String url) {
		this.url = url;
	}

	public String get() {
		return url;
	}
}