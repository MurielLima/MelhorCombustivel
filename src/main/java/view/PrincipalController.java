package view;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController implements Initializable {

    private final char separadorDecimal
            = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();
    private final NumberFormat nf
            = NumberFormat.getInstance(Locale.getDefault());
    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtFldGasolinaValor;
    @FXML
    private TextField txtFldGasolinaRendimento;
    @FXML
    private TextField txtFldAlcoolValor;
    @FXML
    private TextField txtFldAlcoolRendimento;
    @FXML
    private Label lblResultado;

    @FXML
    private void btnFecharClick(Event event) {
        System.exit(0);
    }

    @FXML
    private void btnCalcularClick() {
        try {
            if ((nf.parse(txtFldGasolinaValor.getText()).doubleValue()) != 0
                    && (nf.parse(txtFldGasolinaRendimento.getText()).doubleValue()) != 0
                    && (nf.parse(txtFldAlcoolValor.getText()).doubleValue()) != 0
                    && (nf.parse(txtFldAlcoolRendimento.getText()).doubleValue()) != 0) {

                if ((nf.parse(txtFldGasolinaRendimento.getText()).doubleValue())
                        >= (nf.parse(txtFldAlcoolRendimento.getText()).doubleValue())) {
                    double custoBeneficioGasolina, custoBeneficioAlcool;
                    custoBeneficioGasolina = (nf.parse(txtFldGasolinaValor.getText()).doubleValue()) / (nf.parse(txtFldGasolinaRendimento.getText()).doubleValue());
                    custoBeneficioAlcool = (nf.parse(txtFldAlcoolValor.getText()).doubleValue()) / (nf.parse(txtFldAlcoolRendimento.getText()).doubleValue());

                    if (custoBeneficioAlcool < custoBeneficioGasolina) {
                        lblResultado.setText("O escolhido para você é: Álcool");
                    } else if (custoBeneficioAlcool > custoBeneficioGasolina) {
                        lblResultado.setText("O escolhido para você é: Gasolina");
                    } else {
                        lblResultado.setText("A escolha é sua o custoXbeneficio é o mesmo.");
                    }
                } else {
                    lblResultado.setText("O rendimento do Álcool não pode ser maior/igual a Gasolina.");
                }
            } else {
                lblResultado.setText("Operação inválida: Algum(s) campos estão zerados.");
            }
        } catch (ParseException ex) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnCalcular.disableProperty().bind(txtFldGasolinaValor.textProperty().isEmpty()
                .or(txtFldGasolinaRendimento.textProperty().isEmpty())
                .or(txtFldAlcoolValor.textProperty().isEmpty())
                .or(txtFldAlcoolRendimento.textProperty().isEmpty()));

        txtFldGasolinaValor.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                    && !newValue.isEmpty()) {
                        txtFldGasolinaValor.setText(oldValue);
                    } else {
                        txtFldGasolinaValor.setText(newValue);
                    }
                });
        txtFldGasolinaRendimento.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                    && !newValue.isEmpty()) {
                        txtFldGasolinaRendimento.setText(oldValue);
                    } else {
                        txtFldGasolinaRendimento.setText(newValue);
                    }
                });
        txtFldAlcoolValor.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                    && !newValue.isEmpty()) {
                        txtFldAlcoolValor.setText(oldValue);
                    } else {
                        txtFldAlcoolValor.setText(newValue);
                    }
                });
        txtFldAlcoolRendimento.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                    && !newValue.isEmpty()) {
                        txtFldAlcoolRendimento.setText(oldValue);
                    } else {
                        txtFldAlcoolRendimento.setText(newValue);
                    }
                });
    }
}
