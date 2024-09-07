package com.application.javafxtest.model;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.controller.utility.LoginUtility;
import com.application.javafxtest.data.EmailService;
import com.application.javafxtest.data.UserManager;
import dev.mccue.feather.Provides;

public record FeatherModule(
        @Provides  SceneManager sceneManager,
        @Provides LoginUtility logs,
        @Provides EmailService emailService,
        @Provides UserManager userManager,
        @Provides UserPreferences userPreferences
        ) {

}
