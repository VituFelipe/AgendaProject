package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.entity.Cliente;
import com.AgendamentoProject.agenda.entity.Servico;
import com.AgendamentoProject.agenda.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Optional<Servico> findById(Integer id){return servicoRepository.findById(id);}


    public List<Servico> findAll(){
        return servicoRepository.findAll();
    }

    public Servico add(Servico servico){
        return servicoRepository.save(servico);
    }

    public Servico saveService(Servico servico){
        return servicoRepository.saveAndFlush(servico);
    }

    public void delete(Integer id){
        servicoRepository.deleteById(id);
    }


//    @Transactional
//    public Servico criarServico(Servico servico){
//        return servicoRepository.save(servico);
//    }
//    @Transactional
//    public List<Servico> listarServico(){
//        return servicoRepository.findAll();
//    }
//
//    @Transactional
//    public Servico salvarServico(Servico servico){
//        return servicoRepository.save(servico);
//    }
//
//    public Optional<Servico> buscarPorId(int id) {
//        return servicoRepository.findById(id);
//    }
//
//    @Transactional
//    public void excluirServico(Integer id){
//        if (servicoRepository.existsById(id)){
//            servicoRepository.deleteById(id);
//        } throw new RuntimeException("Nao achemo o serviço");
//    }




}
