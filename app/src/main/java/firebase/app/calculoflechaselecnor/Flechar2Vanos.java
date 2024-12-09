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
 * Controlador para la actividad que permite calcular el ángulo para flechar el segundo vano.
 * Maneja la entrada de datos, validación y cálculo del ángulo.
 */
public class Flechar2Vanos extends Activity {
    public static final String ACCION = "Accion";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flechar2vanos);

        configurarBotones();

        // Obtener referencias a los TextView
        TextView unidadesAngulo = findViewById(R.id.unidadesAngulo);      // Para el ángulo
        TextView unidadesAngulo1 = findViewById(R.id.unidadesAngulo1);      // Para el ángulo
        TextView unidadesAngulo2 = findViewById(R.id.unidadesAngulo2);      // Para el ángulo

        TextView unidadesDistancias = findViewById(R.id.unidadesDistancia); // Para las distancias
        TextView unidadesDistancias1 = findViewById(R.id.unidadesDistancia1); // Para las distancias
        TextView unidadesDistancias2 = findViewById(R.id.unidadesDistancia2); // Para las distancias

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
        } else {
            // Mantener el texto en grados (º) para ángulos
            unidadesAngulo.setText("º");
            unidadesAngulo1.setText("º"); // El símbolo de segundos de arco
            unidadesAngulo2.setText("º"); // El símbolo de segundos de arco
            // Mantener el texto en metros (m) para distancias
            unidadesDistancias.setText("m");
            unidadesDistancias1.setText("m");
            unidadesDistancias2.setText("m");
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
            bundle.putInt(ACCION, 4);
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
     * Vacía todos los campos de entrada y restablece el texto del ángulo para flechar vano 2.
     */
    private void vaciarCampos() {
        EditText anggrapa1 = findViewById(R.id.txtanggrapa1);
        EditText anggrapa2 = findViewById(R.id.txtanggrapa2);
        EditText longvan1 = findViewById(R.id.txtlonvan1);
        EditText longvan2 = findViewById(R.id.txtlonvan2);
        EditText flechteovan2 = findViewById(R.id.txtflechteorvan2);
        TextView anguloflechar = findViewById(R.id.txtanguloflechar);

        anggrapa1.setText("");
        anggrapa2.setText("");
        longvan1.setText("");
        longvan2.setText("");
        flechteovan2.setText("");
        anguloflechar.setText(getString(R.string.anguloflechar_vano2_prefix));
    }

    /**
     * Comprueba los datos ingresados, realiza cálculos y muestra el ángulo para flechar vano 2.
     */
    @SuppressLint("SetTextI18n")
    private void comprobarDatos() {
        EditText anggrapa1 = findViewById(R.id.txtanggrapa1);
        EditText anggrapa2 = findViewById(R.id.txtanggrapa2);
        EditText longvan1 = findViewById(R.id.txtlonvan1);
        EditText longvan2 = findViewById(R.id.txtlonvan2);
        EditText flechteovan2 = findViewById(R.id.txtflechteorvan2);

        TextView msgError = findViewById(R.id.txtMensajeError);
        msgError.setTextColor(getResources().getColor(R.color.error_color));



        boolean falta = false;
        falta |= verificarCampoVacio(anggrapa1, R.id.txtError1);
        falta |= verificarCampoVacio(anggrapa2, R.id.txtError2);
        falta |= verificarCampoVacio(longvan1, R.id.txtError3);
        falta |= verificarCampoVacio(longvan2, R.id.txtError4);
        falta |= verificarCampoVacio(flechteovan2, R.id.txtError5);

        if (!falta) {
            realizarCalculos(anggrapa1, anggrapa2, longvan1, longvan2, flechteovan2);
        } else {
            mostrarResultadosPorDefecto();
        }
    }

    private boolean verificarCampoVacio(EditText campo, int errorViewId) {
        boolean vacio = campo.getText().toString().trim().isEmpty();
        TextView errorView = findViewById(errorViewId);
        if (vacio) {
            errorView.setVisibility(View.VISIBLE);
        } else {
            errorView.setVisibility(View.INVISIBLE);
        }
        return vacio;
    }

    @SuppressLint("SetTextI18n")
    private void realizarCalculos(EditText anggrapa1, EditText anggrapa2, EditText longvan1, EditText longvan2, EditText flechteovan2) {
        double A = Double.parseDouble(anggrapa1.getText().toString());
        double B = Double.parseDouble(anggrapa2.getText().toString());
        double C = Double.parseDouble(longvan1.getText().toString());
        double J = Double.parseDouble(longvan2.getText().toString());
        double F = Double.parseDouble(flechteovan2.getText().toString());

        double D = J + C;
        double E = CalcularE(F, D, C, B, A);
        double X = CalcularX(F, E, D, C);
        double P = CalcularP(A, X, C);

        double resultado = P > 0.0d ? P : 200.0d + P;
        DecimalFormat df = new DecimalFormat("0.000");
        TextView anguloflechar = findViewById(R.id.txtanguloflechar);
        anguloflechar.setText(getString(R.string.anguloflechar_vano2_prefix) + df.format(resultado) + " º");
    }

    private void mostrarResultadosPorDefecto() {
        TextView anguloflechar = findViewById(R.id.txtanguloflechar);
        anguloflechar.setText(getString(R.string.anguloflechar_vano2_placeholder));
    }

    public double CalcularE(double F, double D, double C, double B, double A) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Benrad = OM.aradianes(B);
        double Aenrad = OM.aradianes(A);
        double OPTan = (1.0d / Math.tan(Benrad)) - (1.0d / Math.tan(Aenrad));
        double primfactor = 4.0d * ((D / C) - 1.0d);
        double segfactor = (D * OPTan) - (4.0d * F);
        return Math.sqrt((16.0d * F) - (primfactor * segfactor));
    }

    public double CalcularX(double F, double E, double D, double C) {
        return (((-4.0d) * Math.sqrt(F)) + E) / (2.0d * ((D / C) - 1.0d));
    }

    public double CalcularP(double A, double X, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Aenrad = OM.aradianes(A);
        double RES1 = Math.pow((1.0d / Math.tan(Aenrad)) - (Math.pow(X, 2.0d) / C), -1.0d);
        double res = Math.atan(RES1);
        return OM.agrados(res);
    }
}
