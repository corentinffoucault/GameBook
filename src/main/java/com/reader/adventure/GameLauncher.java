package com.reader.adventure;

import com.reader.adventure.ui.gameHandler.IGameModeHandler;
import com.reader.adventure.ui.GameLauncherUI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class GameLauncher {

    private final IGameModeHandler autoHandler;
    private final IGameModeHandler manualHandler;
    private final IGameModeHandler exportHandler;

    public GameLauncher(
            @Qualifier("auto") IGameModeHandler autoHandler,
            @Qualifier("manual") IGameModeHandler manualHandler,
            @Qualifier("export") IGameModeHandler exportHandler) {
        this.autoHandler = autoHandler;
        this.manualHandler = manualHandler;
        this.exportHandler = exportHandler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void launchUI() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Pas d'affichage disponible, interface graphique désactivée.");
        } else {
        SwingUtilities.invokeLater(() -> {
            try {
                GameLauncherUI gameLauncherUI = GameLauncherUI.create();
                gameLauncherUI.setGameOptionHandler(gameOption -> {
                    try {
                        switch (gameOption.GameType()) {
                            case AUTO -> autoHandler.handle(gameOption, gameLauncherUI);
                            case MANUAL -> manualHandler.handle(gameOption, gameLauncherUI);
                            case EXPORT -> exportHandler.handle(gameOption, gameLauncherUI);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
                    }
                });
                gameLauncherUI.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur au démarrage : " + e.getMessage());
            }
        });
    }}
}
