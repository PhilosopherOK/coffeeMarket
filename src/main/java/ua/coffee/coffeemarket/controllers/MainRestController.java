package ua.coffee.coffeemarket.controllers;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ua.coffee.coffeemarket.models.DeliveryOrder;
import ua.coffee.coffeemarket.models.Item;
import ua.coffee.coffeemarket.models.TakeAwayOrder;
import ua.coffee.coffeemarket.services.DeliveryOrderService;
import ua.coffee.coffeemarket.services.ItemService;
import ua.coffee.coffeemarket.services.TakeAwayOrderService;
import ua.coffee.coffeemarket.services.utils.EmailSenderService;
import ua.coffee.coffeemarket.utils.ApiResponse;
import ua.coffee.coffeemarket.utils.OrderValidator;

import java.util.List;

@Data
@RestController
@RequestMapping("/main")
public class MainRestController {
    private final ItemService itemService;
    private final TakeAwayOrderService takeAwayOrderService;
    private final DeliveryOrderService deliveryOrderService;
    private final EmailSenderService emailSenderService;

    @Value("${admin.mail}")
    private String adminMail;

    @GetMapping("/")
    public List<Item> findAllItems() {
        return itemService.findAll();
    }

    @PostMapping("/order/takeAway")
    public ApiResponse placeAnOrderForTakeout(@RequestBody TakeAwayOrder takeAwayOrder) {
        try {
            OrderValidator.checkToValidOrderForTakeAway(takeAwayOrder);
            takeAwayOrderService.save(takeAwayOrder);
            emailSenderService.sendEmail(adminMail,
                    "К вам заявка на заказ на Вынос от :" + takeAwayOrder.getNameNew(),
                    "Телефон: " + takeAwayOrder.getNumberNew() + "\n" +
                            "На который час: " + takeAwayOrder.getTimeNew() + "\n" +
                            "\n" +
                            "Комантарий: " + takeAwayOrder.getCommentsNew() + "\n");
            return new ApiResponse(true, "your order has been registered");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @PostMapping("/order/delivery")
    public ApiResponse placeAnOrderForDelivery(@RequestBody DeliveryOrder deliveryOrder) {
        try {
            OrderValidator.checkToValidOrderForDelivery(deliveryOrder);
            deliveryOrderService.save(deliveryOrder);

            emailSenderService.sendEmail(adminMail,
                    "К вам заявка на заказ c Доставкой от :" + deliveryOrder.getNameNew(),
                    "Телефон: " + deliveryOrder.getNumberNew() + "\n" +
                            "Улица: " + deliveryOrder.getStreetNew() + "\n" +
                            "дом: " + deliveryOrder.getBuildNew() + "\n" +
                            "квартира: " + deliveryOrder.getApartNew() + "\n" +
                            "\n" +
                            "Комантарий: " + deliveryOrder.getCommentsNew() + "\n");
            return new ApiResponse(true, "your order has been registered");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }
}
