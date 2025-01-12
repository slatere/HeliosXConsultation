package com.slatere.heliosx.response;

import java.util.UUID;

public class UserPrescribeResponse {

    private UUID userId;

    private boolean likelyToPerscribe;

    public UserPrescribeResponse(UUID userId, boolean likelyToPerscribe) {
        this.userId = userId;
        this.likelyToPerscribe = likelyToPerscribe;
    }

    public UUID getUserId() {
        return userId;
    }

    public boolean isLikelyToPerscribe() {
        return likelyToPerscribe;
    }
}
