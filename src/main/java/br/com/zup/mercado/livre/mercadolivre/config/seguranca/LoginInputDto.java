package br.com.zup.mercado.livre.mercadolivre.config.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {

	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.login,
				this.password);
	}
}
