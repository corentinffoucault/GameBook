package com.reader.adventure.ui.export;

import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.export.ExportTypeKey;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExportUI {
    private IStoryDao storyDao;
    private IExporterHandler exporterHandler;

    public ExportUI(IStoryDao storyDao) {
        this.storyDao = storyDao;
    }

    public void run(Component parent) {
        showSaveDialog(parent);
    }

    public void setExporterHandler(IExporterHandler exporterHandler) {
        this.exporterHandler = exporterHandler;
    }

    private void showSaveDialog(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Enregistrer sous");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);

        ExportTypeKey[] extensions = ExportTypeKey.values();

        JComboBox<ExportTypeKey> typeBox = new JComboBox<>(extensions);
        typeBox.setSelectedIndex(0);

        JPanel accessoryPanel = new JPanel(new BorderLayout());
        accessoryPanel.add(new JLabel("Type de fichier :"), BorderLayout.NORTH);
        accessoryPanel.add(typeBox, BorderLayout.CENTER);
        accessoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        chooser.setAccessory(accessoryPanel);

        int result = chooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            int selectedIndex = typeBox.getSelectedIndex();
            ExportTypeKey extension = extensions[selectedIndex];

            Path fileToSave = ensureExtension(selectedFile, extension.toString());

            if (fileToSave.toFile().exists()) {
                int overwrite = JOptionPane.showConfirmDialog(parent,
                        "Le fichier existe déjà. Voulez-vous le remplacer ?",
                        "Remplacer le fichier",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (overwrite != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try {
                extension.getExporter().print(storyDao.getStory(), fileToSave);
                if (exporterHandler != null) {
                    exporterHandler.onExportEnded();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Erreur lors de la sauvegarde : " + e.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Path ensureExtension(File f, String extension) {
        String fileName = FilenameUtils.removeExtension(f.getAbsolutePath());
        return Paths.get(fileName + "." + extension);
    }
}
