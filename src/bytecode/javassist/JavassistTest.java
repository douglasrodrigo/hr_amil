package bytecode.javassist;
import bytecode.Marker;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;


public class JavassistTest {
	public static void main(String[] args) {
		try {
			CtClass calculatorClass = ClassPool.getDefault().get("bytecode.Calculator1");

			ClassFile classFile = calculatorClass.getClassFile();
			ConstPool constantPool = classFile.getConstPool();
			AnnotationsAttribute attr = new AnnotationsAttribute(constantPool, AnnotationsAttribute.visibleTag);
			javassist.bytecode.annotation.Annotation a = new javassist.bytecode.annotation.Annotation("bytecode.Marker", constantPool);
			attr.setAnnotation(a);
			classFile.addAttribute(attr);
			classFile.setVersionToJava5();

			Class<?> clazz = calculatorClass.toClass();
			java.lang.annotation.Annotation[] annots = clazz.getAnnotations(); 
			System.out.print(annots[0] instanceof Marker);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		
		
		/*
		// create the class
		CtClass cc = pool.makeClass("foo");

		// create the method
		CtMethod mthd = CtNewMethod.make("public Integer getInteger() { return null; }", cc);
		cc.addMethod(mthd);

		ClassFile ccFile = cc.getClassFile();
		ConstPool constpool = ccFile.getConstPool();

		// create the annotation
		AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
		Annotation annot = new Annotation("MyAnnotation", constpool);
		annot.addMemberValue("value", new IntegerMemberValue(ccFile.getConstPool(), 0));
		attr.addAnnotation(annot);

		mthd.getMethodInfo().addAttribute(attr);

		// generate the class
		clazz = cc.toClass();

		// length is zero
		java.lang.annotation.Annotation[] annots = clazz.getAnnotations();
		*/
	}
}
