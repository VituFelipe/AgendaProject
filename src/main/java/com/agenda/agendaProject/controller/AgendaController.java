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

    @PostMapping("/salvar")
    public String salvarAgendamento(@ModelAttribute("agenda") Agenda agenda) {
        agenda.setStatus(false);
        agendaService.salvarAgenda(agenda);
        return "redirect:/agenda";
    }

    @GetMapping
    public List<AgendaDTO> listarAgendas(){
        return agendaService.listarAgendas();
    }

    @GetMapping("/{id}")
    public AgendaDTO buscarAgendaPorId(@PathVariable Long id){
        return agendaService.buscarPorId(id);
    }

//    @PostMapping
//    public AgendaDTO criarAgenda(@RequestBody AgendaDTO agendaDTO){
//        return  agendaService.salvarAgenda(agendaDTO);
//    }

    @PostMapping
    public ResponseEntity<AgendaDTO> criarAgenda(@Valid @RequestBody AgendaDTO agendaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build(); // Retorna 400 se houver erros de validação
        }
        AgendaDTO savedAgenda = agendaService.salvarAgenda(agendaDTO);
        return ResponseEntity.ok(savedAgenda); // Retorna 200 com a agenda criada
    }

    @PutMapping("/{id}/realizar")
    public AgendaDTO realizarServico(@PathVariable Long id){
        return agendaService.realizarServico(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> atualizarAgenda(@PathVariable Long id, @Valid @RequestBody AgendaDTO agendaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgenda(@PathVariable Long id){
        agendaService.deletarAgenda(id);
        return ResponseEntity.noContent().build();
    }
}



//    @Autowired
//    private AgendaRepository agendaRepository;
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Autowired
//    private ServicoRepository servicoRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//
//    @GetMapping("/agenda")
//    public String listarAgenda(Model model){
//        model.addAttribute("agenda", agendaRepository.findAll());
//        model.addAttribute("clientes", clienteRepository.findAll());
//        model.addAttribute("servicos", servicoRepository.findAll());
//        model.addAttribute("usuarios", usuarioRepository.findAll());
//        return "agenda";
//    }
//
//    @PostMapping("/agenda/save")
//    public String saveAgenda(
//            @RequestParam("descricao") String descricao,
//            @RequestParam("data") String data,
//            @RequestParam("clienteId") Long clienteId,
//            @RequestParam("servicoId") Long servicoId,
//            @RequestParam("usuarioId") Long usuarioId) {
//
//        Agenda agenda = new Agenda();
//        agenda.setDescricao(descricao);
//        // colocar set da data
//
//        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
//        Servico servico = servicoRepository.findById(servicoId).orElse(null);
//        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
//
//
//        agenda.setCliente(cliente);
//        agenda.setServico(servico);
//        agenda.setUsuario(usuario);
//
//        //chamar agenda service pra salvar
//
//        return "redirect:/agenda";
//    }
//
//}
