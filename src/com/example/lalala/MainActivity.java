package com.example.lalala;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import Analyzer.Lexical.LexicalAnalyzer;
import Dependencies.PR1.FileReader;
import Dependencies.PR3.Token;
import Exceptions.Lexical.LexicalException;
import Interface.MyCanvas;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FrameLayout ll1 = (FrameLayout)findViewById(R.id.fram);
		ll1.setBackgroundColor(Color.GRAY);
		
		Button buttons = (Button)findViewById(R.id.button1);
		buttons.setOnClickListener(OkL);
		
		Button clean = (Button)findViewById(R.id.clean);
		clean.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				limpiarTodo();
			}
		});
	}
	private OnClickListener OkL = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			try {
				analizar();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "ERROR INEXPERADO.", Toast.LENGTH_SHORT).show();	
			}
		}
	};
	MyCanvas mc;
	
	private void limpiarTodo() {
		mc = new MyCanvas(this, new LinkedList<Token>());
		FrameLayout ll = (FrameLayout)findViewById(R.id.fram2);
		ll.addView(mc);
	}
	
	private void analizar() throws IOException {
		// TODO Auto-generated method stub
		EditText entrada = (EditText)findViewById(R.id.editText1);
		
		//Grammar g = new Grammar("");
		
		String str = entrada.getText().toString() + "\n";

		StringTokenizer st = new StringTokenizer(str, "\n");
		
        String aux = "";
        while (st.hasMoreTokens()) {
            aux += st.nextToken() + "$";
        }
        
        if (aux.length() <= 0) {
        	Toast.makeText(getApplicationContext(), "ERROR, NO HAY INSTRUCCIONES", Toast.LENGTH_SHORT).show();	
        }
        else {

			FileReader fr = new FileReader("");
			
            String[] palabrasReservadas = null;
            try {
                palabrasReservadas = fr.palabrasReservadas();
            }
            catch (Exception e) {
            	Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
            
            LexicalAnalyzer la = new LexicalAnalyzer(palabrasReservadas);
			
	        List<Token> listaTokens = null;
	        try {
	        	listaTokens = la.analizarLinea(aux); 
	        }
	        catch(LexicalException el) {
	        	Toast.makeText(getApplicationContext(), "Error léxico: " + el.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	        }
        	
	        if (listaTokens.size() > 0) {
	        	//Pintamos
	        	try {
	    			mc = new MyCanvas(this, listaTokens);
					FrameLayout ll = (FrameLayout)findViewById(R.id.fram2);
					ll.addView(mc);
	    		}
	    		catch (Exception e) {
	    			Toast.makeText(getApplicationContext(), "Error interpretación: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  
	    		}
	        }
	        else {
	        	Toast.makeText(getApplicationContext(), "El conjunto de instrucciones está vacío", Toast.LENGTH_SHORT).show();  
	        }
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}