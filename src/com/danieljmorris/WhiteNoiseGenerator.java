package com.danieljmorris;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class WhiteNoiseGenerator {

	public static void main(String[] args) {
try {
			
			Synthesizer synth = MidiSystem.getSynthesizer();
	
			synth.open();
	
			MidiChannel channels[] = synth.getChannels();

			Soundbank bank = synth.getDefaultSoundbank();
			
			synth.loadAllInstruments(bank);
		
			Instrument instrs[] = synth.getLoadedInstruments();

            Instrument seashore = null;
            
            for (int i=0; i < instrs.length; i++) {
                if (instrs[i].getName().equals("Seashore")) {
                    seashore = instrs[i];
                    break;
                }
            }
            if (seashore == null) {
                System.out.println("Can't find the beach");
                System.exit(0);
            }
            Patch seashorePatch = seashore.getPatch();
            
            channels[1].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
            channels[2].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
            channels[3].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
            channels[4].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
            channels[5].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
            channels[6].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
                
            channels[1].noteOn(32, 127);
            Thread.sleep(5500);
            channels[2].noteOn(32, 127);
            Thread.sleep(4500);
            channels[3].noteOn(32, 127);
            Thread.sleep(3500);
            channels[4].noteOn(32, 127);
            Thread.sleep(2500);
            channels[4].noteOn(32, 127);
            Thread.sleep(1500);
            channels[6].noteOn(32, 127);
            
            for (;;) {
                try { Thread.sleep(999999999); } catch (Exception ignore) {}
            }
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
