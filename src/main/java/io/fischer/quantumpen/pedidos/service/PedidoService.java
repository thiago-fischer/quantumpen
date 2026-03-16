package io.fischer.quantumpen.pedidos.service;

import io.fischer.quantumpen.clientes.model.Cliente;
import io.fischer.quantumpen.clientes.repository.ClienteRepository;
import io.fischer.quantumpen.pedidos.dto.request.CreatePedidoDTO;
import io.fischer.quantumpen.pedidos.dto.request.ItemPedidoDTO;
import io.fischer.quantumpen.pedidos.mapper.PedidoMapper;
import io.fischer.quantumpen.pedidos.model.ItemPedido;
import io.fischer.quantumpen.pedidos.model.Pedido;
import io.fischer.quantumpen.pedidos.repository.PedidoRepository;
import io.fischer.quantumpen.produtos.model.Caneta;
import io.fischer.quantumpen.produtos.repository.CanetaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

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

    public Pedido criarPedido(CreatePedidoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());

        List<ItemPedido> itens = new ArrayList<>();

        double total = 0;

        for (ItemPedidoDTO itemDTO : dto.itens()) {

            Caneta produto = canetaRepository.findById(itemDTO.produtoId())
                    .orElseThrow();

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoUnitario(produto.getPreco());

            total += produto.getPreco() * itemDTO.quantidade();

            itens.add(item);
        }

        pedido.setItens(itens);
        pedido.setValorTotal(total);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscar(Long id) {
        return pedidoRepository.findById(id).orElseThrow();
    }
}