package com.agenda.agendaProject.service;


import com.agenda.agendaProject.dto.AgendaDTO;
import com.agenda.agendaProject.dto.ClienteDTO;
import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.repository.AgendaRepository;
import com.agenda.agendaProject.repository.ClienteRepository;
import com.agenda.agendaProject.repository.FuncionarioRepository;
import com.agenda.agendaProject.repository.ServicoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;
    private ClienteRepository clienteRepository;
    private ServicoRepository servicoRepository;
    private FuncionarioRepository funcionarioRepository;

    public List<AgendaDTO> listarAgendas() {
        return agendaRepository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public AgendaDTO buscarPorId(Long id){
        Agenda agenda= agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda nao tem paizao"));
        return converterParaDTO(agenda);
    }

    public AgendaDTO salvarAgenda(Agenda agendaDTO) {
        if (agendaRepository.existsByClienteAndData(agendaDTO.getCliente().getId())) {
            throw new RuntimeException("Horario ja agendado");
        }
        agendaDTO.setStatus(false);
        Agenda agenda = converterParaEntidade(agendaDTO);
        agendaRepository.save(agenda);
        return converterParaDTO(agenda);
    }

    public AgendaDTO realizarServico(Long id){
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento nao encontrado"));

        if (!agenda.isStatus()){
            agenda.setStatus(true);       // marca o trampo como true
            agendaRepository.save(agenda);
        } else {
            throw new RuntimeException("Serviço ja foi realizado");
        }
        return converterParaDTO(agenda);
    }

    @Transactional
    public Agenda atualizarAgenda(int id, Agenda agendaAtualizada) {
        Agenda agendaExistente = agendaRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Agenda não tem"));

        if (agendaExistente.isStatus()) {
            throw new RuntimeException("Não é possível editar.");
        }

        validarDuplicidade(agendaAtualizada);

        agendaExistente.setDescricao(agendaAtualizada.getDescricao());
        agendaExistente.setServico(agendaAtualizada.getServico());
        agendaExistente.setData(agendaAtualizada.getData());
        agendaExistente.setCliente(agendaAtualizada.getCliente());
        agendaExistente.setFuncionario(agendaAtualizada.getFuncionario());
        agendaExistente.setUsuario(agendaAtualizada.getUsuario());

        return agendaRepository.save(agendaExistente);
    }

    private void validarDuplicidade(Agenda agenda) {
        Optional<Agenda> agendaExistente = agendaRepository
                .findByDataAndClienteId(agenda.getCliente(), agenda.getData()   );

        if (agendaExistente.isPresent() && agendaExistente.get().getId() != agenda.getId()) {
            throw new RuntimeException("Já existe um agendamento para o cliente na mesma data e horário.");
        }
    }

    public void deletarAgenda(Long id){
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda nao encontrada"));
        if (agenda.isStatus()){
            throw new RuntimeException("Serviço realizado nao pode ser excluido com status");
        }
        agendaRepository.delete(agenda);
    }

    private Agenda converterParaEntidade(Agenda agendaDTO){
        Agenda agenda = new Agenda();
        agenda.setId(agendaDTO.getId());
        agenda.setDescricao(agendaDTO.getDescricao());
        agenda.setData(agendaDTO.getData());
        agenda.setStatus(agendaDTO.isStatus());

        return agenda;
    }

    private AgendaDTO converterParaDTO(Agenda agenda){
        AgendaDTO agendaDTO = new AgendaDTO();

        agendaDTO.setId(Long.valueOf(agenda.getId()));
        agendaDTO.setDescricao(agenda.getDescricao());
        agendaDTO.setData(String.valueOf(agenda.getData()));
        agendaDTO.setStatus(agenda.isStatus());
        if (agenda.getCliente() != null){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(agenda.getCliente().getId());
            clienteDTO.setNome(agenda.getCliente().getNome());
            clienteDTO.setTelefone(agenda.getCliente().getTelefone());
            clienteDTO.setEmail(agenda.getCliente().getEmail());
        }
        return agendaDTO;
    }
}
