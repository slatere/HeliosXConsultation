package com.slatere.heliosx.response;

import java.util.UUID;

public class UserPerscribeResponse {

    private UUID userId;

    private boolean likelyToPerscribe;

    public UserPerscribeResponse(UUID userId, boolean likelyToPerscribe) {
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
