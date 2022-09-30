package mtspx.ifoodDevWeek.restApi.service;

import mtspx.ifoodDevWeek.restApi.Models.Item;
import mtspx.ifoodDevWeek.restApi.Models.Sacola;
import mtspx.ifoodDevWeek.restApi.Resource.Dto.ItemDto;

public interface SacolaService
{
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id,int formaPagamento);
    Item incluirItemNaSacola (ItemDto ItemDto);
}
