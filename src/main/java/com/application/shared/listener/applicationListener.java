package com.application.shared.listener;

import com.application.core.model.dto.UserDto;
import com.application.core.usecase.user.RegisterUserUseCase;
import com.application.shared.Constant;
import com.application.shared.listener.util.UserCreator;
import com.application.shared.listener.util.UserCreatorImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class applicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private final RegisterUserUseCase registerUserUseCase;
    private final UserCreator userCreator;

    public applicationListener(RegisterUserUseCase registerUserUseCase, UserCreatorImpl userCreator) {
        this.registerUserUseCase = registerUserUseCase;
        this.userCreator = userCreator;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserDto adminUserDto = userCreator.createUser("admin@gmail.com", "admin", "user", Constant.ADMIN_ID_ROLE);
        UserDto asistUserDto = userCreator.createUser("asist@gmail.com", "asist", "user", Constant.ASIST_ID_ROLE);
        UserDto inspectUserDto = userCreator.createUser("inspec@gmail.com", "inspec", "user", Constant.INSPECT_ID_ROLE);
        registerUserUseCase.execute(adminUserDto, Constant.IS_AN_EVENT_LISTENER);
        registerUserUseCase.execute(asistUserDto, Constant.IS_AN_EVENT_LISTENER);
        registerUserUseCase.execute(inspectUserDto, Constant.IS_AN_EVENT_LISTENER);
    }
}
