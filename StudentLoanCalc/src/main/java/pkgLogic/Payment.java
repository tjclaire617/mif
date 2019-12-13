package pkgLogic;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Payment extends Loan {
	

	private int PaymentNbr;
	private double Interest;
	private LocalDate DueDate;
	private double Principle;
	private double Balance; //Beginning balance
	private double PMT;
	private double AddPMT;
	

	public Payment(int paymentNbr, LocalDate dueDate, double balance,double interestRate,int term,LocalDate firstPMTDate,double addPMT,double loanAmount) {
		super(interestRate, term,firstPMTDate, addPMT, loanAmount);
		this.PMT = super.getPMT();
		this.AddPMT = super.getAddPMT();
		this.PaymentNbr = paymentNbr;
		this.DueDate = dueDate;
		this.Balance = balance;
		this.Interest = balance* this.getInterestRate()/12;
		if((super.getPMT()+super.getAddPMT()) < balance)
			this.Principle = super.getPMT() - balance* this.getInterestRate()/12 + super.getAddPMT();
		else
			this.Principle = balance - Interest + super.getAddPMT();
	}
	
	public double getInterest() {
		return Interest;
	}

	public void setInterest(double interest) {
		Interest = interest;
	}

	public LocalDate getDueDate() {
		return DueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		DueDate = dueDate;
	}

	public double getPrinciple() {
		return Principle;
	}

	public void setPrinciple(double principle) {
		Principle = principle;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}	

	public int getPaymentNbr() {
		return PaymentNbr;
	}

	public void setPaymentNbr(int paymentNbr) {
		PaymentNbr = paymentNbr;
	}
	

}
