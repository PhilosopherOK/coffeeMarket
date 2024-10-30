package ua.coffee.coffeemarket.services.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


@Data
@Service
public class ImbBBService {

    @Value("${ImgBB.key}")
    private String API_KEY;
    private static final String URL = "https://api.imgbb.com/1/upload";
    public String uploadImageAndGetURL(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload an empty file");
        }
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("File should be png/jpeg ");
        }
        long maxFileSize = 10 * 1024 * 1024; // 10 MB in bytes
        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("File size exceeds 10 MB limit");
        }

        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(URL + "?key=" + API_KEY);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        try {
            builder.addBinaryBody("image", file.getInputStream(), org.apache.http.entity.ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
            post.setEntity(builder.build());
            HttpResponse response = client.execute(post);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            String webUrl = extractImageUrl(jsonResponse);
            return webUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractImageUrl(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(jsonResponse);
            boolean isSuccess = rootNode.path("success").asBoolean(false);
            if (isSuccess) {
                String imageUrl = rootNode.path("data").path("url").asText();
                if(imageUrl.equals("missing node")){
                    imageUrl = rootNode.path("data").path("display_url").asText();
                    if(imageUrl.equals("missing node")){
                        imageUrl = rootNode.path("data").path("url_viewer").asText();
                    }
                }
                return imageUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Api cannot upload this file :(");
    }
}
