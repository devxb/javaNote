package org.example.user.domain.port.out;

import org.example.user.domain.entity.User;
import org.example.user.domain.usecase.request.UserCreateRequest;

public interface UserRepositoryPort{

    void createUser(UserCreateRequest createRequest);

    User findById(int id);

}
