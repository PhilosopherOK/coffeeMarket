package ua.coffee.coffeemarket.controllers.view;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.coffee.coffeemarket.models.Announcement;
import ua.coffee.coffeemarket.models.EmailList;
import ua.coffee.coffeemarket.models.Item;
import ua.coffee.coffeemarket.services.*;
import ua.coffee.coffeemarket.services.utils.EmailSenderService;
import ua.coffee.coffeemarket.services.utils.ImbBBService;


@Data
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EmailListService emailListService;
    private final AnnouncementService announcementService;
    private final ItemService itemService;
    private final EmailSenderService emailSenderService;
    private final ImbBBService imbBBService;


    @GetMapping("/")
    public String adminForm() {
        return "admin/main";
    }

    @GetMapping("/upload")
    public String itemForm(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "admin/upload";
    }

    @GetMapping("/announcement")
    public String announcementForm(Model model) {
        model.addAttribute("announcements", announcementService.findAll());
        return "admin/announcement";
    }

    @GetMapping("/emailListing")
    public String emailListingForm() {
        return "admin/emailListing";
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @ModelAttribute Item item,
                                   RedirectAttributes redirectAttributes) {

        try {
            itemService.checkToItemValid(item);
            String webPath = imbBBService.uploadImageAndGetURL(file);

            item.setImagePath(webPath);
            itemService.save(item);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            return "redirect:/admin/upload";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/upload";
        }
    }

    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam(value = "id", required = false, defaultValue = "-1") Long id,
                             RedirectAttributes redirectAttributes) {
        try {
            if(id == null || id <= 0){
                throw new IllegalArgumentException("id must be valid");
            }
            itemService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Item successfully deleted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error deleting item: " + e.getMessage());
        }
        return "redirect:/admin/upload";
    }



    @PostMapping("/announcement")
    public String postSomeAnnouncement(@ModelAttribute Announcement announcement,
                                       RedirectAttributes redirectAttributes) {
        try {
            announcementService.save(announcement);

            redirectAttributes.addFlashAttribute("message", "ad added successfully");
            return "redirect:/admin/announcement";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/announcement";
        }
    }

    @PostMapping("/deleteAnnouncement")
    public String deleteAnnouncement(@RequestParam(value = "id", required = false, defaultValue = "-1") Long id,
                                     RedirectAttributes redirectAttributes) {
        try {
            if(id <= 0){
                throw new IllegalArgumentException("id must be valid");
            }
            announcementService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Announcement successfully deleted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error deleting announcement: " + e.getMessage());
        }
        return "redirect:/admin/announcement";
    }

    @PostMapping("/emailListing")
    public String sendSomeEmail(@ModelAttribute EmailList emailList,
                                RedirectAttributes redirectAttributes) {
        try {
            emailListService.emailValidator(emailList.getEmail(), emailList.getSubject(), emailList.getText());
            emailListService.save(emailList);

            emailSenderService.sendEmail(emailList.getEmail(), emailList.getSubject(), emailList.getText());

            redirectAttributes.addFlashAttribute("message","email was successfully sent to the recipient.");
            return "redirect:/admin/emailListing";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/emailListing";
        }
    }
}
