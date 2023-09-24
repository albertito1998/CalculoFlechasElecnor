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
public class ControladorComprobarFlechas2Vanos extends Activity {
    public static final String Accion = "Accion";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprobarflecha2vanos);
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlechas2Vanos.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ControladorComprobarFlechas2Vanos.this.finish();
            }
        });
        ImageButton botonayuda = (ImageButton) findViewById(R.id.cmdAyuda);
        botonayuda.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlechas2Vanos.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Accion", 2);
                Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
                myIntent.putExtras(bundle);
                ControladorComprobarFlechas2Vanos.this.startActivityForResult(myIntent, 0);
            }
        });
        Button botonvaciar = (Button) findViewById(R.id.cmdVaciar);
        botonvaciar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlechas2Vanos.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText anggrapa1 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtanggrapa1);
                EditText anggrapa2 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtanggrapa2);
                EditText angcablevano = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtangcablevano2);
                EditText longvan1 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtlongvano1);
                EditText longvan2 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtlongvano2);
                EditText flechvano = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtFlechavano2);
                TextView flechareal = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtFlechaReal);
                TextView cablebajo = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtCableBajo);
                anggrapa1.setText("");
                anggrapa2.setText("");
                angcablevano.setText("");
                longvan1.setText("");
                longvan2.setText("");
                flechvano.setText("");
                flechareal.setText("Flecha real:");
                cablebajo.setText("Cable bajo:");
            }
        });
        Button comprobar = (Button) findViewById(R.id.cmdComprobar);
        comprobar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.ControladorComprobarFlechas2Vanos.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                double H;
                double resultado;
                double rescable;
                EditText anggrapa1 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtanggrapa1);
                EditText anggrapa2 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtanggrapa2);
                EditText angcablevano = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtangcablevano2);
                EditText longvan1 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtlongvano1);
                EditText longvan2 = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtlongvano2);
                EditText flechvano = (EditText) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtFlechavano2);
                TextView msgerror = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtMensajeError);
                msgerror.setTextColor(-65536);
                int falta = 0;
                TextView error1 = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtErr1);
                TextView error2 = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtErr2);
                TextView error3 = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtErr3);
                TextView error4 = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtErr4);
                TextView error5 = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtErr5);
                TextView error6 = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtErr6);
                error1.setTextColor(-65536);
                error2.setTextColor(-65536);
                error3.setTextColor(-65536);
                error4.setTextColor(-65536);
                error5.setTextColor(-65536);
                error6.setTextColor(-65536);
                if (anggrapa1.getText().toString().trim().equals("")) {
                    error1.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error1.setVisibility(View.INVISIBLE);
                    msgerror.setVisibility(View.INVISIBLE);
                }
                if (anggrapa2.getText().toString().trim().equals("")) {
                    error2.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error2.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (angcablevano.getText().toString().trim().equals("")) {
                    error3.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error3.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (longvan1.getText().toString().trim().equals("")) {
                    error4.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error4.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (longvan2.getText().toString().trim().equals("")) {
                    error5.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error5.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (flechvano.getText().toString().trim().equals("")) {
                    error6.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error6.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                TextView flechareal = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.txtFlechaReal);
                TextView cablebajo = (TextView) ControladorComprobarFlechas2Vanos.this.findViewById(R.id.TxtCableBajo);
                if (falta == 0) {
                    double E = Double.parseDouble(anggrapa1.getText().toString());
                    double G = Double.parseDouble(anggrapa2.getText().toString());
                    double C = Double.parseDouble(angcablevano.getText().toString());
                    double J = Double.parseDouble(longvan1.getText().toString());
                    double K = Double.parseDouble(longvan2.getText().toString());
                    double F = Double.parseDouble(flechvano.getText().toString());
                    if (E >= 100.0d || C >= 100.0d) {
                        if (E < 100.0d && C > 100.0d) {
                            H = ControladorComprobarFlechas2Vanos.this.CalculoH2(J, E, C);
                        } else {
                            H = ControladorComprobarFlechas2Vanos.this.CalculoH3(J, E, C);
                        }
                    } else {
                        H = ControladorComprobarFlechas2Vanos.this.CalculoH1(J, E, C);
                    }
                    double L = J + K;
                    if (C >= 100.0d || E >= 100.0d) {
                        if (C > 100.0d && G < 100.0d) {
                            resultado = ControladorComprobarFlechas2Vanos.this.flechareal2(G, C, L, H);
                            rescable = ControladorComprobarFlechas2Vanos.this.calculocable(resultado, F);
                        } else {
                            resultado = ControladorComprobarFlechas2Vanos.this.flechareal3(G, C, L, H);
                            rescable = ControladorComprobarFlechas2Vanos.this.calculocable(resultado, F);
                        }
                    } else {
                        resultado = ControladorComprobarFlechas2Vanos.this.flechareal1(G, C, L, H);
                        rescable = ControladorComprobarFlechas2Vanos.this.calculocable(resultado, F);
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

    public double CalculoH1(double J, double E, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang1(E, C);
        double res = J * calctg;
        return res;
    }

    public double CalculoH2(double J, double E, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang2(E, C);
        double res = J * calctg;
        return res;
    }

    public double CalculoH3(double J, double E, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double calctg = OM.calculotang3(E, C);
        double res = J * calctg;
        return res;
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
