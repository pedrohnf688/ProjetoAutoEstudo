package com.pedrohnf688.api.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "arquivo")
@Table
public class Arquivo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String fileName;
	private String fileType;
	@Lob
	private byte[] data;
	private String fileDownloadUri;
	private long size;

	public Arquivo(String fileName, String fileType, byte[] data, String fileDownloadUri, long size) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.fileDownloadUri = fileDownloadUri;
		this.size = size;
	}

}
