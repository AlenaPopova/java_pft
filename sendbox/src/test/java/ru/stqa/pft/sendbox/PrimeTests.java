package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Алёна on 04.06.2017.
 */
public class PrimeTests {

  @Test
  public void testPrime(){

    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test (enabled = false) // этот тест отключен
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test

  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-1));
  }
}
