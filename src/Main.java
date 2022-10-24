import java.util.Scanner;

public class Main {
    private static final String[] _operations = {"\\+", "-", "\\*", "/"};
    private static final String _regex = "\\s";

    public static void main(String[] args)
    {
        Scanner inputStream = new Scanner(System.in);

        System.out.println("Введите выражение, которое хотите посчитать:");
        String inputExpression = inputStream.nextLine().replaceAll(_regex, "");

        ProcessExpression(inputExpression);
    }

    public static void ProcessExpression(String inputString)
    {
        String[] operations = {"+", "-", "*", "/"};

        try
        {
            int length = operations.length;

            for(int i = 0; i < length; i++)
            {
                if(inputString.contains(operations[i]))
                {
                    String[] str = inputString.split(_operations[i]);
                    boolean signature = CheckOneSignature(str); //нужно, что организовать вывод в нужной системе счисления

                    System.out.println(CalculateExpression(str, operations[i], signature));

                    break;
                }
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
    }

    private static String CalculateExpression(String[] numbers, String operation, boolean signature) throws Exception
    {
        RomanNumber roman = new RomanNumber();
        int firstNumber, secondNumber;

        if(signature)
        {
            firstNumber = roman.ConvertToArabian(numbers[0]);
            secondNumber = roman.ConvertToArabian(numbers[1]);
        }
        else
        {
            CheckNumberOnInteger(numbers[0]);
            CheckNumberOnInteger(numbers[1]);

            firstNumber = Integer.parseInt(numbers[0]);
            secondNumber = Integer.parseInt(numbers[1]);
        }

        if((firstNumber < 1 || firstNumber > 10) || (secondNumber < 1 || secondNumber > 10))
        {
            throw new IntegerNumberException("Введено знавение не удовлетворяющее условию [1; 10]");
        }

        int result = switch (operation) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> (int) Math.floor((float)firstNumber / secondNumber);
            default -> 0;
        };

        if(signature)
        {
            if(result < 1)
                throw new IntegerNumberException("Ответ в римской системе не может быть меньше I.");
            else
                return roman.ConvertToRoman(result);
        }
        else
        {
            return Integer.toString(result);
        }
    }

    private static boolean CheckOneSignature(String[] numbers) throws SignatureException {
        RomanNumber roman = new RomanNumber();

        boolean first = roman.IsRoman(numbers[0]); //Если римское число, то 1
        boolean second = roman.IsRoman(numbers[1]);

        if (first != second)
            throw new SignatureException("Введены разные типы систем счисления.");
        else
            return first;
    }

    private static void CheckNumberOnInteger(String number) throws IntegerNumberException
    {
        int index = 0, length = number.length();

        while(index != length)
        {
            char symbol = number.charAt(index);
           if(symbol == '.' || symbol == ',')
           {
               throw new IntegerNumberException("Число не является целым.");
           }
           else if (!(symbol >= 0x30 && symbol <= 0x39))
           {
               throw new IntegerNumberException("Введено текстовое значение, которое невозможно преобразовать в арабское число.");
           }
            index++;
        }
    }
}