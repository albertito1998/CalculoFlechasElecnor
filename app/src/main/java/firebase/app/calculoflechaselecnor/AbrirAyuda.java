package firebase.app.calculoflechaselecnor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class AbrirAyuda extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abririmg);
        ImageView imagen = (ImageView) findViewById(R.id.imgAyuda);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int dedonde = extras.getInt("Accion");
            if (dedonde == 1) {
                imagen.setImageResource(R.drawable.comprobarflecha1vano);
            }
            if (dedonde == 2) {
                imagen.setImageResource(R.drawable.comprobarflecha2vanos);
            }
            if (dedonde == 3) {
                imagen.setImageResource(R.drawable.flechar1vano);
            }
            if (dedonde == 4) {
                imagen.setImageResource(R.drawable.flechar2vanos);
            }
            if (dedonde == 5) {
                imagen.setImageResource(R.drawable.interrogacion);
            }
            if (dedonde == 6) {
                imagen.setImageResource(R.drawable.interrogacion);
            }
        }
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.AbrirAyuda.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AbrirAyuda.this.finish();
            }
        });
    }
}
