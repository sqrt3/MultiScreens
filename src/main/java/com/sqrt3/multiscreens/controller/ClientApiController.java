package com.sqrt3.multiscreens.controller;

import com.sqrt3.multiscreens.model.Client;
import com.sqrt3.multiscreens.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientApiController {

  private final ClientService clientService;

  @GetMapping
  public List<Client> getClients() {
    return clientService.findAllClient();
  }

  @PostMapping
  public Client createClient(@RequestParam String name, @RequestParam MultipartFile file) {
    return clientService.saveClient(name, file);
  }

  @PutMapping("/{clientId}")
  public Client updateClient(@PathVariable Long clientId, @RequestParam String name, @RequestParam MultipartFile file) {
    return clientService.updateClient(clientId, name, file);
  }

  @GetMapping("/status/{name}")
  public Client getClientById(@PathVariable String name) {
    return clientService.findClientByName(name);
  }
}
