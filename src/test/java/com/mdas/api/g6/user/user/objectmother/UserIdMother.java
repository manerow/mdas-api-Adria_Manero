package com.mdas.api.g6.user.user.objectmother;

import com.mdas.api.g6.user.user.domain.valueobject.UserId;

import java.util.UUID;

public class UserIdMother {
    public static UserId random() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId random(UUID id) {
        return new UserId(id);
    }
}
