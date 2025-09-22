package com.reader.adventure.ui.player.Adventurer;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.player.model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class AdventurerSheet extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private boolean locked = false;
    private IPlayerDao playerDao;

    public AdventurerSheet(IPlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void createUi() {
        Player player = playerDao.getPlayer();

        setTitle("Fiche de " + player.getName());
        setSize(500, 600);
        setLocationRelativeTo(null);

        String[] columns = {"Attribut", "Valeur"};

        Object[][] data = {
                {"--- Caractéristiques ---", ""},
                {"Agilité", player.getAgility()},
                {"Force", player.getStrength()},
                {"Intelligence", player.getIntelligence()},
                {"Charisme", player.getCharisma()},
                {"Courage", player.getCourage()},

                {"--- Compétences ---", ""},
                {"Attaque", player.getAttack()},
                {"Parade", player.getParry()},
                {"Lancer", player.getThrowing()},
                {"Esquive", player.getDodge()},
                {"Fouille", player.getSearch()},

                {"--- Magie ---", ""},
                {"Inventivité", player.getInventiveness()},
                {"Magie Physique", player.getPhysical_magic()},
                {"Magie Psychique", player.getPsychic_magic()},
                {"Résistance Magique", player.getMagic_resistance()},

                {"--- Ressources ---", ""},
                {"Or", player.getGold()}
        };

        model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) return false;
                Object val = getValueAt(row, 0);
                return !(val.toString().startsWith("---")) && !locked;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Boutons bas
        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("exporter en JSON");
        saveButton.addActionListener(e -> sauvegarder());

        buttonPanel.add(saveButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setVisible(true);
    }

    // TODO: deplacer cela dans le DAO
    private void sauvegarder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("export de la fiche");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();

            try {
                playerDao.exportToJson(path);
                JOptionPane.showMessageDialog(this, "Sauvegarde réussie en JSON !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde : " + ex.getMessage());
            }
        }
    }
}
