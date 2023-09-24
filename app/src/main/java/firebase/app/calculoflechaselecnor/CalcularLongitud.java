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
public class CalcularLongitud extends Activity {
    public static final String Accion = "Accion";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcularlongitud);
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularLongitud.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CalcularLongitud.this.finish();
            }
        });
        ImageButton botonayuda = (ImageButton) findViewById(R.id.cmdAyuda);
        botonayuda.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularLongitud.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Accion", 6);
                Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
                myIntent.putExtras(bundle);
                CalcularLongitud.this.startActivityForResult(myIntent, 0);
            }
        });
        Button botonvaciar = (Button) findViewById(R.id.cmdVaciar);
        botonvaciar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularLongitud.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText altura = (EditText) CalcularLongitud.this.findViewById(R.id.txtAltura);
                EditText angsup = (EditText) CalcularLongitud.this.findViewById(R.id.txtAnguloSup);
                EditText anginf = (EditText) CalcularLongitud.this.findViewById(R.id.txtAnguloInf);
                TextView longitudmedida = (TextView) CalcularLongitud.this.findViewById(R.id.txtLongitudMedida);
                altura.setText("");
                angsup.setText("");
                anginf.setText("");
                longitudmedida.setText("Longitud Medida:");
            }
        });
        Button comprobar = (Button) findViewById(R.id.cmdComprobar);
        comprobar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.CalcularLongitud.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                double resultado;
                EditText altura = (EditText) CalcularLongitud.this.findViewById(R.id.txtAltura);
                EditText angsup = (EditText) CalcularLongitud.this.findViewById(R.id.txtAnguloSup);
                EditText anginf = (EditText) CalcularLongitud.this.findViewById(R.id.txtAnguloInf);
                TextView msgerror = (TextView) CalcularLongitud.this.findViewById(R.id.txtMensajeError);
                msgerror.setTextColor(-65536);
                int falta = 0;
                TextView error1 = (TextView) CalcularLongitud.this.findViewById(R.id.txtError1);
                TextView error2 = (TextView) CalcularLongitud.this.findViewById(R.id.txtError2);
                TextView error3 = (TextView) CalcularLongitud.this.findViewById(R.id.txtError3);
                error1.setTextColor(-65536);
                error2.setTextColor(-65536);
                error3.setTextColor(-65536);
                if (altura.getText().toString().trim().equals("")) {
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
                        msgerror.setVisibility(0);
                    }
                }
                TextView longitudmedida = (TextView) CalcularLongitud.this.findViewById(R.id.txtLongitudMedida);
                if (falta == 0) {
                    double M = Double.parseDouble(altura.getText().toString());
                    double G = Double.parseDouble(angsup.getText().toString());
                    double C = Double.parseDouble(anginf.getText().toString());
                    if (C < 100.0d && G < 100.0d) {
                        resultado = CalcularLongitud1(M, G, C);
                    } else if (C > 100.0d && G > 100.0d) {
                        resultado = CalcularLongitud2(M, G, C);
                    } else {
                        resultado = CalcularLongitud3(M, G, C);
                    }
                    DecimalFormat df = new DecimalFormat("0.000");
                    longitudmedida.setText("Longitud Medida:  " + df.format(resultado) + " metros");
                    return;
                }
                longitudmedida.setText("Longitud Medida: --");
            }

            private double CalcularLongitud1(double M, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang1(G, C);
                double res = M / tang;
                return res;
            }

            private double CalcularLongitud2(double M, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang2(G, C);
                double res = M / tang;
                return res;
            }

            private double CalcularLongitud3(double M, double G, double C) {
                OperacionesMatematicas OM = new OperacionesMatematicas();
                double tang = OM.calculotang3(G, C);
                double res = M / tang;
                return res;
            }
        });
    }
}
