package com.agenda.agendaProject.service;

import com.agenda.agendaProject.dto.EnderecoDTO;
import com.agenda.agendaProject.model.Endereco;
import com.agenda.agendaProject.repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO buscaEnderecoPorCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        EnderecoDTO enderecoDTO = restTemplate.getForObject(url, EnderecoDTO.class);

        if (enderecoDTO == null || enderecoDTO.getCep() == null) {
            throw new RuntimeException("nao achou o cep: " + cep);
        }
        return enderecoDTO;
    }

//    public EnderecoDTO salvarEndereco(EnderecoDTO enderecoDTO){
//        EnderecoDTO endereco = converterParaEntidade(enderecoDTO);
//        enderecoRepository.save(endereco);
//        return converterParaDTO(endereco);
//    }
       public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    private Endereco converterParaEntidade(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setNumero(Integer.parseInt(enderecoDTO.getNumero()));
        endereco.setComplemento(enderecoDTO.getComplemento());
        return endereco;
    }

    private EnderecoDTO converterParaDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setUf(endereco.getUf());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setNumero(String.valueOf(endereco.getNumero()));
        enderecoDTO.setComplemento(endereco.getComplemento());
        return enderecoDTO;
    }
}






