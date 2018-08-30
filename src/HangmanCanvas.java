/*
* File: HangmanCanvas.java
* ------------------------
* This file keeps track of the Hangman display.
*/
import java.awt.BorderLayout;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	private GLine scaf, beam, rope;
	
	private void setScaf() {
		rope = new GLine(0, 0, 0, ROPE_LENGTH);
		beam = new GLine(0, 0, BEAM_LENGTH, 0);
		scaf = new GLine(0, 0, 0, SCAFFOLD_HEIGHT);		
		
		add(rope, getWidth()/2, 250-ROPE_LENGTH);
		add(beam, rope.getX()-BEAM_LENGTH, rope.getY());
		add(scaf, beam.getLocation());
	}
	
	private void setLabels() {
		String dash = "- - - -";		
		
		word = new GLabel(dash);
		guessLab = new GLabel(guess);
		word.setFont("Helvetica-40");
		guessLab.setFont("Helvetica-25");
		
		add(word, 100, 70);
		add(guessLab, 400, 70);
	}		
	

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		// how to set font?
		setLabels();
		setScaf();
		//drawScaffold
	}
	
	/**
	* Updates the word on the screen to correspond to the current
	* state of the game. The argument string shows what letters have
	* been guessed so far; unguessed letters are indicated by hyphens.
	*/
	public void displayWord(String word) {
		this.word.setLabel(word);
	}
	
	/**
	* Updates the display to correspond to an incorrect guess by the
	* user. Calling this method causes the next body part to appear
	* on the scaffold and adds the letter to the list of incorrect
	* guess that appears at the bottom of the window.
	*/
	public void noteIncorrectGuess(char letter) {
		guess = guess.substring(0, i) + 
				  letter + guess.substring(i + 1);
		
		guessLab.setLabel(guess);
		drawBodyPart(i);
		++i;
	}
	
	private void drawBodyPart(int i) {
		switch(i) {
		case 0:
			drawHead();
			break;
		case 1:
			drawBody();
			break;
		case 2:
			drawLArm();
			break;
		case 3:
			drawRArm();
			break;
		case 4:
			drawLLeg();
			break;
		case 5:
			drawRLeg();
			break;
		case 6:
			drawLFoot();
			break;
		case 7:
			drawRFoot();
			break;			
		}
	}

	private String guess = "--------";
	private int i = 0; // index for wrong guess
	
	private GLabel guessLab, word;
	
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private GObject head, body, lFoot, rFoot;
	// for arms
	private GLine hor, ver;
	// hip
	private GLine hip,  lLeg, rLeg;
	
	private void drawRFoot() {
		rFoot = new GLine(0, 0, FOOT_LENGTH, 0);
		add(rFoot, rLeg.getEndPoint());
	}
	
	private void drawLFoot() {
		lFoot = new GLine(0, 0, FOOT_LENGTH, 0);
		add(lFoot, lLeg.getEndPoint().getX()-FOOT_LENGTH, lLeg.getEndPoint().getY());
	}
	
	
	private void drawRLeg() {
		rLeg = new GLine(0, 0, 0, LEG_LENGTH);
		add(rLeg, hip.getEndPoint());
	}
	
	private void drawLLeg() {
		hip = new GLine(body.getX()-(HIP_WIDTH/2), body.getY()+body.getHeight(), body.getX()+(HIP_WIDTH/2), body.getY()+body.getHeight());
		lLeg = new GLine(0, 0, 0, LEG_LENGTH);
		add(hip);
		add(lLeg, hip.getStartPoint());
	}
	
	private void drawRArm() {
		GLine horR, verR;
		horR = new GLine(0, 0, UPPER_ARM_LENGTH, 0);
		verR = new GLine(0, 0, 0, LOWER_ARM_LENGTH);
		
		add(horR, hor.getEndPoint());
		add(verR, horR.getEndPoint());		
	}
	
	private void drawLArm() {
		hor = new GLine(0, 0, UPPER_ARM_LENGTH, 0);
		ver = new GLine(0, 0, 0, LOWER_ARM_LENGTH);
		
		add(hor, body.getX()-UPPER_ARM_LENGTH, body.getY()+ARM_OFFSET_FROM_HEAD);
		add(ver, hor.getStartPoint());		
	}
	
	private void drawBody() {
		body = new GLine(0, 0, 0, BODY_LENGTH);
		GPoint p1;
		p1 = new GPoint(getWidth()/2, head.getY()+HEAD_RADIUS);
		add(body, p1);
	}
	
	private void drawHead() {
		head = new GOval(HEAD_RADIUS, HEAD_RADIUS);	
		add(head, (getWidth() - HEAD_RADIUS)/2, 250);
	}
}