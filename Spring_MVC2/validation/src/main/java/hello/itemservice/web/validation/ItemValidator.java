package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // item == clazz
        // item == subItem
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        // 검증 로직
        // addError에서 Field단위는 new Field(ObjectName, field, message)
        // FieldError의 rejectedValue는 에러값을 저장한다.
        // errors_en.properties 같이 국제화 처리 가능


        // 특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
