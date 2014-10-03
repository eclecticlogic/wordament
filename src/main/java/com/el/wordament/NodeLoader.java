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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author Karthik Abram
 *
 */
public class NodeLoader {

	
	public static Node init() throws IOException {
		LineIterator iterator = FileUtils.lineIterator(new File("src/main/resources/dict.txt"));
		
		Node root = new Node();
		root.setWord(false);
		
		while (iterator.hasNext()) {
			String line = iterator.next().toLowerCase().trim();
			createNodes(line, root, 0).setWord(true);
		}
		
		return root;
	}
	

	private static Node createNodes(String line, Node current, int index) {
		if (index != line.length()) {
			Node next = current.createOrGet(line.charAt(index));
			return createNodes(line, next, index+1);
		} else {
			return current;
		}
	}
}
