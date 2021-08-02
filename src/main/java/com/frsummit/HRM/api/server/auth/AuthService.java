package com.frsummit.HRM.api.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.frsummit.HRM.model.User;
import com.frsummit.HRM.service.UserService;

@Service
public class AuthService {

	// public static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserService userService;

	public AuthService() {
//		Thread t = new Thread(new TestAuth(this));
//		t.start();
	}

	public AuthResult authenticate(String email, String password) {
		User user = userService.findUserByEmail(email);
		if (user == null) {
			return new AuthResult(AuthStatus.FAILED, "The user with email " + email + " was not found !",
					AuthErrorCode.EMAIL_NOT_FOUND);
		} else if (encoder.matches(password, user.getPassword())) {
			return new AuthResult(AuthStatus.SUCCESS, "Authenticated successfully", null);
		} else {
			return new AuthResult(AuthStatus.FAILED, "Invalid password", AuthErrorCode.PASSWORD_INCORRECT);
		}
	}

	public String encode(String password) {
		return encoder.encode(password);
	}
}
//
//class TestAuth implements Runnable {
//	private OAuthService auth;
//
//	public TestAuth(OAuthService auth) {
//		this.auth = auth;
//	}
//
//	@Override
//	public void run() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("============ TEST AUTHENTICATION ============");
//
//		String email = "api.user@hrm";
//		String pass = "12345678";
//		AuthResult result = this.auth.authenticate(email, pass);
//		System.out.println("============ FIRST AUTHENTICATION ============");
//		System.out.println("email : " + email);
//		System.out.println("pwd   : " + pass);
//		System.out.println("==============================================");
//		System.out.println("status : " + result.getStatus());
//		System.out.println("message : " + result.getMessage());
//		System.out.println("error_code : " + result.getCode());
//
//		email = "api.user@hrm";
//		pass = "1234";
//		result = this.auth.authenticate(email, pass);
//		System.out.println("============ SECOND AUTHENTICATION ===========");
//		System.out.println("email : " + email);
//		System.out.println("pwd   : " + pass);
//		System.out.println("==============================================");
//		System.out.println("status : " + result.getStatus());
//		System.out.println("message : " + result.getMessage());
//		System.out.println("error_code : " + result.getCode());
//	}
//
//}
