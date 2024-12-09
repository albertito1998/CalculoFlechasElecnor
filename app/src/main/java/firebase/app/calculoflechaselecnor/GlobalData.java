package firebase.app.calculoflechaselecnor;

public class GlobalData {
    private static String selectedSystem; // Variable para almacenar la selección

    // Método getter para obtener la selección
    public static String getSelectedSystem() {
        return selectedSystem;
    }

    // Método setter para establecer la selección
    public static void setSelectedSystem(String system) {
        selectedSystem = system;
    }
}
