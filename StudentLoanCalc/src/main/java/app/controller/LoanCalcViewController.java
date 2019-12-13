package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pkgLogic.Loan;
import pkgLogic.Payment;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable {

	private StudentCalc SC = null;

	@FXML
	private TextField LoanAmount;

	@FXML
	private TextField InterestRate;

	@FXML
	private TextField NbrOfYears;

	@FXML
	private TextField AddPMT;

	@FXML
	private DatePicker PaymentStartDate;

	@FXML
	private Label lblTotalPayemnts;

	@FXML
	private Label lblTotalInterest;

	@FXML
	private TableView<Payment> tvResults;

	@FXML
	private TableColumn<Payment, Integer> colPaymentNumber;

	@FXML
	private TableColumn<Payment, Double> colInterest;

	@FXML
	private TableColumn<Payment, Double> colPrinciple;

	@FXML
	private TableColumn<Payment, Double> colBalance;

	@FXML
	private TableColumn<Payment, Double> colPMT;

	@FXML
	private TableColumn<Payment, Double> colAddPMT;

	@FXML
	private TableColumn<Payment, Calendar> colDueDate;

	private ObservableList<Payment> paymentList = FXCollections.observableArrayList();;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		colPaymentNumber.setCellValueFactory(new PropertyValueFactory<>("PaymentNbr"));
		colInterest.setCellValueFactory(new PropertyValueFactory<>("Interest"));
		colPrinciple.setCellValueFactory(new PropertyValueFactory<>("Principle"));
		colBalance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
		colPMT.setCellValueFactory(new PropertyValueFactory<>("PMT"));
		colAddPMT.setCellValueFactory(new PropertyValueFactory<>("AddPMT"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));

	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}

	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		tvResults.getItems().clear();
		lblTotalPayemnts.setText("");
		lblTotalInterest.setText("");

		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dAddPMT = Double.parseDouble(AddPMT.getText());
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		int iTerm = Integer.parseInt(NbrOfYears.getText());
		LocalDate localDate = PaymentStartDate.getValue();

		tvResults.getColumns().setAll(colPaymentNumber, colDueDate, colPMT, colAddPMT, colInterest, colPrinciple,
				colBalance);

		Loan loan = new Loan(dInterestRate, iTerm, localDate, dAddPMT, dLoanAmount);
		loan.setPayments(loan.autoPayments());

		lblTotalPayemnts.setText(Integer.toString(loan.getPayments().size()));
		lblTotalInterest.setText(Double.toString(loan.totalInterest(loan.getPayments())));

		paymentList = FXCollections.observableArrayList(loan.getPayments());

		tvResults.setItems(paymentList);

	}

}

