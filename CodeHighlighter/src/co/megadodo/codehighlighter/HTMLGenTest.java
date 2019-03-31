package co.megadodo.codehighlighter;

/**
 * This class is designed to test all the different states that
 * the <code>HTMLGen</code> class can highlight. This is in a JAVADOC state.
 * @author jackarmstrong
 * 
 * The states are:
 * 
 * COMMENT  (single-line comment (//), normal block comment (/*) )
 * KEYWORD (any keyword : abstract, final, public)
 * CODEWORD (String, Exception)
 * JAVADOC (JavaDoc Comment)
 * TEXT (Anything else)
 * STRING (Things in a string)
 * ORNAMENT (Semicolon, colon, parentheses, brackets, braces)
 * ANNOTATION (Anything with @ , can be something like @ Override or @ code
 * 
 * for testing:
 *  // line comment in javadoc
 *  /* block comment in javadoc (cant end the block comment cuz that wud end the javadoc:)
 */
/* BLOCK COMMENT
 * This class implements CharSequence
 * for testing:
 * 
 *  // line comment in block comment
 *  /** javadoc comment in block comment
 */
public class HTMLGenTest implements CharSequence {

	private static final String HTMLGenTestStr = "String Literal With Multiple Words" ;
	
	@Override
	public int length() {
		return HTMLGenTestStr.length();
	}

	@Override
	public char charAt(int index) {
		return HTMLGenTestStr.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return HTMLGenTestStr.subSequence(start, end);
	}
	
	public HTMLGenTest() {
		Exception ex; // ex is an Exception, so it is highlighted as a CODEWORD!
		/* in the above line the highlighter was able to have highlighted code and comment
		 * in the same line! here is a keyword in a comment: abstract
		*/
		ex = new Exception( "MSG" );
		try {
			throw ex;
		} catch (Exception e) { // never gona happen :)
			e.printStackTrace();
		}
		String str1 = "@Annotation in string!" ;
		String str2 = " @Annotation in string! With spaces on both ends! ";
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(this);
	}

}
