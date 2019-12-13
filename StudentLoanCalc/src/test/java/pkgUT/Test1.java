package pkgUT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Calendar;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.jupiter.api.Test;

import pkgLogic.Loan;

public class Test1 {
	@Test
	public void TestLoanWithoutAddPMT() {

		double r = 0.04;
		int n = 1;
		double p = 5000;

		double apmt = 0;

		LocalDate c = LocalDate.of(19, 12, 9);

		Loan l = new Loan(r, n, c, apmt, p);
		l.setPayments(l.autoPayments());

		double PMTExpected = 425.75;
		assertEquals(PMTExpected, l.getPMT(), 0.01);

		// first payment
		assertEquals(1, l.getPayments().get(0).getPaymentNbr());

		double Principle1Expected = 409.08;
		assertEquals(Principle1Expected, l.getPayments().get(0).getPrinciple(), 0.01);

		double Balance1Expected = 5000;
		assertEquals(Balance1Expected, l.getPayments().get(0).getBalance(), 0.01);

		double Interest1Expected = 16.67;
		assertEquals(Interest1Expected, l.getPayments().get(0).getInterest(), 0.01);

		// second payment
		int PMTNbr2Expected = 2;
		assertEquals(PMTNbr2Expected, l.getPayments().get(1).getPaymentNbr());

		double Principle2Expected = 410.45;
		assertEquals(Principle2Expected, l.getPayments().get(1).getPrinciple(), 0.01);

		double Balance2Expected = 4590.92;
		assertEquals(Balance2Expected, l.getPayments().get(1).getBalance(), 0.01);

		double Interest2Expected = 15.30;
		assertEquals(Interest2Expected, l.getPayments().get(1).getInterest(), 0.01);

		// last payment
		int lastPMTNbrExpected = 12;
		assertEquals(lastPMTNbrExpected, l.getPayments().get(11).getPaymentNbr());

		double Principle12Expected = 422.92;
		assertEquals(Principle12Expected, l.getPayments().get(11).getPrinciple(), 0.01);

		double Balance12Expected = 424.34;
		assertEquals(Balance12Expected, l.getPayments().get(11).getBalance(), 0.01);

		double Interest12Expected = 1.41;
		assertEquals(Interest12Expected, l.getPayments().get(11).getInterest(), 0.01);

	}
	
	@Test
	public void TestLoanWithAddPMT() {

		double r = 0.04;
		int n = 1;
		double p = 5000;

		double apmt = 100;

		LocalDate c = LocalDate.of(19, 12, 9);

		Loan l = new Loan(r, n, c, apmt, p);
		l.setPayments(l.autoPayments());

		double PMTExpected = 425.75;
		assertEquals(PMTExpected, l.getPMT(), 0.01);

		// first payment
		assertEquals(1, l.getPayments().get(0).getPaymentNbr());

		double Principle1Expected = 509.08;
		assertEquals(Principle1Expected, l.getPayments().get(0).getPrinciple(), 0.01);

		double Balance1Expected = 5000;
		assertEquals(Balance1Expected, l.getPayments().get(0).getBalance(), 0.01);

		double Interest1Expected = 16.67;
		assertEquals(Interest1Expected, l.getPayments().get(0).getInterest(), 0.01);

		// second payment
		int PMTNbr2Expected = 2;
		assertEquals(PMTNbr2Expected, l.getPayments().get(1).getPaymentNbr());

		double Principle2Expected = 510.78;
		assertEquals(Principle2Expected, l.getPayments().get(1).getPrinciple(), 0.01);

		double Balance2Expected = 4490.92;
		assertEquals(Balance2Expected, l.getPayments().get(1).getBalance(), 0.01);

		double Interest2Expected = 14.97;
		assertEquals(Interest2Expected, l.getPayments().get(1).getInterest(), 0.01);

		// last payment
		int lastPMTNbrExpected = 10;
		assertEquals(lastPMTNbrExpected, l.getPayments().get(9).getPaymentNbr());

		double Principle12Expected = 455.50;
		assertEquals(Principle12Expected, l.getPayments().get(9).getPrinciple(), 0.01);

		double Balance12Expected = 356.69;
		assertEquals(Balance12Expected, l.getPayments().get(9).getBalance(), 0.01);

		double Interest12Expected = 1.19;
		assertEquals(Interest12Expected, l.getPayments().get(9).getInterest(), 0.01);

	}

}
