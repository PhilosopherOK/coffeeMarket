package ua.coffee.coffeemarket.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.coffee.coffeemarket.models.EmailList;
import ua.coffee.coffeemarket.repositories.EmailListRepo;
import ua.coffee.coffeemarket.utils.mailValidator;

import java.util.List;

@Data
@Service
public class EmailListService implements ServiceExample<EmailList> {

    private final EmailListRepo emailListRepo;

    public boolean emailValidator(String toEmail, String subject, String text) {
        if (!mailValidator.isValidEmailAddress(toEmail)) {
            throw new IllegalArgumentException("Email should be valid");
        }
        if (subject.isBlank() || text.isBlank()) {
            throw new IllegalArgumentException("Fields must not be empty");
        }
        return true;
    }

    @Transactional
    public void save(EmailList emailList) {
        emailListRepo.save(emailList);
    }

    @Transactional
    @Override
    public void saveAll(List<EmailList> list) {
        emailListRepo.saveAll(list);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        emailListRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, EmailList emailList) {
        emailList.setId(id);
        emailListRepo.save(emailList);
    }

    @Transactional(readOnly = true)
    @Override
    public EmailList findById(Long id) {
        return emailListRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<EmailList> findAll() {
        return emailListRepo.findAll();
    }
}
