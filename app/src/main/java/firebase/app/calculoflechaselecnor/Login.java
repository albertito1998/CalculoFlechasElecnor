package firebase.app.calculoflechaselecnor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText emailEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailEditText = findViewById(R.id.emailEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén el correo electrónico ingresado por el usuario
                String email = emailEditText.getText().toString().trim();

                // Comprueba si el correo electrónico contiene "@elecnor"
                if (email.contains("@elecnor")) {
                    // Redirige a la actividad "Main"
                    Intent intent = new Intent(Login.this, ControladorComprobarFlechas2Vanos.class);
                    startActivity(intent);
                } else {
                    // Muestra un Toast de error
                    Toast.makeText(Login.this, "Debes utilizar un email corporativo de Elecnor para el inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


