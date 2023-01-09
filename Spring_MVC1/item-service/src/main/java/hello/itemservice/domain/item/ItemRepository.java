package hello.itemservice.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    // 기본 HansMap 사용시 여러 쓰레드가 접근하기 때문에 ConcurrentHashMap 사용 권장
    private static final Map<Long, Item> store = new HashMap<>(); // static

    // 실무에서 유저가 여러 접근 시 기분 long은 꼬일 수 있기 때문에 atomic long 사용 권징
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // ArrayList에 감싸서 반환하면 Map에 변화가 없기 때문에 감싸서 리턴
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {

        // dto를 따로 만들어서 값을 받고 업데이트 권장
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
