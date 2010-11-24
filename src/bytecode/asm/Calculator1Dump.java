package bytecode.asm;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;



public class Calculator1Dump implements Opcodes {

	public static byte[] dump () throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "Calculator1", null, "java/lang/Object", null);

		{
			fv = cw.visitField(ACC_PRIVATE, "result", "I", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PRIVATE, "sum", "(II)V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ILOAD, 1);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitInsn(IADD);
			mv.visitFieldInsn(PUTFIELD, "Calculator1", "result", "I");
			mv.visitInsn(RETURN);
			mv.visitMaxs(3, 3);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}

