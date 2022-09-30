package mtspx.ifoodDevWeek.restApi.Repository;

import mtspx.ifoodDevWeek.restApi.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long>
{

}
