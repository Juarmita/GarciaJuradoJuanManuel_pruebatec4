package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.model.Hosts;

public interface IHostService {
    Hosts getHostById(Long id);
    void saveHost(Hosts host);
}
