package com.example.beatbox;

import javax.sound.midi.*;
import java.util.HashMap;

public class Player {
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    HashMap<String, Integer> instrument = new HashMap<>();

    public Player(){

        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);

        } catch (MidiUnavailableException e) {
            IO.println("MidiUnavailableException: " + e.getMessage());
        } catch (InvalidMidiDataException e) {
            IO.println("InvalidMidiDataException: " + e.getMessage());
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

    public void makeTracks(int[] list){
        for (int i = 0; i < list.length; i++){
            int key = list[i];
            if (key != 0){
                track.add(makeEvent(ShortMessage.NOTE_ON, 9, key, 100, i));
                track.add(makeEvent(ShortMessage.NOTE_OFF, 9, key, 100, i + 1));
            }
        }
        track.add(makeEvent(ShortMessage.CONTROL_CHANGE, 1, 0, 100, list.length));
    }

    public void resetTrack(){
        sequence.deleteTrack(track);
        track = sequence.createTrack();
    }

    public void play(int beats){
        track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, beats - 1));

        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (InvalidMidiDataException e) {
            IO.println("InvalidMidiDataException: " + e.getMessage());
        }
    }

    public void stop(){
        sequencer.stop();
    }

    public void changeTempo(float tempoMultiplier){
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempoFactor * tempoMultiplier);
    }

}
