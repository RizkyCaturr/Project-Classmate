package com.example.trk_5b.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("nim")
	private String nim;

	@SerializedName("tempat_tanggal_lahir")
	private String tempatTanggalLahir;

	@SerializedName("gender")
	private String gender;

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("semester")
	private String semester;

	@SerializedName("id")
	private int id;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getNim() {
		return nim;
	}

	public void setTempatTanggalLahir(String tempatTanggalLahir) {
		this.tempatTanggalLahir = tempatTanggalLahir;
	}

	public String getTempatTanggalLahir() {
		return tempatTanggalLahir;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNama() {
		return nama;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public String getNoHp() {
		return noHp;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}

	public String getKelas() {
		return kelas;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public String getGambar() {
		return gambar;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "DataItem{" +
				"nim='" + nim + '\'' +
				", tempatTanggalLahir='" + tempatTanggalLahir + '\'' +
				", gender='" + gender + '\'' +
				", nama='" + nama + '\'' +
				", noHp='" + noHp + '\'' +
				", kelas='" + kelas + '\'' +
				", semester='" + semester + '\'' +
				", id='" + id + '\'' +
				", gambar='" + gambar + '\'' +
				", email='" + email + '\'' +
				", status='" + status + '\'' +
				'}';
	}

	// Constructor untuk Parcelable
	protected DataItem(Parcel in) {
		nim = in.readString();
		tempatTanggalLahir = in.readString();
		gender = in.readString();
		nama = in.readString();
		noHp = in.readString();
		kelas = in.readString();
		semester = in.readString();
		id = in.readInt();
		gambar = in.readString();
		email = in.readString();
		status = in.readString();
	}

	// Parcelable Creator
	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nim);
		dest.writeString(tempatTanggalLahir);
		dest.writeString(gender);
		dest.writeString(nama);
		dest.writeString(noHp);
		dest.writeString(kelas);
		dest.writeString(semester);
		dest.writeInt(id);
		dest.writeString(gambar);
		dest.writeString(email);
		dest.writeString(status);
	}
}
