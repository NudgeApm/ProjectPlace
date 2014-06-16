package com.nla.domain;

public class KbUsr {
	public KbUsr(String idusr, String usrLogin) {
		super();
		this.idusr = idusr;
		this.usrLogin = usrLogin;
	}
	private String idusr;
	private String usrLogin;
	public String getIdusr() {
		return idusr;
	}
	public void setIdusr(String idusr) {
		this.idusr = idusr;
	}
	public String getUsrLogin() {
		return usrLogin;
	}
	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

}
