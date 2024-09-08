package com.application.javafxtest.model;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.controller.utility.LoginUtility;
import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.data.EmailService;
import com.application.javafxtest.data.UserManager;
import com.application.javafxtest.model.interfaces.SceneManagerFactory;
import dev.mccue.feather.Provides;
import jakarta.inject.Singleton;

import javax.sql.DataSource;

public record FeatherModule(
        @Provides  SceneManager sceneManager,
        @Provides LoginUtility logs,
        @Provides EmailService emailService,
        @Provides UserPreferences userPreferences,

        @Provides DatabaseManager dbManager,

        @Provides DataSource db
        ) {
        public FeatherModule withSceneManager(SceneManager sceneManager) { return new FeatherModule(sceneManager, logs, emailService, userPreferences, dbManager, db); }
        @Provides
        public SceneManagerFactory sceneManagerFactory() { return stage -> new SceneManager(stage, this); };

        @Provides @Singleton
        public UserManager userManager() { return new UserManager(dbManager); }

        @Provides
        public FeatherModule featherModule() { return this; }

}
