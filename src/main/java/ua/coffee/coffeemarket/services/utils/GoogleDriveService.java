package ua.coffee.coffeemarket.services.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ua.coffee.coffeemarket.models.Item;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.PostConstruct;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


@Service
public class GoogleDriveService {
    private Drive driveService;

    @Value("${google.drive.idForFile}")
    private String fileId;

    @PostConstruct
    public void initializeDriveService() throws IOException, GeneralSecurityException {
        GoogleCredential credential = GoogleCredential.fromStream(
                new ClassPathResource("coffeemarket-421809-c8d3d65075fd.json").getInputStream()
        ).createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));

        driveService = new Drive.Builder(new NetHttpTransport(), new GsonFactory(), credential)
                .setApplicationName("CoffeeMarket")
                .build();
    }

    //if need to create new file and get new fileId
    public String createGoogleDriveFile() throws IOException, GeneralSecurityException {
        File fileMetadata = new File();
        fileMetadata.setName("ItemsList.json");

        ByteArrayContent mediaContent = new ByteArrayContent("application/json", new byte[0]);

        File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        return uploadedFile.getId();
    }

    public void updateGoogleDriveFile(List<Item> items) throws IOException, GeneralSecurityException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(items);

        ByteArrayContent mediaContent = new ByteArrayContent("application/json", jsonContent.getBytes());

        File fileMetadata = new File();
        fileMetadata.setName("ItemsList.json");

        driveService.files().update(fileId, fileMetadata, mediaContent)
                .execute();
    }

    public List<Item> downloadAndConvertItemsFromGoogleDrive() throws IOException {
        byte[] fileData = downloadFileFromGoogleDrive();

        ObjectMapper mapper = new ObjectMapper();
        List<Item> items = mapper.readValue(new String(fileData), new TypeReference<List<Item>>() {});
        return items;
    }

    public byte[] downloadFileFromGoogleDrive() throws IOException {
        Drive.Files.Get request = driveService.files().get(fileId);
        request.getMediaHttpDownloader().setDirectDownloadEnabled(true);
        HttpResponse response = request.executeMedia();
        return response.getContent().readAllBytes();
    }
}
