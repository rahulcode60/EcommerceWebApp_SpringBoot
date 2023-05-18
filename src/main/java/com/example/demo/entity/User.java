package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "userid")
    private Long userId;

    @Column(name = "username")
    private String userName;


    @Column(name = "useraddress")
    private String userAddress;

    @Column(name = "usermobileno")
    private String userMobileNo;

    @Column(name = "userpassword")
    private String userPassword;
    
    @Column(name = "useremail")
    private String useremail;

	public User(Long userId, String userName, String userDescription, String userAddress, String userMobileNo,
			String userPassword, String useremail) {
		super();
		this.userId = userId;
		this.userName = userName;
		
		this.userAddress = userAddress;
		this.userMobileNo = userMobileNo;
		this.userPassword = userPassword;
		this.useremail = useremail;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public User() {
	}

	public User(Long userId, String userName, String userDescription, String userAddress, String userMobileNo,
			String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
	
		this.userAddress = userAddress;
		this.userMobileNo = userMobileNo;
		this.userPassword = userPassword;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName +", userAddress=" + userAddress + ", userMobileNo=" + userMobileNo + ", userPassword=" + userPassword
				+ ", useremail=" + useremail + "]";
	}
    
    
}
