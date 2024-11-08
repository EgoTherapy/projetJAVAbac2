package model;

public class Pawn {
	private Integer casePawn;
	private String imgPath;
	
	//Constructor
	/**
	 * A pawn is basically situated on the first case.
	 * 
	 * @param path the file path to find the image of the pawn.
	 */
	public Pawn(String path) {
		this.casePawn = 1;
		this.imgPath = path;
	}

	//Getters
	/**
	 * Return the case of pawn
	 * 
	 * @return the case of the pawn.
	 */
	public int getCasePawn() {
		return casePawn;
	}
	
	/**
	 * Return the file path of the image of the pawn
	 * 
	 * @return the file path of the image of the pawn.
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * Return the column of the pawn based on the board with the formula (case - 1) / 6
	 * 
	 * @return the column of the pawn on the board
	 */
	public int getColumn() {
		return (casePawn - 1) / 6;
	}
	
	/**
	 * Return the row of the pawn on the board with the formula : 
	 * If the column of the pawn is even then 
	 * 		if the case is divisible by 6 then 
	 * 			it returns 0
	 * 		else 
	 * 			it returns 6 - (case % 6)
	 * else 
	 * 		if the case is divisible by 6 then 
	 * 			it returns 5
	 * 		else 
	 * 			it returns (case % 6) - 1 
	 * 
	 * @return the row of the pawn on the board
	 */
	public int getRow() {
		int col = getColumn();
		if(col % 2 == 0) {
			if(casePawn % 6 == 0) {
				return 0;
			}
			return 6 - (casePawn % 6);
		} else {
			if(casePawn % 6 == 0) {
				return 5;
			}
			return (casePawn % 6) - 1;
		}
	}
	
	//Setters
	/**
	 * Will set the pawn on the first case if the case is under 1.
	 * Else if the pawn is on a case above the 48st case it will be set on the 48st case.
	 * Finally, if everything is okay, the pawn is set on the case it should be on.
	 * 
	 * @param casePawn the case which the pawn is on
	 */
	public void setCasePawn(int casePawn) {
		if(casePawn < 1) {
			this.casePawn = 1;
		} else if(casePawn > 48) {
			this.casePawn = 48;
		} else {
			this.casePawn = casePawn;
		}
	}

	/**
	 * Will set the file path to find the image of the pawn.
	 * 
	 * @param imgPath the file path
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imgPath == null) ? 0 : imgPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pawn other = (Pawn) obj;
		if (imgPath == null) {
			if (other.imgPath != null)
				return false;
		} else if (!imgPath.equals(other.imgPath))
			return false;
		return true;
	}

	//Clone
	/**
	 * Will return a copy of the pawn.
	 * 
	 * @return a copy of the pawn
	 */
	public Pawn clone() {
		Pawn tmp = new Pawn(imgPath);
		tmp.setCasePawn(casePawn);
		return tmp;
	}

	@Override
	public String toString() {
		return "Pawn [casePawn=" + casePawn + ", imgPath=" + imgPath + "]";
	}
}
