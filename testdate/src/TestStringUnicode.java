import java.io.UnsupportedEncodingException;


public class TestStringUnicode {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s1 = "\uFFFD\uFFFD\uFFFD\ufffd\ufffd\ufffd"; 
		String s2 = new String(s1.getBytes("gbk"));
		System.out.println(s2);
		
		String local = "LOcal";
		String localup = "LOCAL";
		if (local.equalsIgnoreCase(localup)){
			System.out.println(local);
		}
	}

}
