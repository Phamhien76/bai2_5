package ra.bai2.mapper;

import org.springframework.stereotype.Component;
import ra.bai2.dto.request.UserPostRequest;
import ra.bai2.dto.response.UserResponse;
import ra.bai2.model.User;

import java.util.Date;

@Component
public class UserPostMapper implements GenericMapper<User, UserPostRequest, UserResponse>{
    @Override
    public User mapperRequestToEntity(UserPostRequest userRequest) {
        return User.builder().name(userRequest.getName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .created(new Date())
                .permission(userRequest.isPermission())
                .status(userRequest.isStatus()).build();
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
