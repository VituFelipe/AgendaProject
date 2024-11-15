package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/id")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable int id){
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.salvarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable int id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable int id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }





}
