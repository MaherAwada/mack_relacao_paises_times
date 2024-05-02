package br.com.exemplo3.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StatusController.URL)
public class StatusController {
	
	public static final String URL = "/api/status";

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
    
}
