//package pl.damian.shop.mapper.impl;
//
//import org.springframework.stereotype.Component;
//import pl.damian.shop.domain.dao.User;
//import pl.damian.shop.domain.dto.UserDto;
//import pl.damian.shop.mapper.UserMapper;
//
//@Component
//public class UserMapperImpl implements UserMapper {
//
//    @Override
//    public User dtoToDao(UserDto userDto) {
//        return User.builder()
//                .id(userDto.getId())
//                .email(userDto.getEmail())
//                .firstName(userDto.getFirstName())
//                .lastName(userDto.getLastName())
//                .mobile(userDto.getMobile())
//                .password(userDto.getPassword())
//                .build();
//    }
//
//    @Override
//    public UserDto daoToDto(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .mobile(user.getMobile())
//                .build();
//    }
//}
