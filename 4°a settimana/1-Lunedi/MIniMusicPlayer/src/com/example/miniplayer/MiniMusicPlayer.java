package com.example.miniplayer;

import com.sun.tools.javac.Main;

import javax.sound.midi.*;

public class MiniMusicPlayer {
    final static int NOTE_ON = ShortMessage.NOTE_ON;
    final static int NOTE_OFF = ShortMessage.NOTE_OFF;
    final static int CHANGE_INSTRUMENT = ShortMessage.PROGRAM_CHANGE;

    public static void main(String[] args) {

        new MiniMusicPlayer().testScale(args);

    }

    private void testPlay(String[] args){
        if (args.length != 2) {
            IO.println("Servono gli argomenti strumento e nota...");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            play(instrument, note);
        }
    }

    private void testScale(String[] args){
        if (args.length != 1) {
            IO.println("Serve lo strumento...");
        } else {
            int instrument = Integer.parseInt(args[0]);
            scale(instrument);
        }
    }


    public MidiEvent makeEvent(int cmd, int chnl, int one, int two, int tick){
        MidiEvent event = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(cmd,chnl,one,two);
            event = new MidiEvent(msg,tick);
        } catch (InvalidMidiDataException e) {
            IO.println("Errore Dati Midi non validi: "  + e.getMessage());
        }
        return  event;
    }


    public void play (int instrument, int note){
        Sequencer player = null;

        try {
            player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            track.add(makeEvent(CHANGE_INSTRUMENT,1,instrument,0,1));
            track.add(makeEvent(NOTE_ON,1,note,100,1));
            track.add(makeEvent(NOTE_OFF,1,note,100,16));

            /*
            //genera la nota
            ShortMessage msgci = new ShortMessage();
            msgci.setMessage(ShortMessage.PROGRAM_CHANGE, 1, 34, 0);
            MidiEvent ci = new MidiEvent(msgci, 1);
            track.add(ci);

            //innesca la nota
            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(ShortMessage.NOTE_ON, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(msg1, 1);
            track.add(noteOn);

            //rilascia la nota
            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(ShortMessage.NOTE_OFF, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(msg2, 16);
            track.add(noteOff);
            */

            player.setSequence(seq);
            player.start();

            long lenght = player.getMicrosecondLength();
            Thread.sleep((lenght/1000) + 1000);

        } catch (MidiUnavailableException e) {
            IO.println("Errore MIni non disponibile: " + e.getMessage());
        } catch (InvalidMidiDataException e) {
            IO.println("Errore data midi: " + e.getMessage());
        } catch (InterruptedException e) {
            IO.println("Errore di interruption: " + e.getMessage());
        } finally {
            if (player != null) {
                player.close();
            }
        }
    }

    public void scale (int instrument){
        Sequencer player = null;

        try {
            player = MidiSystem.getSequencer();
            player.open();

            int[] events = {0};
            player.addControllerEventListener(
                    event -> {
                        IO.println("La");},
                    events
            );

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            track.add(makeEvent(CHANGE_INSTRUMENT,1,instrument,0,1));

            for (int i = 0; i < 128 ; i += 4){
                track.add(makeEvent(NOTE_ON,1,i,100,i));
                track.add(makeEvent(ShortMessage.CONTROL_CHANGE,1,0,0,i));
                track.add(makeEvent(NOTE_OFF,1,i,100,i + 2));
            }

            player.setSequence(seq);
            player.setTempoInBPM(220);
            player.start();

            long lenght = player.getMicrosecondLength();
            Thread.sleep((lenght /1000) + 1000);

        } catch (MidiUnavailableException e) {
            IO.println("Errore MIni non disponibile: " + e.getMessage());
        } catch (InvalidMidiDataException e) {
            IO.println("Errore data midi: " + e.getMessage());
        } catch (InterruptedException e) {
            IO.println("Errore di interruption: " + e.getMessage());
        } finally {
            if (player != null) {
                player.close();
            }
        }
    }
}
