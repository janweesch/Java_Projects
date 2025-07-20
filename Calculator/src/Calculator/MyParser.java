package Calculator;

public class MyParser
{
    private String input;
    private int pos = -1;
    private char currentChar;

    public MyParser(String input)
    {
        this.input = input;
        nextChar();
    }

    private void nextChar()
    {
        pos++;
        if (pos < input.length())
        {
            currentChar = input.charAt(pos);
        }
        else
        {
            currentChar = '\0'; // Nullzeichen: Ende
        }
    }

    public void debugPrintAllChars()
    {
        while (currentChar != '\0')
        {
            System.out.println("Zeichen: " + currentChar);
            nextChar();
        }
    }

    private void skipWhitespace()
    {
        while (currentChar == ' ')
        {
            nextChar();
        }
    }

    private int parseNumber()
    {
        skipWhitespace(); // Leerzeichen überspringen
        StringBuilder number = new StringBuilder();

        while (Character.isDigit(currentChar))
        {
            number.append(currentChar);
            nextChar();
        }

        if (number.length() == 0)
        {
            throw new RuntimeException("Zahl erwartet an Position " + pos);
        }
        return Integer.parseInt(number.toString());
    }

    private int parseFactor()
    {
        skipWhitespace();
        if (currentChar == '-')
        {
            nextChar();
           return -parseFactor();
        }
        else if (currentChar == '+')
        {
            nextChar();
           return parseFactor();
        }
        if (currentChar == '(')
        {
            nextChar(); // '(' überspringen
            int result = parseExpression();
            if (currentChar == ')') {
                nextChar(); // ')' überspringen
            }
            else
            {
                throw new RuntimeException("Klammer nicht geschlossen");
            }
            return result;
        }
        else
        {
            return parseNumber();
        }
    }

    private int parsePower() {
        int base = parseFactor();

        while (currentChar == '^') {
            nextChar();
            int exponent = parsePower(); // rekursiv → rechtsassoziativ!
            int result = 1;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            base = result;
        }

        return base;
    }


    private int parseTerm()
    {
        int result = parsePower();

        while (true)
        {
            skipWhitespace();
            if (currentChar == '*')
            {
                nextChar();
                result *= parseFactor();
            }
            else if (currentChar == '/')
            {
                nextChar();
                int divisor = parseFactor();
                if (divisor == 0) throw new RuntimeException("Division durch 0");
                result /= divisor;
            }
            else if (currentChar == '%')
            {
                nextChar();
                int divisor = parseFactor();
                if (divisor == 0) throw new RuntimeException("Division durch 0");
                result %= divisor;
            }
            else
            {
                break;
            }
        }
        return result;
    }


   private int parseExpression()
   {
        int result = parseTerm();

        while (true)
        {
            skipWhitespace();
            if (currentChar == '+')
            {
                nextChar();
                result += parseTerm();
            }
            else if (currentChar == '-')
            {
                nextChar();
                result -= parseTerm();
            }
            else
            {
                break;
            }
        }
        return result;
    }

    public int parse()
    {
        int result = parseExpression();
        if (currentChar != '\0')
        {
            throw new RuntimeException("Unerwartetes Zeichen am Ende: " + currentChar);
        }
        return result;
    }


    public void testParseNumber()
    {
        int number = parseNumber();

        System.out.println("Gefundene Zahl: " + number);
    }

}
