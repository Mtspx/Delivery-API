package mtspx.ifoodDevWeek.restApi.Resource;

import lombok.RequiredArgsConstructor;
import mtspx.ifoodDevWeek.restApi.Models.Item;
import mtspx.ifoodDevWeek.restApi.Models.Sacola;
import mtspx.ifoodDevWeek.restApi.Resource.Dto.ItemDto;
import mtspx.ifoodDevWeek.restApi.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaResource
{
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto)
    {
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola (@PathVariable("id") Long id)
    {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento)
    {
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
