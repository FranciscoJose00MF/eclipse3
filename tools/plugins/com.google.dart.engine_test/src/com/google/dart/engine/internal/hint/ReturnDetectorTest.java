/*
 * Copyright (c) 2013, the Dart project authors.
 * 
 * Licensed under the Eclipse Public License v1.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.dart.engine.internal.hint;

import com.google.dart.engine.ast.Statement;
import com.google.dart.engine.parser.ParserTestCase;

public class ReturnDetectorTest extends ParserTestCase {

  public void test_asExpression() throws Exception {
    assertFalse("a as Object;");
  }

  public void test_asExpression_throw() throws Exception {
    assertTrue("throw '' as Object;");
  }

  public void test_assertStatement() throws Exception {
    assertFalse("assert(a);");
  }

  public void test_assertStatement_throw() throws Exception {
    assertTrue("assert((throw 0));");
  }

  public void test_assignmentExpression() throws Exception {
    assertFalse("v = 1;");
  }

  public void test_assignmentExpression_lhs_throw() throws Exception {
    assertTrue("a[throw ''] = 0;");
  }

  public void test_assignmentExpression_rhs_throw() throws Exception {
    assertTrue("v = throw '';");
  }

  public void test_binaryExpression() throws Exception {
    assertFalse("a && b;");
  }

  public void test_binaryExpression_and_lhs() throws Exception {
    assertTrue("throw '' && b;");
  }

  public void test_binaryExpression_and_rhs() throws Exception {
    assertTrue("a && (throw '');");
  }

  public void test_binaryExpression_or_lhs() throws Exception {
    assertTrue("throw '' || b;");
  }

  public void test_binaryExpression_or_rhs() throws Exception {
    assertTrue("a || (throw '');");
  }

  public void test_block_empty() throws Exception {
    assertFalse("{}");
  }

  public void test_block_noReturn() throws Exception {
    assertFalse("{ int i = 0; }");
  }

  public void test_block_return() throws Exception {
    assertTrue("{ return 0; }");
  }

  public void test_block_returnNotLast() throws Exception {
    assertTrue("{ return 0; throw 'a'; }");
  }

  public void test_block_throwNotLast() throws Exception {
    assertTrue("{ throw 0; x = null; }");
  }

  public void test_cascadeExpression_argument() throws Exception {
    assertTrue("a..b(throw '');");
  }

  public void test_cascadeExpression_index() throws Exception {
    assertTrue("a..[throw ''];");
  }

  public void test_cascadeExpression_target() throws Exception {
    assertTrue("throw ''..b();");
  }

  public void test_conditional_ifElse_bothThrows() throws Exception {
    assertTrue("c ? throw '' : throw '';");
  }

  public void test_conditional_ifElse_elseThrows() throws Exception {
    assertFalse("c ? i : throw '';");
  }

  public void test_conditional_ifElse_noThrow() throws Exception {
    assertFalse("c ? i : j;");
  }

  public void test_conditional_ifElse_thenThrow() throws Exception {
    assertFalse("c ? throw '' : j;");
  }

  public void test_creation() {
    assertNotNull(new ReturnDetector());
  }

  public void test_doStatement_false_nonReturn() throws Exception {
    assertFalse("{ do { } while (false); }");
  }

  public void test_doStatement_throwCondition() throws Exception {
    assertTrue("{ do { } while (throw ''); }");
  }

  public void test_doStatement_true_nonReturn() throws Exception {
    assertFalse("{ do {} while (true); }");
  }

  public void test_doStatement_true_return() throws Exception {
    assertTrue("{ do { return null; } while (true);  }");
  }

  public void test_emptyStatement() throws Exception {
    assertFalse(";");
  }

  public void test_forEachStatement() throws Exception {
    assertFalse("for (element in list) {}");
  }

  public void test_forEachStatement_throw() throws Exception {
    assertTrue("for (element in throw '') {}");
  }

  public void test_forStatement() throws Exception {
    assertFalse("for (;;) {}");
  }

  public void test_forStatement_condition() throws Exception {
    assertTrue("for (; throw 0;) {}");
  }

  public void test_forStatement_initialization() throws Exception {
    assertTrue("for (i = throw 0;;) {}");
  }

  public void test_forStatement_updaters() throws Exception {
    assertTrue("for (;; i++, throw 0) {}");
  }

  public void test_forStatement_variableDeclaration() throws Exception {
    assertTrue("for (int i = throw 0;;) {}");
  }

  public void test_functionExpression() throws Exception {
    assertFalse("(){};");
  }

  public void test_functionExpression_bodyThrows() throws Exception {
    assertFalse("(int i) => throw '';");
  }

  public void test_functionExpressionInvocation() throws Exception {
    assertFalse("f(g);");
  }

  public void test_functionExpressionInvocation_argumentThrows() throws Exception {
    assertTrue("f(throw '');");
  }

  public void test_functionExpressionInvocation_targetThrows() throws Exception {
    assertTrue("throw ''(g);");
  }

  public void test_identifier_prefixedIdentifier() throws Exception {
    assertFalse("a.b;");
  }

  public void test_identifier_simpleIdentifier() throws Exception {
    assertFalse("a;");
  }

  public void test_if_noReturn() throws Exception {
    assertFalse("if (c) i++;");
  }

  public void test_if_return() throws Exception {
    assertFalse("if (c) return 0;");
  }

  public void test_ifElse_bothReturn() throws Exception {
    assertTrue("if (c) return 0; else return 1;");
  }

  public void test_ifElse_elseReturn() throws Exception {
    assertFalse("if (c) i++; else return 1;");
  }

  public void test_ifElse_noReturn() throws Exception {
    assertFalse("if (c) i++; else j++;");
  }

  public void test_ifElse_thenReturn() throws Exception {
    assertFalse("if (c) return 0; else j++;");
  }

  public void test_indexExpression() throws Exception {
    assertFalse("a[b];");
  }

  public void test_indexExpression_index() throws Exception {
    assertTrue("a[throw ''];");
  }

  public void test_indexExpression_target() throws Exception {
    assertTrue("throw ''[b];");
  }

  public void test_instanceCreationExpression() throws Exception {
    assertFalse("new A(b);");
  }

  public void test_instanceCreationExpression_argumentThrows() throws Exception {
    assertTrue("new A(throw '');");
  }

  public void test_isExpression() throws Exception {
    assertFalse("A is B;");
  }

  public void test_isExpression_throws() throws Exception {
    assertTrue("throw '' is B;");
  }

  public void test_labeledStatement() throws Exception {
    assertFalse("label: a;");
  }

  public void test_labeledStatement_throws() throws Exception {
    assertTrue("label: throw '';");
  }

  public void test_literal_boolean() throws Exception {
    assertFalse("true;");
  }

  public void test_literal_double() throws Exception {
    assertFalse("1.1;");
  }

  public void test_literal_integer() throws Exception {
    assertFalse("1;");
  }

  public void test_literal_null() throws Exception {
    assertFalse("null;");
  }

  public void test_literal_String() throws Exception {
    assertFalse("'str';");
  }

  public void test_methodInvocation() throws Exception {
    assertFalse("a.b(c);");
  }

  public void test_methodInvocation_argument() throws Exception {
    assertTrue("a.b(throw '');");
  }

  public void test_methodInvocation_target() throws Exception {
    assertTrue("throw ''.b(c);");
  }

  public void test_parenthesizedExpression() throws Exception {
    assertFalse("(a);");
  }

  public void test_parenthesizedExpression_throw() throws Exception {
    assertTrue("(throw '');");
  }

  public void test_propertyAccess() throws Exception {
    assertFalse("new Object().a;");
  }

  public void test_propertyAccess_throws() throws Exception {
    assertTrue("(throw '').a;");
  }

  public void test_rethrow() throws Exception {
    assertTrue("rethrow;");
  }

  public void test_return() throws Exception {
    assertTrue("return 0;");
  }

  public void test_superExpression() throws Exception {
    assertFalse("super.a;");
  }

  public void test_switch_allReturn() throws Exception {
    assertTrue("switch (i) { case 0: return 0; default: return 1; }");
  }

  public void test_switch_noDefault() throws Exception {
    assertFalse("switch (i) { case 0: return 0; }");
  }

  public void test_switch_nonReturn() throws Exception {
    assertFalse("switch (i) { case 0: i++; default: return 1; }");
  }

  public void test_thisExpression() throws Exception {
    assertFalse("this.a;");
  }

  public void test_throwExpression() throws Exception {
    assertTrue("throw new Object();");
  }

  public void test_tryStatement_noReturn() throws Exception {
    assertFalse("try {} catch (e, s) {} finally {}");
  }

  public void test_tryStatement_return_catch() throws Exception {
    assertFalse("try {} catch (e, s) { return 1; } finally {}");
  }

  public void test_tryStatement_return_finally() throws Exception {
    assertTrue("try {} catch (e, s) {} finally { return 1; }");
  }

  public void test_tryStatement_return_try() throws Exception {
    assertTrue("try { return 1; } catch (e, s) {} finally {}");
  }

  public void test_variableDeclarationStatement_noInitializer() throws Exception {
    assertFalse("int i;");
  }

  public void test_variableDeclarationStatement_noThrow() throws Exception {
    assertFalse("int i = 0;");
  }

  public void test_variableDeclarationStatement_throw() throws Exception {
    assertTrue("int i = throw new Object();");
  }

  public void test_whileStatement_false_nonReturn() throws Exception {
    assertFalse("{ while (false) {} }");
  }

  public void test_whileStatement_throwCondition() throws Exception {
    assertTrue("{ while (throw '') {} }");
  }

  public void test_whileStatement_true_nonReturn() throws Exception {
    assertFalse("{ while (true) {} }");
  }

  public void test_whileStatement_true_return() throws Exception {
    assertTrue("{ while (true) { return null; } }");
  }

  public void test_whileStatement_true_throw() throws Exception {
    assertTrue("{ while (true) { throw ''; } }");
  }

  private void assertFalse(String source) throws Exception {
    assertHasReturn(false, source);
  }

  private void assertHasReturn(boolean expectedResult, String source) throws Exception {
    ReturnDetector detector = new ReturnDetector();
    Statement statement = parseStatement(source);
    assertSame(expectedResult, statement.accept(detector));
  }

  private void assertTrue(String source) throws Exception {
    assertHasReturn(true, source);
  }

}
