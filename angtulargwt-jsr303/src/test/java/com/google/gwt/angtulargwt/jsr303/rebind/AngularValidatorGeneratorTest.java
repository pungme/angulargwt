package com.google.gwt.angtulargwt.jsr303.rebind;

import static org.junit.Assert.assertEquals;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gwt.angtulargwt.jsr303.rebind.test.SomeNgValidator;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dev.CompilerContext;
import com.google.gwt.dev.javac.CompilationStateBuilder;
import com.google.gwt.dev.javac.testing.impl.JavaResourceBase;
import com.google.gwt.dev.resource.Resource;
import com.google.gwt.dev.util.log.PrintWriterTreeLogger;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwtmockito.GwtMockitoTestRunner;

@RunWith(GwtMockitoTestRunner.class)
public class AngularValidatorGeneratorTest {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConsumption() throws Exception {
		
		Object object = GWT.create(SomeNgValidator.class);
//		assertEquals("com.google.gwt.angtulargwt.jsr303.AngularValidatorImpl", object.getClass().getName());
	}

	
	// how to unit-test generators?
	// referring to
	// https://gwt.googlesource.com/gwt/+/release/2.6/user/test/com/google/gwt/place/rebind/PlaceHistoryGeneratorContextTest.java

	private static TypeOracle createTypeOracle(Resource... extraResources) {
		Set<Resource> resources = new HashSet<Resource>(
				Arrays.asList(JavaResourceBase.getStandardResources()));
//		 resources.add(new RealJavaResource(Tokenizer4.class));
		resources.addAll(Arrays.asList(extraResources));
		try {
			return CompilationStateBuilder.buildFrom(createCompileLogger(),
					new CompilerContext(), resources).getTypeOracle();
		} catch (UnableToCompleteException e) {
			throw new RuntimeException(e);
		}
	}

	private static final JType[] EMPTY_JTYPE_ARRAY = new JType[0];

	private static TreeLogger createCompileLogger() {
		PrintWriterTreeLogger logger = new PrintWriterTreeLogger(
				new PrintWriter(System.err, true));
		logger.setMaxDetail(TreeLogger.ERROR);
		return logger;
	}

}
