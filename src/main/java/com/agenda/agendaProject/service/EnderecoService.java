package com.agenda.agendaProject.service;

import com.agenda.agendaProject.dto.EnderecoDTO;
import com.agenda.agendaProject.model.Endereco;
import com.agenda.agendaProject.repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    public RestTemplate restTemplate;
    public EnderecoDTO buscaEnderecoPorCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        EnderecoDTO enderecoDTO = restTemplate.getForObject(url, EnderecoDTO.class);
        return enderecoDTO; // retorna o dto com  o dado do cep

        if (enderecoDTO != null && enderecoDTO.getCep() != null) {
            System.out.println("nao achou o cep");
            //lançar uma exception
        }
        return enderecoDTO;
    }

    public EnderecoDTO salvarEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = converterParaEntidade(enderecoDTO);
        enderecoRepository.save(endereco);
        return converterParaDTO(endereco);
    }

    private EnderecoDTO converterParaDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setUf(endereco.getUf());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setNumero(String.valueOf(endereco.getNumero()));
        return enderecoDTO;
    }

    private Endereco converterParaEntidade(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        endereco.setId(endereco.getId());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setEstado(endereco.getEstado());
        endereco.setCep(endereco.getCep());
        endereco.setCidade(endereco.getCidade());
        endereco.setNumero(endereco.getNumero());
        endereco.setComplemento(endereco.getComplemento());
        return endereco;

    }


}






