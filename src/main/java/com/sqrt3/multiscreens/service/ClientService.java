package com.sqrt3.multiscreens.service;

import com.sqrt3.multiscreens.model.Client;
import com.sqrt3.multiscreens.repository.ClientRepository;
import com.sqrt3.multiscreens.util.FileSaver;
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

  public Client findClientByName(String name) {
    Client client = clientRepository.findByName(name);
    if (client == null) {
      throw new IllegalArgumentException("Client not found with name: " + name);
    }
    return client;
  }

  public Client saveClient(String name, MultipartFile file) {
    FileSaver.saveFile(name, file);
    return clientRepository.save(name);
  }

  public Client updateClient(String name, MultipartFile file) {
    Client existingClient = clientRepository.findByName(name);
    if (existingClient == null) {
      throw new IllegalArgumentException("Client not found with name: " + name);
    }
    FileSaver.saveFile(name, file);
    return clientRepository.update(name);
  }

  public Client updateClientStatus(String name, boolean status) {
    Client existingClient = clientRepository.findByName(name);
    if (existingClient == null) {
      throw new IllegalArgumentException("Client not found with name: " + name);
    }
    existingClient.setScreenshotRequested(status);
    return clientRepository.update(name, status);
  }

  public void updateAllClient() {
    clientRepository.updateAll();
  }
}
