package com.example.smartcity.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Tag implements Serializable, Parcelable {
	private Integer id;
	private String nom;

	public Tag() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public boolean equals(@Nullable Object obj) {
		return !(obj == null ||
				!(obj instanceof Tag) ||
				((Tag) obj).getNom().compareTo(this.getNom()) != 0);

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nom);
	}

	public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
		@Override
		public Tag createFromParcel(Parcel in) {
			return new Tag(in);
		}

		@Override
		public Tag[] newArray(int size) {
			return new Tag[size];
		}
	};

	private Tag(Parcel in) {
		nom = in.readString();
	}
}
