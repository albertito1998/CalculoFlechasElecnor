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
public class ControladorComprobarFlecha1Vano extends Activity {
    public static final String Accion = "Accion";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprobarflecha1vano);
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlecha1Vano.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ControladorComprobarFlecha1Vano.this.finish();
            }
        });
        ImageButton botonayuda = (ImageButton) findViewById(R.id.cmdAyuda);
        botonayuda.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlecha1Vano.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Accion", 1);
                Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
                myIntent.putExtras(bundle);
                ControladorComprobarFlecha1Vano.this.startActivityForResult(myIntent, 0);
            }
        });
        Button botonvaciar = (Button) findViewById(R.id.cmdVaciar);
        botonvaciar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlecha1Vano.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText dist = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtDistancia);
                EditText anggrapa = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtAnguloGrapa);
                EditText angcable = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtAnguloCable);
                EditText longvan = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtLongitudVano);
                EditText flechteor = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtFlechaTeorica);
                TextView flechareal = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtFlechaReal);
                TextView cablebajo = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtCableBajo);
                dist.setText("");
                anggrapa.setText("");
                angcable.setText("");
                longvan.setText("");
                flechteor.setText("");
                flechareal.setText("Flecha real:");
                cablebajo.setText("Cable bajo:");
            }
        });
        Button comprobar = (Button) findViewById(R.id.cmdComprobar);
        comprobar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlecha1Vano.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                double resultado;
                double rescable;
                EditText dist = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtDistancia);
                EditText anggrapa = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtAnguloGrapa);
                EditText angcable = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtAnguloCable);
                EditText longvan = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtLongitudVano);
                EditText flechteor = (EditText) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtFlechaTeorica);
                TextView msgerror = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtMensajeError);
                msgerror.setTextColor(-65536);
                int falta = 0;
                TextView error1 = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtErr1);
                TextView error2 = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtErr2);
                TextView error3 = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtErr3);
                TextView error4 = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtErr4);
                TextView error5 = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtErr5);
                error1.setTextColor(-65536);
                error2.setTextColor(-65536);
                error3.setTextColor(-65536);
                error4.setTextColor(-65536);
                error5.setTextColor(-65536);
                if (dist.getText().toString().trim().equals("")) {
                    error1.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error1.setVisibility(View.INVISIBLE);
                    msgerror.setVisibility(View.INVISIBLE);
                }
                if (anggrapa.getText().toString().trim().equals("")) {
                    error2.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error2.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (angcable.getText().toString().trim().equals("")) {
                    error3.setVisibility(0);
                    msgerror.setVisibility(0);
                    falta = 1;
                } else {
                    error3.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                if (longvan.getText().toString().trim().equals("")) {
                    error4.setVisibility(0);
                    msgerror.setVisibility(0);
                    falta = 1;
                } else {
                    error4.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                if (flechteor.getText().toString().trim().equals("")) {
                    error5.setVisibility(0);
                    msgerror.setVisibility(0);
                    falta = 1;
                } else {
                    error5.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                TextView flechareal = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.txtFlechaReal);
                TextView cablebajo = (TextView) ControladorComprobarFlecha1Vano.this.findViewById(R.id.TxtCableBajo);
                if (falta == 0) {
                    double G = Double.parseDouble(anggrapa.getText().toString());
                    double C = Double.parseDouble(angcable.getText().toString());
                    double L = Double.parseDouble(longvan.getText().toString());
                    double H = Double.parseDouble(dist.getText().toString());
                    double F = Double.parseDouble(flechteor.getText().toString());
                    if (C >= 100.0d || G >= 100.0d) {
                        if (C > 100.0d && G > 100.0d) {
                            resultado = ControladorComprobarFlecha1Vano.this.flechareal2(G, C, L, H);
                            rescable = ControladorComprobarFlecha1Vano.this.calculocable(resultado, F);
                        } else {
                            resultado = ControladorComprobarFlecha1Vano.this.flechareal3(G, C, L, H);
                            rescable = ControladorComprobarFlecha1Vano.this.calculocable(resultado, F);
                        }
                    } else {
                        resultado = ControladorComprobarFlecha1Vano.this.flechareal1(G, C, L, H);
                        rescable = ControladorComprobarFlecha1Vano.this.calculocable(resultado, F);
                    }
                    DecimalFormat df = new DecimalFormat("0.000");
                    flechareal.setText("Flecha real:  " + df.format(resultado) + " metros");
                    if (rescable < 0.0d) {
                        cablebajo.setText("Cable alto:  " + df.format(Math.abs(rescable)) + " metros");
                        return;
                    } else {
                        cablebajo.setText("Cable bajo:  " + df.format(Math.abs(rescable)) + " metros");
                        return;
                    }
                }
                flechareal.setText("Flecha real: --");
                cablebajo.setText("Cable bajo: --");
            }
        });
    }

    public double flechareal1(double G, double C, double L, double H) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang1(G, C);
        double calcraiz = OM.calculoraiz(calctg, L, H);
        double res = Math.pow(calcraiz / 2.0d, 2.0d);
        return res;
    }

    public double flechareal2(double G, double C, double L, double H) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang2(G, C);
        double calcraiz = OM.calculoraiz(calctg, L, H);
        double res = Math.pow(calcraiz / 2.0d, 2.0d);
        return res;
    }

    public double flechareal3(double G, double C, double L, double H) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang3(G, C);
        double calcraiz = OM.calculoraiz(calctg, L, H);
        double res = Math.pow(calcraiz / 2.0d, 2.0d);
        return res;
    }

    public double calculocable(double res, double F) {
        double resu = res - F;
        return resu;
    }
}
