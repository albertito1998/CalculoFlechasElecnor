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
 * Actividad para calcular la longitud basada en la altura y ángulos proporcionados.
 */
public class CalcularLongitud extends Activity {

    // Constante para la acción de la Intent
    public static final String ACCION = "Accion";



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcularlongitud);

        // Configuración del botón "Cancelar" para cerrar la actividad
        Button botonCancelar = findViewById(R.id.cmdVolver);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cierra la actividad actual
            }
        });

        // Referencia al TextView
        TextView tvSelectedUnits = findViewById(R.id.tvSelectedUnits);

        // Obtener el valor de la variable global (selected system) y mostrarlo en el TextView
        String selectedSystem;


        selectedSystem = GlobalData.getSelectedSystem();

// Obtener referencias a los TextView
        TextView unidadesAngulo = findViewById(R.id.unidadesAngulo);      // Para el ángulo
        TextView unidadesAngulo1 = findViewById(R.id.unidadesAngulo1);      // Para el ángulo
        TextView unidadesDistancias = findViewById(R.id.unidadesDistancias); // Para las distancias



        // Actualizar el texto del TextView
        // Actualizar el texto del TextView
        tvSelectedUnits.setText("Selected units: " + selectedSystem);

        // Verificar si el sistema es imperial o métrico y ajustar el texto del TextView para los ángulos
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


        // Configuración del botón "Ayuda" para abrir la actividad de ayuda
        ImageButton botonAyuda = findViewById(R.id.cmdAyuda);
        botonAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad de ayuda
                Intent ayudaIntent = new Intent(view.getContext(), AbrirAyuda.class);
                ayudaIntent.putExtra(ACCION, 6);
                startActivityForResult(ayudaIntent, 0);
            }
        });

        // Configuración del botón "Vaciar" para limpiar los campos
        Button botonVaciar = findViewById(R.id.cmdVaciar);
        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // Limpiar los campos de entrada y la salida
                EditText altura = findViewById(R.id.txtAltura);
                EditText angSup = findViewById(R.id.txtAnguloSup);
                EditText angInf = findViewById(R.id.txtAnguloInf);
                TextView longitudMedida = findViewById(R.id.txtLongitudMedida);
                altura.setText("");
                angSup.setText("");
                angInf.setText("");
                longitudMedida.setText("Longitud Medida:");
            }
        });

        // Configuración del botón "Comprobar" para calcular la longitud
        Button botonComprobar = findViewById(R.id.cmdComprobar);
        botonComprobar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // Obtener las entradas del usuario
                EditText altura = findViewById(R.id.txtAltura);
                EditText angSup = findViewById(R.id.txtAnguloSup);
                EditText angInf = findViewById(R.id.txtAnguloInf);
                TextView msgError = findViewById(R.id.txtMensajeError);
                TextView error1 = findViewById(R.id.txtError1);
                TextView error2 = findViewById(R.id.txtError2);
                TextView error3 = findViewById(R.id.txtError3);
                TextView longitudMedida = findViewById(R.id.txtLongitudMedida);



                // Inicializar variables de error
                boolean hasError = false;
                msgError.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                error1.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                error2.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                error3.setTextColor(getResources().getColor(android.R.color.holo_red_dark));

                // Validar campos de entrada
                if (altura.getText().toString().trim().isEmpty()) {
                    error1.setVisibility(View.VISIBLE);
                    hasError = true;
                } else {
                    error1.setVisibility(View.INVISIBLE);
                }

                if (angSup.getText().toString().trim().isEmpty()) {
                    error2.setVisibility(View.VISIBLE);
                    hasError = true;
                } else {
                    error2.setVisibility(View.INVISIBLE);
                }

                if (angInf.getText().toString().trim().isEmpty()) {
                    error3.setVisibility(View.VISIBLE);
                    hasError = true;
                } else {
                    error3.setVisibility(View.INVISIBLE);
                }

                // Si no hay errores, calcular la longitud
                if (!hasError) {
                    double M = Double.parseDouble(altura.getText().toString());
                    double G = Double.parseDouble(angSup.getText().toString());
                    double C = Double.parseDouble(angInf.getText().toString());
                    double resultado;

                    // Determinar la fórmula adecuada para el cálculo
                    if (C < 100.0 && G < 100.0) {
                        resultado = calcularLongitud1(M, G, C);
                    } else if (C > 100.0 && G > 100.0) {
                        resultado = calcularLongitud2(M, G, C);
                    } else {
                        resultado = calcularLongitud3(M, G, C);
                    }

                    // Mostrar el resultado formateado
                    DecimalFormat df = new DecimalFormat("0.000");
                    longitudMedida.setText("Longitud Medida: " + df.format(resultado) + " metros");
                } else {
                    longitudMedida.setText("Longitud Medida: --");
                }
            }

            /**
             * Calcula la longitud usando la fórmula 1.
             */
            private double calcularLongitud1(double M, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang1(G, C);
                return M / tang;
            }

            /**
             * Calcula la longitud usando la fórmula 2.
             */
            private double calcularLongitud2(double M, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang2(G, C);
                return M / tang;
            }

            /**
             * Calcula la longitud usando la fórmula 3.
             */
            private double calcularLongitud3(double M, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang3(G, C);
                return M / tang;
            }
        });
    }
}
