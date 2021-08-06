package pl.damian.shop.flyweight.strategy.mail;

import pl.damian.shop.flyweight.strategy.GenericStrategy;
import pl.damian.shop.flyweight.strategy.mail.model.MailType;

public interface MailStrategy extends GenericStrategy<MailType> {
    void sent();
}
