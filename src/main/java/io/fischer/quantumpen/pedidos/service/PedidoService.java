package io.fischer.quantumpen.pedidos.service;

import io.fischer.quantumpen.clientes.model.Cliente;
import io.fischer.quantumpen.clientes.repository.ClienteRepository;
import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.pedidos.dto.request.CreatePedidoDTO;
import io.fischer.quantumpen.pedidos.mapper.PedidoMapper;
import io.fischer.quantumpen.pedidos.model.ItemPedido;
import io.fischer.quantumpen.pedidos.model.Pedido;
import io.fischer.quantumpen.pedidos.repository.PedidoRepository;
import io.fischer.quantumpen.produtos.model.Caneta;
import io.fischer.quantumpen.produtos.repository.CanetaRepository;

import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
public class PedidoService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final CanetaRepository canetaRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         CanetaRepository canetaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.canetaRepository = canetaRepository;
    }

    @Transactional
    public Pedido criarPedido(CreatePedidoDTO dto) {

        log.info("Criando pedido para clienteId={}", dto.clienteId());

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() ->
                        new NotFoundException("Cliente com id " + dto.clienteId() + " não encontrado!")
                );

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());

        List<ItemPedido> itens = dto.itens().stream().map(itemDTO -> {

            Caneta produto = canetaRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() ->
                            new NotFoundException("Caneta com id " + itemDTO.produtoId() + " não encontrada!")
                    );

            log.debug("Adicionando item produtoId={} quantidade={}",
                    itemDTO.produtoId(), itemDTO.quantidade());

            return PedidoMapper.toItemEntity(itemDTO, produto, pedido);

        }).toList();

        pedido.setItens(itens);
        pedido.setValorTotal(calcularTotal(itens));

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        log.info("Listando todos os pedidos");
        return pedidoRepository.findAll();
    }

    public Pedido buscar(Long id) {
        log.info("Buscando pedido id={}", id);
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Pedido com id " + id + " não encontrado!")
                );
    }

    private double calcularTotal(List<ItemPedido> itens) {
        return itens.stream()
                .mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade())
                .sum();
    }
}