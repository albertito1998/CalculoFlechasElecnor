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

public class CalcularAltura extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcularaltura);

        // Inicializar componentes de UI
        Button botonCancelar = findViewById(R.id.cmdVolver);
        ImageButton botonAyuda = findViewById(R.id.cmdAyuda);
        Button botonVaciar = findViewById(R.id.cmdVaciar);
        Button botonComprobar = findViewById(R.id.cmdComprobar);

        EditText longVano = findViewById(R.id.txtLongVano);
        EditText angSup = findViewById(R.id.txtAngSup);
        EditText angInf = findViewById(R.id.txtAngInf);
        TextView alturaMedida = findViewById(R.id.txtAlturaMedida);
        TextView msgError = findViewById(R.id.txtMensajeError);
        // Obtener referencias a los TextView
        TextView unidadesAngulo = findViewById(R.id.unidadesAngulo); // Para el ángulo
        TextView unidadesAngulo1 = findViewById(R.id.unidadesAngulo1); // Para el ángulo
        TextView unidadesDistancias = findViewById(R.id.unidadesDistancias); // Para las distancias

        // Referencia al TextView
        TextView tvSelectedUnits = findViewById(R.id.tvSelectedUnits);

        // Obtener el valor de la variable global (selected system) y mostrarlo en el
        // TextView
        String selectedSystem;

        selectedSystem = GlobalData.getSelectedSystem();

        // Actualizar el texto del TextView
        tvSelectedUnits.setText("Selected units: " + selectedSystem);

        // Verificar si el sistema es imperial o métrico y ajustar el texto del TextView
        // para los ángulos
        if ("Imperial".equals(selectedSystem)) {
            // Cambiar el texto a segundos de arco para ángulos
            unidadesAngulo.setText("\""); // El símbolo de segundos de arco
            unidadesAngulo1.setText("\""); // El símbolo de segundos de arco
            // Cambiar el texto a "pies" para distancias
            unidadesDistancias.setText("ft");
        } else {
            // Mantener el texto en grados (º) para ángulos
            unidadesAngulo.setText("º");
            unidadesAngulo1.setText("º"); // El símbolo de segundos de arco
            // Mantener el texto en metros (m) para distancias
            unidadesDistancias.setText("m");
        }

        // Configurar acción del botón Cancelar
        botonCancelar.setOnClickListener(view -> finish());

        // Configurar acción del botón Ayuda
        botonAyuda.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("Accion", 5);
            Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
            myIntent.putExtras(bundle);
            startActivityForResult(myIntent, 0);
        });

        // Configurar acción del botón Vaciar
        botonVaciar.setOnClickListener(view -> {
            longVano.setText("");
            angSup.setText("");
            angInf.setText("");
            alturaMedida.setText(getString(R.string.altura_medida_placeholder));
            msgError.setVisibility(View.INVISIBLE);
        });

        // Configurar acción del botón Comprobar
        botonComprobar.setOnClickListener(view -> {
            if (validarCampos(longVano, angSup, angInf, msgError)) {
                double L = Double.parseDouble(longVano.getText().toString());
                double G = Double.parseDouble(angSup.getText().toString());
                double C = Double.parseDouble(angInf.getText().toString());

                // Si el sistema es imperial, se aplican las conversiones
                if ("Imperial".equals(selectedSystem)) {
                    // Si el sistema es imperial, convertir L de pies a metros para cálculos
                    L = L / 3.28084; // Convertir pies a metros (inverso de metrosAPies)

                    // Convertir ángulos decimales a sexagesimales (para que la función
                    // OperacionesMatematicas trabaje correctamente)
                    double[] G_sexagesimal = decimalASexagesimal(G);
                    double[] C_sexagesimal = decimalASexagesimal(C);

                    // Extraer grados, minutos y segundos
                    int G_grados = (int) G_sexagesimal[0];
                    int G_minutos = (int) G_sexagesimal[1];
                    double G_segundos = G_sexagesimal[2];

                    int C_grados = (int) C_sexagesimal[0];
                    int C_minutos = (int) C_sexagesimal[1];
                    double C_segundos = C_sexagesimal[2];

                    // Convertir los valores de sexagesimal a decimal para trabajar en el formato
                    // esperado
                    G = sexagesimalesADecimales(G_grados, G_minutos, G_segundos);
                    C = sexagesimalesADecimales(C_grados, C_minutos, C_segundos);
                } else {
                    // Si es métrico, los valores de L ya están en metros, no hacer nada.
                }

                double resultado = calcularAltura(L, G, C);
                DecimalFormat df = new DecimalFormat("0.000");

                // Si el sistema es imperial, convertir el resultado a pies
                if ("Imperial".equals(selectedSystem)) {
                    resultado = metrosAPies(resultado); // Convertir el resultado de metros a pies
                    alturaMedida.setText(getString(R.string.altura_medida_result_imperial, df.format(resultado)));
                } else {
                    alturaMedida.setText(getString(R.string.altura_medida_result, df.format(resultado)));
                }

            } else {
                alturaMedida.setText(getString(R.string.altura_medida_placeholder));
            }
        });
    }

    public double[] decimalASexagesimal(double decimal) {
        int grados = (int) decimal;
        double resto = decimal - grados;
        int minutos = (int) (resto * 60);
        double segundos = (resto * 60 - minutos) * 60;
        return new double[] { grados, minutos, segundos };
    }

    // Función para convertir grados sexagesimales a grados decimales
    public double sexagesimalesADecimales(int grados, int minutos, double segundos) {
        return grados + (minutos / 60.0) + (segundos / 3600.0);
    }

    // Función para convertir metros a pies
    public double metrosAPies(double metros) {
        return metros * 3.28084;
    }

    private boolean validarCampos(EditText longVano, EditText angSup, EditText angInf, TextView msgError) {
        boolean esValido = true;

        if (longVano.getText().toString().trim().isEmpty()) {
            esValido = false;
            longVano.setError(getString(R.string.error_campo_obligatorio));
        }

        if (angSup.getText().toString().trim().isEmpty()) {
            esValido = false;
            angSup.setError(getString(R.string.error_campo_obligatorio));
        }

        if (angInf.getText().toString().trim().isEmpty()) {
            esValido = false;
            angInf.setError(getString(R.string.error_campo_obligatorio));
        }

        if (!esValido) {
            msgError.setVisibility(View.VISIBLE);
        } else {
            msgError.setVisibility(View.INVISIBLE);
        }

        return esValido;
    }

    private double calcularAltura(double L, double G, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double tang;

        if (C < 100.0 && G < 100.0) {
            tang = OM.calculotang1(G, C);
        } else if (C > 100.0 && G > 100.0) {
            tang = OM.calculotang2(G, C);
        } else {
            tang = OM.calculotang3(G, C);
        }

        return L * tang;
    }
}
