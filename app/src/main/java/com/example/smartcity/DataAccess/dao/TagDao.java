package com.example.smartcity.DataAccess.dao;

public class TagDao implements TagDataAccess {
/*
	public ArrayList<Tag> getAllTag(AccessToken accessToken)throws Exception{
		URL url = new URL("https://smartcityjober.azurewebsites.net/tag");
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer"+accessToken);
		switch (connection.getResponseCode()) {
			case 500: throw new ApiAccessException();
		}
		BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String stringJSON, line;
		while((line = buffer.readLine())!=null)
			builder.append(line);
		buffer.close();
		stringJSON = builder.toString();
		return Utils.jsonToTags(stringJSON);
	}
	public ArrayList<Tag> getTagsAnnonce(AccessToken accessToken, Annonce annonce)throws Exception{
		URL url = new URL("https://smartcityjober.azurewebsites.net/tag/annonce/"+annonce.getId());
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer"+accessToken);
		connection.setRequestProperty("Content-Type","application/json");
		connection.setRequestProperty("Accept","application/json");
		switch (connection.getResponseCode()) {
			case 404: throw new AnnonceDontExist();
			case 500: throw new ApiAccessException();
		}
		BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String stringJSON , line;
		while((line = buffer.readLine())!=null)
			builder.append(line);
		buffer.close();
		stringJSON = builder.toString();
		return Utils.jsonToTags(stringJSON);
	}
	public ArrayList<Tag>getTagsEtudiant(AccessToken accessToken, UserEtudiant userEtudiant)throws Exception{
		URL url = new URL("https://smartcityjober.azurewebsites.net/tag/etudiant/"+ userEtudiant.getEtudiant().getId());
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer"+accessToken);
		connection.setRequestProperty("Content-Type","application/json");
		connection.setRequestProperty("Accept","application/json");
		switch (connection.getResponseCode()) {
			case 400: throw new NoTag();
			case 500: throw new ApiAccessException();
		}
		BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String stringJSON , line;
		while((line = buffer.readLine())!=null)
			builder.append(line);
		buffer.close();
		stringJSON = builder.toString();
		return Utils.jsonToTags(stringJSON);
	}

*/
}