/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jclarity.advjava.ex2;

/**
 *
 * @author boxcat
 */
public class Update {
  private final Author author;
  private final String updateText;

  private Update(Builder b) {
    author = b.author;
    updateText = b.updateText;
  }

  public static class Builder implements ObjBuilder<Update> {
    private Author author;
    private String updateText;

    public Builder author(Author author_) {
      author = author_;
      return this;
    }

    public Builder updateText(String updateText_) {
      updateText = updateText_;
      return this;
    }
    
    public Update build() {
      return new Update(this);
    }
    
    public static Builder of(Update u) {
        Builder b = new Builder();
        b.author = u.author;
        b.updateText = u.updateText;
        return b;
    }
  }
}