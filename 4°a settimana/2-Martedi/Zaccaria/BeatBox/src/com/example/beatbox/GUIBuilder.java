package com.example.beatbox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIBuilder {
    private ArrayList<JCheckBox> checkboxList;
    private Player player = new Player();
    private int beats = 16;
    private String[] instrumentNames = {
            "Bass Drum", "Closed Hi-Hat",
            "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"
    };
    private int[] instrumentKeys = {
            35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63
    };

    private void startPlayer() {
        int[] tracks;
        player.resetTrack();

        for (int i = 0; i < instrumentKeys.length; i++) {
            tracks = new int[beats];
            int key = instrumentKeys[i];

            for (int j = 0; j < beats; j++) {
                JCheckBox jc = checkboxList.get(j + beats * i);
                if (jc.isSelected()) {
                    tracks[j] = key;
                } else  {
                    tracks[j] = 0;
                }
            }
            player.makeTracks(tracks);
        }

        player.play(beats);
    }

    private Box buildButtonBox() {
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startPlayer());
        buttonBox.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> player.stop());
        buttonBox.add(stopButton);

        JButton upTempoButton = new JButton("Tempo Up");
        upTempoButton.addActionListener(e -> player.changeTempo(1.03f));
        buttonBox.add(upTempoButton);

        JButton downTempoButton = new JButton("Tempo Down");
        downTempoButton.addActionListener(e -> player.changeTempo(0.97f));
        buttonBox.add(downTempoButton);

        return buttonBox;
    }

    private Box buildNameBox() {
        Box nameBox = new Box(BoxLayout.Y_AXIS);

        for (String name : instrumentNames) {
            JLabel nameLabel = new JLabel(name);
            nameLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            nameBox.add(nameLabel);
        }

        return nameBox;
    }

    private JPanel buildGrid() {
        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setHgap(2);
        gridLayout.setVgap(1);

        JPanel gridPanel = new JPanel(gridLayout);

        checkboxList = new ArrayList<>();
        for (int i = 0; i < (instrumentKeys.length * beats); i++) {
            JCheckBox checkbox = new JCheckBox();
            checkbox.setSelected(false);
            checkboxList.add(checkbox);
            gridPanel.add(checkbox);
        }

        return gridPanel;
    }

    public void buildGUI() {
        JFrame frame = new JFrame("Cyber Beat Box");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box buttonBox = buildButtonBox();
        Box nameBox = buildNameBox();
        JPanel grid = buildGrid();

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);
        background.add(BorderLayout.CENTER, grid);

        frame.getContentPane().add(background);
        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }


}
