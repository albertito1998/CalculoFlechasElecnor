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
public class Flechar1Vano extends Activity {
    public static final String Accion = "Accion";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flechar1vano);
        Button botoncancelar = (Button) findViewById(R.id.cmdVolver);
        botoncancelar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar1Vano.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Flechar1Vano.this.finish();
            }
        });
        ImageButton botonayuda = (ImageButton) findViewById(R.id.cmdAyuda);
        botonayuda.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar1Vano.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Accion", 3);
                Intent myIntent = new Intent(view.getContext(), AbrirAyuda.class);
                myIntent.putExtras(bundle);
                Flechar1Vano.this.startActivityForResult(myIntent, 0);
            }
        });
        Button botonvaciar = (Button) findViewById(R.id.cmdVaciar);
        botonvaciar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar1Vano.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText dist = (EditText) Flechar1Vano.this.findViewById(R.id.txtDistancia);
                EditText anggrapa = (EditText) Flechar1Vano.this.findViewById(R.id.txtanggrapa);
                EditText longvan = (EditText) Flechar1Vano.this.findViewById(R.id.txtlongvano);
                EditText flechteor = (EditText) Flechar1Vano.this.findViewById(R.id.txtflechteor);
                TextView anguloflechar = (TextView) Flechar1Vano.this.findViewById(R.id.txtanguloflechar);
                dist.setText("");
                anggrapa.setText("");
                longvan.setText("");
                flechteor.setText("");
                anguloflechar.setText("Ángulo para flechar:");
            }
        });
        Button comprobar = (Button) findViewById(R.id.cmdComprobar);
        comprobar.setOnClickListener(new View.OnClickListener() { // from class: com.example.calcularflechas.Flechar1Vano.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText dist = (EditText) Flechar1Vano.this.findViewById(R.id.txtDistancia);
                EditText anggrapa = (EditText) Flechar1Vano.this.findViewById(R.id.txtanggrapa);
                EditText longvan = (EditText) Flechar1Vano.this.findViewById(R.id.txtlongvano);
                EditText flechteor = (EditText) Flechar1Vano.this.findViewById(R.id.txtflechteor);
                TextView msgerror = (TextView) Flechar1Vano.this.findViewById(R.id.txtMensajeError);
                msgerror.setTextColor(-65536);
                int falta = 0;
                TextView error1 = (TextView) Flechar1Vano.this.findViewById(R.id.txterror1);
                TextView error2 = (TextView) Flechar1Vano.this.findViewById(R.id.txterror2);
                TextView error3 = (TextView) Flechar1Vano.this.findViewById(R.id.txterror3);
                TextView error4 = (TextView) Flechar1Vano.this.findViewById(R.id.txterror4);
                error1.setTextColor(-65536);
                error2.setTextColor(-65536);
                error3.setTextColor(-65536);
                error4.setTextColor(-65536);
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
                if (longvan.getText().toString().trim().equals("")) {
                    error3.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error3.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(View.VISIBLE);
                    }
                }
                if (flechteor.getText().toString().trim().equals("")) {
                    error4.setVisibility(View.VISIBLE);
                    msgerror.setVisibility(View.VISIBLE);
                    falta = 1;
                } else {
                    error4.setVisibility(View.INVISIBLE);
                    if (falta == 1) {
                        msgerror.setVisibility(0);
                    }
                }
                TextView anguloflechar = (TextView) Flechar1Vano.this.findViewById(R.id.txtanguloflechar);
                if (falta == 0) {
                    double H = Double.parseDouble(dist.getText().toString());
                    double G = Double.parseDouble(anggrapa.getText().toString());
                    double V = Double.parseDouble(longvan.getText().toString());
                    double F = Double.parseDouble(flechteor.getText().toString());
                    double S = Flechar1Vano.this.CalculoS(F, H, V);
                    double arctan = Flechar1Vano.this.CalculoArcTan(G, S);
                    double resultado = arctan;
                    if (arctan < 0.0d) {
                        resultado = Flechar1Vano.this.CalculoC(S, G);
                    }
                    DecimalFormat df = new DecimalFormat("0.000");
                    anguloflechar.setText("Ángulo para flechar:  " + df.format(resultado) + " º");
                    return;
                }
                anguloflechar.setText("Ángulo para flechar: --");
            }
        });
    }

    public double CalculoS(double F, double H, double V) {
        double res = Math.pow((Math.sqrt(F) * 2.0d) - Math.sqrt(H), 2.0d) / V;
        return res;
    }

    public double CalculoArcTan(double G, double S) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Genrad = OM.aradianes(G);
        double op1 = 1.0d / ((1.0d / Math.tan(Genrad)) - S);
        double res1 = Math.atan(op1);
        double res = OM.agrados(res1);
        return res;
    }

    public double CalculoC(double S, double G) {
        OperacionesMatematicas OM = new OperacionesMatematicas();
        double Genrad = OM.aradianes(G - 100.0d);
        double op1 = S + Math.tan(Genrad);
        double res1 = Math.atan(op1);
        double res = 100.0d + OM.agrados(res1);
        return res;
    }
}
