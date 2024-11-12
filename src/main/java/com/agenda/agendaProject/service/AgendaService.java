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

import java.util.List;
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
        if (!agenda.getStatus()){
            agenda.setStatus(true);       // marca o trampo como true
            agendaRepository.save(agenda);
        } else {
            throw new RuntimeException("Serviço ja foi realizado");
        }
        return converterParaDTO(agenda);
    }

    public void deletarAgenda(Long id){
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda nao encontrada"));
        if (agenda.getStatus()){
            throw new RuntimeException("Serviço realizado nao pode ser excluido");
        }
        agendaRepository.delete(agenda);
    }

    private Agenda converterParaEntidade(Agenda agendaDTO){
        Agenda agenda = new Agenda();
        agenda.setDescricao(agendaDTO.getDescricao());
        agenda.setData(agendaDTO.getData());
        agenda.setStatus(agendaDTO.getStatus());
//        agenda.setCliente(clienteService.buscarPorId(agendaDTO.getClienteId()));
        agenda.setFuncionario(funcionarioService.buscarPorId(agendaDTO.getFuncionarioId()));
        agenda.setServico(servicoService.buscarPorId(agendaDTO.getServicoId()));
        return agenda;
    }

    private AgendaDTO converterParaDTO(Agenda agenda){
        AgendaDTO agendaDTO = new AgendaDTO();

        agendaDTO.setId(agenda.getId());
        agendaDTO.setDescricao(agenda.getDescricao());
        agendaDTO.setData(agenda.getData());
        agendaDTO.setStatus(agenda.isStatus());
        if (agenda.getCliente() != null){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(agenda.getCliente().getId());
            clienteDTO.setNome(agenda.getCliente().getNome());
            clienteDTO.setTelefone(agenda.getCliente().getTelefone());
            clienteDTO.setEmail(agenda.getCliente().getEmail());
            agendaDTO.setCliente(clienteDTO);
        }

//        agendaDTO.setClienteId(clienteService.converterParaDTO(agenda.getCliente()));
//        agendaDTO.setFuncionarioId(funcionarioService.converterParaDTO(agenda.getFuncionario()));
//        agendaDTO.setServicoId(servicoService.converterParaDTO(agenda.getServico()));
        return agendaDTO;
    }




}


//
//    public Agenda buscarPorId(Long id) {
//        return agendaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado!")); // Retorna o agendamento ou lança exceção
//    }
//
//    public void concluirAgendamento(Long id) {
//        Agenda agenda = buscarPorId(id);
//        if (agenda.isStatus()) {
//            throw new IllegalArgumentException("Agendamento já foi concluído.");
//        }
//        agenda.setStatus(true);
//        agendaRepository.save(agenda);
//    }
//
//    public void deletar(Long id) {
//        Agenda agenda = buscarPorId(id);
//        if (agenda.isStatus()) {
//            throw new IllegalArgumentException("Não é permitido excluir agendamentos concluídos.");
//        }
//        agendaRepository.delete(agenda);
//    }
//
//}


//    @Autowired
//    public AgendaService(AgendaRepository agendaRepository,
//                         ClienteRepository clienteRepository,
//                         ServicoRepository servicoRepository,
//                         FuncionarioRepository funcionarioRepository) {
//        this.agendaRepository = agendaRepository;
//        this.clienteRepository = clienteRepository;
//        this.servicoRepository = servicoRepository;
//        this.funcionarioRepository = funcionarioRepository;
//    }
//
//    public AgendaDTO criar(AgendaDTO dto){
//        validarAgendamento(dto);
//
//        Agenda agenda = new Agenda();
//        agenda.setDescricao(dto.getDescricao());
//        agenda.setDataHora(dto.getDataHora());
//        agenda.setStatus(false);
//
//        agenda.setCliente(clienteRepository.findById(dto.getClienteId())
//                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado")));
//
//        agenda.setServico(servicoRepository.findById(dto.getServicoId())
//                .orElseThrow(() -> new EntityNotFoundException("não achou")));
//
//        agenda.setFuncionario(funcionarioRepository.findById(dto.getFuncionarioId())
//                .orElseThrow(() -> new EntityNotFoundException(" não encontrado")));
//
//        return converterParaDTO(agendaRepository.save(agenda));
//    }
//
//    private void validarAgendamento(AgendaDTO dto) {
//        Cliente cliente = clienteRepository.findById(dto.getClienteId())
//                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
//
//        LocalDateTime inicio = dto.getDataHora().minusMinutes(30);
//        LocalDateTime fim = dto.getDataHora().plusMinutes(30);
//
//    }
//
//    public void concluirAgendamento(Long id) {
//        Agenda agenda = agendaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));
//
//        agenda.setStatus(true);
//        agendaRepository.save(agenda);
