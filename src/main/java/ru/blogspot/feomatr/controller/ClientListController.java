/**
 * 
 */
package ru.blogspot.feomatr.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.entity.Client;

/**
 * Handles requests for the application Client List page
 * 
 * @author iipolovinkin
 *
 */
@Controller
@RequestMapping(value = "clients")
public class ClientListController {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientListController.class);
	private ClientService clientService;

	@Inject
	public ClientListController(ClientService clientService) {
		this.setClientService(clientService);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addClientFromForm(@Valid Client client,
			BindingResult bindingResult) {
		logger.info("addClientFromForm");
		if (bindingResult.hasErrors()) {
			return "clients/edit";
		}

		client = getClientService().saveClient(client);
		logger.info(client.toString());

		return "redirect:/clients/" + client.getId();
	}

	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String createClientProfile(Model model) {
		logger.info("createClientProfile");
		model.addAttribute(new Client());
		return "clients/edit";
	}

	@RequestMapping()
	public String showCLients(Model model) {
		logger.info("showClients");

		model.addAttribute("clientList", getClientService().getAllClients());
		return "clients";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showClient(@PathVariable("id") Long id, Model model) {
		logger.info("showClient");
		model.addAttribute("client", getClientService().getClientById(id));
		return "clients/show";
	}

	/**
	 * @return the clientService
	 */
	public ClientService getClientService() {
		return clientService;
	}

	/**
	 * @param clientService
	 *            the clientService to set
	 */
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

}
