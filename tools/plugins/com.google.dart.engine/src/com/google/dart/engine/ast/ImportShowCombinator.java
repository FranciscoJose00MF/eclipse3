/*
 * Copyright (c) 2012, the Dart project authors.
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
package com.google.dart.engine.ast;

import com.google.dart.engine.scanner.Token;

import java.util.List;

/**
 * Instances of the class {@code ImportShowCombinator} represent a combinator that restricts the
 * names being imported to those in a given list.
 * 
 * <pre>
 * importShowCombinator ::=
 *     'show' {@link SimpleIdentifier identifier} (',' {@link SimpleIdentifier identifier})*
 * </pre>
 */
public class ImportShowCombinator extends ImportCombinator {
  /**
   * The list of names from the library that are made visible by this combinator.
   */
  private NodeList<Identifier> shownNames = new NodeList<Identifier>(this);

  /**
   * Initialize a newly created import show combinator.
   */
  public ImportShowCombinator() {
    super();
  }

  /**
   * Initialize a newly created import show combinator.
   * 
   * @param keyword the comma introducing the combinator
   * @param shownNames the list of names from the library that are made visible by this combinator
   */
  public ImportShowCombinator(Token keyword, List<Identifier> shownNames) {
    super(keyword);
    this.shownNames.addAll(shownNames);
  }

  @Override
  public <R> R accept(ASTVisitor<R> visitor) {
    return visitor.visitImportShowCombinator(this);
  }

  @Override
  public Token getEndToken() {
    return shownNames.getEndToken();
  }

  /**
   * Return the list of names from the library that are made visible by this combinator.
   * 
   * @return the list of names from the library that are made visible by this combinator
   */
  public NodeList<Identifier> getShownNames() {
    return shownNames;
  }

  @Override
  public void visitChildren(ASTVisitor<?> visitor) {
    shownNames.accept(visitor);
  }
}
