package com.sqrt3.multiscreens.service;

import com.sqrt3.multiscreens.model.Client;
import com.sqrt3.multiscreens.repository.ClientRepository;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;

  public List<Client> findAllClient() {
    return clientRepository.findAll();
  }

  public Client findClientById(Long id) {
    return clientRepository.findById(id);
  }

  public Client findClientByName(String name) {
    return clientRepository.findByName(name);
  }

  public Client saveClient(String name, MultipartFile file) {
    try {
      File uploadDir = new File("C:/Files/");
      if (!uploadDir.exists()) {
        uploadDir.mkdirs();
      }

      File uploadedFile = new File(uploadDir, name + ".png");
      file.transferTo(uploadedFile);

      return clientRepository.save(name);
    } catch (IOException e) {
      log.error("Error saving client");
      return null;
    }
  }

  public void deleteClientById(Long id) {
    clientRepository.delete(id);
  }

  public Client updateClient(String name, MultipartFile file) {
    Client existingClient = clientRepository.findByName(name);
    if (existingClient == null) {
      throw new IllegalArgumentException("Client not found with id: " + id);
    }
    return clientRepository.update(id);
  }
}
