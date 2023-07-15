package com.example.chat_app.projections;

import java.util.UUID;

public interface UserProjection {
    String getUsername();
     UUID getId();
    String getLast_message();
    Integer getM_count();
}