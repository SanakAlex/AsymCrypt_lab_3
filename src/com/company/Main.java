package com.company;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import static com.company.BigIntSqRoot.bigIntSqRootFloor;

class BigIntSqRoot {

    public static BigInteger bigIntSqRootFloor(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x .equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
             y.compareTo(x.divide(y)) > 0;
             y = ((x.divide(y)).add(y)).divide(two));
        return y;
    } // end bigIntSqRootFloor

    public static BigInteger bigIntSqRootCeil(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x == BigInteger.ZERO || x == BigInteger.ONE) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
             y.compareTo(x.divide(y)) > 0;
             y = ((x.divide(y)).add(y)).divide(two));
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(BigInteger.ONE);
        }
    } // end bigIntSqRootCeil
} // end class bigIntSqRoot


public class Main {

    static Scanner in = new Scanner(System.in);

    static BigInteger n = new BigInteger("DC7291D8A45ABB28652D9549544E9B329F1DED4D61D0C717678DFA0C1A90DECDFDAE7445A6A418145FCA57895A8C170F011F15CCBB16030419361A06EC92E886B073B1441B06B76104FF71ED8174FEBFBFA03ED2BF713158C6D3CEB9BB66A28F43142344EC09BFB8C8FE0C0DF17EBF66E84440D10C842CE45F7DC610F52B89437CEDD2C2336322200CB772EB9138DC91BE6051FC2CD8455948CA23385009249325FA47EEB052FB8EE47556E87B5950F128F9A5595CAEDE03FF988DD8995128A0A30F0161B397581B860D8D1E7C437C3CAF4012AAFF38A0758CA55FD207F1EC0665048D51C2A5DD2F0CEB774C2799722041A2979B56D13F0589AE5A7260D71191",16);
//    static BigInteger border = bigIntSqRootFloor(n);

    public static void main(String[] args) {
        Random random = new Random();
        BigInteger sqrt;

        do {
            BigInteger randomBigInteger = new BigInteger(1024, random);
            BigInteger newRandomBigInteger = randomBigInteger.modPow(BigInteger.valueOf(2L), n);
            System.out.println("Generated value is: " + newRandomBigInteger.toString(16));
            System.out.println("Enter the server's answer(хочешь выйти, нажми 'q'): ");
            String next = in.next();
            if (next.equals("q")) {
                break;
            } else {
                sqrt = new BigInteger(next, 16);
//                System.out.println(sqrt.toString(16));
                if (!randomBigInteger.equals(sqrt) && !randomBigInteger.equals(sqrt.multiply(BigInteger.valueOf(-1L)))) {
                    BigInteger answer1 = (randomBigInteger.add(sqrt)).gcd(n);
                    BigInteger answer2 = n.divide(answer1);
                    if (answer1.equals(BigInteger.ONE) || answer2.equals(BigInteger.ONE)) {
                        System.out.println("не зашло");
                        continue;
                    }
                    System.out.println("q = " + answer1.toString(16));
                    System.out.println("p = " + answer2.toString(16));
                    System.out.println("ans = " + answer1.multiply(answer2) );
                    System.out.println(answer1.multiply(answer2).equals(n));
                    break;
                } else {
                    System.out.println("Shit--->chance missed");
                }
            }
        } while (true);
    }
}
