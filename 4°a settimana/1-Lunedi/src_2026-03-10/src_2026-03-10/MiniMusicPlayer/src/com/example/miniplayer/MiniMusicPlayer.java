package com.example.miniplayer;

import javax.sound.midi.*;

public class MiniMusicPlayer {
    public static void main(String[] args) {
        Sequencer player = null;
        try {
            player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            ShortMessage msgci =  new ShortMessage();
            msgci.setMessage(ShortMessage.PROGRAM_CHANGE, 1, 100, 0);
            MidiEvent ci = new MidiEvent(msgci, 1);
            track.add(ci);

            ShortMessage msg1 =  new ShortMessage();
            msg1.setMessage(ShortMessage.NOTE_ON, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(msg1, 1);
            track.add(noteOn);

            ShortMessage msg2 =  new ShortMessage();
            msg2.setMessage(ShortMessage.NOTE_OFF, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(msg2, 16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();

            long length = player.getMicrosecondLength();
            Thread.sleep(length / 1000 + 1000);

        } catch (MidiUnavailableException e) {
            IO.println("Errore Mini non disponibile: " + e.getMessage());
        } catch (InvalidMidiDataException e) {
            IO.println("Errore data midi: " + e.getMessage());
        } catch (InterruptedException e) {
            IO.println("Errore Interrupted: " + e.getMessage());
        } finally {
            if (player != null) {
                player.close();
            }
        }

    }
}
