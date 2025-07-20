import Calculator.MyParser;

public class Main
{
    public static void main (String args[])
    {
        System.out.println("===========================");
        System.out.println("Welcome to my Java Projects");
        System.out.println("===========================");

        MyParser parser = new MyParser("3 + 4 * 2");
        parser.debugPrintAllChars();

        MyParser parser2 = new MyParser("   1234 + 56");
        parser2.testParseNumber(); // Ausgabe: Gefundene Zahl: 1234

        MyParser parser3 = new MyParser("5*3-24%3");
        int result = parser3.parse();
        System.out.println("Ergebnis: " + result); // sollte 11 sein

    }








}
