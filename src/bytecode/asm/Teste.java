package bytecode.asm;
import java.lang.annotation.Annotation;

import bytecode.Calculator1;
import bytecode.Marker;


public class Teste {
	public static void main(String[] args) {
		Class<?> c = AsmHelper.addClassAnnotation(Calculator1.class, Marker.class);
		Annotation a = c.getAnnotation(Marker.class);
	    System.out.println(a instanceof Marker);
	}
}
