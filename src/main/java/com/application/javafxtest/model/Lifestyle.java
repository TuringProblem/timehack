package com.application.javafxtest.model;

import dev.mccue.jdbc.Column;

public record Lifestyle(
        int id,
        @Column(label = "creator_id") int creatorId,
        String title,
        String description,
        String content,
        @Column(label = "is_public") boolean isPublic,
        int downloads,
        double averageRating
){



}
