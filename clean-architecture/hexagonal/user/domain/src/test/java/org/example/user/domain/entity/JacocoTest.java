package org.example.user.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JacocoTest{

    @Test
    @DisplayName("Jacoco reports가 만들어지나?")
    void JACOCO_REPORTS_MAKE_TEST(){



        User user = new User(1, "Jacoco");
    }

}
