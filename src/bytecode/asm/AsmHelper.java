package bytecode.asm;
import java.lang.annotation.Annotation;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;


public class AsmHelper {

	
	private static final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
	
	private static final AsmUtilClassLoader classLoader = new AsmUtilClassLoader();
	
	
	public static <T> Class<T> addClassAnnotation(Class<T> clazz, Class<? extends Annotation> annotation) {
		try {
			new ClassReader(clazz.getName()).accept(new AnnotationClassAdapter(cw, annotation), 0);
			return (Class<T>) classLoader.defineAnnotatedClass(clazz.getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	private static class AnnotationClassAdapter extends ClassAdapter {
		private Class<? extends Annotation> annotation;
		
		public AnnotationClassAdapter(ClassWriter cw, Class<? extends Annotation> annotation) {
			super(cw);
			this.annotation = annotation;
		}
		
		@Override
		public void visit(int version, int access, String name,
				String signature, String superName, String[] interfaces) {
			super.visit(version, access, name, signature, superName, interfaces);
			AnnotationVisitor av0;

			av0 = cw.visitAnnotation("L" + annotation.getName().replaceAll("\\.", "/") + ";", true);
			av0.visit("value", "Class");
			av0.visitEnd();
		}
	}

	private static class AsmUtilClassLoader extends ClassLoader {
		private String className;

		public Class<?> loadClass(final String name) throws ClassNotFoundException {
	          if (name.equals(className)) {
                  byte[] b = cw.toByteArray();
                  return defineClass(name, b, 0, b.length);
              }
	          
              return super.loadClass(name);
   		}
		
		public Class<?> defineAnnotatedClass(final String name)  throws ClassNotFoundException {
			className = name;
			return loadClass(name);
		}
	}
	
}
