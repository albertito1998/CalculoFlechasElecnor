package firebase.app.calculoflechaselecnor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/* loaded from: classes.dex */
public class CalculoFlechas extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button comprfl1van = (Button) findViewById(R.id.cmdCalcularFlecha1Vano);
        comprfl1van.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ControladorComprobarFlecha1Vano.class);
                CalculoFlechas.this.startActivityForResult(myIntent, 0);
            }
        });
        Button comprfl2van = (Button) findViewById(R.id.cmdComprobarFlecha2vanos);
        comprfl2van.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ControladorComprobarFlechas2Vanos.class);
                CalculoFlechas.this.startActivityForResult(myIntent, 0);
            }
        });
        Button flechar1vano = (Button) findViewById(R.id.cmdFlechar1Vano);
        flechar1vano.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Flechar1Vano.class);
                CalculoFlechas.this.startActivityForResult(myIntent, 0);
            }
        });
        Button flechar2vanos = (Button) findViewById(R.id.cmdFlechar2Vanos);
        flechar2vanos.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Flechar2Vanos.class);
                CalculoFlechas.this.startActivityForResult(myIntent, 0);
            }
        });
        Button calcularaltura = (Button) findViewById(R.id.cmdMedirAltura);
        calcularaltura.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CalcularAltura.class);
                CalculoFlechas.this.startActivityForResult(myIntent, 0);
            }
        });
        Button calcularlongitud = (Button) findViewById(R.id.cmdMedirLongitud);
        calcularlongitud.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CalcularLongitud.class);
                CalculoFlechas.this.startActivityForResult(myIntent, 0);
            }
        });
        Button salir = (Button) findViewById(R.id.cmdSalir);
        salir.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalculoFlechas.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CalculoFlechas.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
