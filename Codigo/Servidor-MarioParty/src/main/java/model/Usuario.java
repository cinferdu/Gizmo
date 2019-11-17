package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3378000445303652318L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "user",unique = true, nullable = false, length = 50)
	private String user;
	@Column(name = "pass", nullable = false, length = 50)
	private String pass;
	
	public Usuario() {}
	
	public Usuario(Long id, String user, String pass) {
		super();
		this.id = id;
		this.user = user;
		this.pass = pass;
	}

	public Usuario(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}
