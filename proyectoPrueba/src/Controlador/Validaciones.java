
package Controlador;

import Modelo.Mascota;
import Modelo.Persona;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validaciones { 
    
    public void valNum(KeyEvent num, JTextField txt, int tam) {
        try {
            char c = num.getKeyChar();
            if (!Character.isDigit(c)) {
                num.consume();
            }
            if (txt.getText().length() == tam) {
                num.consume();
                Toolkit.getDefaultToolkit().beep();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Error");
        }
    }
    
    public void valLetr(KeyEvent num,JTextField txt,int tam){
        try {
            char c = num.getKeyChar();
            if (Character.isDigit(c)) {
                num.consume();
            }
            if (txt.getText().length() == tam) {  
                num.consume();
                Toolkit.getDefaultToolkit().beep();
            
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, ex + "Error");
        }
    }
    
    
    
    public void valNumReal(KeyEvent num,JTextField txt,int tam){
    
         try {
            char c = num.getKeyChar();

            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
                    && (c != '.')) {
                num.consume();
            }

            if (c == '.' && txt.getText().contains(".")) {
                num.consume();
            }
            if (txt.getText().length() == tam) { // 2 es la cantidad de caracteres permitidos
                num.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Error");
        }
    }
    
    public boolean validadorDeCedula(String cedula, JTextField txt) {
        boolean cedulaCorrecta = false;
        try {
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }
                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Una Excepción Ha Ocurrido En El Proceso de Validacion: " + err.getMessage());
            cedulaCorrecta = false;
        }
        if (!cedulaCorrecta) {
            JOptionPane.showMessageDialog(null, "La Cédula Ingresada Es Incorrecta", "Mensaje", JOptionPane.ERROR_MESSAGE);
            txt.setText("");
            txt.requestFocus();
        }
        return cedulaCorrecta;
    }
    
    public void Mayusculas(String cad, KeyEvent evt) {
        char letra = evt.getKeyChar();
        if (Character.isLowerCase(letra)) {
            cad = ("" + letra).toUpperCase();
            letra = cad.charAt(0);
            evt.setKeyChar(letra);
        }
    }
    
    public void EnterAJtexFiel(JTextField txt, KeyEvent evt) {
        char letra = evt.getKeyChar();
        if (letra == KeyEvent.VK_ENTER) {
            txt.requestFocus();
        }
    }
    
     public String ObtenerCodString(int c) {
        String cdd = "000001";
        if (c < 10) {
            cdd = "00000" + c;
        } else if (c < 100) {
            cdd = "0000" + c;
        } else if (c < 1000) {
            cdd = "000" + c;
        } else if (c < 10000) {
            cdd = "00" + c;
        } else if (c < 100000) {
            cdd = "0" + c;
        } else {
            cdd = "" + c;
        }
        return cdd;
    }
     
    public boolean esNumerico(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public boolean validarCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {
            // ConstantesApp.LongitudCedula
            if (cedula.length() == 10){
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }
                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else cedulaCorrecta = false;
                } else cedulaCorrecta = false;
            }else cedulaCorrecta = false;
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            cedulaCorrecta = false;
        }
        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }
    
    public boolean validarCorreo(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
 
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
    
    public Mascota buscarMascota(String cedula, PersonaDB perDB){
            List<Persona> listaP = null;
            List<Mascota> listaM = null;
            Mascota mas = null;
            String presentar = "";

            listaP = perDB.buscarPersonaCed(cedula);
            listaM = listaP.get(0).getMascota();

            for (int i = 0; i < listaM.size(); i++) {
                presentar += i + 1 + " Nombre: " + listaM.get(i).getNombre() + "\n"
                        + " Raza: " + listaM.get(i).getRaza() + "\n"
                        + " Sexo: " + listaM.get(i).getSexo() + "\n\n";
            }

            int op = Integer.parseInt(JOptionPane.showInputDialog("Elija una mascota:\n" + presentar));

            mas = listaM.get(op - 1);
            
        return mas;
    }
    
    
}
