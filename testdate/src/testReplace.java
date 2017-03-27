
public class testReplace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "aaaaaaaaaaaaaaaaaaaadddddddddddddddddsssssssssssssssss";
		a = replace(a);
		System.out.println("a = " + a);
		
		
	}
	
	public static String replace(String a) {
		a = a.replaceAll("d", "");
		System.out.println("a replace= " + a);
		//replace2(a);
		return a;
	}
	
	private  String replace2(String a) {
		a = a.replaceAll("d", "");
		System.out.println("a replace= " + a);
		this.getClass().getClassLoader().getResourceAsStream("testng-1.0.dtd");
		return a;
	}

}
