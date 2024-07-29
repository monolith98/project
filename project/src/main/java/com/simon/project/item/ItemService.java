package com.simon.project.item;

import com.simon.project.member.Member;
import com.simon.project.member.MemberRepository;
import com.simon.project.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price, Authentication auth, String imageUrl){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setMember(auth.getName());
        item.setImageUrl(imageUrl);
        itemRepository.save(item);
    }

    public Item getItemById(Integer id){
        Optional<Item> result = itemRepository.findById(id);
        return result.orElse(null);
        // 해당 id가 존재한다면 Item 반환, 없다면 null 반환

    }

    public void editItem(Integer id, String title, Integer price){
        Optional<Item> editItem = itemRepository.findById(id);
        if (editItem.isPresent()){
            Item item = editItem.get();
            if(title.length() >=100 || price < 0){
                throw new IllegalArgumentException("상품은 100자 이내, 가격은 0보다 큰 숫자를 입력해주시기 바랍니다.");
            }else{
                item.setTitle(title);
                item.setPrice(price);
                itemRepository.save(item);
                // 업데이트된 항목 반환
            }
        }

    }




}
