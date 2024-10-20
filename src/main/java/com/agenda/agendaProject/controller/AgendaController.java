package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.model.Usuario;
import com.agenda.agendaProject.repository.AgendaRepository;
import com.agenda.agendaProject.repository.ClienteRepository;
import com.agenda.agendaProject.repository.ServicoRepository;
import com.agenda.agendaProject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/agenda")
    public String listarAgenda(Model model){
        model.addAttribute("agenda", agendaRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("servicos", servicoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "agenda";
    }

    @PostMapping("/agenda/save")
    public String saveAgenda(
            @RequestParam("descricao") String descricao,
            @RequestParam("data") String data,
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("servicoId") Long servicoId,
            @RequestParam("usuarioId") Long usuarioId) {

        Agenda agenda = new Agenda();
        agenda.setDescricao(descricao);
        // colocar set da data

        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        Servico servico = servicoRepository.findById(servicoId).orElse(null);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);


        agenda.setCliente(cliente);
        agenda.setServico(servico);
        agenda.setUsuario(usuario);

        //chamar agenda service pra salvar

        return "redirect:/agenda";
    }

}
