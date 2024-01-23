package ra.bai2.mapper;

import org.springframework.stereotype.Component;
import ra.bai2.dto.request.UserPutRequest;
import ra.bai2.dto.response.UserResponse;
import ra.bai2.model.User;

import java.util.Date;

@Component
public class UserPutMapper implements GenericMapper<User, UserPutRequest, UserResponse>{
    @Override
    public User mapperRequestToEntity(UserPutRequest userPutRequest) {
        return User.builder().id(userPutRequest.getId())
                .name(userPutRequest.getName())
                .email(userPutRequest.getEmail())
                .phone(userPutRequest.getPhone())
                .created(new Date())
                .password(userPutRequest.getPassword())
                .status(userPutRequest.isStatus()).build();


    }

    @Override
    public UserResponse mapperEntityToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated(),
                user.getPhone(),
                user.isStatus()
        );
    }
}
