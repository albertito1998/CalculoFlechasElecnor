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
 * Controlador para la actividad que permite calcular el ángulo para flechar un vano.
 * Maneja la entrada de datos, validación y cálculo del ángulo.
 */
public class Flechar1Vano extends Activity {
    public static final String ACCION = "Accion";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flechar1vano);

        // Obtener referencias a los TextView
        TextView unidadesAngulo = findViewById(R.id.unidadesAngulo);      // Para el ángulo
        TextView unidadesAngulo1 = findViewById(R.id.unidadesAngulo1);      // Para el ángulo
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
            // Cambiar el texto a "pies" para distancias
            unidadesDistancias.setText("ft");
            unidadesDistancias1.setText("ft");
            unidadesDistancias2.setText("ft");
        } else {
            // Mantener el texto en grados (º) para ángulos
            unidadesAngulo.setText("º");
            unidadesAngulo1.setText("º"); // El símbolo de segundos de arco
            // Mantener el texto en metros (m) para distancias
            unidadesDistancias.setText("m");
            unidadesDistancias1.setText("m");
            unidadesDistancias2.setText("m");
        }



        configurarBotones();
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
            bundle.putInt(ACCION, 3);
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
     * Vacía todos los campos de entrada y restablece el texto del ángulo para flechar.
     */
    private void vaciarCampos() {
        EditText dist = findViewById(R.id.txtDistancia);
        EditText anggrapa = findViewById(R.id.txtanggrapa);
        EditText longvan = findViewById(R.id.txtlongvano);
        EditText flechteor = findViewById(R.id.txtflechteor);
        TextView anguloflechar = findViewById(R.id.txtanguloflechar);

        dist.setText("");
        anggrapa.setText("");
        longvan.setText("");
        flechteor.setText("");
        anguloflechar.setText(getString(R.string.anguloflechar_prefix));
    }

    /**
     * Comprueba los datos ingresados, realiza cálculos y muestra el ángulo para flechar.
     */
    @SuppressLint("SetTextI18n")
    private void comprobarDatos() {
        EditText dist = findViewById(R.id.txtDistancia);
        EditText anggrapa = findViewById(R.id.txtanggrapa);
        EditText longvan = findViewById(R.id.txtlongvano);
        EditText flechteor = findViewById(R.id.txtflechteor);

        TextView msgError = findViewById(R.id.txtMensajeError);
        msgError.setTextColor(getResources().getColor(R.color.error_color));





        boolean falta = false;
        falta |= verificarCampoVacio(dist, R.id.txterror1, msgError);
        falta |= verificarCampoVacio(anggrapa, R.id.txterror2, msgError);
        falta |= verificarCampoVacio(longvan, R.id.txterror3, msgError);
        falta |= verificarCampoVacio(flechteor, R.id.txterror4, msgError);

        if (!falta) {
            realizarCalculos(dist, anggrapa, longvan, flechteor);
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
        return findViewById(R.id.txterror1).isShown() ||
                findViewById(R.id.txterror2).isShown() ||
                findViewById(R.id.txterror3).isShown() ||
                findViewById(R.id.txterror4).isShown();
    }

    @SuppressLint("SetTextI18n")
    private void realizarCalculos(EditText dist, EditText anggrapa, EditText longvan, EditText flechteor) {
        double H = Double.parseDouble(dist.getText().toString());
        double G = Double.parseDouble(anggrapa.getText().toString());
        double V = Double.parseDouble(longvan.getText().toString());
        double F = Double.parseDouble(flechteor.getText().toString());

        double S = CalculoS(F, H, V);
        double arctan = CalculoArcTan(G, S);
        double resultado = arctan;

        if (arctan < 0.0d) {
            resultado = CalculoC(S, G);
        }

        DecimalFormat df = new DecimalFormat("0.000");
        TextView anguloflechar = findViewById(R.id.txtanguloflechar);
        anguloflechar.setText(getString(R.string.anguloflechar_prefix) + df.format(resultado) + " º");
    }

    private void mostrarResultadosPorDefecto() {
        TextView anguloflechar = findViewById(R.id.txtanguloflechar);
        anguloflechar.setText(getString(R.string.anguloflechar_placeholder));
    }

    public double CalculoS(double F, double H, double V) {
        return Math.pow((Math.sqrt(F) * 2.0d) - Math.sqrt(H), 2.0d) / V;
    }

    public double CalculoArcTan(double G, double S) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Genrad = OM.aradianes(G);
        double op1 = 1.0d / ((1.0d / Math.tan(Genrad)) - S);
        double res1 = Math.atan(op1);
        return OM.agrados(res1);
    }

    public double CalculoC(double S, double G) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Genrad = OM.aradianes(G - 100.0d);
        double op1 = S + Math.tan(Genrad);
        double res1 = Math.atan(op1);
        return 100.0d + OM.agrados(res1);
    }
}
