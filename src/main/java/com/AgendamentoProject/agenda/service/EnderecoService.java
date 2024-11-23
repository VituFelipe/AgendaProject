package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.Endereco;
import com.AgendamentoProject.agenda.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    private ClienteService clienteService;
    private final RestTemplate restTemplate;

    @Autowired
    public EnderecoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Endereco> findALl(){
        return enderecoRepository.findAll();
    }

    public Endereco add(Endereco endereco){
        return enderecoRepository.saveAndFlush(endereco);
    }

    public Optional<Endereco> findOne(Integer id){
        return  enderecoRepository.findById(id);
    }

    public void delete (Integer id){
        enderecoRepository.deleteById(id);
    }

    public Endereco buscarEnderecoExternoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        Endereco endereco = restTemplate.getForObject(url, Endereco.class);
        if (cep == null || !cep.matches("[0-9]{8}")) {
            throw new RuntimeException("NAO TEM O CEP IRMÃO");
        }
        return restTemplate.getForObject(url, Endereco.class);
    }

//    private RestTemplate restTemplate;
//
//    public Endereco salvarEndereco(Endereco endereco) {
//        return enderecoRepository.save(endereco);
//    }
//
//    public List<Endereco> listarEnderecos() {
//        return enderecoRepository.findAll();
//    }
//
//    public Optional<Endereco> buscarPorCep(String cep) {
//        return enderecoRepository.findByCep(cep);
//    }
//
//    public Endereco buscarEnderecoExternoPorCep(String cep) {
//        if (cep == null || !cep.matches("[0-9]{8}")) {
//            return null;
//        }
//
//        String url = "https://viacep.com.br/ws/" + cep + "/json/";
//        return restTemplate.getForObject(url, Endereco.class);
//    }
//
//    public void excluirEndereco(Integer id) {
//        enderecoRepository.deleteById(id);
//    }
//
//    public Optional<Endereco> buscarPorId(int id){
//        return enderecoRepository.findById(id);
//    }


}
