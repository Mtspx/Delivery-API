package mtspx.ifoodDevWeek.restApi.Repository;

import mtspx.ifoodDevWeek.restApi.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long>
{

}
