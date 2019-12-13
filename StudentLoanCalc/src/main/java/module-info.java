module StudentLoanCalc {
	
	exports app;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.media;
	requires poi;
	
	opens pkgLogic;

	opens app.controller to javafx.fxml;
}