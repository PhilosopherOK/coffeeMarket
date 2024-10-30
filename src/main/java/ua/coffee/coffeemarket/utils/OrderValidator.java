package ua.coffee.coffeemarket.utils;

import ua.coffee.coffeemarket.models.DeliveryOrder;
import ua.coffee.coffeemarket.models.TakeAwayOrder;

import java.time.LocalDate;

public class OrderValidator {

    public static Boolean checkToValidOrderForTakeAway(TakeAwayOrder takeAwayOrder) {
        if (takeAwayOrder.getNameNew().isBlank() || takeAwayOrder.getNumberNew().isBlank() ||
                takeAwayOrder.getTimeNew() == null || takeAwayOrder.getCommentsNew().isBlank()) {
            throw new IllegalArgumentException("All fields must be provided and not be empty.");
        }

        if (!PhoneValidator.validate(takeAwayOrder.getNumberNew())) {
            throw new IllegalArgumentException("Phone number must be valid.");
        }

        if (takeAwayOrder.getNameNew().length() > 30) {
            throw new IllegalArgumentException("Name must not exceed 30 characters.");
        }

        if (takeAwayOrder.getCommentsNew().length() > 1000) {
            throw new IllegalArgumentException("Comments must not exceed 1000 characters.");
        }

        return true;
    }
    public  static Boolean checkToValidOrderForDelivery(DeliveryOrder deliveryOrder) {
        if (deliveryOrder.getNameNew().isBlank() || deliveryOrder.getNumberNew().isBlank() ||
                deliveryOrder.getStreetNew().isBlank() || deliveryOrder.getBuildNew().isBlank() ||
                deliveryOrder.getApartNew().isBlank() || deliveryOrder.getCommentsNew().isBlank()) {
            throw new IllegalArgumentException("All fields must be provided and not be empty.");
        }

        if (!PhoneValidator.validate(deliveryOrder.getNumberNew())) {
            throw new IllegalArgumentException("Phone number must be valid.");
        }

        if (deliveryOrder.getNameNew().length() > 30) {
            throw new IllegalArgumentException("Name must not exceed 30 characters.");
        }

        if (deliveryOrder.getCommentsNew().length() > 1000) {
            throw new IllegalArgumentException("Comments must not exceed 1000 characters.");
        }

        if (deliveryOrder.getStreetNew().length() > 50) {
            throw new IllegalArgumentException("Street name must not exceed 50 characters.");
        }

        if (deliveryOrder.getBuildNew().length() > 10) {
            throw new IllegalArgumentException("Building number must not exceed 10 characters.");
        }

        if (deliveryOrder.getApartNew().length() > 10) {
            throw new IllegalArgumentException("Apartment number must not exceed 10 characters.");
        }

        return true;
    }
}
