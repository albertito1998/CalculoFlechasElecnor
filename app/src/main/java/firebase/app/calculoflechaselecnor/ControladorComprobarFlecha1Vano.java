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
 * Controlador para la actividad que permite comprobar la flecha en un vano.
 * Maneja la entrada de datos, validación y cálculo de los resultados.
 */
public class ControladorComprobarFlecha1Vano extends Activity {

    public static final String ACCION = "Accion"; // Constante para identificar la acción en los Intents

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprobarflecha1vano);

        // Configura los botones y sus listeners
        configurarBotones();

        // Obtener referencias a los TextView
        TextView unidadesAngulo = findViewById(R.id.unidadesAngulo);      // Para el ángulo
        TextView unidadesAngulo1 = findViewById(R.id.unidadesAngulo1);      // Para el ángulo


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

            // Cambiar el texto a "pies" para distancias
            unidadesDistancias.setText("ft");
            unidadesDistancias1.setText("ft");
            unidadesDistancias2.setText("ft");
            unidadesDistancias3.setText("ft");
        } else {
            // Mantener el texto en grados (º) para ángulos
            unidadesAngulo.setText("º");
            unidadesAngulo1.setText("º"); // El símbolo de segundos de arco

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
        // Configura el botón para cancelar y cerrar la actividad
        Button botonCancelar = findViewById(R.id.cmdVolver);
        botonCancelar.setOnClickListener(view -> finish());

        // Configura el botón de ayuda para abrir la actividad de ayuda
        ImageButton botonAyuda = findViewById(R.id.cmdAyuda);
        botonAyuda.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AbrirAyuda.class);
            Bundle bundle = new Bundle();
            bundle.putInt(ACCION, 1); // Establece la acción para la ayuda
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        });

        // Configura el botón para vaciar todos los campos
        Button botonVaciar = findViewById(R.id.cmdVaciar);
        botonVaciar.setOnClickListener(view -> vaciarCampos());

        // Configura el botón para comprobar los datos ingresados
        Button botonComprobar = findViewById(R.id.cmdComprobar);
        botonComprobar.setOnClickListener(view -> comprobarDatos());
    }

    /**
     * Vacía todos los campos de entrada y restablece los textos de los resultados.
     */
    private void vaciarCampos() {
        EditText dist = findViewById(R.id.txtDistancia);
        EditText anggrapa = findViewById(R.id.TxtAnguloGrapa);
        EditText angcable = findViewById(R.id.TxtAnguloCable);
        EditText longvan = findViewById(R.id.TxtLongitudVano);
        EditText flechteor = findViewById(R.id.TxtFlechaTeorica);
        TextView flechareal = findViewById(R.id.txtFlechaReal);
        TextView cablebajo = findViewById(R.id.TxtCableBajo);

        dist.setText("");
        anggrapa.setText("");
        angcable.setText("");
        longvan.setText("");
        flechteor.setText("");
        flechareal.setText(getString(R.string.flechareal_prefix));
        cablebajo.setText(getString(R.string.cablebajo_prefix));
    }

    /**
     * Comprueba los datos ingresados, realiza cálculos y muestra los resultados.
     */
    @SuppressLint("SetTextI18n")
    private void comprobarDatos() {
        EditText dist = findViewById(R.id.txtDistancia);
        EditText anggrapa = findViewById(R.id.TxtAnguloGrapa);
        EditText angcable = findViewById(R.id.TxtAnguloCable);
        EditText longvan = findViewById(R.id.TxtLongitudVano);
        EditText flechteor = findViewById(R.id.TxtFlechaTeorica);

        TextView msgError = findViewById(R.id.txtMensajeError);
        msgError.setTextColor(getResources().getColor(R.color.error_color));





        // Verifica si todos los campos están llenos
        boolean camposVacios = false;
        if (isFieldEmpty(dist)) {
            setErrorVisibility(R.id.txtErr1, msgError);
            camposVacios = true;
        } else {
            hideError(R.id.txtErr1, msgError);
        }

        if (isFieldEmpty(anggrapa)) {
            setErrorVisibility(R.id.txtErr2, msgError);
            camposVacios = true;
        } else {
            hideError(R.id.txtErr2, msgError);
        }

        if (isFieldEmpty(angcable)) {
            setErrorVisibility(R.id.txtErr3, msgError);
            camposVacios = true;
        } else {
            hideError(R.id.txtErr3, msgError);
        }

        if (isFieldEmpty(longvan)) {
            setErrorVisibility(R.id.txtErr4, msgError);
            camposVacios = true;
        } else {
            hideError(R.id.txtErr4, msgError);
        }

        if (isFieldEmpty(flechteor)) {
            setErrorVisibility(R.id.txtErr5, msgError);
            camposVacios = true;
        } else {
            hideError(R.id.txtErr5, msgError);
        }

        // Realiza los cálculos si todos los campos están llenos
        if (!camposVacios) {
            realizarCalculos(dist, anggrapa, angcable, longvan, flechteor);
        } else {
            mostrarResultadosPorDefecto();
        }
    }

    private boolean isFieldEmpty(EditText field) {
        return field.getText().toString().trim().isEmpty();
    }

    private void setErrorVisibility(int errorViewId, TextView msgError) {
        TextView errorView = findViewById(errorViewId);
        errorView.setVisibility(View.VISIBLE);
        msgError.setVisibility(View.VISIBLE);
    }

    private void hideError(int errorViewId, TextView msgError) {
        TextView errorView = findViewById(errorViewId);
        errorView.setVisibility(View.INVISIBLE);
        if (errorView.getVisibility() == View.INVISIBLE && !msgError.isShown()) {
            msgError.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    private void realizarCalculos(EditText dist, EditText anggrapa, EditText angcable, EditText longvan, EditText flechteor) {
        double G = Double.parseDouble(anggrapa.getText().toString());
        double C = Double.parseDouble(angcable.getText().toString());
        double L = Double.parseDouble(longvan.getText().toString());
        double H = Double.parseDouble(dist.getText().toString());
        double F = Double.parseDouble(flechteor.getText().toString());

        double resultado;
        double rescable;

        if (C >= 100.0d || G >= 100.0d) {
            if (C > 100.0d && G > 100.0d) {
                resultado = flechareal2(G, C, L, H);
            } else {
                resultado = flechareal3(G, C, L, H);
            }
        } else {
            resultado = flechareal1(G, C, L, H);
        }

        rescable = calculocable(resultado, F);

        DecimalFormat df = new DecimalFormat("0.000");
        TextView flechaReal = findViewById(R.id.txtFlechaReal);
        TextView cableBajo = findViewById(R.id.TxtCableBajo);

        flechaReal.setText(getString(R.string.flechareal_prefix) + df.format(resultado) + " metros");
        cableBajo.setText(getString(R.string.cablebajo_prefix) + df.format(Math.abs(rescable)) + " metros");
    }

    private void mostrarResultadosPorDefecto() {
        TextView flechaReal = findViewById(R.id.txtFlechaReal);
        TextView cableBajo = findViewById(R.id.TxtCableBajo);

        flechaReal.setText(getString(R.string.flechareal_placeholder));
        cableBajo.setText(getString(R.string.cablebajo_placeholder));
    }

    public double flechareal1(double G, double C, double L, double H) {
        OperacionesMatematicas om = new OperacionesMatematicas();
        double calctg = om.calculotang1(G, C);
        double calcraiz = om.calculoraiz(calctg, L, H);
        return Math.pow(calcraiz / 2.0d, 2.0d);
    }

    public double flechareal2(double G, double C, double L, double H) {
        OperacionesMatematicas om = new OperacionesMatematicas();
        double calctg = om.calculotang2(G, C);
        double calcraiz = om.calculoraiz(calctg, L, H);
        return Math.pow(calcraiz / 2.0d, 2.0d);
    }

    public double flechareal3(double G, double C, double L, double H) {
        OperacionesMatematicas om = new OperacionesMatematicas();
        double calctg = om.calculotang3(G, C);
        double calcraiz = om.calculoraiz(calctg, L, H);
        return Math.pow(calcraiz / 2.0d, 2.0d);
    }

    public double calculocable(double res, double F) {
        return res - F;
    }
}
