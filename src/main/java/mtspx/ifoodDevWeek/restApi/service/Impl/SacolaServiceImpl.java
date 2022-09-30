package mtspx.ifoodDevWeek.restApi.service.Impl;

import lombok.RequiredArgsConstructor;
import mtspx.ifoodDevWeek.restApi.Models.Item;
import mtspx.ifoodDevWeek.restApi.Models.Restaurante;
import mtspx.ifoodDevWeek.restApi.Models.Sacola;
import mtspx.ifoodDevWeek.restApi.Repository.ItemRepository;
import mtspx.ifoodDevWeek.restApi.Repository.ProdutoRepository;
import mtspx.ifoodDevWeek.restApi.Repository.SacolaRepository;
import mtspx.ifoodDevWeek.restApi.Resource.Dto.ItemDto;
import mtspx.ifoodDevWeek.restApi.enumeration.FormaPagamento;
import mtspx.ifoodDevWeek.restApi.service.SacolaService;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService
{
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;

    private final ItemRepository itemRepository;
    @Override
    public Sacola verSacola(Long id)
    {
        return sacolaRepository.findById(id).orElseThrow(
                ()->
                {
                    throw  new RuntimeException("Essa sacola não existe");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento)
    {
        Sacola sacola = verSacola(id);
        if(sacola.getItens().isEmpty())
        {
            throw new RuntimeException("Inclua itens na sacola");
        }
        FormaPagamento formaPagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechado(true);

        return sacolaRepository.save(sacola);
    }

    @Override
    public Item incluirItemNaSacola(ItemDto ItemDto)
    {
        Sacola sacola = verSacola(ItemDto.getIdSacola());

        if (sacola.isFechado())
        {
            throw new RuntimeException("Está sacola está fechada");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(ItemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(ItemDto.getProdutoId())
                        .orElseThrow(
                                () -> {
                                    throw new RuntimeException("Esse produto não existe");
                                }
                        ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty())
        {
            itensDaSacola.add(itemParaSerInserido);
        }
        else
        {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar))
            {
                itensDaSacola.add(itemParaSerInserido);
            }
            else
            {
                throw new RuntimeException("Não é possivel adicionar produtos de restaurantes diferentes. Limpe a sacola ou conclua o pedido.");
            }

        }

        sacolaRepository.save(sacola);

        return itemRepository.save(itemParaSerInserido);
    }
}
