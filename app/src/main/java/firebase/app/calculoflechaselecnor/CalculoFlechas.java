package firebase.app.calculoflechaselecnor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad principal para la aplicación de cálculo de flechas.
 * Contiene un menú con botones que redirigen a diferentes partes de la aplicación.
 */
public class CalculoFlechas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura los botones y sus listeners
        configurarBotones();


        // Referencias a los componentes en el layout
        RadioGroup radioGroupUnits = findViewById(R.id.radioGroupUnits);
        RadioButton rbMetric = findViewById(R.id.rbMetric);
        RadioButton rbImperial = findViewById(R.id.rbImperial);
     //   rbImperial.isEnabled = false; // Deshabilitar

        // Configurar el listener para cambios en la selección
        radioGroupUnits.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Verificar qué opción fue seleccionada y guardarla en GlobalData
                if (checkedId == R.id.rbMetric) {
                    // Si se selecciona el sistema métrico
                    GlobalData.setSelectedSystem("Métrico");
                } else if (checkedId == R.id.rbImperial) {
                    // Si se selecciona el sistema imperial
                    GlobalData.setSelectedSystem("Imperial");
                }
            }
        });

        // Inicializar con el valor predeterminado (si se quiere configurar un valor inicial)
        if (rbMetric.isChecked()) {
            GlobalData.setSelectedSystem("Métrico");
        } else if (rbImperial.isChecked()) {
            GlobalData.setSelectedSystem("Imperial");
        }
    }

    /**
     * Configura los botones en la actividad y asigna sus listeners.
     */
    private void configurarBotones() {
        // Configura el botón para comprobar flecha en un vano
        Button botonComprobarFlecha1Vano = findViewById(R.id.cmdCalcularFlecha1Vano);
        botonComprobarFlecha1Vano.setOnClickListener(view -> {
            Intent intent = new Intent(CalculoFlechas.this, ControladorComprobarFlecha1Vano.class);
            startActivity(intent);
        });

        // Configura el botón para comprobar flechas en dos vanos
        Button botonComprobarFlechas2Vanos = findViewById(R.id.cmdComprobarFlecha2vanos);
        botonComprobarFlechas2Vanos.setOnClickListener(view -> {
            Intent intent = new Intent(CalculoFlechas.this, ControladorComprobarFlechas2Vanos.class);
            startActivity(intent);
        });

        // Configura el botón para calcular flecha en un vano
        Button botonFlechar1Vano = findViewById(R.id.cmdFlechar1Vano);
        botonFlechar1Vano.setOnClickListener(view -> {
            Intent intent = new Intent(CalculoFlechas.this, Flechar1Vano.class);
            startActivity(intent);
        });

        // Configura el botón para calcular flecha en dos vanos
        Button botonFlechar2Vanos = findViewById(R.id.cmdFlechar2Vanos);
        botonFlechar2Vanos.setOnClickListener(view -> {
            Intent intent = new Intent(CalculoFlechas.this, Flechar2Vanos.class);
            startActivity(intent);
        });

        // Configura el botón para calcular la altura
        Button botonCalcularAltura = findViewById(R.id.cmdMedirAltura);
        botonCalcularAltura.setOnClickListener(view -> {
            Intent intent = new Intent(CalculoFlechas.this, CalcularAltura.class);
            startActivity(intent);
        });

        // Configura el botón para calcular la longitud
        Button botonCalcularLongitud = findViewById(R.id.cmdMedirLongitud);
        botonCalcularLongitud.setOnClickListener(view -> {
            Intent intent = new Intent(CalculoFlechas.this, CalcularLongitud.class);
            startActivity(intent);
        });

        // Configura el botón para salir de la aplicación
        Button botonSalir = findViewById(R.id.cmdSalir);
        botonSalir.setOnClickListener(view -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú desde el archivo de menú XML
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
