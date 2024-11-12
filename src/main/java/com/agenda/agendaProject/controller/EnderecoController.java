package com.agenda.agendaProject.controller;


import com.agenda.agendaProject.dto.ClienteDTO;
import com.agenda.agendaProject.dto.EnderecoDTO;
import com.agenda.agendaProject.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> listarEndereco() {
        return enderecoService.salvarEndereco();
    }

    @GetMapping
    public EnderecoDTO buscarEnderecoPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id);
    }

    @PostMapping
    public EnderecoDTO criarEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        return enderecoService.salvarEndereco(enderecoDTO);
    }

    @PutMapping("/{id}")
    public EnderecoDTO atualizarEndereco(@PathVariable int id, @RequestBody EnderecoDTO enderecoDTO) {
        return enderecoService.atualizarEndereco(id, enderecoDTO);
    }

    @DeleteMapping("/{id}") {
        public void deletarEndereco (@PathVariable Long id){
            enderecoService.deletarEndereco(id);
        }
    }

}
