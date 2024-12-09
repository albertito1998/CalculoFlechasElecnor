package firebase.app.calculoflechaselecnor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Controlador para la actividad que permite comprobar las flechas en dos vanos.
 * Maneja la entrada de datos, validación y cálculo de los resultados.
 */
public class ControladorComprobarFlechas2Vanos extends Activity {
    public static final String ACCION = "Accion";

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprobarflecha2vanos);

        configurarBotones();

        // Obtener referencias a los TextView
        TextView unidadesAngulo = findViewById(R.id.unidadesAngulo);      // Para el ángulo
        TextView unidadesAngulo1 = findViewById(R.id.unidadesAngulo1);      // Para el ángulo
        TextView unidadesAngulo2 = findViewById(R.id.unidadesAngulo2);      // Para el ángulo

        TextView unidadesDistancias = findViewById(R.id.unidadesDistancia); // Para las distancias
        TextView unidadesDistancias1 = findViewById(R.id.unidadesDistancia1); // Para las distancias
        TextView unidadesDistancias2 = findViewById(R.id.unidadesDistancia2); // Para las distancias
        TextView unidadesDistancias3 = findViewById(R.id.unidadesDistancia3); // Para las distancias
        // Referencia al TextView
        TextView tvSelectedUnits = findViewById(R.id.tvSelectedUnits);

        // Obtener el valor de la variable global (selected system) y mostrarlo en el TextView
        String selectedSystem;


        selectedSystem = GlobalData.getSelectedSystem();


        // Actualizar el texto del TextView
        tvSelectedUnits.setText("Selected units: " + selectedSystem);

        // Verificar si el sistema es imperial o métrico y ajustar el texto del TextView para los ángulos
        if ("Imperial".equals(selectedSystem)) {
            // Cambiar el texto a segundos de arco para ángulos
            unidadesAngulo.setText("\""); // El símbolo de segundos de arco
            unidadesAngulo1.setText("\""); // El símbolo de segundos de arco
            unidadesAngulo2.setText("\""); // El símbolo de segundos de arco
            // Cambiar el texto a "pies" para distancias
            unidadesDistancias.setText("ft");
            unidadesDistancias1.setText("ft");
            unidadesDistancias2.setText("ft");
            unidadesDistancias3.setText("ft");
        } else {
            // Mantener el texto en grados (º) para ángulos
            unidadesAngulo.setText("º");
            unidadesAngulo1.setText("º"); // El símbolo de segundos de arco
            unidadesAngulo2.setText("\""); // El símbolo de segundos de arco
            // Mantener el texto en metros (m) para distancias
            unidadesDistancias.setText("m");
            unidadesDistancias1.setText("m");
            unidadesDistancias2.setText("m");
            unidadesDistancias3.setText("m");
        }
    }

    /**
     * Configura los botones en la actividad y asigna sus listeners.
     */
    private void configurarBotones() {
        Button botonCancelar = findViewById(R.id.cmdVolver);
        botonCancelar.setOnClickListener(view -> finish());

        ImageButton botonAyuda = findViewById(R.id.cmdAyuda);
        botonAyuda.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ACCION, 2);
            Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
            myIntent.putExtras(bundle);
            startActivityForResult(myIntent, 0);
        });

        Button botonVaciar = findViewById(R.id.cmdVaciar);
        botonVaciar.setOnClickListener(view -> vaciarCampos());

        Button botonComprobar = findViewById(R.id.cmdComprobar);
        botonComprobar.setOnClickListener(view -> comprobarDatos());
    }

    /**
     * Vacía todos los campos de entrada y restablece los textos de los resultados.
     */
    @SuppressLint("SetTextI18n")
    private void vaciarCampos() {
        EditText anggrapa1 = findViewById(R.id.txtanggrapa1);
        EditText anggrapa2 = findViewById(R.id.txtanggrapa2);
        EditText angcablevano = findViewById(R.id.txtangcablevano2);
        EditText longvan1 = findViewById(R.id.txtlongvano1);
        EditText longvan2 = findViewById(R.id.txtlongvano2);
        EditText flechavano = findViewById(R.id.TxtFlechavano2);
        TextView flechaReal = findViewById(R.id.txtFlechaReal);
        TextView cableBajo = findViewById(R.id.TxtCableBajo);

        anggrapa1.setText("");
        anggrapa2.setText("");
        angcablevano.setText("");
        longvan1.setText("");
        longvan2.setText("");
        flechavano.setText("");
        flechaReal.setText(getString(R.string.flechareal_prefix));
        cableBajo.setText(getString(R.string.cablebajo_prefix));



    }

    /**
     * Comprueba los datos ingresados, realiza cálculos y muestra los resultados.
     */
    private void comprobarDatos() {
        EditText anggrapa1 = findViewById(R.id.txtanggrapa1);
        EditText anggrapa2 = findViewById(R.id.txtanggrapa2);
        EditText angcablevano = findViewById(R.id.txtangcablevano2);
        EditText longvan1 = findViewById(R.id.txtlongvano1);
        EditText longvan2 = findViewById(R.id.txtlongvano2);
        EditText flechavano = findViewById(R.id.TxtFlechavano2);

        TextView msgError = findViewById(R.id.txtMensajeError);
        msgError.setTextColor(getResources().getColor(R.color.error_color));

        // Verifica si todos los campos están llenos
        boolean falta = false;
        falta |= verificarCampoVacio(anggrapa1, R.id.TxtErr1, msgError);
        falta |= verificarCampoVacio(anggrapa2, R.id.TxtErr2, msgError);
        falta |= verificarCampoVacio(angcablevano, R.id.TxtErr3, msgError);
        falta |= verificarCampoVacio(longvan1, R.id.TxtErr4, msgError);
        falta |= verificarCampoVacio(longvan2, R.id.TxtErr5, msgError);
        falta |= verificarCampoVacio(flechavano, R.id.TxtErr6, msgError);

        if (!falta) {
            realizarCalculos(anggrapa1, anggrapa2, angcablevano, longvan1, longvan2, flechavano);
        } else {
            mostrarResultadosPorDefecto();
        }
    }

    private boolean verificarCampoVacio(EditText campo, int errorViewId, TextView msgError) {
        boolean vacio = campo.getText().toString().trim().isEmpty();
        TextView errorView = findViewById(errorViewId);
        if (vacio) {
            errorView.setVisibility(View.VISIBLE);
            msgError.setVisibility(View.VISIBLE);
        } else {
            errorView.setVisibility(View.INVISIBLE);
            if (!msgError.isShown() && !isAnyErrorVisible()) {
                msgError.setVisibility(View.INVISIBLE);
            }
        }
        return vacio;
    }

    private boolean isAnyErrorVisible() {
        // Verifica si algún error está visible
        return findViewById(R.id.TxtErr1).isShown() ||
                findViewById(R.id.TxtErr2).isShown() ||
                findViewById(R.id.TxtErr3).isShown() ||
                findViewById(R.id.TxtErr4).isShown() ||
                findViewById(R.id.TxtErr5).isShown() ||
                findViewById(R.id.TxtErr6).isShown();
    }

    @SuppressLint("SetTextI18n")
    private void realizarCalculos(EditText anggrapa1, EditText anggrapa2, EditText angcablevano, EditText longvan1, EditText longvan2, EditText flechavano) {
        double E = Double.parseDouble(anggrapa1.getText().toString());
        double G = Double.parseDouble(anggrapa2.getText().toString());
        double C = Double.parseDouble(angcablevano.getText().toString());
        double J = Double.parseDouble(longvan1.getText().toString());
        double K = Double.parseDouble(longvan2.getText().toString());
        double F = Double.parseDouble(flechavano.getText().toString());

        double H;
        if (E >= 100.0d || C >= 100.0d) {
            H = E < 100.0d && C > 100.0d ? CalculoH2(J, E, C) : CalculoH3(J, E, C);
        } else {
            H = CalculoH1(J, E, C);
        }

        double L = J + K;
        double resultado;
        double rescable;

        if (C >= 100.0d || E >= 100.0d) {
            if (C > 100.0d && G < 100.0d) {
                resultado = flechareal2(G, C, L, H);
                rescable = calculocable(resultado, F);
            } else {
                resultado = flechareal3(G, C, L, H);
                rescable = calculocable(resultado, F);
            }
        } else {
            resultado = flechareal1(G, C, L, H);
            rescable = calculocable(resultado, F);
        }

        DecimalFormat df = new DecimalFormat("0.000");
        TextView flechaReal = findViewById(R.id.txtFlechaReal);
        TextView cableBajo = findViewById(R.id.TxtCableBajo);
        flechaReal.setText(getString(R.string.flechareal_prefix) + df.format(resultado) + " metros");
        cableBajo.setText(getString(rescable < 0.0d ? R.string.cablealto_prefix : R.string.cablebajo_prefix) + df.format(Math.abs(rescable)) + " metros");
    }

    private void mostrarResultadosPorDefecto() {
        TextView flechaReal = findViewById(R.id.txtFlechaReal);
        TextView cableBajo = findViewById(R.id.TxtCableBajo);
        flechaReal.setText(getString(R.string.flechareal_placeholder));
        cableBajo.setText(getString(R.string.cablebajo_placeholder));
    }

    public double CalculoH1(double J, double E, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang1(E, C);
        return J * calctg;
    }

    public double CalculoH2(double J, double E, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang2(E, C);
        return J * calctg;
    }

    public double CalculoH3(double J, double E, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang3(E, C);
        return J * calctg;
    }

    public double flechareal1(double G, double C, double L, double H) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang1(G, C);
        double calcraiz = OM.calculoraiz(calctg, L, H);
        return Math.pow(calcraiz / 2.0d, 2.0d);
    }

    public double flechareal2(double G, double C, double L, double H) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang2(G, C);
        double calcraiz = OM.calculoraiz(calctg, L, H);
        return Math.pow(calcraiz / 2.0d, 2.0d);
    }

    public double flechareal3(double G, double C, double L, double H) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang3(G, C);
        double calcraiz = OM.calculoraiz(calctg, L, H);
        return Math.pow(calcraiz / 2.0d, 2.0d);
    }

    public double calculocable(double res, double F) {
        return res - F;
    }
}
