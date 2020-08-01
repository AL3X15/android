package com.example.smartcity.DataAccess;

import com.example.smartcity.MyApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.AuthSessionService;


public class ApiClient {
	private static ApiClient instance;
	private static Retrofit retrofit;

	private static final String API_URL = "https://smartcityjober.azurewebsites.net/";

	public static ApiClient getInstance() {
		if (ApiClient.instance == null) {
			ApiClient.instance = new ApiClient();
		}
		return ApiClient.instance;
	}

	private ApiClient() {
		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

		// Add jwt token to header

		httpClientBuilder
				.addInterceptor(chain -> {
					Request request = chain.request();
					String jwtAuthToken = AuthSessionService.getToken(MyApplication.getInstance().getBaseContext());
					if (!jwtAuthToken.equals("")) {
						request = request.newBuilder().header("Authorization", "Bearer " + jwtAuthToken).build();
					}

					return chain.proceed(request);
				});

		// Log every request
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		httpClientBuilder.addInterceptor(interceptor).build();

		// React on http code error 500 and 401
		httpClientBuilder.addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				Response response = chain.proceed(request);
				MyApplication application = MyApplication.getInstance();

				// Display a Toast when server got an internal error
				if (response.code() == 500) {
					// Toast.makeText(application.getBaseContext(), "Erreur interne du serveur", Toast.LENGTH_LONG).show();
				}

				// Disconnect the user if jwt auth token is expired
				else if (response.code() == 401) {
//                     Toast.makeText(application.getBaseContext(), "Le jeton d'accès a expiré. Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();
//                     AuthSessionService.disconnectUser();
//                     Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
//                     application.startActivity(intent);
				}
				return response;
			}
		});


		// Format date format
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Gson gson = gsonBuilder.create();

		// Build the final retrofit httpClient
		ApiClient.retrofit = new Retrofit.Builder()
				.baseUrl(API_URL)
				.client(httpClientBuilder.build())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
	}

	public Retrofit getRetrofit() {
		return retrofit;
	}
}
