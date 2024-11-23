package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.entity.Cliente;
import com.AgendamentoProject.agenda.service.AgendaService;
import com.AgendamentoProject.agenda.service.ClienteService;
import com.AgendamentoProject.agenda.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/clientes")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("/cliente/clientes");
        mv.addObject("clientes", clienteService.findAll());
        return mv;
    }

    @GetMapping("/cliente")
    public ModelAndView add(Cliente cliente){
        ModelAndView mv = new ModelAndView("cliente/clienteform");
        mv.addObject("clientes", clienteService.findAll());
        return  mv;
    }

    @GetMapping("/clienteDelete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        clienteService.delete(id);
        return findAll();
    }

    @GetMapping("/cliente/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return add(clienteService.findById(id).get());
    }

    @PostMapping("/cliente")
    public ModelAndView save(Cliente cliente, BindingResult result){
        if (result.hasErrors()  ){
            return add(cliente);
        }
        cliente.setEndereco(enderecoService.add(cliente.getEndereco()));
        clienteService.add(cliente);
        return findAll();
    }


//    @GetMapping("/criar")
//    public String criarCliente(Model model, @RequestParam(required = false) Integer id) {
//        Cliente cliente = id != null
//                clienteService.buscarporid(id).orElse(new Cliente()) :
//                new Cliente();
//        model.addAttribute("cliente", cliente);
//        return "/clientes/criar";
//    }
//
//    @GetMapping
//    public String listarClientes(Model model) {
//        List<Cliente> clientes = clienteService.listarCliente();
//        model.addAttribute("clientes", clientes);
//        return "clientes/clientes";
//    }
//
//    @GetMapping("/buscar")
//    public String buscarPorNome(@RequestParam String nome, Model model) {
//        List<Cliente> clientes = clienteService.buscarPorNome(nome);
//        model.addAttribute("clientes", clientes);
//        return "clientes/clientes";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String editarCliente(@PathVariable("id") Integer id, Model model) {
//        Optional<Cliente> clienteOptional = clienteService.buscarporid(id);
//        if (clienteOptional.isPresent()) {
//            model.addAttribute("cliente", clienteOptional.get());
//            return "clientes/criar";
//        } else {
//            return "redirect:/clientes";
//        }
//    }
//
//    @PostMapping("/salvar")
//    public String salvarCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("cliente", cliente);
//            return "/clientes/criar"; // Retorna para o formulário de criação em caso de erro
//        }
//        clienteService.salvarCliente(cliente);
//        return "redirect:/clientes"; // Redireciona para a lista de clientes
//    }
//
//    @GetMapping("/excluir/{id}")
//    public String excluirCliente(@PathVariable Integer id) {
//        clienteService.excluirCliente(id);
//        return "redirect:/clientes";
//    }

}
