
public class TestIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "afdsaf..dsaf.css";
		int index = url.lastIndexOf(".");
		if (index != -1) {
			String a = url.substring(index);
			System.out.println(a);
		}
	}

}
