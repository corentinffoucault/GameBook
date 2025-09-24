package com.reader.adventure.ui.player.adventurer;

import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.adventurer.model.Adventurer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdventurerSheet extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private boolean locked = false;
    private IAdventurerDao adventurerDao;

    public AdventurerSheet(IAdventurerDao adventurerDao) {
        this.adventurerDao = adventurerDao;
    }

    public Adventurer getAdventurer() {
        return adventurerDao.getAdventurer();
    }

    public void createUi() {
        Adventurer adventurer = adventurerDao.getAdventurer();

        setTitle("Fiche de " + adventurer.getName());
        setSize(500, 600);
        setLocationRelativeTo(null);

        String[] columns = {"Attribut", "Valeur"};

        Object[][] data = {
                {"--- Caractéristiques ---", ""},
                {"Agilité", adventurer.getAgility()},
                {"Force", adventurer.getStrength()},
                {"Intelligence", adventurer.getIntelligence()},
                {"Charisme", adventurer.getCharisma()},
                {"Courage", adventurer.getCourage()},

                {"--- Compétences ---", ""},
                {"Attaque", adventurer.getAttack()},
                {"Parade", adventurer.getParry()},
                {"Lancer", adventurer.getThrowing()},
                {"Esquive", adventurer.getDodge()},
                {"Fouille", adventurer.getSearch()},

                {"--- Magie ---", ""},
                {"Inventivité", adventurer.getInventiveness()},
                {"Magie Physique", adventurer.getPhysical_magic()},
                {"Magie Psychique", adventurer.getPsychic_magic()},
                {"Résistance Magique", adventurer.getMagic_resistance()},

                {"--- Ressources ---", ""},
                {"Or", adventurer.getGold()}
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
        saveButton.addActionListener(e -> save());

        buttonPanel.add(saveButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setVisible(true);
    }

    private void save() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("export de la fiche");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();

            try {
                adventurerDao.exportToJson(path);
                JOptionPane.showMessageDialog(this, "Sauvegarde réussie en JSON !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde : " + ex.getMessage());
            }
        }
    }
}
