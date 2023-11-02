package pl.mt.task;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutoServiceController {
    private final MailService mailService;

    public AutoServiceController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    String home() {
        return "index";
    }

    @GetMapping("/form")
    String contactForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact-form";
    }

    @PostMapping("/send")
    String sendMessage(Message message, Model model) {
        try {
            mailService.sendEmail(message);
            mailService.sendAutoReply(message);
            model.addAttribute("status", "Wiadomość wysłana poprawnie!");
        } catch (MessagingException e) {
            model.addAttribute("status", "Wiadomość nie została wysłana :(");
        }
        return "status";
    }
}
