package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import pl.domowyrelaks.domowyrelaks.model.Client;
import pl.domowyrelaks.domowyrelaks.repository.ClientRepository;

@Controller
public class ClientController {
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
