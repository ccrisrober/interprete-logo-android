package Dependencies.PR1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Dependencies.PR1.Symbols.V;
import Dependencies.PR1.Symbols.VN;
import Dependencies.PR1.Symbols.VT;
import Dependencies.PR6.Production;

public class FileReader {

    //Atributos.
    protected String path;

    //Constructores.
    public FileReader(String path) {
        this.path = path;
    }

    //Getter & Setter.
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //MÃ©todos.
    /**
     * Utilizado en la PrÃ¡ctica 3.
     *
     * @param lineas
     */
	/*public List<String> devolverLineas() {

        List<String> lineas = new ArrayList<String>();

        try {

            FileInputStream file = new FileInputStream(this.path);
            InputStreamReader isr = new InputStreamReader(file, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.length() > 0) {
                    lineas.add(linea);
                }
            }

        }
        catch (Exception e) {
            throw new IOException("Error de parseo");
        }
        finally {
            return lineas;
        }

    }*/

    /**
     * Utilizado en la PrÃ¡ctica 1.
     *
     * @param listaVN
     * @param listaVT
     * @param producciones
     * @param simbInicial
     */
    public void analizarFichero(Collection<VN> listaVN, Collection<VT> listaVT, Collection<Production> producciones, VN simbInicial) throws IOException {

    	String p = "Inicio";
    	
        try {

            /*FileInputStream file = new FileInputStream(this.path);
            InputStreamReader isr = new InputStreamReader(file, "UTF-8");
            BufferedReader br = new BufferedReader(isr);*/
            String aux;
            String[] split;


            //aux = br.readLine(); //LÃ­nea que describe la gramÃ¡tica

            //Tratamos VN.

            p = "VN ini";
            
            aux = "VN = {PASEO, PASO, E, E_PRIMA, F, F_PRIMA, T, COLOR, CONDICIONAL, COND_PAR, AND_OR, NOT, COMP, MAS_MENOS}";//br.readLine(); //LÃ­nea de VN.
            String fragmento_VN = aux.substring(6, aux.length() - 1);
            split = fragmento_VN.split(", ");
            for (String s : split) {
                listaVN.add(new VN(s));
            }

            p = "VN fin";
            
            //Tratamos VT.

            p = "VT ini";
            
            aux = "VT = {casa, giro, avanza, pinta, id, =, si, entonces, ir_a, +, -, *, /, n, (, ), >, <, <>, ==, >=, <=, and, or, negro, verde, naranja, rosa, rojo, blanco, amarillo, magenta}";//br.readLine(); //LÃ­nea de VT.
            String linea_VT = aux.substring(6, aux.length() - 1);
            split = linea_VT.split(", ");
            for (String s : split) {
                listaVT.add(new VT(s));
            }

            p = "VT fin";
            		
            //Tratamos P.

            /*aux = br.readLine(); //LÃ­nea de P.

            while ((aux = br.readLine()).compareTo("}") != 0) {
                if (!aux.startsWith("//") && aux.length() != 0) {
                    split = aux.split(" -> ");
                    VN antecedente = new VN(split[0]);
                    List<V> consecuentes = analizarConsecuentes(split[1], listaVN, listaVT);
                    producciones.add(new Production(antecedente, consecuentes));
                }
            }*/
            
            p = "P ini";
            
            aux = "P = {";
            aux = "\n// PASEO\n\nPASEO -> PASO PASEO\nPASEO -> λ\n\n// PASO\n\nPASO -> casa\nPASO -> giro E\nPASO -> pinta E\nPASO -> id = E\nPASO -> color = COLOR\nPASO -> si CONDICIONAL entonces PASO\nPASO -> ir_a E\n\n// CONDICIONAL\n\nCONDICIONAL -> NOT COND_PAR\nCONDICIONAL -> NOT COMP\n\n// COND_PAR\n\nCOND_PAR -> ( CONDICIONAL ) AND_OR\nCOND_PAR -> λ\n\n// AND_OR\n\nAND_OR ->  λ\nAND_OR -> and CONDICIONAL\nAND_OR -> or CONDICIONAL\n\n// NOT\n\nNOT -> not NOT\nNOT -> λ\n\n// COMP\n\nCOMP -> E > E\nCOMP -> E < E\nCOMP -> E <> E\nCOMP -> E == E\nCOMP -> E >= E\nCOMP -> E <= E\n\n// COLOR\n\nCOLOR -> negro\nCOLOR -> verde\nCOLOR -> naranja\nCOLOR -> rosa\nCOLOR -> rojo\nCOLOR -> blanco\nCOLOR -> amarillo\nCOLOR -> magenta\n\n// E\n\nE -> T E_PRIMA\n\n// E_PRIMA\n\nE_PRIMA -> + T E_PRIMA\nE_PRIMA -> - T E_PRIMA\n\n// T\n\nT -> F T_PRIMA\n\n// T_PRIMA\n\nT_PRIMA -> F * T_PRIMA\nT_PRIMA -> F / T_PRIMA\n\n// F\n\nF -> ( E )\nF -> id\nF -> n\n}\n";
            
            String[] sp = aux.split("\n");
            int i = 0;
            while (sp[i].compareTo("}") != 0) {
            	if (!sp[i].startsWith("//") && sp[i].length() != 0) {
	                split = sp[i].split(" -> ");
	                VN antecedente = new VN(split[0]);
	                List<V> consecuentes = analizarConsecuentes(split[1], listaVN, listaVT);
	                producciones.add(new Production(antecedente, consecuentes));
	            }
            	i++;
            }
            
            p = "P fin";
            
            //Tratamos S.

            p = "S ini";
            		
            aux = "S = PASEO";//br.readLine(); //LÃ­nea de S.
            String simI = aux.substring(4);
            simbInicial.setV(simI);
            
            p = "S fin";

        }
        catch (Exception e) {
            throw new IOException("Error de parseo" + p);
        }

    }

