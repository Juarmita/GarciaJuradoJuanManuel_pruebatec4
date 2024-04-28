package AgenciaTurismo.pruebaTecnica.repository;


import AgenciaTurismo.pruebaTecnica.model.Hosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostsRepository extends JpaRepository<Hosts, Long> {
}
