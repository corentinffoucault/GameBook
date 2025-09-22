package com.reader.adventure.ui.player.Adventurer;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.ui.player.AUIPlayer;

import javax.swing.*;
import java.awt.*;

public class AdventurerForm extends JFrame {
    private JTextField nameField;

    private JTextField agilityField;
    private JTextField strengthField;
    private JTextField intelligenceField;
    private JTextField charismaField;
    private JTextField courageField;

    private JTextField attackField;
    private JTextField parryField;
    private JTextField throwingField;
    private JTextField dodgeField;
    private JTextField searchField;

    private JTextField inventivenessField;
    private JTextField physicalMagicField;
    private JTextField psychicMagicField;
    private JTextField magicResistanceField;

    private JTextField goldField;

    private Player lastCreatedPlayer;
    private AUIPlayer uiPlayerJFrame;
    private IPlayerDao playerDao;

    public AdventurerForm(AUIPlayer uiPlayerJFrame, IPlayerDao playerDao) {
        this.uiPlayerJFrame = uiPlayerJFrame;
        this.playerDao = playerDao;
        setTitle("Création d'un personnage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Champs
        nameField = addField(panel, "Nom");

        agilityField = addField(panel, "Agilité");
        strengthField = addField(panel, "Force");
        intelligenceField = addField(panel, "Intelligence");
        charismaField = addField(panel, "Charisme");
        courageField = addField(panel, "Courage");

        attackField = addField(panel, "Attaque");
        parryField = addField(panel, "Parade");
        throwingField = addField(panel, "Lancer");
        dodgeField = addField(panel, "Esquive");
        searchField = addField(panel, "Fouille");

        inventivenessField = addField(panel, "Inventivité");
        physicalMagicField = addField(panel, "Magie Physique");
        psychicMagicField = addField(panel, "Magie Psychique");
        magicResistanceField = addField(panel, "Résistance Magique");

        goldField = addField(panel, "Or");

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Créer le personnage");
        JButton ficheButton = new JButton("Afficher la fiche");

        createButton.addActionListener(e -> {
            lastCreatedPlayer = createPlayer();
            JOptionPane.showMessageDialog(AdventurerForm.this,
                    "Personnage " + lastCreatedPlayer.getName() + " créé !");
            goToGame(lastCreatedPlayer);
        });

        ficheButton.addActionListener(e -> {
            if (lastCreatedPlayer != null) {
                goToGame(lastCreatedPlayer);
            } else {
                JOptionPane.showMessageDialog(AdventurerForm.this,
                        "⚠ Aucun personnage créé pour l’instant !");
            }
        });

        buttonPanel.add(createButton);
        buttonPanel.add(ficheButton);

        getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private Player createPlayer() {
        Player p = new Player();
        p.setName(nameField.getText());

        p.setAgility(parseInt(agilityField));
        p.setStrength(parseInt(strengthField));
        p.setIntelligence(parseInt(intelligenceField));
        p.setCharisma(parseInt(charismaField));
        p.setCourage(parseInt(courageField));

        p.setAttack(parseInt(attackField));
        p.setParry(parseInt(parryField));
        p.setThrowing(parseInt(throwingField));
        p.setDodge(parseInt(dodgeField));
        p.setSearch(parseInt(searchField));

        p.setInventiveness(parseInt(inventivenessField));
        p.setPhysical_magic(parseInt(physicalMagicField));
        p.setPsychic_magic(parseInt(psychicMagicField));
        p.setMagic_resistance(parseInt(magicResistanceField));

        p.setGold(parseInt(goldField));

        return p;
    }

    private Integer parseInt(JTextField field) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void goToGame(Player player) {
        dispose();
        this.playerDao.saveAdventurer(player);
        uiPlayerJFrame.startGame("Noeud 1");
    }
}
