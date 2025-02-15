package com.sqrt3.multiscreens.controller;

import com.sqrt3.multiscreens.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @GetMapping
  public String getAllClients(HttpServletRequest request, Model model) {
    if (request.getRemoteAddr().equals("0:0:0:0:0:0:0:1"))
      model.addAttribute("clients", clientService.findAllClient());
    return "client_list";
  }
}
