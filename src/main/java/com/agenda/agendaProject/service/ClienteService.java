package com.agenda.agendaProject.service;

import com.agenda.agendaProject.dto.ClienteDTO;
import com.agenda.agendaProject.dto.EnderecoDTO;
import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.model.Endereco;
import com.agenda.agendaProject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoService enderecoService;


    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tem nao paizao"));
        return converterParaDTO(cliente);
    }

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        EnderecoDTO enderecoDTO = enderecoService.buscaEnderecoPorCep(clienteDTO.getEndereco().getCep());

        clienteDTO.setEndereco(enderecoDTO);

        Cliente cliente = converterParaEntidade(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterParaDTO(cliente);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("cliente deletado"));
        clienteRepository.delete(cliente);
    }

    private Cliente converterParaEntidade(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(converterParaEntidadeEndereco(clienteDTO.getEndereco()));
        return cliente;
    }

    private ClienteDTO converterParaDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setEndereco(converterParaDTOEndereco(cliente.getEndereco()));
        return clienteDTO;
    }

    private Endereco converterParaEntidadeEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setNumero(Integer.parseInt(enderecoDTO.getNumero()));
        endereco.setComplemento(enderecoDTO.getComplemento());
        return endereco;
    }

    private EnderecoDTO converterParaDTOEndereco(Endereco endereco) {
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
