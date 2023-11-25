package com.example.hobbybungae.domain.userProfile.dto;

import com.example.hobbybungae.domain.hobby.entity.Hobby;
import com.example.hobbybungae.domain.user.entity.User;
import com.example.hobbybungae.domain.userProfile.entity.UserHobby;
import lombok.Getter;

import java.util.List;

public record UserProfileResponseDto<Hobby>(
        Long userId,
        String idName,
        String name,
        String introduction,
        List<Hobby> hobbyList
) {
    public static UserProfileResponseDto of(User user) {
        return new UserProfileResponseDto(
                user.getId(),
                user.getIdName(),
                user.getName(),
                user.getIntroduction(),
                user.getUserHobbyList().stream()
                    .map(userHobby -> userHobby.getHobby()).toList()
        );
    }
}
