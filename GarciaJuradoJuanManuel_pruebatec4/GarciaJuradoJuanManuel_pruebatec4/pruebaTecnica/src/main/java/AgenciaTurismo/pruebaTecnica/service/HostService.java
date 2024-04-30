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
        Optional<Hosts> hosts = hostsRepository.findById(id);
        if (hosts.isPresent()) {
            return hosts.get();
        } else {
            throw new RuntimeException("Host not found");
        }
    }
    @Override
    public void saveHost(Hosts host) {
        // Logic to save the host
        hostsRepository.save(host);
    }
}