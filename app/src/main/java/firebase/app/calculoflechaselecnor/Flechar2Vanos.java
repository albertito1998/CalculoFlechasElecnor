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
public class Flechar2Vanos extends Activity {
    public static final String Accion = "Accion";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flechar2vanos);
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar2Vanos.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Flechar2Vanos.this.finish();
            }
        });
        ImageButton botonayuda = (ImageButton) findViewById(R.id.cmdAyuda);
        botonayuda.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar2Vanos.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Accion", 4);
                Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
                myIntent.putExtras(bundle);
                Flechar2Vanos.this.startActivityForResult(myIntent, 0);
            }
        });
        Button botonvaciar = (Button) findViewById(R.id.cmdVaciar);
        botonvaciar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar2Vanos.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText anggrapa1 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtanggrapa1);
                EditText anggrapa2 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtanggrapa2);
                EditText longvan1 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtlonvan1);
                EditText longvan2 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtlonvan2);
                EditText flechteovan2 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtflechteorvan2);
                TextView anguloflechar = (TextView) Flechar2Vanos.this.findViewById(R.id.txtanguloflechar);
                anggrapa1.setText("");
                anggrapa2.setText("");
                longvan1.setText("");
                longvan2.setText("");
                flechteovan2.setText("");
                anguloflechar.setText("Ángulo para flechar vano 2:");
            }
        });
        Button comprobar = (Button) findViewById(R.id.cmdComprobar);
        comprobar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar2Vanos.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                double resultado;
                EditText anggrapa1 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtanggrapa1);
                EditText anggrapa2 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtanggrapa2);
                EditText longvan1 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtlonvan1);
                EditText longvan2 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtlonvan2);
                EditText flechteovan2 = (EditText) Flechar2Vanos.this.findViewById(R.id.txtflechteorvan2);
                TextView msgerror = (TextView) Flechar2Vanos.this.findViewById(R.id.txtMensajeError);
                msgerror.setTextColor(-65536);
                int falta = 0;
                TextView error1 = (TextView) Flechar2Vanos.this.findViewById(R.id.txtError1);
                TextView error2 = (TextView) Flechar2Vanos.this.findViewById(R.id.txtError2);
                TextView error3 = (TextView) Flechar2Vanos.this.findViewById(R.id.txtError3);
                TextView error4 = (TextView) Flechar2Vanos.this.findViewById(R.id.txtError4);
                TextView error5 = (TextView) Flechar2Vanos.this.findViewById(R.id.txtError5);
                error1.setTextColor(-65536);
                error2.setTextColor(-65536);
                error3.setTextColor(-65536);
                error4.setTextColor(-65536);
                error5.setTextColor(-65536);
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
                if (longvan1.getText().toString().trim().equals("")) {
                    error3.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error3.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                if (longvan2.getText().toString().trim().equals("")) {
                    error4.setVisibility(0);
                    msgerror.setVisibility(0);
                    falta = 1;
                } else {
                    error4.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                if (flechteovan2.getText().toString().trim().equals("")) {
                    error5.setVisibility(0);
                    msgerror.setVisibility(0);
                    falta = 1;
                } else {
                    error5.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                TextView anguloflechar = (TextView) Flechar2Vanos.this.findViewById(R.id.txtanguloflechar);
                if (falta == 0) {
                    double A = Double.parseDouble(anggrapa1.getText().toString());
                    double B = Double.parseDouble(anggrapa2.getText().toString());
                    double C = Double.parseDouble(longvan1.getText().toString());
                    double J = Double.parseDouble(longvan2.getText().toString());
                    double F = Double.parseDouble(flechteovan2.getText().toString());
                    double D = J + C;
                    double E = Flechar2Vanos.this.CalcularE(F, D, C, B, A);
                    double X = Flechar2Vanos.this.CalcularX(F, E, D, C);
                    double P = Flechar2Vanos.this.CalcularP(A, X, C);
                    if (P > 0.0d) {
                        resultado = P;
                    } else {
                        resultado = 200.0d + P;
                    }
                    DecimalFormat df = new DecimalFormat("0.000");
                    anguloflechar.setText("Ángulo para flechar vano 2:  " + df.format(resultado) + " º");
                    return;
                }
                anguloflechar.setText("Ángulo para flechar vano 2: --");
            }
        });
    }

    public double CalcularE(double F, double D, double C, double B, double A) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Benrad = OM.aradianes(B);
        double Aenrad = OM.aradianes(A);
        double OPTan = (1.0d / Math.tan(Benrad)) - (1.0d / Math.tan(Aenrad));
        double primfactor = 4.0d * ((D / C) - 1.0d);
        double segfactor = (D * OPTan) - (4.0d * F);
        double res = Math.sqrt((16.0d * F) - (primfactor * segfactor));
        return res;
    }

    public double CalcularX(double F, double E, double D, double C) {
        double res = (((-4.0d) * Math.sqrt(F)) + E) / (2.0d * ((D / C) - 1.0d));
        return res;
    }

    public double CalcularP(double A, double X, double C) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Aenrad = OM.aradianes(A);
        double RES1 = Math.pow((1.0d / Math.tan(Aenrad)) - (Math.pow(X, 2.0d) / C), -1.0d);
        double res = Math.atan(RES1);
        double RESengrad = OM.agrados(res);
        return RESengrad;
    }
}
