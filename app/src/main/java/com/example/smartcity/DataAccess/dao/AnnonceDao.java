package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.AnnonceService;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.PageResultAnnonce;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public class AnnonceDao implements AnnonceDataAccess {

	@Override
	public Response<ArrayList<Annonce>> getAnnonceEtudiant(int id) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(AnnonceService.class)
				.getAnnonceEtudiant(id)
				.execute();
	}

	@Override
	public Response<PageResultAnnonce> getResultatSerch(int ligne, CritereRecherche critereRecherche) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(AnnonceService.class)
				.getResultatSerch(ligne, critereRecherche)
				.execute();
	}

	@Override
	public Response<Void> acceptAnnonce(int id) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(AnnonceService.class)
				.acceptAnnonce(id)
				.execute();
	}
/*
	@Override
	public ArrayList<Annonce> getAnnonceEtudiant(AccessToken accessToken, UserEtudiant userEtudiant) throws Exception {
		URL url = new URL("https://smartcityjober.azurewebsites.net/postulation/etudiant/"+ userEtudiant.getEtudiant().getId());
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer"+accessToken);
		connection.setRequestProperty("Content-Type","application/json");
		connection.setRequestProperty("Accept","application/json");
		switch (connection.getResponseCode()) {
			case 400: throw new NothingFoundException();
			case 500: throw new ApiAccessException();
		}
		BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String stringJSON, line;
		while((line = buffer.readLine())!=null)
			builder.append(line);
		buffer.close();
		stringJSON = builder.toString();
		return Utils.jsonToAnnonces(stringJSON);
	}

	@Override
	public ArrayList<Annonce> getResultatSerch(AccessToken accessToken, GregorianCalendar dateDebut, GregorianCalendar dateFin, ArrayList<Tag> tags) throws Exception{
		StringBuilder tagDeRecherche = new StringBuilder();
		for (Tag tag: tags) {
			tagDeRecherche.append(tag.getNom());
			tagDeRecherche.append(',');
		}
		if (tags.size() == 0) tagDeRecherche.append("\"\"");
		URL url = new URL("https://smartcityjober.azurewebsites.net/annonce/recherche/"+Utils.CalendarToString(dateDebut)+"_"+Utils.CalendarToString(dateFin)+"_"+tagDeRecherche);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer"+accessToken);
		connection.setRequestProperty("Content-Type","application/json");
		connection.setRequestProperty("Accept","application/json");
		switch (connection.getResponseCode()) {
			case 404: throw new EtudiantDontExist();
			case 500: throw new ApiAccessException();
		}
		BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String stringJSON, line;
		while((line = buffer.readLine())!=null)
			builder.append(line);
		buffer.close();
		stringJSON = builder.toString();
		if(stringJSON.isEmpty()) throw  new NothingFoundException();
		return Utils.jsonToAnnonces(stringJSON);
	}

	@Override
	public void acceptAnnonce(AccessToken accessToken, Annonce annonce, UserEtudiant userEtudiant)throws Exception {

		String jsonString = Utils.postulationToJson(annonce.getId(), userEtudiant.getEtudiant().getId());
		URL url = new URL("https://smartcityjober.azurewebsites.net/postulation");
		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
		urlConnection.setRequestProperty("Authorization","Bearer "+accessToken);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type","application/json");
		urlConnection.setRequestProperty("Accept","application/json");
		urlConnection.setDoOutput(true);

		OutputStream out = urlConnection.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(out);
		urlConnection.connect();

		writer.write(jsonString);

		writer.flush();
		writer.close();
		out.close();
		switch (urlConnection.getResponseCode()) {
			case 400: throw new AlreadyPostul();
			case 404: throw new EtudiantDontExist();
			case 500: throw new ApiAccessException();
		}
		urlConnection.disconnect();
	}*/
}
