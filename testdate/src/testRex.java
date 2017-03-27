import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class testRex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//  <p><a href=\"http://www.ktvu.com\"></a><img alt=\"FOX\" src=\"http://static.lakana.com/lakana-fox-global/theme/images/ktvu/logo-fox-2-san-francisco-ktvu-alt.png\" class = \"class\" /></p>
		//  \\< *[img][^\\\\>]*[src] *= *[\"\']{0,1}([^\"\'\\ >]*)
		
		//  ^(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?
		//  http://static.lakana.com/media.fox9.com/photo/2016/05/12/Twins_ballboy_makes_an_amazing_catch_0_1305807_ver1.0_640_360.jpg
		//  (<[^\\s\\/>]+)\\b[^>]*>
		//  <img src=\"a.jpg\" width=\"100px\" height=\"100px\" /> 
		//  ([^=\"\" ]+ *= *\"\")
		//  <P><article did = \"\" class = \"class1\" do = \"\" class = \"class2\">sfsf</article></p><P><a did = \"\" class = \"class1\" do = \"\" class = \"class2\">sfsf</a></p>
		
		
		
		
		
//		String storyBody = "<P><article did = \"\" class = \"class1\"      do = \"\" class = \"class2\">sfsf</article></p><P><a did = \"\" class = \"class1\" do = \"\" class = \"class2\">sfsf</a></p>";
//		String a = storyBody.replaceAll("[^=\"\" ]+ *= *\"\"", "");
//		System.out.println("a = " + a);
//		a = a.replaceAll(" {2,}", " ");
//		System.out.println("a = " + a);
//		
//		Pattern pattern = Pattern.compile("([^=\"\" ]+ *= *\"\")");
//        Matcher matcher = pattern.matcher(storyBody);
//        StringBuffer tempSB = new StringBuffer();
//        String replacement = "";
//        while (matcher.find()) {
//        	replacement = matcher.group(0);
//        	//replacement = replacement.substring(0,matcher.start(1)-matcher.start(0)) + replacement.substring(matcher.end(1)-matcher.start(0)) + matcher.group(1);
//        	matcher.appendReplacement(tempSB, Matcher.quoteReplacement(replacement));
//        	//matcher.appendReplacement(tempSB, replacement);
//            System.out.println(matcher.group());
//        }
//        //System.out.println(replacement);
//        System.out.println(tempSB.toString());
		
		StringBuffer sb = new StringBuffer();
		sb.append("<p><a ");
		for(int i = 0; i < 1000; i ++) {
			sb.append("7='' 969='' a='' ability='' active='' c.l.e.a.t.=''");
		}
		sb.append("> <img src = \"a.png\">FOX 7 reported that also according to the report</a>, Texas leads the U.S. when it comes to officers killed on the job. 14 in the state since January.</p>");
		String storyBody = sb.toString();
		Date startDate = new Date();
		System.out.println("startDate = " + startDate.getTime());
        Pattern pattern = Pattern.compile("<a (?:[^<]|<[^ip/a])*+((?:\\s*<img[^>]*>)+)(?:(?:[^/]|/[^a])*)/a>");
        Matcher matcher = pattern.matcher(storyBody);
        StringBuffer tempSB = new StringBuffer();
        while (matcher.find()) {
            String replacement = matcher.group(0);
            replacement = replacement.substring(0,matcher.start(1)-matcher.start(0)) + replacement.substring(matcher.end(1)-matcher.start(0)) + matcher.group(1);
            matcher.appendReplacement(tempSB, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(tempSB);
        Date endDate = new Date();
        System.out.println("endDate = " + endDate.getTime());
        System.out.println("Spend Date = " + (endDate.getTime() - startDate.getTime()));
        System.out.println("storyBody = " + tempSB.toString());
        
        
//		String storyBody = "<P><a did = \"\" class = \"class1\" href = \"http://static.lakana.com/lakana-fox-global/theme/images/ktvu/logo-fox-2-san-francisco-ktvu-alt.png\" do = \"\" class = \"class2\">sfsf</a></p><P><a did = \"\" href = \"http://fsalfdjfla\" class = \"class1\" do = \"\" class = \"class2\">sfsf</a></p>";
//        Pattern pattern = Pattern.compile("< *\\ba\\b[^>]*>");
//        Matcher matcher = pattern.matcher(storyBody);
//        while (matcher.find()) {
//            String aTagReplacement = matcher.group(0);
//            pattern = Pattern.compile("(?:(?:(?:\\b(?:href|id|class|style|charset|coords|hreflang|name|rel|rev|shape|target|tabindex|title|type|accesskey)\\b)(?: )*=(?: )*[\'\"]{1}[^\'\"]*[\'\"]{1})+)");
//            Matcher attributeMatcher = pattern.matcher(aTagReplacement);
//            String replacement = "";
//            while (attributeMatcher.find()) {
//            	String hrefValue = attributeMatcher.group(0);
//            	if(hrefValue.startsWith("href")) {
//            		pattern = Pattern.compile("(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?");
//            		Matcher hrefMatcher = pattern.matcher(hrefValue);
//            		if(hrefMatcher.find()){
//            			replacement += hrefValue + " ";
//            		}
//            	} else {
//            		replacement += attributeMatcher.group(0) + " ";
//            	}
//            }
//            replacement = "<a " + replacement + ">";
//            storyBody = storyBody.replace(aTagReplacement, replacement);
//            System.out.println("storyBody = " + storyBody);
//        }
        
		//  <div>(\\s+)?     <div>(\\s*)
		
//		String storyBody = "<div>  <p><a href=\"http://www.ktvu.com\"></a><img alt=\"FOX\" src=\"alt.png\" /></p>";
//		StringBuffer body = new StringBuffer(storyBody.replaceAll("<div>(\\s+)?", "<p>"));
//		System.out.println(body);
        
        
        for(int i = 1 ; i < 64;){
			System.out.println(i + " = " + (i & 19));
			i = i * 2;
		}

	}

}
