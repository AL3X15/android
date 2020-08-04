package service;

import android.content.Context;

public class AuthSessionService {
	private static final AuthSessionService instance = new AuthSessionService();

	private static final String AUTH_SESSION = "AUTH_SESSION";
	private static final String JWT_AUTH_TOKEN = "JWT_AUTH_TOKEN";

	private AuthSessionService() {
	}

	public static AuthSessionService getInstance() {
		return instance;
	}

	public static void setToken(Context context, String authToken) {
		context
				.getSharedPreferences(AUTH_SESSION, Context.MODE_PRIVATE)
				.edit()
				.putString(JWT_AUTH_TOKEN, authToken)
				.apply();
	}

	public static String getToken(Context context) {
		return context.getSharedPreferences(AUTH_SESSION, Context.MODE_PRIVATE)
				.getString(JWT_AUTH_TOKEN, "");
	}

	public static void disconnectUser(Context context) {
		context.getSharedPreferences(AUTH_SESSION, Context.MODE_PRIVATE)
				.edit()
				.remove(JWT_AUTH_TOKEN)
				.apply();
	}
}
