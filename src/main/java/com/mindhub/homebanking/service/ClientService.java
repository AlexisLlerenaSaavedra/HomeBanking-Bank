package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {
    public List<ClientDTO> getClientsDTO();
    public ClientDTO getClient( long id);
    public Client findByEmail(String email);
    public void saveClient(Client client);

}
