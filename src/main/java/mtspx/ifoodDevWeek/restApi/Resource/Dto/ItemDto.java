package mtspx.ifoodDevWeek.restApi.Resource.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor

public class ItemDto
{
    private Long produtoId;
    private  int quantidade;
    private Long idSacola;
}
