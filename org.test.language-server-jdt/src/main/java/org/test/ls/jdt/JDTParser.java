package org.test.ls.jdt;

import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JDTParser {

	public static void parseDoc(String doc) throws Exception {
		ASTParser parser = createParser();

		String unitName = "Test.java";
		parser.setUnitName(unitName);

		parser.setSource(doc.toCharArray());

		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		if (cu != null) {
			System.out.println("Parsing done: " + cu.getRoot().toString());
		}

	}

	private static ASTParser createParser() throws Exception {
		String[] classpathEntries = new String[0];
		String[] sourceEntries = new String[0];

		ASTParser parser = ASTParser.newParser(AST.JLS11);
		Map<String, String> options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_11, options);
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setStatementsRecovery(true);
		parser.setBindingsRecovery(true);
		parser.setResolveBindings(true);
		parser.setIgnoreMethodBodies(false);

		parser.setEnvironment(classpathEntries, sourceEntries, null, true);
		return parser;
	}

}
