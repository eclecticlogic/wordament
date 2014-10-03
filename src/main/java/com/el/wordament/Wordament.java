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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Karthik Abram
 * 
 */
public class Wordament {

    private static Set<String> wordsFound = new HashSet<String>();


    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        Node root = NodeLoader.init();
        Scanner scanner = new Scanner(System.in);
        char grid[][] = new char[4][4];
        String parts[] = new String[4];

        parts[0] = scanner.nextLine();
        parts[1] = scanner.nextLine();
        parts[2] = scanner.nextLine();
        parts[3] = scanner.nextLine();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = parts[i].charAt(j);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                traverse(grid, new boolean[4][4], i, j, root, "");
            }
        }

        List<String> temp = new ArrayList<String>(wordsFound);
        Collections.sort(temp, new Comparator<String>() {

            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        for (String word : temp) {
            System.out.println(word);
        }
    }


    /**
     * @param grid The grid of letters representing the game.
     * @param visited A grid of indicators telling us which letters have already been visited.
     * @param i Our current location in the grid. x-axis
     * @param j Our current location in the grid. y-axis
     * @param node The node we have just visited.
     * @param current The currently formulated word.
     */
    private static void traverse(char[][] grid, boolean[][] visited, int i, int j, Node node, String current) {
        char c = grid[i][j];
        String newStr = current + Character.toString(c);
        Node next = node.getNode(c);

        if (next != null) {
            visited[i][j] = true;
            if (next.isWord() && newStr.length() > 2) {
                wordsFound.add(newStr);
            }

            if (j < 3 && !visited[i][j + 1]) {
                traverse(grid, visited, i, j + 1, next, newStr);
            }
            if (i < 3 && !visited[i + 1][j]) {
                traverse(grid, visited, i + 1, j, next, newStr);
            }
            if (j > 0 && !visited[i][j - 1]) {
                traverse(grid, visited, i, j - 1, next, newStr);
            }
            if (i > 0 && !visited[i - 1][j]) {
                traverse(grid, visited, i - 1, j, next, newStr);
            }
            if (i < 3 && j < 3 && !visited[i + 1][j + 1]) {
                traverse(grid, visited, i + 1, j + 1, next, newStr);
            }
            if (i < 3 && j > 0 && !visited[i + 1][j - 1]) {
                traverse(grid, visited, i + 1, j - 1, next, newStr);
            }
            if (i > 0 && j > 0 && !visited[i - 1][j - 1]) {
                traverse(grid, visited, i - 1, j - 1, next, newStr);
            }
            if (i > 0 && j < 3 && !visited[i - 1][j + 1]) {
                traverse(grid, visited, i - 1, j + 1, next, newStr);
            }
            visited[i][j] = false;
        }

    }
}
