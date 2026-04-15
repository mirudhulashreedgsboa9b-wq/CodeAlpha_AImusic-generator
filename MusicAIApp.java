package music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;
import java.util.Random;

public class MusicAIApp {

    public static void main(String[] args) {

        // FRAME
        JFrame frame = new JFrame("AI Music Generator 🎵");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // PANEL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 20, 20)); // dark theme
        panel.setLayout(null);

        // TITLE
        JLabel title = new JLabel("AI MUSIC GENERATOR");
        title.setBounds(90, 30, 300, 30);
        title.setForeground(new Color(255, 105, 180)); // pink
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(title);

        // SUBTITLE
        JLabel subtitle = new JLabel("Create AI-based music instantly");
        subtitle.setBounds(100, 60, 300, 20);
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(subtitle);

        // BUTTON
        JButton btn = new JButton("Generate Music");
        btn.setBounds(130, 120, 180, 45);
        btn.setBackground(new Color(255, 105, 180));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(btn);

        // STATUS
        JLabel status = new JLabel("Status: Ready");
        status.setBounds(140, 200, 200, 30);
        status.setForeground(new Color(255, 105, 180));
        status.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(status);

        // BUTTON ACTION
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Thread(() -> {
                    try {
                        status.setText("Status: Generating... 🎶");

                        int[] notes = generateMusic();

                        status.setText("Status: Playing Music... 🎹");
                        playMusic(notes);

                        status.setText("Status: Completed ✅");

                    } catch (Exception ex) {
                        status.setText("Error ❌");
                    }
                }).start();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    // 🎼 AI MUSIC GENERATION (SIMULATION)
    public static int[] generateMusic() {
        Random rand = new Random();
        int[] notes = new int[30];

        for (int i = 0; i < notes.length; i++) {
            notes[i] = 60 + rand.nextInt(20);
        }

        return notes;
    }

    // 🔊 PLAY MUSIC USING MIDI
    public static void playMusic(int[] notes) {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();

            MidiChannel channel = synth.getChannels()[0];

            for (int note : notes) {
                channel.noteOn(note, 600);
                Thread.sleep(200);
                channel.noteOff(note);
            }

            synth.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
