package ua.coffee.coffeemarket.scheduling;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.coffee.coffeemarket.models.Item;
import ua.coffee.coffeemarket.services.ItemService;
import ua.coffee.coffeemarket.services.utils.GoogleDriveService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
public class RefreshBaseForItemsFromGoogleDrive {
    private final GoogleDriveService googleDriveService;
    private final ItemService itemService;
    @Scheduled(fixedRate = 21_600_000)
    public void refreshItemsIfNoExist() throws IOException, GeneralSecurityException {
        if (itemService.findIfExist()) {
            List<Item> itemsFromGoogleDrive = googleDriveService.downloadAndConvertItemsFromGoogleDrive();
            System.out.println("get list from google and save to database at: " + LocalDateTime.now());
            itemService.saveAll(itemsFromGoogleDrive);

        } else {
            List<Item> itemList = itemService.findAll();
            googleDriveService.updateGoogleDriveFile(itemList);
            System.out.println("getList from database and save to google at: " + LocalDateTime.now());
        }
    }
}
