package com.pavelkisliuk.fth.servlet;

public enum PageType {
	CLIENT_AUTH("http://localhost:8080/FTH/jsp/ClientAuth.jsp"),
	TRAINER_AUTH("http://localhost:8080/FTH/jsp/TrainerAuth.jsp"),
	TRAINER_PAGE("http://localhost:8080/FTH/jsp/TrainerPage.jsp");

	private String url;

	PageType(String url) {
		this.url = url;
	}

	public String get() {
		return url;
	}
}