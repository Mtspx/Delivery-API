package mtspx.ifoodDevWeek.restApi.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Embeddable

public class Endereco
{
    private String cep;
    private String complemento;
}
