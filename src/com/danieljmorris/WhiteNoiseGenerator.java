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
			//Create sythesizer instance
			Synthesizer synth = MidiSystem.getSynthesizer();
			//To play the sythesizer it must be opened
			synth.open();
			//Create an array of the midi channels, there are 16 channels
			MidiChannel channels[] = synth.getChannels();
			//Get the default soundbank of instruments
			Soundbank bank = synth.getDefaultSoundbank();
			//Load all of the instruments from the soundbank
			synth.loadAllInstruments(bank);
			//Create an array of the instruments. The loop is used to print
			//out a list of the instruments to the console.
			Instrument instrs[] = synth.getLoadedInstruments();
			for (int i = 0; i < instrs.length; i++) {
				System.out.println(instrs[i].getName());
			}

			Instrument seashore = null;
			//Loop through array of instruments to find the instrument call "Seashore"
			for (int i = 0; i < instrs.length; i++) {
				if (instrs[i].getName().equals("Seashore")) {
					seashore = instrs[i];
					break;
				}
			}
			if (seashore == null) {
				System.out.println("Can't find the beach");
				System.exit(0);
			}
			//Create a patch, which contains the soundbank and program number for the instrument
			Patch seashorePatch = seashore.getPatch();
			
			//Assign the instrument patch to the midi channels to play note events through
			channels[1].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
			channels[2].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
			channels[3].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
			channels[4].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
			channels[5].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
			channels[6].programChange(seashorePatch.getBank(), seashorePatch.getProgram());
			//Trigger note on events to play the loaded instrument. The first number is the note
			//and the second number is the velocity. Thread.sleep is used to delay execution of 
			//next channel note on event.
			channels[1].noteOn(32, 127);
			Thread.sleep(5500);
			channels[2].noteOn(33, 127);
			Thread.sleep(4500);
			channels[3].noteOn(34, 127);
			Thread.sleep(3500);
			channels[4].noteOn(35, 127);
			Thread.sleep(2500);
			channels[4].noteOn(36, 127);
			Thread.sleep(1500);
			channels[6].noteOn(37, 127);

			for (;;) {
				try {
					Thread.sleep(999999999);
				} catch (Exception ignore) {
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
