package at.ac.fhcampuswien;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void oneMonthCalendar( int daysInMonth, int firstDayOfWeek )
    {
        // Print first line with alignment offset depending on what day the first day is
        for( int i = 0; i < 3 * ( firstDayOfWeek - 1 ); i++ )
        {
            System.out.print( " " );
        }

        for( int i = 1; i <= daysInMonth; i++)
        {
            // print an extra space if the day has a single digit for proper alignment
            if( i < 10 )
            {
                System.out.print( " " );
            }

            // Print the day in the calendar
            System.out.print( i + " " );

            // If it's sunday, jump to the next line
            if( ( i + firstDayOfWeek ) % 7 == 1 || ( i == daysInMonth ) )
            {
                System.out.println();
            }
        }
    }

    public static long[] lcg( long seed )
    {
        final long M = (long) Math.pow( 2, 31 );
        final long A = 1103515245;
        final int C = 12345;

        long X_i;
        long[] arrGeneratedNumbers = new long[10];

        for( int i = 0; i < arrGeneratedNumbers.length; i++ )
        {
            if( i == 0 )
            {
                X_i = seed;
            }
            else
            {
                X_i = arrGeneratedNumbers[ i - 1 ];
            }

            arrGeneratedNumbers[i] = ( ( A * X_i ) + C ) % M;
        }

        return arrGeneratedNumbers;
    }

    public static int randomNumberBetweenOneAndHundred()
    {
        Random random = new Random();

        return random.ints(1, 101).findAny().getAsInt();
    }

    public static void guessingGame( int numberToGuess )
    {
        final int MAX_ATTEMPTS = 10;

        int inputNumber;
        int i;
        Scanner scanner = new Scanner( System.in );

        for( i = 0; i < MAX_ATTEMPTS; i++ )
        {
            System.out.print( "Guess number " + ( i + 1 ) + ": " );
            inputNumber = scanner.nextInt();

            if( inputNumber == numberToGuess )
            {
                System.out.println( "You won wisenheimer!" );
                break;
            }
            else if( inputNumber < numberToGuess && i != MAX_ATTEMPTS - 1 )
            {
                System.out.println( "The number AI picked is higher than your guess." );
            }
            else if( inputNumber > numberToGuess && i != MAX_ATTEMPTS - 1 )
            {
                System.out.println( "The number AI picked is lower than your guess." );
            }
        }

        if( i == MAX_ATTEMPTS )
        {
            System.out.println( "You lost! Have you ever heard of divide & conquer?" );
        }
    }

    public static boolean swapArrays( int[] arr1, int[] arr2 )
    {
        int swapHelper;

        if( arr1.length != arr2.length )
        {
            return false;
        }

        for( int i = 0; i < arr1.length; i++ )
        {
            swapHelper = arr1[i];
            arr1[i] = arr2[i];
            arr2[i] = swapHelper;
        }

        return true;
    }

    public static String camelCase( String input )
    {
        // Difference between upper and lowercase letters in ASCII table is always 32
        final int ASCII_DIFFERENCE = 32;
        // Control variable to set capital letter
        boolean isCapital = true;
        char[] inputAsChars = input.toCharArray();

        StringBuilder output = new StringBuilder();

        // Loop through every character in array
        for( char letter : inputAsChars )
        {
            // If it's a lowercase letter and the control variable is true,
            // get the capital letter, append it to output and set control variable to false
            if( letter >= 'a' && letter <= 'z' )
            {
                if( isCapital )
                {
                    letter -= ASCII_DIFFERENCE;
                }
                output.append( letter );
                isCapital = false;
            }
            // If it's a capital letter and the control variable is false,
            // get the lowercase letter, append it to output and set control variable to false
            else if( letter >= 'A' && letter <= 'Z' )
            {
                if( !isCapital )
                {
                    letter += ASCII_DIFFERENCE;
                }
                output.append( letter );
                isCapital = false;
            }
            // If the current 'letter' is a space character, next letter has to be capital
            // -> set control variable to true
            else if( letter == ' ' )
            {
                isCapital = true;
            }
        }

        return output.toString();
    }


    public static int checkDigit( int[] arr )
    {
        int sumOfProducts = 0;
        int checkDigit;

        for( int i = 0 ; i < arr.length; i++ )
        {
            sumOfProducts += arr[i] * ( i + 2 );
        }

        checkDigit = 11 - ( sumOfProducts % 11 );

        switch( checkDigit )
        {
            case 10:
                return 0;
            case 11:
                return 5;
            default:
                return checkDigit;
        }
    }

    public static void main(String[] args) {
        oneMonthCalendar( 28, 2 );
        System.out.println();
        oneMonthCalendar( 31, 3 );
        System.out.println();
        oneMonthCalendar( 28, 1 );

        System.out.println();
        long[] arrGeneratedNumbers = lcg( 0 );
        for( long arrGeneratedNumber : arrGeneratedNumbers ) {
            System.out.print( arrGeneratedNumber + "\t" );
        }
        System.out.println();

        System.out.println();
        int numberToGuess = randomNumberBetweenOneAndHundred();
        guessingGame( numberToGuess );

        System.out.println();
        int[] arr1 = new int[]{ 1, 2, 3, 4, 5, 6 };
        int[] arr2 = new int[]{ 100, 202, 30, 14, 15, 16 };
        boolean isSwapped = swapArrays( arr1, arr2 );
        System.out.println( "Method swapArrays returned " + isSwapped );

        System.out.println();
        String input = "my name isn't Alice";
        String input2 = "AnY noise annoYs an oYster but a noisY noise annoYs an oYster more.";
        String camelCasedString = camelCase( input );
        String camelCasedString2 = camelCase( input2 );
        System.out.println( camelCasedString);
        System.out.println( camelCasedString2 );

        System.out.println();
        int[] arr = new int[]{ 3, 9, 1, 5, 8 };
        int checkDigit = checkDigit( arr );
        System.out.println( "checkDigit: " + checkDigit );

    }
}