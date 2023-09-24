package firebase.app.calculoflechaselecnor;

/* loaded from: classes.dex */
public class OperacionesMatematicas {
    public double calculotang1(double G, double C) {
        double Genrad = aradianes(G);
        double Cenrad = aradianes(C);
        double tg1 = Math.tan(Genrad);
        double val1 = 1.0d / tg1;
        double tg2 = Math.tan(Cenrad);
        double val2 = 1.0d / tg2;
        double res = val1 - val2;
        return res;
    }

    public double calculotang2(double G, double C) {
        double Genrad = aradianes(G);
        double Cenrad = aradianes(C);
        double val1 = Math.tan(Cenrad - 100.0d);
        double val2 = Math.tan(Genrad - 100.0d);
        double res = val1 - val2;
        return res;
    }

    public double calculotang3(double G, double C) {
        double Genrad = aradianes(G);
        double Cenrad = aradianes(C);
        double val1 = 1.0d / Math.tan(Genrad);
        double val2 = Math.tan(Cenrad - 100.0d);
        double res = val1 + val2;
        return res;
    }

    public double calculoraiz(double tg, double L, double H) {
        double op1 = Math.sqrt(L * tg);
        double op2 = Math.sqrt(H);
        double res = op1 + op2;
        return res;
    }

    public double aradianes(double val) {
        double res = (3.141592d * val) / 200.0d;
        return res;
    }

    public double agrados(double val) {
        double res = (200.0d * val) / 3.1415952d;
        return res;
    }
}
