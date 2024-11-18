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

    private RestTemplate restTemplate;

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }

    public Endereco buscarEnderecoExternoPorCep(String cep) {
        if (cep == null || !cep.matches("[0-9]{8}")) {
            return null;
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, Endereco.class);
    }

    public void excluirEndereco(Integer id) {
        enderecoRepository.deleteById(id);
    }

    public Optional<Endereco> buscarPorId(int id){
        return enderecoRepository.findById(id);
    }


}
