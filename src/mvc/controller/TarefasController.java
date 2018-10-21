package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TarefasController {
	@RequestMapping("/")
	public String execute() {
		String user = "Cruno";
		System.out.println("Lógica do MVC");
		return "index";
	}
}