    private List<V> analizarConsecuentes(String consecuentes, Collection<VN> listaVN, Collection<VT> listaVT) {
        List<V> toReturn = new ArrayList<V>();
        String[] sp = consecuentes.split(" ");
        for (int i = 0; i < sp.length; i++) {
            String c = sp[i];//consecuentes.charAt(i);
            if (esTerminal(c, listaVN, listaVT)) {
                VT consecuente = new VT(c + "");
                toReturn.add(consecuente);
            }
            else {
                toReturn.add(new VN(c + ""));
            }
        }
        return toReturn;
    }

    private boolean esTerminal(String simb, Collection<VN> listaVN, Collection<VT> listaVT) {
        VT v = new VT(simb);
        return listaVT.contains(v);//!(Character.isUpperCase(simb));
    }

    /**
     * Utilizado en la PrÃ¡ctica 10.
     *
     * @param listaVN
     * @param listaVT
     * @param producciones
     * @param simbInicial
     */
    /*public void analizarFicheroPR_10(Collection<VN> listaVN, Collection<VT> listaVT, Collection<Production> producciones, VN simbInicial) throws IOException {

        try {

            FileInputStream file = new FileInputStream(this.path);
            InputStreamReader isr = new InputStreamReader(file, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String aux;
            String[] split;


            aux = br.readLine(); //LÃ­nea que describe la gramÃ¡tica

            //Tratamos VN.

            aux = br.readLine(); //LÃ­nea de VN.
            String fragmento_VN = aux.substring(6, aux.length() - 1);
            split = fragmento_VN.split(", ");
            for (String s : split) {
                listaVN.add(new VN(s));
            }

            //Tratamos VT.

            aux = br.readLine(); //LÃ­nea de VT.
            String linea_VT = aux.substring(6, aux.length() - 1);
            split = linea_VT.split(", ");
            for (String s : split) {
                listaVT.add(new VT(s));
            }

            //Tratamos P.

            aux = br.readLine(); //LÃ­nea de P.

            while ((aux = br.readLine()).compareTo("}") != 0) {
                if (!aux.startsWith("//") && aux.length() != 0) {
                    split = aux.split(" ::= ");
                    VN antecedente = new VN(split[0]);
                    List<V> consecuentes = analizarConsecuentes(split[1], listaVN, listaVT);
                    producciones.add(new Production(antecedente, consecuentes));
                }
            }

            //Tratamos S.

            aux = br.readLine(); //LÃ­nea de S.
            String simI = aux.substring(4);
            simbInicial.setV(simI);

        }
        catch (Exception e) {
            throw new IOException("Error de parseo");
        }

    }*/

    /**
     * Utilizado en la PrÃ¡ctica 10.
     *
     * @return List<String>
     */
    /*public List<String> devolverProducciones() throws IOException {
        try {

            FileInputStream file = new FileInputStream(this.path);
            InputStreamReader isr = new InputStreamReader(file, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String aux;

            br.readLine();
            
            //Saltamos VN.
            aux = br.readLine(); //LÃ­nea de VN.

            //Saltamos VT.
            aux = br.readLine(); //LÃ­nea de VT.

            //Tratamos P.
            aux = br.readLine(); //LÃ­nea de P.

            List<String> listaProducciones = new ArrayList<String>();
            while ((aux = br.readLine()).compareTo("}") != 0) {
                listaProducciones.add(aux);
            }

            //Saltamos S.
            aux = br.readLine(); //LÃ­nea de S.

            return listaProducciones;

        }
        catch (Exception e) {
            throw new IOException("Error de parseo");
        }
    }*/
    
    public String[] palabrasReservadas () throws IOException {
        /*try {
            FileInputStream file = new FileInputStream(this.path);
            InputStreamReader isr = new InputStreamReader(file, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            String aux = br.readLine().substring(1);
            String[] split = aux.split(", ");
            return split;
        }
        catch (Exception e) {
            throw new IOException("Error de parseo");
        }*/
    	String[] split = "casa, CASA, giro, GIRO, avanza, AVANZA, pinta, PINTA, color, COLOR, rojo, ROJO, verde, VERDE, negro, NEGRO, naranja, NARANJA, rosa, ROSA, blanco, BLANCO, amarillo, AMARILLO, magenta, MAGENTA, si, SI, entonces, ENTONCES, ir_a, IR_A, and, AND, or, OR, not, NOT".split(", ");
    	return split;
    }
}