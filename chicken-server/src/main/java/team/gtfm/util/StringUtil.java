package team.gtfm.util;

public class StringUtil {
	public static String fromUnderToCamel(String str){
		StringBuilder builder = new StringBuilder();
		String[] strs = str.split("_");
		for(String word : strs){
			int length = word.length();
			builder.append(word.substring(0, 1).toUpperCase())
				.append(word.substring(1, length));
		}
		
		return builder.toString();
	}
}
