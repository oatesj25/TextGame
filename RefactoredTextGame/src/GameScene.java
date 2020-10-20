

public class GameScene {
	private String message;
	private String choiceA; 
	private String choiceB;
	private String choiceC;
	private int AJumpsTo;
	private int BJumpsTo;
	private int CJumpsTo;
	
	//one constructor that takes full parameters only
	public GameScene(String m, String a, String b, String c, int aj, int bj, int cj) {
		this.message = m;
		this.choiceA = a;
		this.choiceB = b;
		this.choiceC = c;
		this.AJumpsTo = aj;
		this.BJumpsTo = bj;
		this.CJumpsTo = cj;
	}

	
	//generic setter and getter methods
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message= message;
	}
	public String getChoiceA() {
		return this.choiceA;
	}
	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}
	public String getChoiceB() {
		return this.choiceB;
	}
	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}
	public String getChoiceC() {
		return this.choiceC;
	}
	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}
	public int getAJumpsTo() {
		return this.AJumpsTo;
	}
	public void setAJumpsTo(int a) {
		this.AJumpsTo = a;
	}
	public int getBJumpsTo() {
		return this.BJumpsTo;
	}
	public void setbJumpsTo(int b) {
		this.BJumpsTo = b;
	}
	public int getCJumpsTo() {
		return this.CJumpsTo;
	}	
	public void setCJumpsTo(int c) {
		this.CJumpsTo = c;
	}
}
