package firebase.app.calculoflechaselecnor;

// Importaciones necesarias para trabajar con Android y sus componentes.
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// Importación de la clase base AppCompatActivity, que permite usar características modernas de Android.
import androidx.appcompat.app.AppCompatActivity;

// Clase principal de la pantalla de inicio de sesión.
public class Login extends AppCompatActivity {

    // Variables de los elementos de la interfaz de usuario.
    private EditText emailEditText; // Campo para ingresar el correo electrónico.
    private TextView emailErrorTextView; // Mensaje de error para el correo electrónico.
    private ProgressBar progressBar; // Indicador de progreso durante la autenticación.
    private Button loginButton; // Botón para iniciar sesión.

    @SuppressLint("SetTextI18n") // Anotación para permitir establecer texto directamente, aunque generalmente no se recomienda.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verificar si el usuario ya ha iniciado sesión previamente utilizando SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            // Si el usuario ya ha iniciado sesión, navega directamente a la actividad principal (CalculoFlechas).
            navigateToCalculoFlechas();
            return;
        }


    }

    // Metodo para inicializar los elementos de la interfaz y configurar sus listeners.
    private void initializeUI() {
        // Asociar las variables a los elementos de la interfaz mediante su ID.
        emailEditText = findViewById(R.id.emailEditText);
        emailErrorTextView = findViewById(R.id.emailErrorTextView);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        // Listener del botón de inicio de sesión.
        loginButton.setOnClickListener(view -> {
            // Ocultar el mensaje de error y mostrar la barra de progreso.
            emailErrorTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setEnabled(false); // Desactivar el botón para evitar múltiples clics.

            // Obtener el correo electrónico ingresado por el usuario.
            String email = emailEditText.getText().toString().trim();
            if (!isValidEmail(email)) {
                // Si el correo no es válido, muestra un error.
                showEmailError();
            } else {
                // Autentica al usuario si el correo es válido.
                authenticateUser(email);
            }
        });

        // Listener para navegar a la actividad de términos y condiciones.
        findViewById(R.id.termsTextView).setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, TermsActivity.class);
            startActivity(intent);
        });
    }

    // Muestra un mensaje de error si el correo no es válido.
    private void showEmailError() {
        emailErrorTextView.setVisibility(View.VISIBLE);
        emailErrorTextView.setText(getString(R.string.error_invalid_email));
        progressBar.setVisibility(View.GONE); // Ocultar la barra de progreso.
        loginButton.setEnabled(true); // Rehabilitar el botón.
    }

    // Establece la versión de la aplicación en un TextView.
    private void setAppVersion() {
        String versionName = "";
        try {
            // Obtiene el nombre de la versión de la aplicación del PackageManager.
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Login", "Error fetching version name", e);
        }
        TextView tvVersion = findViewById(R.id.tvVersion);
        tvVersion.setText("Versión " + versionName); // Muestra la versión en el TextView.
    }

    // Valida si el correo electrónico ingresado es válido.
    private boolean isValidEmail(String email) {
        // Dominio esperado cifrado en Base64.
        String encryptedDomain = "QGVsZWNub3I="; // Corresponde a "@elecnor"
        String domain = new String(Base64.decode(encryptedDomain, Base64.NO_WRAP)); // Decodifica el dominio.
        // Verifica que el correo contenga el dominio y tenga un formato válido.
        return email.contains(domain) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Simula la autenticación del usuario.
    private void authenticateUser(String email) {
        boolean authenticationSuccess = true; // Simulación de autenticación exitosa.

        if (authenticationSuccess) {
            // Guarda el estado de inicio de sesión en SharedPreferences.
            SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();

            // Navega a la actividad principal si la autenticación es exitosa.
            navigateToCalculoFlechas();
        } else {
            // Muestra un mensaje si la autenticación falla.
            Toast.makeText(this, getString(R.string.error_authentication_failed), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE); // Oculta la barra de progreso.
            loginButton.setEnabled(true); // Rehabilita el botón.
        }
    }

    // Navega a la actividad principal `CalculoFlechas`.
    private void navigateToCalculoFlechas() {
        Intent intent = new Intent(Login.this, CalculoFlechas.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual para evitar regresar a esta pantalla.
    }
}
