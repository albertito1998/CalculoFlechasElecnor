package firebase.app.calculoflechaselecnor;

/**
 * Clase que proporciona varias operaciones matemáticas comunes utilizadas en cálculos de ingeniería.
 */
public class OperacionesMatematicas {

    /**
     * Calcula la diferencia entre las tangentes inversas de dos ángulos.
     *
     * @param G Ángulo en grados centesimales.
     * @param C Ángulo en grados centesimales.
     * @return La diferencia entre las tangentes inversas de los ángulos en radianes.
     */
    public double calculotang1(double G, double C) {
        double Genrad = aradianes(G);  // Convertir ángulo G a radianes
        double Cenrad = aradianes(C);  // Convertir ángulo C a radianes
        double tg1 = Math.tan(Genrad); // Calcular la tangente de G
        double val1 = 1.0 / tg1;       // Inversa de la tangente de G
        double tg2 = Math.tan(Cenrad); // Calcular la tangente de C
        double val2 = 1.0 / tg2;       // Inversa de la tangente de C
        return val1 - val2;            // Retornar la diferencia
    }

    /**
     * Calcula la diferencia entre las tangentes de los ángulos modificados por un valor constante (100).
     *
     * @param G Ángulo en grados centesimales.
     * @param C Ángulo en grados centesimales.
     * @return La diferencia entre las tangentes de los ángulos modificados.
     */
    public double calculotang2(double G, double C) {
        double Genrad = aradianes(G);  // Convertir ángulo G a radianes
        double Cenrad = aradianes(C);  // Convertir ángulo C a radianes
        double val1 = Math.tan(Cenrad - aradianes(100.0)); // Calcular tangente de C modificado
        double val2 = Math.tan(Genrad - aradianes(100.0)); // Calcular tangente de G modificado
        return val1 - val2;            // Retornar la diferencia
    }

    /**
     * Calcula la suma de la inversa de la tangente de un ángulo y la tangente de otro ángulo modificado.
     *
     * @param G Ángulo en grados centesimales.
     * @param C Ángulo en grados centesimales.
     * @return La suma entre la inversa de la tangente de G y la tangente de C modificado.
     */
    public double calculotang3(double G, double C) {
        double Genrad = aradianes(G);  // Convertir ángulo G a radianes
        double Cenrad = aradianes(C);  // Convertir ángulo C a radianes
        double val1 = 1.0 / Math.tan(Genrad);              // Inversa de la tangente de G
        double val2 = Math.tan(Cenrad - aradianes(100.0)); // Tangente de C modificado
        return val1 + val2;            // Retornar la suma
    }

    /**
     * Calcula la suma de las raíces cuadradas de dos productos.
     *
     * @param tg Tangente de un ángulo.
     * @param L Longitud.
     * @param H Altura.
     * @return La suma de las raíces cuadradas de L*tg y H.
     */
    public double calculoraiz(double tg, double L, double H) {
        double op1 = Math.sqrt(L * tg); // Calcular la raíz cuadrada del producto L*tg
        double op2 = Math.sqrt(H);      // Calcular la raíz cuadrada de H
        return op1 + op2;               // Retornar la suma
    }

    /**
     * Convierte un valor de grados centesimales a radianes.
     *
     * @param val Valor en grados centesimales.
     * @return El valor convertido a radianes.
     */
    public double aradianes(double val) {
        return (Math.PI * val) / 200.0; // Retornar la conversión a radianes
    }

    /**
     * Convierte un valor de radianes a grados centesimales.
     *
     * @param val Valor en radianes.
     * @return El valor convertido a grados centesimales.
     */
    public double agrados(double val) {
        return (200.0 * val) / Math.PI; // Retornar la conversión a grados centesimales
    }


    // 1. Función para convertir metros a pies
    public double metrosAPies(double metros) {
        // Fórmula: pies = metros * 3.28084
        return metros * 3.28084;
    }

    // 2. Función para convertir grados sexagesimales a decimales
    public double sexagesimalesADecimales(int grados, int minutos, double segundos) {
        // Fórmula: grados decimales = grados + (minutos / 60) + (segundos / 3600)
        return grados + (minutos / 60.0) + (segundos / 3600.0);
    }


}
