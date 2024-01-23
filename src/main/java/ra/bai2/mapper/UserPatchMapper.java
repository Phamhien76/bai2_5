package ra.bai2.mapper;

import org.springframework.stereotype.Component;
import ra.bai2.dto.request.UserPatchRequest;
import ra.bai2.dto.response.UserResponse;
import ra.bai2.model.User;
@Component
public class UserPatchMapper implements GenericMapper<User, UserPatchRequest, UserResponse>{
    @Override
    public User mapperRequestToEntity(UserPatchRequest userPatchRequest) {
        return User.builder().id(userPatchRequest.getId())
                .status(userPatchRequest.isStatus()).build();
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
