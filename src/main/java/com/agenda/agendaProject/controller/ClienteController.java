package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.dto.ClienteDTO;
import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.model.Endereco;
import com.agenda.agendaProject.service.ClienteService;
import com.agenda.agendaProject.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<ClienteDTO> listarTodosClientes() {
        return clienteService.listarClientes();
    }

//    @GetMapping
//    public ClienteDTO buscarClientePorId(@PathVariable Long id) {
//        return clienteService.buscarPorId(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    //tratar erro de validação do criar cliente
    @PostMapping
    public ClienteDTO criarCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.salvarCliente(clienteDTO);
    }



    @DeleteMapping("/{id}") {
        public void deletarAgenda(@PathVariable Long id){
            clienteService.deletarCliente(id);
        }

    }
}