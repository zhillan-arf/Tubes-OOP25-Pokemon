public class move {
	private String name;
	private ElementType elementType;
	private int accuracy;
	private int priority;
	private int ammunition;

	public Move() {
		pass
	}

	// Getter method
	public String getName() {
		return name;
	}

	public ElementType getElementType() {
		return elementType;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getPriority() {
		return priority;
	}

	public int getAmmunition() {
		return ammunition;
	}
	
}
