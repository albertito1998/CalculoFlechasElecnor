package firebase.app.calculoflechaselecnor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms);

        // Referencia al botón de aceptar
        Button acceptButton = findViewById(R.id.acceptButton);

        // Configuración del evento de clic en el botón de aceptar
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de login
                Intent intent = new Intent(TermsActivity.this, Login.class);
                startActivity(intent);
                finish(); // Opcional: cerrar la actividad de términos para no volver atrás
            }
        });
    }
}
