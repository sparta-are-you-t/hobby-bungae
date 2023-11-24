package com.example.hobbybungae.domain.hobby;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public HobbyResponseDto postHobby(HobbyRequestDto requestDto) {
        String hobbyName = requestDto.getHobbyName();

        //중복 검사
        Optional<Hobby> checkHobbyName = hobbyRepository.findByHobbyName(hobbyName);
        if(checkHobbyName.isPresent()){
            throw new IllegalArgumentException("중복된 취미 카테고리가 존재합니다.");
        }

        Hobby hobby = new Hobby(hobbyName);
        hobbyRepository.save(hobby);
        return new HobbyResponseDto(hobby);
    }

}