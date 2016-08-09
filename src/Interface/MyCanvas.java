package Interface;

import java.util.LinkedList;
import java.util.List;

import Analyzer.Syntax.Interpreter;
import Dependencies.PR3.Token;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCanvas extends View {

    List<Token> instrucciones;
    
    public MyCanvas(Context context) {
    	this(context, new LinkedList<Token>());
    }
    
	public MyCanvas(Context context, List<Token> ins) {
		super(context);
		this.instrucciones = ins;
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (instrucciones != null) {
			if (instrucciones.size() > 0) {
				Paint paint = new Paint();
				paint.setStyle(Paint.Style.FILL);
				paint.setColor(Color.BLACK);
				paint.setAntiAlias(true);
				canvas.drawColor(Color.GRAY);
				Turtle t = new Turtle(paint, canvas, this.getWidth()/2, this.getHeight()/2);
				
				Interpreter interprete = new Interpreter(t);
				try {
					interprete.interpretar(instrucciones);
				}
				catch(Exception ei) {
					canvas.drawColor(Color.GRAY);	
					//Toast.makeText(context, "Error interpretación", Toast.LENGTH_SHORT).show();  
				}
			}
			else {
				canvas.drawColor(Color.GRAY);
			}
		}
		else {
			this.redraw(new LinkedList<Token>());
		}
	}
	
	public void redraw(List<Token> listaTokens) {
		instrucciones = listaTokens;
	}

}
