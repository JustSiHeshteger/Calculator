import java.util.TreeMap;

public class RomanNumber
{
    private final TreeMap<Object, Integer> _romanSymbols;

    public RomanNumber()
    {
        _romanSymbols = new TreeMap<>();
        _romanSymbols.put('I', 1);
        _romanSymbols.put('V', 5);
        _romanSymbols.put('X', 10);
        _romanSymbols.put('L', 50);
        _romanSymbols.put('C', 100);
        _romanSymbols.put('D', 500);
        _romanSymbols.put('M', 1000);
    }

    public int ConvertToArabian(String romanString) throws Exception {
        int length = romanString.length();
        int previousNumber = 0, result = 0;

        for(int index = length - 1; index >= 0; index--)
        {
            char romanSymbol = romanString.charAt(index);
            switch (romanSymbol) {
                case 'I' -> {
                    result = AddNumber(previousNumber, result, 1);
                    previousNumber = 1;
                }
                case 'V' -> {
                    result = AddNumber(previousNumber, result, 5);
                    previousNumber = 5;
                }
                case 'X' -> {
                    result = AddNumber(previousNumber, result, 10);
                    previousNumber = 10;
                }
                case 'L' -> {
                    result = AddNumber(previousNumber, result, 50);
                    previousNumber = 50;
                }
                case 'C' -> {
                    result = AddNumber(previousNumber, result, 100);
                    previousNumber = 100;
                }
                case 'D' -> {
                    result = AddNumber(previousNumber, result, 500);
                    previousNumber = 500;
                }
                case 'M' -> {
                    result = AddNumber(previousNumber, result, 1000);
                    previousNumber = 1000;
                }
                default -> throw new Exception("Встретился неопознанный знак " + romanString.charAt(index));
            }
        }

        return result;
    }

    public String ConvertToRoman(int number)
    {
        StringBuilder arabianNumber = new StringBuilder();

        arabianNumber.append(CountM(number / 1000));

        int num = number % 1000;
        arabianNumber.append(CountD(num / 500));

        num %= 500;
        arabianNumber.append(CountC(num / 100));

        num %= 100;
        arabianNumber.append(CountL(num / 50));

        num %= 50;
        arabianNumber.append(CountX(num / 10));

        num %= 10;
        arabianNumber.append(Base(num));

        return arabianNumber.toString();
    }

    public boolean IsRoman(String number)
    {
        return _romanSymbols.containsKey(number.charAt(0));
    }

    private int AddNumber(int previousNumber, int currentNumber, int romanValue)
    {
        if(previousNumber > romanValue)
            return currentNumber - romanValue;
        else
            return currentNumber + romanValue;
    }

    private String CountM(int m)
    {
        StringBuilder str = new StringBuilder();
        int index = 0;

        while(index < m)
        {
            str.append("M");
            index++;
        }

        return str.toString();
    }

    private String CountD(int d)
    {
        if(d == 4)
        {
            return "CM";
        }
        else if (d == 0)
        {
            return "";
        }
        else
        {
            return "D";
        }
    }

    private String CountC(int c)
    {
        if(c == 4)
        {
            return "CD";
        }
        else if(c != 0 && c < 4)
        {
            StringBuilder str = new StringBuilder();
            int index = 0;

            while(index < c)
            {
                str.append("C");
                index++;
            }

            return str.toString();
        }
        else
        {
            return "";
        }
    }

    private String CountX(int x)
    {
        if(x == 4)
        {
            return "XL";
        }
        else if(x != 0 && x < 4)
        {
            StringBuilder str = new StringBuilder();
            int index = 0;

            while(index < x)
            {
                str.append("X");
                index++;
            }

            return str.toString();
        }
        else
        {
            return "";
        }
    }

    private String CountL(int l)
    {
        if(l == 4)
        {
            return "XC";
        }
        else if(l == 0)
        {
            return "";
        }
        else
        {
            return "L";
        }

    }

    private static String Base(int num) {
        String[] a = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        return a[num];
    }
}
