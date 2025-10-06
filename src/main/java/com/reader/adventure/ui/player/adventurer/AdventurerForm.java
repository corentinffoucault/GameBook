package com.reader.adventure.ui.player.adventurer;

import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.adventurer.model.Adventurer;

import javax.swing.*;
import java.awt.*;

public class AdventurerForm extends JFrame {
    private final JTextField nameField;

    private final JSpinner agilityField;
    private final JSpinner strengthField;
    private final JSpinner intelligenceField;
    private final JSpinner charismaField;
    private final JSpinner courageField;

    private final JSpinner attackField;
    private final JSpinner parryField;
    private final JSpinner throwingField;
    private final JSpinner dodgeField;
    private final JSpinner searchField;

    private final JSpinner inventivenessField;
    private final JSpinner physicalMagicField;
    private final JSpinner psychicMagicField;
    private final JSpinner magicResistanceField;

    private final JSpinner goldField;
    private final IAdventurerDao adventurerDao;

    private IAdventurerHandler adventurerHandler;

    public AdventurerForm(IAdventurerDao adventurerDao) {
        this.adventurerDao = adventurerDao;
        setTitle("Création d'un personnage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        Adventurer adventurer = this.adventurerDao.getAdventurer();
        // Champs
        nameField = addTextField(panel, "Nom", adventurer.getName());

        agilityField = addCompField(panel, "Agilité", adventurer.getAgility());
        strengthField = addCompField(panel, "Force", adventurer.getStrength());
        intelligenceField = addCompField(panel, "Intelligence", adventurer.getIntelligence());
        charismaField = addCompField(panel, "Charisme", adventurer.getCharisma());
        courageField = addCompField(panel, "Courage", adventurer.getCourage());

        attackField = addCompField(panel, "Attaque", adventurer.getAttack());
        parryField = addCompField(panel, "Parade", adventurer.getParry());
        throwingField = addCompField(panel, "Lancer", adventurer.getThrowing());
        dodgeField = addCompField(panel, "Esquive", adventurer.getDodge());
        searchField = addCompField(panel, "Fouille", adventurer.getSearch());

        inventivenessField = addCompField(panel, "Inventivité", adventurer.getInventiveness());
        physicalMagicField = addCompField(panel, "Magie Physique", adventurer.getPhysical_magic());
        psychicMagicField = addCompField(panel, "Magie Psychique", adventurer.getPsychic_magic());
        magicResistanceField = addCompField(panel, "Résistance Magique", adventurer.getMagic_resistance());

        goldField = addNumberField(panel, "Or", adventurer.getGold(), 1000);

        // Boutons
        JPanel buttonPanel = addCreateButton();

        getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel addCreateButton() {
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Créer le personnage");

        createButton.addActionListener(e -> {
            Adventurer lastCreatedAdventurer = createAdventurer();
            JOptionPane.showMessageDialog(AdventurerForm.this,
                    "Personnage " + lastCreatedAdventurer.getName() + " créé !");
            try {
                goToGame();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(createButton);
        return buttonPanel;
    }

    public void setAdventurerHandler(IAdventurerHandler adventurerHandler) {
        this.adventurerHandler = adventurerHandler;
    }

    private JTextField addTextField(JPanel panel, String label, String initialValue) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField(initialValue);
        panel.add(field);
        return field;
    }

    private JSpinner addCompField(JPanel panel, String label, int initialValue) {
        panel.add(new JLabel(label));
        JSpinner field = new JSpinner(new SpinnerNumberModel(initialValue, 0, 20, 1));
        panel.add(field);
        return field;
    }

    private JSpinner addNumberField(JPanel panel, String label, int initialValue, int maxValue) {
        panel.add(new JLabel(label));
        JSpinner field = new JSpinner(new SpinnerNumberModel(initialValue, 0, maxValue, 1));
        panel.add(field);
        return field;
    }

    private Adventurer createAdventurer() {
        Adventurer adventurer = new Adventurer();
        adventurer.setName(nameField.getText());

        adventurer.setAgility((int) agilityField.getValue());
        adventurer.setStrength((int) strengthField.getValue());
        adventurer.setIntelligence((int) intelligenceField.getValue());
        adventurer.setCharisma((int) charismaField.getValue());
        adventurer.setCourage((int) courageField.getValue());

        adventurer.setAttack((int) attackField.getValue());
        adventurer.setParry((int) parryField.getValue());
        adventurer.setThrowing((int) throwingField.getValue());
        adventurer.setDodge((int) dodgeField.getValue());
        adventurer.setSearch((int) searchField.getValue());

        adventurer.setInventiveness((int) inventivenessField.getValue());
        adventurer.setPhysical_magic((int) physicalMagicField.getValue());
        adventurer.setPsychic_magic((int) psychicMagicField.getValue());
        adventurer.setMagic_resistance((int) magicResistanceField.getValue());

        adventurer.setGold((int) goldField.getValue());

        adventurerDao.saveAdventurer(adventurer);

        return adventurer;
    }

    private void goToGame() throws Exception {
        dispose();
        adventurerHandler.onAdventurerCompleted();
    }
}
