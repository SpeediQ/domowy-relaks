package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.domowyrelaks.domowyrelaks.model.Client;
import pl.domowyrelaks.domowyrelaks.model.ProductComponent;
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

    @GetMapping("/clientForm")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clientForm";
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute Client client, Model model) {
        clientRepository.save(client);
        model.addAttribute("productComponent", new ProductComponent());
        model.addAttribute("clientId", client.getId());
//        return "redirect:/clientForm";
        return "productComponentForm";
    }
}
