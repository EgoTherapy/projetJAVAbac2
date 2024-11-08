package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.DoubleException;
import exceptions.NullException;

public class PawnManagement {
	private List<Pawn> pawns;
	
	//Constructor
	/**
	 * All of the pawns are stocked in a list.
	 */
	public PawnManagement() {
		pawns = new ArrayList<>();
	}
	
	//Getter
	/**
	 * Return the number of pawns in the PawnManagement
	 * 
	 * @return the number of pawns in the PawnManagement
	 */
	public int getNbPawns() {
		return pawns.size();
	}
	
	/**
	 * Return the list of pawns in the list
	 * 
	 * @return return a copy of the pawns list
	 */
	public List<Pawn> getPawns() {
		List<Pawn> tmp = new ArrayList<>();
		for(Pawn p : pawns) {
			tmp.add(p.clone());
		}
		return tmp;
	}
		
	/**
	 * This method will add the pawn that we put in the parameters.
	 * 
	 * @param p the pawn to add to the list
	 * @throws NullException will be thrown if the pawn is null
	 * @throws DoubleException will be thrown if the pawn that we want to add is already in the list
	 */
	public void add(Pawn p) throws NullException, DoubleException{
		if(pawns.contains(p)) {
			throw new DoubleException();
		}
		if(p == null) {
			throw new NullException();
		}
		pawns.add(p.clone());
	}
	
	/**
	 * This method will research the pawn that the user want in function of the index.
	 * 
	 * @param index the index to find the pawn
	 * @return the copy of the pawn that we want to find
	 * @throws IndexOutOfBoundsException will be thrown if the index is out of the list
	 */
	public Pawn find(int index) throws IndexOutOfBoundsException{
		return pawns.get(index).clone();
	}
	
	/**
	 * This method will research the pawn that the user want in function of an object (pawn).
	 * 
	 * @param pawn the index of the wanted pawn
	 * @return the pawn that we want to find
	 */
	public int find(Pawn pawn) {
		return pawns.indexOf(pawn);
	}
	
	/**
	 * This method will remove all of the pawns.
	 * 
	 * @return true if the pawns have been correctly removed and false if not
	 */
	public boolean removeAll() {
		return pawns.removeAll(pawns);
	}
	
	/**
	 * This method will return the next pawn.
	 * 
	 * @param currentPawn the current pawn
	 * @return the next pawn following the current pawn
	 */
	public Pawn nextPawn(Pawn currentPawn) {
		if(currentPawn.equals(pawns.get(pawns.size() - 1))) {
			return pawns.get(0).clone();
		} else {
			return pawns.get(pawns.indexOf(currentPawn) + 1).clone();
		}
	}
	
	/**
	 * This method will move the current pawn in function of the movement gave in the parameters.
	 * 
	 * @param pawn the current pawn
	 * @param movement the number of squares that the pawn will move
	 */
	public void movePawn(Pawn pawn, int movement) {
		pawns.get(pawns.indexOf(pawn)).setCasePawn(pawn.getCasePawn() + movement);
	}

	@Override
	public String toString() {
		return "PawnManagement [pawns=" + pawns + "]";
	}
}
