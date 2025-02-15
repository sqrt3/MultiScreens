package com.sqrt3.multiscreens.controller;

import com.sqrt3.multiscreens.model.Client;
import com.sqrt3.multiscreens.service.ClientService;
import com.sqrt3.multiscreens.dto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientApiController {

  private final ClientService clientService;

  @GetMapping
  public ResponseEntity<ResponseDto<List<Client>>> getClients() {
    return ResponseEntity.ok(new ResponseDto<>("success", "Clients retrieved successfully", clientService.findAllClient()));
  }

  @PostMapping
  public ResponseEntity<ResponseDto<Client>> createClient(@RequestParam String name, @RequestParam MultipartFile file) {
    return ResponseEntity.ok(new ResponseDto<>("success", "Client created successfully", clientService.saveClient(name, file)));
  }

  @GetMapping("/{name}")
  public ResponseEntity<ResponseDto<Client>> getClient(@PathVariable String name) {
    return ResponseEntity.ok(new ResponseDto<>("success", "Client retrieved successfully", clientService.findClientByName(name)));
  }

  @PutMapping("/{name}")
  public ResponseEntity<ResponseDto<Client>> updateClient(@PathVariable String name, @RequestParam MultipartFile file) {
    return ResponseEntity.ok(new ResponseDto<>("success", "Client updated successfully", clientService.updateClient(name, file)));
  }

  @PutMapping
  public ResponseEntity<ResponseDto<Void>> updateAllClients() {
    clientService.updateAllClient();
    return ResponseEntity.ok(new ResponseDto<>("success", "All clients updated successfully", null));
  }

  @GetMapping("/{name}/status")
  public ResponseEntity<ResponseDto<Boolean>> getClientStatus(@PathVariable String name) {
    return ResponseEntity.ok(new ResponseDto<>("success", "Client status retrieved successfully", clientService.findClientByName(name).getScreenshotRequested()));
  }

  @PutMapping("/{name}/status")
  public ResponseEntity<ResponseDto<Client>> updateClientStatus(@PathVariable String name, @RequestParam boolean status) {
    return ResponseEntity.ok(new ResponseDto<>("success", "Client status updated successfully", clientService.updateClientStatus(name, status)));
  }
}