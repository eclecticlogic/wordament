/**
 * Copyright (c) 2013 Eclectic Logic LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.el.wordament;


/**
 * @author Karthik Abram
 *
 */
public class Node {

    private boolean word;

    private Node[] branches = new Node[26];


    public boolean isWord() {
        return word;
    }


    public void setWord(boolean word) {
        this.word = word;
    }


    public Node getNode(char c) {
        int i = c - 'a';
        return branches[i];
    }


    public Node createOrGet(char c) {
        Node n = getNode(c);
        if (n == null) {
            n = new Node();
            branches[c - 'a'] = n;
        }

        return n;
    }
}
