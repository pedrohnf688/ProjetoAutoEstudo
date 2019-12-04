package com.pedrohnf688.api.servico;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pedrohnf688.api.config.FileStorageException;
import com.pedrohnf688.api.config.MyFileNotFoundException;
import com.pedrohnf688.api.modelo.Arquivo;
import com.pedrohnf688.api.repositorio.ArquivoRepositorio;

@Service
public class ArquivoServico {

	@Autowired
	private ArquivoRepositorio ar;

	public Arquivo storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (fileName.contains("..")) {
				throw new FileStorageException(
						"Desculpa! Nome do arquivo contém sequência de caminho inválida " + fileName);
			}

			Arquivo arquivo = new Arquivo(fileName, file.getContentType(), file.getBytes(), null, 0);

			return ar.save(arquivo);
		} catch (IOException ex) {
			throw new FileStorageException(
					"Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
		}
	}

	public Arquivo getFile(String fileId) {
		return ar.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("Arquivo não encontrado com o id " + fileId));
	}

}
