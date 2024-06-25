public class app {
	
	public static int screenWidth = 1094, screenHeight = 945;
	
	public static String url = "jdbc:mysql://localhost:3306/netflixmis", user = "root", password = "root";
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		login ln = new login();
		ln.frame.show();
	}
}
