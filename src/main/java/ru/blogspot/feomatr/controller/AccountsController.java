/**
 * 
 */
package ru.blogspot.feomatr.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransactionService;

/**
 * 
 * @author iipolovinkin
 *
 */

@Controller
@RequestMapping(value = "accounts")
public class AccountsController {
	private static final Logger logger = LoggerFactory
			.getLogger(AccountsController.class);
	private AccountService accountService;
	private TransactionService transactionService;

	@Inject
	public AccountsController(AccountService accountService,
			TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@RequestMapping()
	public String showAllAccounts(Model model) {
		logger.info(" {}", "showAccounts");
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "accounts";
	}
}
