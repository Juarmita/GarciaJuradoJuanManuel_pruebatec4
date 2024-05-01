package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.model.Hosts;
import AgenciaTurismo.pruebaTecnica.repository.HostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostService implements IHostService {

    @Autowired
    private HostsRepository hostsRepository;

    @Override
    public Hosts getHostById(Long id) {
        //Logica para obtener un host. Logic to get a host.
        Optional<Hosts> hosts = hostsRepository.findById(id);
        if (hosts.isPresent()) {
            return hosts.get();
        } else {
            throw new RuntimeException("Host not found");
        }
    }
    @Override
    public void saveHost(Hosts host) {
        //Logica para guardar un host. Logic to save a host.
        hostsRepository.save(host);
    }
}