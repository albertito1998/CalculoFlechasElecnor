package firebase.app.calculoflechaselecnor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class CalcularAltura extends Activity {
    public static final String Accion = "Accion";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcularaltura);
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularAltura.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CalcularAltura.this.finish();
            }
        });
        ImageButton botonayuda = (ImageButton) findViewById(R.id.cmdAyuda);
        botonayuda.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularAltura.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Accion", 5);
                Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
                myIntent.putExtras(bundle);
                CalcularAltura.this.startActivityForResult(myIntent, 0);
            }
        });
        Button botonvaciar = (Button) findViewById(R.id.cmdVaciar);
        botonvaciar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularAltura.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText longvan = (EditText) CalcularAltura.this.findViewById(R.id.txtLongVano);
                EditText angsup = (EditText) CalcularAltura.this.findViewById(R.id.txtAngSup);
                EditText anginf = (EditText) CalcularAltura.this.findViewById(R.id.txtAngInf);
                TextView alturamedida = (TextView) CalcularAltura.this.findViewById(R.id.txtAlturaMedida);
                longvan.setText("");
                angsup.setText("");
                anginf.setText("");
                alturamedida.setText("Altura Medida:");
            }
        });
        Button comprobar = (Button) findViewById(R.id.cmdComprobar);
        comprobar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularAltura.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                double resultado;
                EditText longvan = (EditText) CalcularAltura.this.findViewById(R.id.txtLongVano);
                EditText angsup = (EditText) CalcularAltura.this.findViewById(R.id.txtAngSup);
                EditText anginf = (EditText) CalcularAltura.this.findViewById(R.id.txtAngInf);
                TextView msgerror = (TextView) CalcularAltura.this.findViewById(R.id.txtMensajeError);
                msgerror.setTextColor(-65536);
                int falta = 0;
                TextView error1 = (TextView) CalcularAltura.this.findViewById(R.id.txtError1);
                TextView error2 = (TextView) CalcularAltura.this.findViewById(R.id.txtError2);
                TextView error3 = (TextView) CalcularAltura.this.findViewById(R.id.txtError3);
                error1.setTextColor(-65536);
                error2.setTextColor(-65536);
                error3.setTextColor(-65536);
                if (longvan.getText().toString().trim().equals("")) {
                    error1.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error1.setVisibility(View.INVISIBLE);
                    msgerror.setVisibility(View.INVISIBLE);
                }
                if (angsup.getText().toString().trim().equals("")) {
                    error2.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error2.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (anginf.getText().toString().trim().equals("")) {
                    error3.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error3.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                TextView alturamedida = (TextView) CalcularAltura.this.findViewById(R.id.txtAlturaMedida);
                if (falta == 0) {
                    double L = Double.parseDouble(longvan.getText().toString());
                    double G = Double.parseDouble(angsup.getText().toString());
                    double C = Double.parseDouble(anginf.getText().toString());
                    if (C < 100.0d && G < 100.0d) {
                        resultado = CalcularAltura1(L, G, C);
                    } else if (C > 100.0d && G > 100.0d) {
                        resultado = CalcularAltura2(L, G, C);
                    } else {
                        resultado = CalcularAltura3(L, G, C);
                    }
                    DecimalFormat df = new DecimalFormat("0.000");
                    alturamedida.setText("Altura Medida:  " + df.format(resultado) + " metros");
                    return;
                }
                alturamedida.setText("Altura Medida: --");
            }

            private double CalcularAltura1(double L, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang1(G, C);
                double res = L * tang;
                return res;
            }

            private double CalcularAltura2(double L, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang2(G, C);
                double res = L * tang;
                return res;
            }

            private double CalcularAltura3(double L, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang3(G, C);
                double res = L * tang;
                return res;
            }
        });
    }
}
