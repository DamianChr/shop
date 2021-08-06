package pl.damian.shop.flyweight.strategy.mail.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.flyweight.strategy.mail.MailStrategy;
import pl.damian.shop.flyweight.strategy.mail.model.MailType;

@Component
@Slf4j
public class RegisteredMailStrategy implements MailStrategy {
    @Override
    public MailType getType() {
        return MailType.REGISTERED;
    }

    @Override
    public void sent() {
        log.info("Registered");
    }
}
