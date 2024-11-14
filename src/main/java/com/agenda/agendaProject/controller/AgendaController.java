package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.dto.AgendaDTO;
import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.model.Usuario;
import com.agenda.agendaProject.repository.AgendaRepository;
import com.agenda.agendaProject.repository.ClienteRepository;
import com.agenda.agendaProject.repository.ServicoRepository;
import com.agenda.agendaProject.repository.UsuarioRepository;
import com.agenda.agendaProject.service.AgendaService;
import com.agenda.agendaProject.service.ClienteService;
import com.agenda.agendaProject.service.FuncionarioService;
import com.agenda.agendaProject.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/form")
    public String formAgenda(Model model) {
        model.addAttribute("agendaDTO", new AgendaDTO());
        return "agendaForm";
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(@ModelAttribute("agenda") Agenda agenda) {
        agenda.setStatus(false);
        agendaService.salvarAgenda(agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/editar/{id}")
    public String editarAgenda(@PathVariable("id") Long id, Model model) {
        AgendaDTO agendaDTO = agendaService.buscarPorId(id);
        model.addAttribute("agendaDTO", agendaDTO);
        return "agendaForm";
    }


    @GetMapping("/listar")
    public List<AgendaDTO> listarAgendas(Model model) {
        return agendaService.listarAgendas();
    }
     // to testando os dois
    @GetMapping("/listar")
    public String listarAgenda(Model model){
        List<AgendaDTO> agendas = agendaService.listarAgendas();
        model.addAttribute("agendas", agendas);
        return "listaAgendas";
    }

    @GetMapping("/{id}")
    public AgendaDTO buscarAgendaPorId(@PathVariable Long id) {
        return agendaService.buscarPorId(id);
    }

//    @PostMapping
//    public ResponseEntity<AgendaDTO> criarAgenda(@Valid @RequestBody AgendaDTO agendaDTO, BindingResult result) {
//        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().build(); // Retorna 400 se houver erros de validação
//        }
//        AgendaDTO savedAgenda = agendaService.salvarAgenda(agendaDTO);
//        return ResponseEntity.ok(savedAgenda); // Retorna 200 com a agenda criada
//    }

    @PutMapping("/{id}/realizar")
    public AgendaDTO realizarServico(@PathVariable Long id) {
        return agendaService.realizarServico(id);
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarAgenda(@PathVariable("id") Long id, @ModelAttribute("agendaDTO") AgendaDTO agendaDTO) {
        //chamar atualizar agenda
        return "redirect:/agendas/listar";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgenda(@PathVariable Long id) {
        agendaService.deletarAgenda(id);
        return ResponseEntity.noContent().build();
    }
}




