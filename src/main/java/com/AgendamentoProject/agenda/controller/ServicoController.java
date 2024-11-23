package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Cliente;
import com.AgendamentoProject.agenda.entity.Servico;
import com.AgendamentoProject.agenda.repository.ServicoRepository;
import com.AgendamentoProject.agenda.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ServicoRepository servicoRepository;


    @GetMapping("/servicos")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("servico/servicos");
        mv.addObject("servico", servicoService.findAll());
        return mv;
    }

    @GetMapping("/servicoAdd")
    public ModelAndView add(Servico servico) {
        ModelAndView mv = new ModelAndView("servico/servicoform");
        mv.addObject("servicos", servicoService.findAll());
        return mv;
    }

    @GetMapping("/servicoDelete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        servicoService.delete(id);
        return findAll();
    }
    @GetMapping("/servicoEdit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return add(servicoService.findById(id).get());
    }

    @PostMapping("/servicoSave")
    public ModelAndView save(Servico servico, BindingResult result){
        if (result.hasErrors()  ){
            return add(servico);
        }
        servicoService.saveService(servico);
        return findAll();
    }

}
//    @GetMapping("/criar")
//    public String criarServico(Model model) {
//        model.addAttribute("servico", new Servico());
//        return "servicos/criar";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String editarServico(@PathVariable("id") Integer id, Model model) {
//        Optional<Servico> servicoOptional = servicoService.buscarPorId(id);
//        if (servicoOptional.isPresent()) {
//            model.addAttribute("servico", servicoOptional.get());
//            return "servicos/criar";
//        }
//        return "redirect:/servicos";
//    }
//
//    @GetMapping
//    public String listarServico(Model model) {
//        List<Servico> servicos = servicoService.listarServico();
//        model.addAttribute("servicos", servicos);
//        return "servicos/servicos";
//    }
//
//    @PostMapping("/salvar")
//    public String salvarServico(@ModelAttribute("servico") Servico servico) {
//        servicoService.salvarServico(servico);
//        return "redirect:/servicos";
//    }
//
//    @GetMapping("/excluir/{id}")
//    public String excluirServico(@PathVariable int id) {
//        servicoService.excluirServico(id);
//        return "redirect:/servicos";
//    }



