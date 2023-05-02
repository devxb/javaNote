package org.example.user.domain.port.out;

import org.example.user.domain.entity.User;
import org.example.user.domain.usecase.request.UserCreateRequest;
import org.example.user.domain.usecase.response.UserResponse;

public interface UserRepositoryPort{

    void createUser(UserCreateRequest createRequest);

    UserResponse findById(int id);

}
