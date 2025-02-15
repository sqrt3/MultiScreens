package com.sqrt3.multiscreens.repository;

import com.sqrt3.multiscreens.model.Client;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

  private static final Map<Long, Client> clients = new HashMap<>();
  private Long sequence = 0L;

  public Client save(String name) {
    Client client;
    if (findByName(name) != null) {
      client = findByName(name);

      client.setUpdatedTime(LocalDateTime.now());
      client.setScreenshotRequested(false);
    } else {
      client = new Client();

      client.setId(sequence++);
      client.setName(name);
      client.setUpdatedTime(LocalDateTime.now());
      client.setScreenshotRequested(false);
      clients.put(client.getId(), client);
    }
    return client;
  }

  public Client findByName(String name) {
    for (Client client : clients.values()) {
      if (client.getName().equalsIgnoreCase(name)) {
        return client;
      }
    }
    return null;
  }

  public List<Client> findAll() {
    return new ArrayList<>(clients.values());
  }

  public Client update(String name) {
    Client existingClient = findByName(name);
    if (existingClient != null) {
      existingClient.setUpdatedTime(LocalDateTime.now());
      return existingClient;
    } else {
      return null;
    }
  }

  public Client update(String name, boolean status) {
    Client existingClient = findByName(name);
    if (existingClient != null) {
      existingClient.setScreenshotRequested(status);
      existingClient.setUpdatedTime(LocalDateTime.now());
      return existingClient;
    } else {
      return null;
    }
  }

  public void updateAll() {
    for (Client client : clients.values()) {
      client.setUpdatedTime(LocalDateTime.now());
      client.setScreenshotRequested(true);
    }
  }
}
